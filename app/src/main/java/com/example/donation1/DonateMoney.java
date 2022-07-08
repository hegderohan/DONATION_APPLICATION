package com.example.donation1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class DonateMoney extends AppCompatActivity implements PaymentResultListener {

//Button b1;
ImageView iv;
//EditText e4;
//FirebaseDatabase db;
 //   DatabaseReference reference;
//int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_money);



        String sAmount="100";

        int amount=Math.round(Float.parseFloat(sAmount)*100);
       // b1=findViewById(R.id.button2);
        iv=findViewById(R.id.imageView3);
       iv.setImageResource(R.drawable.icon);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Checkout checkout=new Checkout();//intializing razorpays checkout


                checkout.setKeyID("rzp_test_SldUh1fdJcPeJL");

                JSONObject object=new JSONObject();
                //put name
                try {
                    object.put("name","");
                    object.put("description","Test payment");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact"," ");
                    object.put("prefill.email","");

                    //open razorpay checkout activiy
                    checkout.open(DonateMoney.this,object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }


    @Override
    public void onPaymentSuccess(String s) {
        //intialize alert dailog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Payment" +
                "");
        builder.setMessage(s);//s will be returnbed on scucessfulll transitin
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
//display errot toast
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
    }
}