package com.example.chatapplication.view.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.databinding.ActivityPhoneLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneLoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityPhoneLoginBinding binding;

    private FirebaseAuth mAuth;
    private String mVerificationId;

    String[] country = {"Russia", "USA", "China", "Japan", "Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_login);

        //getting the instance of Spinner and applying onItemSelectedListener on it
        Spinner spin = findViewById(R.id.spinner_country);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(getApplicationContext(), country[position], Toast.LENGTH_LONG).show();
    }
}