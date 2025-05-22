package ar.edu.ungs.prog2.ticketek;

public class SedeConPlatea extends Sede {
    protected int[] porcentajeAdicional;
    //protected int[] asientos;
    //protected int[] codigos_entradas;
    protected int[] porcentajePlateas;
    protected String[] sectores;
    protected int[] capacidades;

    public SedeConPlatea(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidades, 
    int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMaxima);
        this.sectores = sectores;
        this.capacidades = capacidades;
        this.porcentajeAdicional = porcentajeAdicional;
        int totalAsientos = 0;
        for (int cap : capacidades) totalAsientos += cap;
        //this.asientos = new int[totalAsientos];
        //this.codigos_entradas = new int[totalAsientos];
    }

    public double getPorcentaje(String platea) {
        for (int i = 0; i < sectores.length; i++) {
            if (platea.equals(sectores[i])) {
                return porcentajeAdicional[i];
            }
        }
        throw new RuntimeException("Tipo de platea no válido: " + platea);
    }

    public int getIndiceSector(String sector) {
        String buscado = sector.trim().toUpperCase();
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].trim().toUpperCase().equals(buscado)) {
                return i;
            }
        }
        throw new RuntimeException("Sector no válido: " + sector);
    }

    public String[] getSectores() {
        return sectores;
    }

    public int[] getCapacidades() {
        return capacidades;
    }
}