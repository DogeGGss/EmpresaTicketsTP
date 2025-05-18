package ar.edu.ungs.prog2.ticketek;

public class Funcion {
    private String nombreEspectaculo;
    private String fecha;
    private String sede;
    private double precioBase;

    // Constructor original
    public Funcion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.precioBase = precioBase;
    }

    // Constructor alternativo para compatibilidad con Espectaculo
    public Funcion(Fecha fecha, Sede sede) {
        this.fecha = fecha.toString();
        this.sede = sede.getNombre();
        // Puedes asignar valores por defecto o modificar según tu modelo
        this.precioBase = 0;
        this.nombreEspectaculo = "";
    }

    public String getNombreEspectaculo() {
        return nombreEspectaculo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getSede() {
        return sede;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}