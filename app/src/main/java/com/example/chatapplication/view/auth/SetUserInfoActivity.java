package com.example.chatapplication.view.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivitySetUserInfoBinding;
import com.example.chatapplication.view.MainActivity;

public class SetUserInfoActivity extends AppCompatActivity {
//sumi
    private ActivitySetUserInfoBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(Activity this, R.layout.activity_set_user_info);
        progressDialog = new ProgressDialog()
        initButtonClick();
    }
    private void initButtonClick() {
        binding.btnNext.setOnClickListener(new View().OnClickListener() {

            @Override
            public void onClick(View v){
                if (TextUtils.isEmpty(binding.edName.getText().toString())){
                    Toast.makeText(getApplicationContext(), text:"Please input username",Toast.LENGTH_SHORT).show();
                } else {
                    doUpdate();
                }

            }
        });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pickImaage();
                  //I will do next video
                Toast.makeText(getApplicationContext(), text:"this function is not ready to use",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void doUpdate() {
    ///
    progressDialog.setMessage("Please Wait");
    progressDialog.show();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    if (firebaseUser != null) {
       firebaseFirestore.collection(collectionPath:"Users").document(firebaseUser.getUid()).update(field: "Username",bindinf.edName.getText().toString())  //problemsumi
        .addOnSuccessfulListener(new OnSuccessListener<void>() {
            @Override
            public void onSuccess(Void aVoid){
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),text: "UpdateSuccessfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

      } else{
        Toast.makeText(getApplicationContext(), text: "you need to log in first", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        }
    }
}