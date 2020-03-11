package com.app.idnbin.MainScreen.Profile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.MainScreen.Profile.Balance.BalanceActivity;
import com.app.idnbin.MainScreen.Profile.Deposit.DepositActivity;
import com.app.idnbin.MainScreen.Profile.Help.HelpActivity;
import com.app.idnbin.MainScreen.Profile.History.HistoryActivity;
import com.app.idnbin.MainScreen.Profile.NotificationSettings.NotificationSettingActivity;
import com.app.idnbin.MainScreen.Profile.Payment.PaymentActivity;
import com.app.idnbin.MainScreen.Profile.PrivacySettings.PrivacySettingActivity;
import com.app.idnbin.R;
import com.app.idnbin.MainScreen.Profile.Settings.SettingActivity;
import com.app.idnbin.MainScreen.Profile.MyProfile.UserProfileActivity;
import com.app.idnbin.MainScreen.Profile.Withdraw.WithdrawActivity;
import com.app.idnbin.util.BaseFragment;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    CardView CvSetting,CvProfile,CvHelp,CvBalance,CvDeposit,CvWithdraw,CvHistory,CvPrivacySetting,CvPayment,CvNotificationSetting,CvRateus,CvLegal,CVLogout;
    CircleImageView IvUserProfile;
    DatabaseReference reference;
    FirebaseUser fuser;

    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        CvSetting = v.findViewById(R.id.CvSetting);
        CvProfile = v.findViewById(R.id.CvProfile);
        CvHelp = v.findViewById(R.id.CvHelp);
        CvBalance = v.findViewById(R.id.CvBalance);
        CvDeposit = v.findViewById(R.id.CvDeposit);
        CvWithdraw = v.findViewById(R.id.CvWithdraw);
        CvHistory = v.findViewById(R.id.CvHistory);
        CvPrivacySetting = v.findViewById(R.id.CvPrivacySetting);
        CvPayment = v.findViewById(R.id.CvPayment);
        CvNotificationSetting = v.findViewById(R.id.CvNotificationSetting);
        CvRateus = v.findViewById(R.id.CvRateus);
        CvLegal = v.findViewById(R.id.CvLegal);
        CVLogout = v.findViewById(R.id.CVLogout);
        IvUserProfile = v.findViewById(R.id.IvUserProfile);

        CvSetting.setOnClickListener(this);
        CvProfile.setOnClickListener(this);
        CvHelp.setOnClickListener(this);
        CvBalance.setOnClickListener(this);
        CvDeposit.setOnClickListener(this);
        CvWithdraw.setOnClickListener(this);
        CvHistory.setOnClickListener(this);
        CvPrivacySetting.setOnClickListener(this);
        CvPayment.setOnClickListener(this);
        CvNotificationSetting.setOnClickListener(this);
        CvRateus.setOnClickListener(this);
        CvLegal.setOnClickListener(this);
        CVLogout.setOnClickListener(this);
        IvUserProfile.setOnClickListener(this);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("UserDetails").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getActivity() == null) {
                    return;
                }
                UserDetails user = dataSnapshot.getValue(UserDetails.class);

                if (user.getImageURL().equals("default")){
                    IvUserProfile.setImageResource(R.mipmap.ic_launcher);
                }
                else {
                    Glide.with(getActivity()).load(user.getImageURL()).into(IvUserProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CvProfile:
                startActivity(new Intent(getContext(), UserProfileActivity.class));
                break;
            case R.id.CvHelp:
                startActivity(new Intent(getContext(), HelpActivity.class));
                break;
            case R.id.CvSetting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.CvBalance:
                startActivity(new Intent(getContext(), BalanceActivity.class));
                break;
            case R.id.CvDeposit:
                startActivity(new Intent(getContext(), DepositActivity.class));
                break;
            case R.id.CvWithdraw:
                startActivity(new Intent(getContext(), WithdrawActivity.class));
                break;
            case R.id.CvHistory:
                startActivity(new Intent(getContext(), HistoryActivity.class));
                break;
            case R.id.CvPrivacySetting:
                startActivity(new Intent(getContext(), PrivacySettingActivity.class));
                break;
            case R.id.CvPayment:
                startActivity(new Intent(getContext(), PaymentActivity.class));
                break;
            case R.id.CvNotificationSetting:
                startActivity(new Intent(getContext(), NotificationSettingActivity.class));
                break;
            case R.id.CvRateus:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=dts.app")));
                break;
            case R.id.CvLegal:
                break;
            case R.id.IvUserProfile:
                openImage();
                break;
            case R.id.CVLogout:
                break;
        }

    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImg(){
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading");
        pd.show();

        if (imageUri != null){
            final  StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }

                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("UserDetails").child(fuser.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", ""+mUri);
                        reference.updateChildren(map);

                        pd.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        } else {
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();

            if (uploadTask != null && uploadTask.isInProgress()){
                Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
            } else {
                uploadImg();
            }
        }
    }

}