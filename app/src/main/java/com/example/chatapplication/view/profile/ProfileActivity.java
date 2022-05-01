package com.example.chatapplication.view.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chatapplication.R;
import com.example.chatapplication.common.Common;
import com.example.chatapplication.databinding.ActivityProfileBinding;
import com.example.chatapplication.view.startup.SplashScreenActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;

    private BottomSheetDialog bottomSheetDialog ,bsDialogEditName;
    private ProgressDialog progressDialog;

    private int IMAGE_GALLERY_REQUEST = 111;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_profile);

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        bottomSheetDialog = new BottomSheetDialog(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        if (firebaseUser!=null){
            getInfo();
        }

        initActionClick();

    }

    private void initActionClick() {
        binding.fabCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showBottomSheetPickPhoto();
            }
        });
        binding.inEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetEditName();
            }
        });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.imageProfile.invalidate();
                Drawable dr = binding.imageProfile.getDrawable();
                Common.IMAGE_BITMAP = ((GlideBitmapDrawable) dr.getDrawable().getCurrent()).getBitmap();
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ProfileActivity.this, binding.imageProfile"image");
                Intent intent = new Intent(ProfileActivity.this, viewImageActivity.class);
                startActivity(intent, activityOptionsCompat.toBundle());
            }
        });
        binding.btnLogOut.setOnCickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            showDialogSingOut();
            }
            });
    }

    private void showBottomSheetPickPhoto() {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_pick, null);

    ((View) view.findViewById(R.id.In_gallery)).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openGallery();
            bottomSheetDialog.dismiss();
        }
    });
    ((View) view.findViewById(R.id.In_camera)).setOnClickListener((View) -> {
                    Toast.makeText(getApplicationContext(),"camera",Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
    });


    bottomSheetDialog = new BottomSheetDialog(this);
    bottomSheetDialog.setContentView(view);

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Objects.requireNonNull(bottomSheetDialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            bottomSheetDialog = null;
        }
    });

    bottomSheetDialog.show();
}
    private void showBottomSheetEditName(){
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottom_sheet_edit_name, null);

        ((View) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsDialogEditName.dismiss();
            }
        });

        EditText edUserName = view.findViewById(R.id.ed_username);
        ((View) view.findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edUserName.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Name can't be empty", Toast.LENGTH_SHORT).show();
                }else {
                    updateName(edUserName.getText().toString());
                    bsDialogEditName.dismiss();
                }
            }
        });

       bsDialogEditName = new BottomSheetDialog(this);
        bsDialogEditName.setContentView(view);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(bsDialogEditName.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

       bsDialogEditName.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                bsDialogEditName = null;
            }
        });

       bsDialogEditName.show();
    }
    private void getInfo() {
        firestore.collection("User").document(firebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username = documentSnapshot.getString("userName");
                String userPhone = documentSnapshot.getString("userPhone");
                String imageProfile = documentSnapshot.getString("imageProfile");

                binding.tvUsername.setText(username);
                binding.tvPhone.setText(userPhone);
                Glide.with(ProfileActivity.this).load(imageProfile).into(binding.imageProfile);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void openGallery() {
        Intent intent= new Intent();
        intent.setType("image/");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select image"), IMAGE_GALLERY_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == IMAGE_GALLERY_REQUEST
                    && resultCode == RESULT_OK
                    && data != null
                    && data.getData()!=null) {

             imageUri = data.getData();
             uploadToFirebase();

            /* try {
                 Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                 binding.imageProfile.setImageBitmap(bitmap); }
             catch (Exception e){
                 e.printStackTrace();
             }*/
            }
        }
    private String getFileExtention(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadToFirebase() {
        if(imageUri!=null){
            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("ImagesProfile/" + System.currentTimeMillis()+"."+getFileExtention(imageUri));
            riversRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){

                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();

                        final String sdownload_url = String.valueOf(downloadUrl);

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("imageProfile",sdownload_url);
                        progressDialog.dismiss();

                        firestore.collection("Users").document (firebaseUser.getUid()).update(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid){
                                    Toast.makeText(getApplicationContext(),"uploaded successfully",Toast.LENGTH_SHORT ).show();
                                    getInfo();
                                }
                            });
                        }
                }).addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(getApplicationContext(),"upload failed",Toast.LENGTH_SHORT ).show();
                        progressDialog.dismiss();
                    }
                });
            }
    }
    private void updateName(String newName){
       firestore.collection("Users").document(firebaseUser.getUid()).update("userName",newName).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
              Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_SHORT).show();
              getInfo();
           }
       }) ;
    }
    private void  showDialogSingOut(){
        final AlertDialog.Builder builder =new AlertDialog.Builder( ProfileActivity.this);
        builder.setMessage("Do you want Log out from RoyalChat?");
        builder.setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialog.cancel();
              FirebaseAuth.getInstance().signOut();
              startActivity(new Intent(ProfileActivity.this, SplashScreenActivity.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }
}