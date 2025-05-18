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

    public int getCantidadPuestos() {
        return cantidadPuestos;
    }

    public double getPrecioConsumicion() {
        return precioAniadido;
    }
}