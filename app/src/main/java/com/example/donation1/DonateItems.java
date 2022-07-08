package com.example.donation1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class DonateItems extends AppCompatActivity {
    FirebaseUser user;
TextView t1,t2;
EditText e1,e2,e3;
ImageView iv;
Button b1;
    String EMAIL;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase db;
    DatabaseReference reference;

    String upi_id,cause,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_items);

 user = FirebaseAuth.getInstance().getCurrentUser();

        t1=findViewById(R.id.textView4);
         EMAIL=user.getEmail();
        EMAIL=EMAIL.substring(0,EMAIL.length()-10);
        t1.setText("WELCOME "+EMAIL);

        e1=findViewById(R.id.editTextTextPersonName3);//cause
        e2=findViewById(R.id.editTextTextPersonName4);//amount
        e3=findViewById(R.id.editTextTextPersonName5);//upi

        iv=findViewById(R.id.imageView);//image
        b1=findViewById(R.id.button4);//submit button

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();


        db=FirebaseDatabase.getInstance();
        reference=db.getReference("Users");

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePictures();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 upi_id=e3.getText().toString();
 cause=e1.getText().toString();
 amount=e2.getText().toString();

Cause_details cd=new Cause_details();
cd.setUpi_id(upi_id);
cd.setAmount(amount);
cd.setCause(cause);
uploadPicture();

reference.child(EMAIL).setValue(cd).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        e1.setText(" ");
        e2.setText(" ");
        e3.setText(" ");
        Toast.makeText(getApplicationContext(), "Inserted sucessfuly", Toast.LENGTH_SHORT).show();
    }
});


            }
        });
    }

    private void choosePictures() {
        Intent i1=new Intent();
        i1.setType("image/*");
        i1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i1,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri=data.getData();
            iv.setImageURI(imageUri);
            // uploadPicture();

        }
    }

    private void uploadPicture() {
        final String randomKey= UUID.randomUUID().toString();

        StorageReference mountainsRef = storageReference.child("images");


        StorageReference mountainImagesRef = storageReference.child("images/"+EMAIL);


        mountainsRef.getName().equals(mountainImagesRef.getName());

        mountainImagesRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                iv.setImageDrawable(null);
            }
        });

        Toast.makeText(getApplicationContext(), "Inserted Succesfully", Toast.LENGTH_SHORT).show();
    }
}
