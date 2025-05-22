package ar.edu.ungs.prog2.ticketek;

public class Estadio extends Sede {
   
    public Estadio(String nombre, String direccion, int capacidadMaxima) {
        super(nombre, direccion, capacidadMaxima);   
    }
    
    @Override
    public String toString() {
        return "Estadio: " + getNombre() + " - Dirección: " + getDireccion() + " - Capacidad: " + getCapacidadMaxima();
    }
}