package com.example.varifyotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.varifyotp.databinding.ActivityMObileNumberBinding;

public class MobileNumber extends AppCompatActivity {

    private ActivityMObileNumberBinding binding;
    private String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_m_obile_number);

        binding.sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mobileNumber = binding.editET.getText().toString();
                if (!TextUtils.isEmpty(mobileNumber) && mobileNumber.matches("01[0-9]{9}")){
                    Intent intent = new Intent(MobileNumber.this,mobileOTP.class);
                    intent.putExtra("mobileNumber",mobileNumber);
                    startActivity(intent);
                }else {
                    Toast.makeText(MobileNumber.this, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}