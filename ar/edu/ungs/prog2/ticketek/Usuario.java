package ar.edu.ungs.prog2.ticketek;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Map<Integer, IEntrada> entradas = new HashMap<>();; // clave= id de entrada
    

    public Usuario(String email, String nombre, String apellido, String contrasenia) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
    }
    
    @Override
    public String toString() {
        return "Usuario: " + nombre + " " + apellido + " (" + email + ")";
    }

    public void agregarEntrada(IEntrada entrada) {
        if (entrada instanceof Entrada) {
            entradas.put(((Entrada)entrada).getId(), entrada);
        }
    }

    public IEntrada getEntradaPorId(int id) {
        return entradas.get(id);
    }

    public boolean validarContrasenia(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
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

    public Collection<IEntrada> getEntradas() {
        return entradas.values();
    }

}