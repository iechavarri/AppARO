package com.none.kedadas;

import com.google.firebase.database.IgnoreExtraProperties;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by diego on 22/03/2017.
 */
@IgnoreExtraProperties
public class Kedada {

    String id;
    String nombre;
    Date fecha;
    public Kedada() {
        // needed for firebase
    }
    public Kedada(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }
    public Kedada(String id, String nombre, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        return "Kedada{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + d.format(this.fecha) +
                '}';
    }
}
