package com.example.varifyotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.varifyotp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_in);
       init();


       binding.ButtonLogInId.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = binding.EmailSingInId.getText().toString();
               String password= binding.PasswordSingInId.getText().toString();
               if (email.isEmpty()){
                binding.EmailSingInId.setError("Enter Your UserName!");
                binding.EmailSingInId.requestFocus();
                return;
            }
            //checking the validity of the email
            else  if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.EmailSingInId.setError("Enter a valid email address");
                binding.EmailSingInId.requestFocus();
                return;
            }

            //checking the validity of the password
            else if (password.isEmpty()) {
                binding.PasswordSingInId.setError("Enter a password");
                binding.PasswordSingInId.requestFocus();
                return;
            }
            else if (password.length()<6) {
                binding.PasswordSingInId.setError("Minimum 6 Character Password Should be Used");
                binding.PasswordSingInId.requestFocus();
                return;
            }
            binding.ProgressBarId.setVisibility(View.VISIBLE);
               assert auth != null;
               auth.createUserWithEmailAndPassword( email,password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       binding.ProgressBarId.setVisibility(View.GONE);
                           startActivity(new Intent(SignIn.this,MobileNumber.class));
                           finish();
                       }
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

           }
       });



       binding.SkipSingInId.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(SignIn.this,SignUp.class));
               finish();
           }
       });

    }

//    private void userRegister() {
//        String email = binding.EmailSingInId.getText().toString();
//        String password= binding.PasswordSingInId.getText().toString();
//
//            if (email.isEmpty()){
//                binding.EmailSingInId.setError("Enter Your UserName!");
//                binding.EmailSingInId.requestFocus();
//                return;
//            }
//            //checking the validity of the email
//            if (email.isEmpty()) {
//                binding.EmailSingInId.setError("Enter an email address!");
//                binding.EmailSingInId.requestFocus();
//                return;
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                binding.EmailSingInId.setError("Enter a valid email address");
//                binding.EmailSingInId.requestFocus();
//                return;
//            }
//
//            //checking the validity of the password
//            if (password.isEmpty()) {
//                binding.PasswordSingInId.setError("Enter a password");
//                binding.PasswordSingInId.requestFocus();
//                return;
//            }
//            if (password.length()<6) {
//                binding.PasswordSingInId.setError("Minimum 6 Character Password Should be Used");
//                binding.PasswordSingInId.requestFocus();
//                return;
//            }
//            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(getApplicationContext(), "Your Registration Successful", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Your Registration Unsuccessful", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }

    private void init() {
    auth = FirebaseAuth.getInstance();
    }
}