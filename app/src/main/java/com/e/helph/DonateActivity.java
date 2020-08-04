package com.e.helph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonateActivity extends AppCompatActivity {
    private EditText name,donation,location,description,quantity;
    Button save;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        databaseReference = FirebaseDatabase.getInstance().getReference("helper");

        save=findViewById(R.id.dsave);
        name=findViewById(R.id.PersonName);
        donation=findViewById(R.id.donatione);
        location=findViewById(R.id.locatione);
        description=findViewById(R.id.descriptione);
        quantity=findViewById(R.id.quantitye);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DonateActivity.this,"THANK YOU",Toast.LENGTH_SHORT).show();
                addHelper();
            }
        });
    }
    public void addHelper(){
        String userName=name.getText().toString();
        String userDonation=donation.getText().toString();
        String userLocation=location.getText().toString();
        String userDescription=description.getText().toString();
        String userQuantity=quantity.getText().toString();
        if(!TextUtils.isEmpty(userName)&& !TextUtils.isEmpty(userDonation)&&!TextUtils.isEmpty(userLocation)&&!TextUtils.isEmpty(userDescription)&&!TextUtils.isEmpty(userQuantity)){
            String id=databaseReference.push().getKey();
            Helper helper=new Helper(id,userName,userDonation,userLocation,userDescription,userQuantity);
            databaseReference.child(id).setValue(helper);
            name.setText("");
            donation.setText("");
            location.setText("");
            description.setText("");
            quantity.setText("");

        }
        else {
            Toast.makeText(DonateActivity.this,"Please Type Your Credentials",Toast.LENGTH_LONG).show();
        }
    }

}