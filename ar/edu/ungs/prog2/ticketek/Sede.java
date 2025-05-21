package ar.edu.ungs.prog2.ticketek;

public class Sede {
    private String nombre;
    private String direccion;
    private int capacidadMaxima;

    public Sede(String nombre, String direccion, int capacidadMaxima) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public String toString() {
        return "Sede: " + nombre + " - Dirección: " + direccion + " - Capacidad: " + capacidadMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}