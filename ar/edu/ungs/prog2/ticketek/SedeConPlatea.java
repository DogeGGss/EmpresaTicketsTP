package ar.edu.ungs.prog2.ticketek;

public class SedeConPlatea extends Sede {
    protected int[] porcentajeAdicional;
    protected int[] asientos;
    protected int[] codigos_entradas;
    protected int[] porcentajePlateas;
    protected String[] sectores;
    protected int[] capacidades;

    public SedeConPlatea(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidades, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMaxima);
        this.sectores = sectores;
        this.capacidades = capacidades;
        this.asientos = new int[asientosPorFila * 4];
        this.codigos_entradas = new int[asientosPorFila * 4];
        this.porcentajeAdicional = porcentajeAdicional;
    }

    public String[] getSectores() {
        return sectores;
    }

    public int[] getCapacidades() {
        return capacidades;
    }
}