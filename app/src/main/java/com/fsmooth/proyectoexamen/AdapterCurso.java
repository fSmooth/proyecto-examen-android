package com.fsmooth.proyectoexamen;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterCurso extends BaseAdapter{
    // atributos
    private Context context;
    private List<Formacion> list;
    private int layout;

    // constructor
    public AdapterCurso(Context context, List<Formacion> list, int layout) {
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.curso = (TextView) convertView.findViewById(R.id.textCurso);
            vh.duracion = (TextView) convertView.findViewById(R.id.textDuracion);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Formacion current = list.get(position);

        vh.curso.setText(current.getCurso());
        vh.duracion.setText(String.valueOf(current.getDuracion()));

        return convertView;

    }

    public class ViewHolder {
        TextView curso;
        TextView duracion;
    }
}
