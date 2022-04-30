package com.example.chatapplication.view.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;

    private BottomSheetDialog bottomSheetDialog;
    private int IMAGE_GALLERY_REQUEST = 111;
    private  Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_profile);

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        bottomSheetDialog = new BottomSheetDialog(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        if (firebaseUser!=null){

            getInfo();
        }

        initaActionClick();

    }

    private void initaActionClick() {
        binding.facCamera.setOnCamera.setOnClickListener(new view.onclickListener(){
            @Override
            public void onClick(view v) {
                showBottomsheetpickphoto();
            }
            }
        });
    }

private void showBottomsheetpickphoto() {
    @SuppressLint("inflateParams") view view = getLayoutInflater().inflate(R.layout.bottom_sheet_pick, null);
    ((view)view.findViewById(R.id.in_gallary)).setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            openGallery();
            bottomSheetDialog.dismiss();
        }
    });
    ((view)view.findViewById(R.id.in_camera)).setOnClickListener((View)
            {
                    Toast.makeText(getApplicationContext(),"camera",Toast.LENGTH_SHORT).show();
            });

    bottomSheetDialog= new bottomSheetDialog(context: this);
    bottomSheetDialog.setContentView(view);

    if (build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        objects.requireNonNull(bottomSheetDialog.getWindow()).addFlags(WindowManager.layoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            bottomSheetDialog = null;

        }
    });
    bottomSheetDialog.show();
}
    private void getInfo() {
        firestore.collection("User").document(firebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username = documentSnapshot.getString(field:"userName");
                String userPhone = documentSnapshot.getString(field:"userPhone");
                binding.tvUsername.setText(username);
                binding.tvPhone.setText(userPhone);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void openGallery() {
        Intent intent =new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select image"),IMAGE_GALLERY_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
    super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == IMAGE_GALLERY_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData()!=null)
        {
         imageUri =data.getData();
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
        if (imageUri!=null){
            StorageReference riversRef=FirebaseFirestore.getInstance().getReference().child("imagesProfile/"+System.currentTimeMillis()+"-"+getFileExtention(mFileProfileUri));
        }
    }

}