package ar.edu.ungs.prog2.ticketek;

import javax.management.RuntimeErrorException;

public class SedeConPlatea extends Sede {
    public int[] porcentajeAdicional; //CAMBIAR A PROTECTED
    protected int[] asientos;
    protected int[] codigos_entradas;
    protected int[] porcentajePlateas;
    public String[] sectores;
    protected int[] capacidades;

    public SedeConPlatea(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidades, 
    int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMaxima);
        this.sectores = sectores;
        this.capacidades = capacidades;
        this.asientos = new int[asientosPorFila * 4];
        this.codigos_entradas = new int[asientosPorFila * 4];
        this.porcentajeAdicional = porcentajeAdicional;
    }

    public double getPorcentaje(String platea){
        String Platea= platea.toUpperCase();
         switch (Platea) {
            case "PLATEA VIP":
                return porcentajePlateas[0];
            case "PLATEA COMÚN":
                return porcentajePlateas[1];
            case "PLATEA BAJA":
                return porcentajePlateas[2];
            case "PLATEA ALTA":
               return porcentajePlateas[3];
            default:
                //throw new RuntimeException("Tipo de platea no valido");
                return 0;
        }
    }

    public String[] getSectores() {
        return sectores;
    }

    public int[] getCapacidades() {
        return capacidades;
    }
}