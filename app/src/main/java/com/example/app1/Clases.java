package com.example.app1;

public class Clases {

    String idUsuario;
    String nombre;
    String apellidos;
    String lada;
    String telefono;
    String contrasena;
    String correo;

    public Clases(String idUsuario, String nombre, String apellidos, String lada, String telefono, String contrasena, String correo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.lada = lada;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getLada() {
        return lada;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCorreo() {
        return correo;
    }


}
