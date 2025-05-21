package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

public class Espectaculo {
    private String nombre;
    private int codigoEspectaculo;
    private HashMap<String, Funcion> funciones; // clave: fecha
    private HashMap<String, Double> recaudacionesPorSede = new HashMap<>();
    private double recaudacionTotal = 0;

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

    public void sumarRecaudacion(double monto, String sede) {
        recaudacionTotal += monto;
        recaudacionesPorSede.put(sede, recaudacionesPorSede.getOrDefault(sede, 0.0) + monto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Espectáculo: ").append(nombre)
        .append(" (Código: ").append(codigoEspectaculo).append(")\n");
        sb.append("Funciones:\n");
        for (Funcion f : funciones.values()) {
            sb.append("  ").append(f).append("\n");
        }
        sb.append("Recaudación total: $").append(getRecaudacionTotal());
        return sb.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public int getCodigoEspectaculo() {
        return codigoEspectaculo;
    }

    public double getRecaudacionPorSede(String sede) {
        return recaudacionesPorSede.getOrDefault(sede, 0.0);
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