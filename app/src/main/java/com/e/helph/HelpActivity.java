package com.e.helph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    List<Helper> helperList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        listView=findViewById(R.id.list_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("helper");
        helperList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                    Helper helper = studentSnapshot.getValue(Helper.class);
                    helperList.add(helper);
                }
                HelperinfoAdapter helperinfoAdapter=new HelperinfoAdapter(HelpActivity.this,helperList);
                listView.setAdapter(helperinfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}