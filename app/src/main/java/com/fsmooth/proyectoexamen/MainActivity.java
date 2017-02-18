package com.fsmooth.proyectoexamen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        btnAgregar = (Button) findViewById(R.id.butAgregar);
        btnBorrar = (Button) findViewById(R.id.butBorrar);

        helper = new SQLiteHelper(this, "DBTrabajadores", null, 1);
        db = helper.getWritableDatabase();

        adapterTrabajadores = new AdapterTrabajadores(this, trabajadoresList, R.layout.item_trabajadores);
        listViewTrabajadores.setAdapter(adapterTrabajadores);

        update();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AgregarTrabajador.class);
                startActivity(i);
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


        listViewTrabajadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();

                bundle.putString("id_trab", String.valueOf(trabajadoresList.get(position).getId()));
                bundle.putString("nombre", trabajadoresList.get(position).getNombre());

                Intent i = new Intent(MainActivity.this, AgregarFormacion.class);
                i.putExtras(bundle);

                startActivity(i);
                finish();

                return false;

            }
        });

        listViewTrabajadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                bundle.putString("id_trab", String.valueOf(trabajadoresList.get(position).getId()));
                bundle.putString("nombre", trabajadoresList.get(position).getNombre());
                bundle.putString("apellidos", trabajadoresList.get(position).getApellidos());
                bundle.putString("edad", String.valueOf(trabajadoresList.get(position).getEdad()));

                Intent i = new Intent(MainActivity.this, InfoTrabajador.class);
                i.putExtras(bundle);

                startActivity(i);
                finish();

            }
        });





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
