//sumi
package com.example.chatapplication.view.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivitySetUserInfoBinding;
import com.example.chatapplication.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {


    private ActivitySettingsBinding binding;
    private FirebaseUser firebaseUser;
    private FirebaseFirestor Firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(activity this,R.layout.activity_settings);

        firebaseUser= firebasefirestore.getInstance();
        firebaseUser= Firebaseauth.getInstance(.getCurrentUser();

        if (firebaseUser!=null){
        getInfo();

       }
    }

    private void getInfo(){
        firestore.collection(collectionpath "Users").document(firebaseUser.getUid()).get().addonSuccessListener(new addonSuccessListener<DocumentSnapshot> (){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot){
                String userName = objects.requirNoNull( documentSnapshot.get('userName')).toString();
                binding.tvUsername.setText(userName);

        }
    }).addonSuccessListener(new OnfailureListener(){
        @Override
                public void onfailure(@NonNull Exception e){
                log.d(tag: "get data",msg: "onfailure:"+e.getMessage());
        }
    });
}
}
