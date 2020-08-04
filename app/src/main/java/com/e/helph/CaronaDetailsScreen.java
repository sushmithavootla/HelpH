package com.e.helph;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CaronaDetailsScreen extends AppCompatActivity {
    TextView tvSummary,tv1,tv2,tv3,tv4,tv5;
    EditText etSearch;
    ArrayAdapter aa;
    ArrayList<CasesDO> casesDOS;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carano_details);

        tvSummary = findViewById(R.id.tv6);
        tv1=findViewById(R.id.tv7);
        tv2=findViewById(R.id.tv8);
        tv3=findViewById(R.id.tv9);
        tv4=findViewById(R.id.tv10);
        tv5=findViewById(R.id.tv11);
        Intent intent = getIntent();
        int newConfirmed=intent.getIntExtra("newConfirmed",0);
        int totalConfirmed=intent.getIntExtra("totalConfirmed",0);
        int newDeaths=intent.getIntExtra("newDeaths",0);
        int totalDeaths=intent.getIntExtra("totalDeaths",0);
        int newRecovered=intent.getIntExtra("newRecovered",0);
        int totalRecovered=intent.getIntExtra("totalRecovered",0);



        tvSummary.setText("New Confirmed    - "+newConfirmed);

        tv1.setText( "Total Confirmed - "+totalConfirmed);
        tv2.setText("New Deaths            - "+newDeaths);
        tv3.setText( "Total Deaths         - "+totalDeaths);
        tv4.setText( "New Recovered     - "+newRecovered);
        tv5.setText(  "Total Recovered  - "+totalRecovered);


    }


}
