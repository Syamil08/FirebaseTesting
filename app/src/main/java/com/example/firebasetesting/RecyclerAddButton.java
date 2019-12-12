package com.example.firebasetesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RecyclerAddButton extends AppCompatActivity implements DialogPage.DialogPageListener {
    public static ArrayList<RecyclerAddModel> mRecyclerList = new ArrayList<>();
    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    Button btn_tambah,btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_add_button);

        buildRecyclerView();

        btn_tambah = findViewById(R.id.btn_tambah);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = mRecyclerList.size();
                openDialog();
                insertItem(position);
            }
        });

        btn_load = findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoadActivity.class));
            }
        });
    }

    private void insertItem(int position) {
        mAdapter.notifyItemInserted(position);
    }

    private void openDialog() {
        DialogPage dialog = new DialogPage();
        dialog.show(getSupportFragmentManager(),"Tambah Agenda");
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewTambah);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAddAdapter(mRecyclerList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }



    @Override
    public void applyTexts(String judul, String deskripsi) {
        RecyclerAddButton.mRecyclerList.add(new RecyclerAddModel(judul,deskripsi));
        Log.d("Data:",judul);
        Log.d("Data:",deskripsi);
    }
}
