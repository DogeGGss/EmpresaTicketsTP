package ar.edu.ungs.prog2.ticketek;

public class Funcion {
    private String nombreEspectaculo;
    private String fecha;
    private String sede;
    private double precioBase;
    private int codigoEspectaculo;

    // Constructor original
    public Funcion(String nombreEspectaculo,int codigoEspectaculo, String fecha, String sede, double precioBase) {
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.precioBase = precioBase;
        this.codigoEspectaculo=codigoEspectaculo;
    }

    // Constructor alternativo para compatibilidad con Espectaculo
    public Funcion(Fecha fecha, Sede sede) {
        this.fecha = fecha.toString();
        this.sede = sede.getNombre();
        // Puedes asignar valores por defecto o modificar según tu modelo
        this.precioBase = 0;
        this.nombreEspectaculo = "";
    }

    public boolean mismaFecha(String fecha) {
    return this.fecha.equals(fecha);
    }


    public int getCodigoEspectaculoEstaFuncion(){
        return codigoEspectaculo;
    }

    public String getNombreEspectaculo() {
        return nombreEspectaculo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombreSede() {
        return sede;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}