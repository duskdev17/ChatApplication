package com.example.chatapplication.view.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;

    private BottomSheetDialog bottomSheetDialog;



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
}