package com.example.donation1;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    TextView t1;
    MaterialButton b1;
    FirebaseAuth mAuth;
    //GoogleSignInOptions gso;
    //GoogleSignInClient gsc;
   // ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=findViewById(R.id.username);
        e2=findViewById(R.id.password);
        t1=findViewById(R.id.forgotPassword);
        b1=findViewById(R.id.loginbtn);
        mAuth=FirebaseAuth.getInstance();
      // iv=findViewById(R.id.googleBtn);
      //  gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
      //  gsc= GoogleSignIn.getClient(this,gso);
        //GoogleSignInAccount acct=GoogleSignIn.getLastSignedInAccount(this);
     //  if(acct!=null)
     //   {
     //       finish();

       //     startActivity(new Intent(Login.this,SignIn.class));
      //  }
/*
       iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignIn.class));
            }
        });
    }
/*
 void signIn()
   {
       Intent signInIntent=gsc.getSignInIntent();
      startActivityForResult(signInIntent,1000);
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                Toast.makeText(getApplicationContext(),"SUCESSFULLY LOGID IN",Toast.LENGTH_LONG).show();
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_LONG).show();
            }
        }
    }
 void navigateToSecondActivity()
    {
        finish();
       Intent i1=new Intent(Login.this,MainActivity.class);
      startActivity(i1);
        //startActivity(new Intent(Login.this,MainActivity.class));
    }
*/
    public void loginUser()
    {
        String s11=e1.getText().toString();
        String s22=e2.getText().toString();//dont ever use same name i don't know now it is working
       // Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
        // Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();
        if(TextUtils.isEmpty(s11))//we will check if entered username is empty
        {
            e1.setError("EMAIL cannot be empty");
            e1.requestFocus();
        }
        else if(TextUtils.isEmpty(s22))
        {
            e2.setError("PASSWORD cannot be empty");
            e2.requestFocus();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(s11,s22).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())   //swap now it works  //no it works even if password is wrong
                    {

                        Toast.makeText(Login.this, "LOGIN Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,MainActivity.class));

                    }
                    else
                    {
                        Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();//we go to this activity and dispaly details later we will change to display items
                    }
                }
            });
        }
    }
}