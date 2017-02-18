package com.fsmooth.proyectoexamen;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class InfoTrabajador extends AppCompatActivity {

    private String idTrabajador;
    private String nombreTrabajador;
    private String apellidosTrabajador;
    private String edadTrabajador;

    private TextView textInfo;
    private TextView textNombre;
    private TextView textApellidos;
    private TextView textEdad;

    private ListView listViewCursos;
    private AdapterCurso adapterCurso;

    private List<Formacion> cursosList;

    private SQLiteHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_trabajador);

        Bundle bundle = this.getIntent().getExtras();

        idTrabajador = bundle.getString("id_trab");
        nombreTrabajador = bundle.getString("nombre");
        apellidosTrabajador = bundle.getString("apellidos");
        edadTrabajador = bundle.getString("edad");


        textInfo = (TextView) findViewById(R.id.textInfo);
        textNombre = (TextView) findViewById(R.id.textNombre);
        textApellidos = (TextView) findViewById(R.id.textApellidos);
        textEdad = (TextView) findViewById(R.id.textEdad);
        listViewCursos = (ListView) findViewById(R.id.listViewCursos);
        cursosList = new ArrayList<>();

        textInfo.append(idTrabajador);
        textNombre.append(nombreTrabajador);
        textApellidos.append(apellidosTrabajador);
        textEdad.append(edadTrabajador);


        helper = new SQLiteHelper(this, "DBTrabajadores", null, 1);
        db = helper.getWritableDatabase();

        adapterCurso = new AdapterCurso(this, cursosList, R.layout.item_curso);
        listViewCursos.setAdapter(adapterCurso);

        update();

        listViewCursos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int posicion = position;
                // Dialog to confirm deletion
                String dialog_tittle = getResources().getString(R.string.dialog_tittle);
                String dialog_message = getResources().getString(R.string.dialog_message);
                String dialog_positive_button = getResources().getString(R.string.dialog_positive_button);
                String dialog_negative_button = getResources().getString(R.string.dialog_negative_button);

                AlertDialog.Builder dialog = new AlertDialog.Builder(InfoTrabajador.this);
                dialog.setTitle(dialog_tittle);
                dialog.setMessage(dialog_message);
                dialog.setCancelable(false);
                dialog.setPositiveButton(dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.delete("Formacion", "id="+ cursosList.get(posicion).getId(), null);

                        update();
                    }
                });
                dialog.setNegativeButton(dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                });
                dialog.show();

                return false;
            }
        });

    }


    private List<Formacion> getAllCursos() {
        Cursor cursor = db.rawQuery("select * from Formacion where id_trab=" +idTrabajador, null);
        List<Formacion> list = new ArrayList<Formacion>();

        if (cursor.moveToFirst()){
            while (cursor.isAfterLast() == false){

                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int id_trab = cursor.getInt(cursor.getColumnIndex("id_trab"));
                String curso = cursor.getString(cursor.getColumnIndex("curso"));
                int duracion = cursor.getInt(cursor.getColumnIndex("duracion"));

                list.add(new Formacion(id,id_trab,curso,duracion));
                cursor.moveToNext();
            }
        }
        return list;
    }

    private void update() {
        cursosList.clear();
        cursosList.addAll(getAllCursos());
        adapterCurso.notifyDataSetChanged();
    }

    protected void onDestroy() {
        db.close();
        finish();
        super.onDestroy();
    }
}
