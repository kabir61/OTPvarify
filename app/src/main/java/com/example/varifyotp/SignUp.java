package com.example.varifyotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.varifyotp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        init();

        binding.ButtonSingUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=binding.UserNameSignUpEditId.getText().toString();
                String email=binding.EmailSingUpId.getText().toString();
                String password=binding.PasswordSingUpId.getText().toString();
                String contact =binding.ContactNoSignUpId.getText().toString();

                if (userName.isEmpty()){
                    binding.UserNameSignUpEditId.setError("plz enter name");
                    binding.UserNameSignUpEditId.requestFocus();
                    return;

                }if (email.isEmpty()){
                    binding.EmailSingUpId.setError("plz enter email");
                    binding.EmailSingUpId.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    binding.PasswordSingUpId.setError("plz password");
                    binding.PasswordSingUpId.requestFocus();
                    return;

                } if (password.length()>6){
                    binding.PasswordSingUpId.setError("plz 6 character number");
                    binding.PasswordSingUpId.requestFocus();
                    return;

                }if (contact.isEmpty()){
                    binding.ContactNoSignUpId.setError("plz contact number");
                    binding.ContactNoSignUpId.requestFocus();
                    return;
                }
                if (contact.matches("01[0-9]{9}")){
                    binding.ContactNoSignUpId.setError("plz enter valid number");
                    binding.ContactNoSignUpId.requestFocus();
                    return;
                }
                binding.ProgressBarId.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            binding.ProgressBarId.setVisibility(View.GONE);
                            Toast.makeText(SignUp.this, "SignUp successfully done! plz click back", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        binding.BackSingUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });


    }

    private void init() {
        auth = FirebaseAuth.getInstance();
    }

}