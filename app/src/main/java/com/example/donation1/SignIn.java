package com.example.donation1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    EditText t1,t2;
    MaterialButton b1,b2;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        t1=findViewById(R.id.editTextTextPersonName);
        t2=findViewById(R.id.editTextTextPersonName2);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button22);
        mAuth=FirebaseAuth.getInstance();

     b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
      createUser();
         }
     });

     b2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(SignIn.this, Login.class));
         }
     });
    }
    private  void createUser()
    {
        String s1=t1.getText().toString();
        String s2=t2.getText().toString();
        if(TextUtils.isEmpty(s1))//checks if string s1 is empty
        {
            t1.setError("EMAIL cannot be empty");
            t1.requestFocus();
        }
        else if(TextUtils.isEmpty(s2))//checks if string s2 is empty
        {
            t2.setError("PASSWORD cannot be empty");
            t2.requestFocus();
        }

        else
        {
            mAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(SignIn.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignIn.this, Login.class));
                    }
                    else
                    {
                        Toast.makeText(SignIn.this,"SOME ERROR OCCURRED PLEASE CONTACT ROHAN",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}