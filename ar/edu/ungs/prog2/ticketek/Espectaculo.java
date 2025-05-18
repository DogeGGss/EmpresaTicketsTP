package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

public class Espectaculo {
    private String nombre;
    private int codigoEspectaculo;
    private HashMap<String, Funcion> funciones; // clave: fecha
    private HashMap<String, Integer> recaudacionesPorSede;

    public Espectaculo(String nombre, int codigoEspectaculo) {
        this.nombre = nombre;
        this.codigoEspectaculo = codigoEspectaculo;
        this.funciones = new HashMap<>();
    }

    public boolean tieneFuncionEnSedeYFecha(String sede, String fecha) {
        for (Funcion f : funciones.values()) {
            if (f.getNombreSede().equalsIgnoreCase(sede) && f.getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigoEspectaculo() {
        return codigoEspectaculo;
    }

    public Map<String, Funcion> getFunciones() {
        return funciones;
    }

    public void crearFuncion(String fecha, Sede sede, double precioBase) {
        if (funciones.containsKey(fecha)) {
            throw new RuntimeException("Ya existe una función para ese espectáculo en esa fecha");
        }
        funciones.put(fecha, new Funcion(this.nombre,this.codigoEspectaculo, fecha, sede.getNombre(), precioBase));
    }
}