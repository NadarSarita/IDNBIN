package com.app.idnbin.LoginRegister;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.app.idnbin.util.GlobalConstants;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends BaseActivity {

    private ConstraintLayout Cl_Verify;
    private PinView pinView;
    private TextView Tv_ResendCode, Tv_Timer;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private String mobile, mobileCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        Cl_Verify = findViewById(R.id.Cl_Verify);
        pinView = findViewById(R.id.pinView);
        Button btn_Continue = findViewById(R.id.Btn_Continue);
        Tv_ResendCode = findViewById(R.id.Tv_ResendCode);
        Tv_Timer = findViewById(R.id.Tv_Timer);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        mobileCode = intent.getStringExtra("mobileCode");
        sendVerificationCode(mobile, mobileCode);
        startCounter();

        btn_Continue.setOnClickListener(v -> {
            String code = pinView.getText().toString().trim();
            if (code.isEmpty() || code.length() < 6) {
                pinView.setError("Enter valid code");
                pinView.requestFocus();
                return;
            }
            verifyVerificationCode(code);
        });

        Tv_ResendCode.setOnClickListener(v -> {
            sendVerificationCode(mobile, mobileCode);
            startCounter();
        });
    }

    private void startCounter() {
        new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                Tv_Timer.setVisibility(View.VISIBLE);
                Tv_ResendCode.setVisibility(View.GONE);
                Tv_Timer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Tv_Timer.setVisibility(View.GONE);
                Tv_ResendCode.setVisibility(View.VISIBLE);
            }

        }.start();
    }

    private void sendVerificationCode(String mobile, String mobileCode) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobileCode + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);

        Log.d("FALALA", "Sent");
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                pinView.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d("KALAALA", "" + e.getMessage());
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        phoneLogin(credential);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            retrieveUserDetail(currentUser);
        }
    }

    private void phoneLogin(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        if (firebaseUser != null) {
                            Log.d("GAHHAA", "" + mobile);
                            UserDetails user = new UserDetails(firebaseUser.getUid(), firebaseUser.getEmail(), "", "", currentDateTimeString, "");
                            GlobalConstants.UsersInstance.child(firebaseUser.getUid()).setValue(user);
                        }

                        if (task.getResult() != null) {
                            FirebaseUser user = task.getResult().getUser();

                            if (user != null) {
                                GlobalConstants.UsersInstance.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                                        if (!dataSnapshot.exists()) {

                                            updateUI(user);
                                        } else {
                                            updateUI(user);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }


                    } else {
                        if (task.getException() != null) {
                            snackBar(Cl_Verify, "" + task.getException().getMessage());
                        }
                    }
                });
    }
}
