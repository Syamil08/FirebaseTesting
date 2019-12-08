package com.example.firebasetesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText e1,e2;

//    define firebase auth
    FirebaseAuth auth;
//    untuk membuat uid
    FirebaseUser firebaseuser;

    DatabaseReference rootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        definisikan dan hubungkan ke id
        e1 = (EditText) findViewById(R.id.edtEmail);
        e2 = (EditText) findViewById(R.id.edtPassword);

//        get data for auth
        auth = FirebaseAuth.getInstance();

        rootReference = FirebaseDatabase.getInstance().getReference();

    }

    public void createUser(View v){

//        buat variable untuk mendapatkan nilai dari inputan kita
        final String email, password;
        email = e1.getText().toString().trim();
        password = e2.getText().toString().trim();

//        jika email dan pas kosong maka akan keluar toast yang memberi tahu bahwa tidak boleh kosong
//        kemudian jika tidak maka akan dikirim data ke firebase seperti cara di else
        if (e1.getText().toString().equals("") && e2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Tidak Boleh Anda Kosongi",Toast.LENGTH_SHORT).show();
        }
        else{
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                firebaseuser = auth.getCurrentUser();

                                User myUserInsertObj = new User(email,password);

                                rootReference.child("Pengguna").child(firebaseuser.getUid()).setValue(myUserInsertObj)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getApplicationContext(),"Nilai berhasil ditambah di database",Toast.LENGTH_SHORT).show();
                                                finish();
                                                Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                                                startActivity(i);
                                            }
                                        });

//                                simpan nilai ke dalam database


                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Pengguna Gagal dibuat",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
