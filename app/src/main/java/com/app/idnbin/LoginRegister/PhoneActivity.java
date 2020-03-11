package com.app.idnbin.LoginRegister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.app.idnbin.util.ContactWork;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PhoneActivity extends BaseActivity {
    private Spinner Sp_CountryCode;
    private EditText Et_PhoneNumber;
    private String code;
    private ArrayAdapter<State> StateAdapter;
    private String[] permissions = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_phone);
        Sp_CountryCode = findViewById(R.id.Sp_CountryCode);
        Et_PhoneNumber = findViewById(R.id.Et_PhoneNumber);
        Button btn_Next = findViewById(R.id.Btn_Next);

        State[] stateSpinner = new State[]{
                new State("+91", "India"),

        };
        setPopup1();

        StateAdapter = new ArrayAdapter<>(this, R.layout.spinner_text, stateSpinner);
        StateAdapter.setDropDownViewResource(R.layout.spinner_text);
        Sp_CountryCode.setAdapter(StateAdapter);

        Sp_CountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                State user = StateAdapter.getItem(position);
                if (user != null){
                    code=user.getCode();
                    ((TextView) view).setText(user.getCode());
                    ((TextView) view).setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        btn_Next.setOnClickListener(v -> {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                String mobile_no = Et_PhoneNumber.getText().toString();
                saveContacts(mobile_no);;
                if (mobile_no.isEmpty() || mobile_no.length() < 10) {
                    Et_PhoneNumber.setError("Enter Valid Mobile Number");
                    Et_PhoneNumber.requestFocus();
                    return;
                }
                Intent intent = new Intent(PhoneActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobileCode",code);
                intent.putExtra("mobile", mobile_no);
                startActivity(intent);
                finish();
            } else {
                checkPermissions();
            }

        });
    }

    private class State {
        private String code;
        private String statename;

        public State(String code, String statename) {
            this.code = code;
            this.statename = statename;
        }

        public String getCode() {
            return code;
        }

        public String getStatename() {
            return statename;
        }

        @Override
        public String toString() {
            return this.statename;

        }
    }

    private void setPopup1() {
        Field popup1;
        try {
            popup1 = Spinner.class.getDeclaredField("mPopup");
            popup1.setAccessible(true);

            ListPopupWindow StatepopupWindow = (ListPopupWindow) popup1.get(Sp_CountryCode);

            Objects.requireNonNull(StatepopupWindow).setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {

        }
    }

    private void saveContacts(String user) {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ContactWork.class)
                .setInputData(createInputData(user))
                .setInitialDelay(2, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(PhoneActivity.this).enqueue(workRequest);
    }

    private Data createInputData(String value) {
        return new Data.Builder().putString("keyUserName", value).build();
    }

    private final int MULTIPLE_PERMISSIONS = 10;

    private void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (listPermissionsNeeded.size() != 0) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), MULTIPLE_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0) {
                ArrayList<String> permissionsDenied = new ArrayList<>();
                for (String per : permissions) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        permissionsDenied.add(per);
                    }
                }

                if (permissionsDenied.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) || shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                            alertDialogPermission(true);
                        } else {
                            alertDialogPermission(false);
                        }
                    }
                }
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void alertDialogPermission(boolean check) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Need Permission");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);

        if (check) {
            builder.setMessage("Please Allow Permission,\nWhich will help us to Improve your App Experience");
            builder.setPositiveButton("Grant",(dialog, id) -> {
                checkPermissions();
                dialog.cancel();
            });
        } else {
            builder.setMessage("App Need Contact Permission,\nGrant Permission in Setting➟Permissions");
            builder.setPositiveButton("Grant",(dialog, id) -> {
                sendToSetting();
                dialog.cancel();
            });
        }

        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    int REQUEST_PERMISSION_SETTING = 27;

    private void sendToSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                Toast.makeText(this, "Thank You For Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
