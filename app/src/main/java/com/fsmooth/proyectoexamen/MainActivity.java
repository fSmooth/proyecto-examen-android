package com.fsmooth.proyectoexamen;

import android.content.Intent;
import android.database.Cursor;
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

    private SQLiteHelper helper;
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
                Intent intent = new Intent(MainActivity.this, AgregarTrabajador.class);
                startActivity(intent);
                finish();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
                update();
            }
        });

        helper = new SQLiteHelper(this, "DBTrabajadores", null, 1);
        db = helper.getWritableDatabase();

        adapterTrabajadores = new AdapterTrabajadores(this, trabajadoresList, R.layout.item_trabajadores);
        listViewTrabajadores.setAdapter(adapterTrabajadores);

        update();



    }

    private List<Trabajadores> getAllTrabajadores() {
        Cursor cursor = db.rawQuery("select * from Trabajadores", null);
        List<Trabajadores> list = new ArrayList<Trabajadores>();

        if (cursor.moveToFirst()){
            while (cursor.isAfterLast() == false){

                int id = cursor.getInt(cursor.getColumnIndex("id_trab"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String apellidos = cursor.getString(cursor.getColumnIndex("apellidos"));
                int edad = cursor.getInt(cursor.getColumnIndex("edad"));

                list.add(new Trabajadores(id, nombre, apellidos, edad));
                cursor.moveToNext();
            }
        }
        return list;
    }

    private void update() {
        trabajadoresList.clear();
        trabajadoresList.addAll(getAllTrabajadores());
        adapterTrabajadores.notifyDataSetChanged();
    }

    private void removeAll(){
        db.delete("Trabajadores", "", null);
        db.delete("Formacion", "", null);
    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
