package com.fsmooth.proyectoexamen;

public class Formacion {
    // atributos
    private int id;
    private int trabajador;
    private String curso;
    private int duracion;

    // constructores
    public Formacion(int id, int trabajador, String curso, int duracion) {
        this.id = id;
        this.trabajador = trabajador;
        this.curso = curso;
        this.duracion = duracion;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(int trabajador) {
        this.trabajador = trabajador;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
