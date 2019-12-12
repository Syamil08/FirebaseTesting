package com.example.firebasetesting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DialogPage extends AppCompatDialogFragment {
    EditText _judul,_deskripsi;
    DialogPageListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialogg,null);

        builder.setView(view)
                .setTitle("Agenda")
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            String judul = _judul.getText().toString().trim();
                            String deskripsi = _deskripsi.getText().toString().trim();
                            listener.applyTexts(judul,deskripsi);
                            LoadActivity.mRecyclerList.add(new RecyclerAddModel(judul,deskripsi));
                    }
                });

        _judul = view.findViewById(R.id.edit_usernamee);
        _deskripsi = view.findViewById(R.id.edit_passwordd);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogPageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }

    }

    public interface DialogPageListener{
        void applyTexts(String judul, String deskripsi);
    }
}
