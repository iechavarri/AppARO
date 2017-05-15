package com.none.kedadas;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by diego on 22/03/2017.
 */
@IgnoreExtraProperties
public class Kedada {

    String id;
    String nombre;
    Date fecha;

    public static class Users{
        String userId;
        String userEmail;
        String latitud;
        String longitud;

        /**
         * Se que esto es una marranada. Pero es para agilizar desarrollo y organizar un poquito esto para
         * que sea mas facil seguir el trazado de las cosas debugeando
         */
        public Users(){
            this.userId = null;
            this.userEmail = null;
            this.latitud = null;
            this.longitud = null;
        }
        public Users(String userId,String userEmail, String latitud, String longitud) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.latitud = latitud;
            this.longitud = longitud;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getLatitud() {
            return latitud;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }


//TODO getters and setters;
    }



    @Exclude
    ArrayList<Users> users = new ArrayList<>();
    public Kedada() {
        // needed for firebase
    }
    public Kedada(String nombre, Date fecha, Users user) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.users.add(user);
    }

    public void AddUser(Users user) {
        this.users.add(user);
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
