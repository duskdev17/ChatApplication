package com.example.chatapplication.view.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivitySetUserInfoBinding;

public class SetUserInfoActivity extends AppCompatActivity {
//sumi
    private ActivitySetUserInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(Activity this, R.layout.activity_set_user_info);
        initButtonClick();
    }
    private void initButtonClick() {
        binding.btnNext.setOnClickListener(new View().OnClickListener() {

            @Override
            public void onClick (View v){
                doUpdate();
            }
        });
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pickImaage();
                  //I will do next video
            }
        });
    }
private void doUpdate() {
    ///
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance().getCurrentUser();
    if (firebaseUser != null) {
       FirebaseFirestore.collection(collectionPath:"Users").document(firebaseUsre.getUid()).up   //problemsumi
    }
}
}