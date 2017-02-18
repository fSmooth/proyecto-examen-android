package com.fsmooth.proyectoexamen;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarFormacion extends AppCompatActivity {

    private String idTrabajador;
    private String nombreTrabajador;

    private EditText editCurso;
    private EditText editDuracion;

    private Button butGuardar;
    private Button butSalir;

    private SQLiteHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_formacion);

        Bundle bundle = this.getIntent().getExtras();

        idTrabajador = bundle.getString("id_trab");
        nombreTrabajador = bundle.getString("nombre");

        editCurso = (EditText) findViewById(R.id.editCurso);
        editDuracion = (EditText) findViewById(R.id.editDuracion);

        butGuardar = (Button) findViewById(R.id.butGuardar);
        butSalir = (Button) findViewById(R.id.butSalir);

        helper = new SQLiteHelper(this, "DBTrabajadores", null, 1);
        db = helper.getWritableDatabase();


        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    create();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        butSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarFormacion.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void create() {
        if (db != null) {
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("id_trab", Integer.parseInt(idTrabajador));
            nuevoRegistro.put("curso", editCurso.getText().toString());
            nuevoRegistro.put("duracion", Integer.parseInt(editDuracion.getText().toString()));

            db.insert("Formacion", null, nuevoRegistro);

            Intent intent = new Intent(AgregarFormacion.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
