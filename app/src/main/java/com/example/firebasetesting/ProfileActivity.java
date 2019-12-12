package com.example.firebasetesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    EditText e1,e2,e3,e4,e5;
    TextView tv1,tv2;
    Button btn_dialog;
    FirebaseAuth auth;
    FirebaseUser user;
//    buat variabel  untuk menampung profil yang login
    TextView profileText;

    DatabaseReference reference,bmr;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        profileText = (TextView) findViewById(R.id.profileText);
        e1 = findViewById(R.id.usiaa);
        e2 = findViewById(R.id.beraat);
        e3 = findViewById(R.id.kelaamin);
        tv1 = findViewById(R.id.tv_user);
        tv2 = findViewById(R.id.tv_pass);
        btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        user = auth.getCurrentUser();

//        profileText.setText(user.getEmail() );



        reference = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());
        bmr = FirebaseDatabase.getInstance().getReference().child("Bmr");

    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    public void signOut(View v){
        auth.signOut();
        finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void displayData(View view) {
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String username = dataSnapshot.child("username").getValue().toString();
//                String password = dataSnapshot.child("password").getValue().toString();
//
//
//                Toast.makeText(getApplicationContext(), username + password,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });

        bmr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String kelamin,_kelamin;
                int umur_min,umur_max ,berat,energi = 0,_usia,_berat;
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    RuleModel model = ds.getValue(RuleModel.class);
                    berat = model.getBeratBadan();
                    kelamin = model.getJenis_kelamin();
                    umur_min = model.getUmurbawah();
                    umur_max = model.getUmurAtas();
                    _kelamin = e3.getText().toString().trim();
                    _usia = Integer.parseInt(e1.getText().toString());
                    _berat = Integer.parseInt(e2.getText().toString());

                    if (umur_min < _usia && umur_max >= _usia && berat <= _berat ){
                        energi = model.getEnergi();
                    }

//                    Log.d("user", String.valueOf(model.getBeratBadan()));
//                    Log.d("user", String.valueOf(model.getUmurAtas()));

                }
                Log.d("energi", String.valueOf(energi));
                profileText.setText(energi+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


//    public void displayEnergi(View v){
//
//    }

    public void displayFormrule(View view) {
        Intent i = new Intent(this,Rule.class);
        startActivity(i);
        finish();
    }

    @Override
    public void applyTexts(String username, String password) {
        tv1.setText(username);
        tv2.setText(password);
    }
}
