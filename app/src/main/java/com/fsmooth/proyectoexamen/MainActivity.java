package com.fsmooth.proyectoexamen;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregar;
    private Button btnBorrar;

    private SQLiteHelper Helper;
    private SQLiteDatabase db;

    private ListView listViewTrabajadores;
    private AdapterTrabajadores adapterTrabajadores;

    private List<Trabajadores> trabajadoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTrabajadores = (ListView) findViewById(R.id.listView);
        trabajadoresList = new ArrayList<Trabajadores>();

        btnAgregar = (Button) findViewById(R.id.buttonAgregar);
        btnBorrar = (Button) findViewById(R.id.buttonBorrar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
