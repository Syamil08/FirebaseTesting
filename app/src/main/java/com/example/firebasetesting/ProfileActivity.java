package com.example.firebasetesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
//    buat variabel  untuk menampung profil yang login
    TextView profileText;

    DatabaseReference reference,bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        profileText = (TextView) findViewById(R.id.profileText);
        user = auth.getCurrentUser();

        profileText.setText(user.getEmail() );


//      Mengambil bagian pada uid di firebase

        reference = FirebaseDatabase.getInstance().getReference().child("Pengguna").child(user.getUid());
        bmr = FirebaseDatabase.getInstance().getReference().child("Bmr");

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
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    int umur_min,umur_max,energi;
                    String kelamin;
                    umur_min = (int) ds.child("umurBawah").getValue();
                    umur_max = (int) ds.child("umurAtas").getValue();
                    kelamin = (String) ds.child("jenis_kelamin").getValue();

//                    if ()
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void displayEnergi(View v){

    }

    public void displayFormrule(View view) {
        Intent i = new Intent(this,Rule.class);
        startActivity(i);
        finish();
    }
}
