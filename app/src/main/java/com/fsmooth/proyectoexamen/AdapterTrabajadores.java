package com.fsmooth.proyectoexamen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterTrabajadores extends BaseAdapter{
    // atributos
    private Context context;
    private List<Trabajadores> list;
    private int layout;

    // constructor
    public AdapterTrabajadores(Context context, List<Trabajadores> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.id = (TextView) convertView.findViewById(R.id.textViewId);
            vh.nombre = (TextView) convertView.findViewById(R.id.textViewNombre);
            vh.apellido = (TextView) convertView.findViewById(R.id.textViewApellido);
            vh.edad = (TextView) convertView.findViewById(R.id.textViewEdad);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Trabajadores currentCar = list.get(position);

        vh.id.setText(currentCar.getId() + "");
        vh.nombre.setText(currentCar.getNombre());
        vh.apellido.setText(currentCar.getApellido());
        vh.edad.setText(currentCar.getEdad());




        return convertView;
    }

    public class ViewHolder {
        TextView id;
        TextView nombre;
        TextView apellido;
        TextView edad;
    }
}
