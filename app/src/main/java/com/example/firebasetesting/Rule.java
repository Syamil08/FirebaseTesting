package com.example.firebasetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rule extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;

    FirebaseUser user;

    DatabaseReference root;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        e1 = findViewById(R.id.kelamin);
        e2 = findViewById(R.id.berat);
        e3 = findViewById(R.id.umurBawah);
        e4 = findViewById(R.id.umurAtas);
        e5 = findViewById(R.id.energi);

        root = FirebaseDatabase.getInstance().getReference();

//        database = FirebaseDatabase.getInstance();
//        String key = database.getReference("Bmr").push().getKey();
    }

    public void tambahRule(View view) {
        final String _kelamin;
        final int _berat, _umurB, _umurA,_energi;

        _kelamin = e1.getText().toString().trim();
        _berat  = Integer.parseInt(e2.getText().toString().trim());
        _umurB  = Integer.parseInt(e3.getText().toString().trim());
        _umurA  = Integer.parseInt(e4.getText().toString().trim());
        _energi = Integer.parseInt(e5.getText().toString().trim());

//        Log.d("s1",_kelamin);
//        Log.d("s2", Integer.toString(_berat));
//        Log.d("s3",Integer.toString(_umurB));
//        Log.d("s4",Integer.toString(_umurA));

        RuleModel model = new RuleModel(_kelamin,_berat,_umurB,_umurA,_energi);


        root.child("Bmr").push().setValue(model);

    }
}
