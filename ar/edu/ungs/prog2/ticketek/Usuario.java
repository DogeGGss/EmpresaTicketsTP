package ar.edu.ungs.prog2.ticketek;

import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private List<IEntrada> entradas;

    public Usuario(String email, String nombre, String apellido, String contrasenia) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.entradas = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public List<IEntrada> getEntradas() {
        return entradas;
    }

    public void agregarEntrada(IEntrada entrada) {
        entradas.add(entrada);
    }

    public boolean validarContrasenia(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
    }
}