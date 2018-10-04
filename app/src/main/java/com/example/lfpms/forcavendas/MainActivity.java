package com.example.lfpms.forcavendas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, firebase;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private List<Venda> vendaLista, listaTeste;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recycleviewmain);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), vendaLista);
        recyclerView.setAdapter(recyclerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });




        vendaLista = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mAuth.getCurrentUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        firebase = database.getReference("venda");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vendaLista.clear();

                for(DataSnapshot dados : dataSnapshot.getChildren()){
                    Venda novaVenda = dados.getValue(Venda.class);
                    Log.d("Luisao", novaVenda.getDescricao() + " - " + novaVenda.getTipo() );
                    vendaLista.add(novaVenda);

                }
                //Log.d("Luisao3", vendaLista.get(2).getDescricao());
                recyclerAdapter.setVendaLista(vendaLista);
                Log.d("Luisao2", String.valueOf(vendaLista.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Luisao", "A leitura falhou!" + databaseError.getCode());
            }
        });



        if(user != null){
            Toast.makeText(getApplicationContext(), "Bem vindo de volta " + user.getEmail() + " !", Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void setarListaTeste(){
        listaTeste = new ArrayList<Venda>();
        listaTeste.add(new Venda("martelo", 5, "22/10/2018", "A vista"));
        listaTeste.add(new Venda("bala", 8, "22/10/2018", "A vista"));
        listaTeste.add(new Venda("calça", 50, "22/10/2018", "A vista"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
