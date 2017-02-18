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

public class AgregarTrabajador extends AppCompatActivity {

    private SQLiteHelper helper;
    private SQLiteDatabase db;

    private EditText editNombre;
    private EditText editApellidos;
    private EditText editEdad;
    private Button butGuadar;
    private Button butSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trabajador);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellidos = (EditText) findViewById(R.id.editApellidos);
        editEdad = (EditText) findViewById(R.id.editEdad);

        butGuadar = (Button) findViewById(R.id.butGuardar);
        butSalir = (Button) findViewById(R.id.butSalir);

        helper = new SQLiteHelper(this, "DBTrabajadores", null, 1);
        db = helper.getWritableDatabase();

        butGuadar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    crearTrabajador();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        butSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarTrabajador.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });



    }

    public void crearTrabajador() {
        if(db != null) {
            ContentValues nuevoRegistro = new ContentValues();

            nuevoRegistro.put("nombre", editNombre.getText().toString());
            nuevoRegistro.put("apellidos", editApellidos.getText().toString());
            nuevoRegistro.put("edad", editEdad.getText().toString());

            db.insert("Trabajadores", null, nuevoRegistro);

            Intent i = new Intent(AgregarTrabajador.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
