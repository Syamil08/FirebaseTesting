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

public class LoginActivity extends AppCompatActivity {

    EditText e1,e2;

    //    define firebase auth
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //        definisikan dan hubungkan ke id
        e1 = (EditText) findViewById(R.id.edtEmail);
        e2 = (EditText) findViewById(R.id.edtPassword);

//        get data for auth
        auth = FirebaseAuth.getInstance();
    }

    public void loginuser(View v){
        String email, password;
        email = e1.getText().toString().trim();
        password = e2.getText().toString().trim();
        if (e1.getText().toString().equals("") && e2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Tidak Boleh Anda Kosongi",Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Login Sukses",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
