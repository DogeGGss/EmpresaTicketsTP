package ar.edu.ungs.prog2.ticketek;

import java.util.List;

public class Ticketek implements ITicketek {

    public Ticketek() {
        // Constructor: inicializar estructuras de datos internas si es necesario
    }

    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
        // TODO: Implementar
    }

    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        // TODO: Implementar
    }

    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        // TODO: Implementar
    }

    @Override
    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
        // TODO: Implementar
    }

    @Override
    public void registrarEspectaculo(String nombre) {
        // TODO: Implementar
    }

    @Override
    public void registrarFuncion(String nombreEspectaculo, String nombreSede, String fecha, double precioBase) {
        // TODO: Implementar
    }

    @Override
    public void registrarFuncion(String nombreEspectaculo, String nombreSede, String fecha, double precioBase, String sector) {
        // TODO: Implementar
    }

    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidad) {
        // TODO: Implementar
        return null;
    }

    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidad, String sector) {
        // TODO: Implementar
        return null;
    }

    @Override
    public void anularEntrada(IEntrada entrada, String contrasenia) {
        // TODO: Implementar
    }

    @Override
    public void cambiarEntrada(IEntrada entrada, String contrasenia, String nuevaFecha) {
        // TODO: Implementar
    }

    @Override
    public void cambiarEntrada(IEntrada entrada, String contrasenia, String nuevaFecha, String nuevoSector, int nuevoAsiento) {
        // TODO: Implementar
    }

    @Override
    public double costoEntrada(String nombreEspectaculo, String fecha) {
        // TODO: Implementar
        return 0;
    }

    @Override
    public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
        // TODO: Implementar
        return 0;
    }

    @Override
    public List<String> listarFunciones(String nombreEspectaculo) {
        // TODO: Implementar
        return null;
    }

    @Override
    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
        // TODO: Implementar
        return null;
    }

    @Override
    public double totalRecaudado(String nombreEspectaculo) {
        // TODO: Implementar
        return 0;
    }

    @Override
    public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
        // TODO: Implementar
        return 0;
    }

    @Override
    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        // TODO: Implementar
        return null;
    }

    @Override
    public String toString() {
        // TODO: Implementar
        return super.toString();
    }
}