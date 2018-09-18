package com.example.lfpms.forcavendas;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final EditText edt_data = (EditText)findViewById(R.id.txtData);
        final EditText edt_descricao = (EditText)findViewById(R.id.txtDescricao);
        final EditText edt_valor = (EditText)findViewById(R.id.txtValor);
        final Spinner spn_tipo = (Spinner)findViewById(R.id.spnTipo);
        Button btn_salvar = (Button)findViewById(R.id.btnSalvar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edt_data.setText(sdf.format(new Date()));

        demo = mDatabase.child("venda");

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                venda = new Venda(edt_descricao.getText().toString(), Float.valueOf(edt_valor.getText().toString()), edt_data.getText().toString(), spn_tipo.getSelectedItem().toString());
                demo.push().setValue(venda);
            }
        });



    }
}
