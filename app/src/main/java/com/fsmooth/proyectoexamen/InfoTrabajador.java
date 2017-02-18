package com.fsmooth.proyectoexamen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoTrabajador extends AppCompatActivity {

    private TextView textInfo;
    private TextView textNombre;
    private TextView textApellidos;
    private TextView textEdad;

    private ListView listViewCursos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_trabajador);

        Bundle bundle = this.getIntent().getExtras();

        textInfo = (TextView) findViewById(R.id.textInfo);
        textNombre = (TextView) findViewById(R.id.textNombre);
        textApellidos = (TextView) findViewById(R.id.textApellidos);
        textEdad = (TextView) findViewById(R.id.textEdad);
        listViewCursos = (ListView) findViewById(R.id.listViewCursos);

        textInfo.append(bundle.getString("id_trab"));
        textNombre.append(bundle.getString("nombre"));
        textApellidos.append(bundle.getString("apellidos"));
        textEdad.append(bundle.getString("edad"));



    }
}
