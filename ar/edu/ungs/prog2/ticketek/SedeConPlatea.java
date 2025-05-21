package ar.edu.ungs.prog2.ticketek;

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
        String p = platea.trim().toUpperCase();
        switch (p) {
            case "PLATEA VIP":
            case "VIP":
                return porcentajeAdicional[0];
            case "PLATEA COMÚN":
            case "PLATEA COMUN":
            case "COMUN":
            case "COMÚN":
                return porcentajeAdicional[1];
            case "PLATEA BAJA":
            case "BAJA":
                return porcentajeAdicional[2];
            case "PLATEA ALTA":
            case "ALTA":
                return porcentajeAdicional[3];
            default:
                throw new RuntimeException("Tipo de platea no valido");
        }
    }

    public String[] getSectores() {
        return sectores;
    }

    public int[] getCapacidades() {
        return capacidades;
    }
}