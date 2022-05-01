package com.example.chatapplication.view.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.chatapplication.databinding.ActivityContactsMainBinding;

public class ContactsMainActivity extends AppCompatActivity {
    private ActivityContactsMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_contacts_main);
    }
}