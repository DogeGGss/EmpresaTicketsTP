package ar.edu.ungs.prog2.ticketek;

public class MiniEstadio extends SedeConPlatea {
    private int cantidadPuestos;
    private double precioAniadido;

    public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos,
     double precioAniadido, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);
        this.cantidadPuestos = cantidadPuestos;
        this.precioAniadido = precioAniadido;
    }

    // MiniEstadio.java
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MiniEstadio: ").append(getNombre())
        .append(" - Dirección: ").append(getDireccion())
        .append(" - Capacidad: ").append(getCapacidadMaxima())
        .append(" - Puestos: ").append(getCantidadPuestos())
        .append(" - Precio consumición: $").append(getPrecioConsumicion())
        .append(" - Sectores: ");
        String[] sectores = getSectores();
        int[] capacidades = getCapacidades();
        for (int i = 0; i < sectores.length; i++) {
            sb.append(sectores[i]).append("(").append(capacidades[i]).append(")");
            if (i < sectores.length - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public int getCantidadPuestos() {
        return cantidadPuestos;
    }

    public double getPrecioConsumicion() {
        return precioAniadido;
    }
}