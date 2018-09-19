package com.example.lfpms.forcavendas;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private DatabaseReference mDatabase, demo;
    private Venda venda;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final EditText edt_data = (EditText)findViewById(R.id.txtData);
        final EditText edt_descricao = (EditText)findViewById(R.id.txtDescricao);
        final EditText edt_valor = (EditText)findViewById(R.id.txtValor);
        final Spinner spn_tipo = (Spinner)findViewById(R.id.spnTipo);
        Button btn_salvar = (Button)findViewById(R.id.btnSalvar);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Venda");
        builder.setMessage("Confirmar venda?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                venda = new Venda(edt_descricao.getText().toString(), Float.valueOf(edt_valor.getText().toString()), edt_data.getText().toString(), spn_tipo.getSelectedItem().toString());
                demo.push().setValue(venda);
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alerta.dismiss();
            }
        });
        alerta = builder.create();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edt_data.setText(sdf.format(new Date()));

        demo = mDatabase.child("venda");

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.show();
            }
        });
    }
}
