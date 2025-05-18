package ar.edu.ungs.prog2.ticketek;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Ticketek implements ITicketek {

    private Map<String, Sede> sedes = new HashMap<>();

    public Ticketek() {
        // Constructor: inicializar estructuras de datos internas si es necesario
    }

    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
        if (nombre == null || nombre.length() <= 4)
            throw new RuntimeException("El nombre debe tener más de 4 caracteres");
        if (direccion == null || direccion.length() <= 4)
            throw new RuntimeException("La dirección debe tener más de 4 caracteres");
        if (capacidadMaxima <= 0)
            throw new RuntimeException("La capacidad debe ser positiva");
        if (sedes.containsKey(nombre))
            throw new RuntimeException("Sede ya registrada");
        for (Sede s : sedes.values()) {
            if (s.getDireccion().equalsIgnoreCase(direccion))
                throw new RuntimeException("La dirección ya está registrada");
        }
        sedes.put(nombre, new Estadio(nombre, direccion, capacidadMaxima));
    }

    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        if (nombre == null || nombre.length() <= 4)
            throw new RuntimeException("El nombre debe tener más de 4 caracteres");
        if (direccion == null || direccion.length() <= 4)
            throw new RuntimeException("La dirección debe tener más de 4 caracteres");
        if (capacidadMaxima <= 0)
            throw new RuntimeException("La capacidad debe ser positiva");
        if (asientosPorFila <= 0)
            throw new RuntimeException("Los asientos por fila deben ser positivos");
        if (sectores == null || capacidad == null || porcentajeAdicional == null
            || sectores.length != capacidad.length || sectores.length != porcentajeAdicional.length)
            throw new RuntimeException("Datos de sectores inválidos");
        if (sedes.containsKey(nombre))
            throw new RuntimeException("Sede ya registrada");
        for (Sede s : sedes.values()) {
            if (s.getDireccion().equalsIgnoreCase(direccion))
                throw new RuntimeException("La dirección ya está registrada");
        }
        sedes.put(nombre, new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional));
    }

    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        if (nombre == null || nombre.length() <= 4)
            throw new RuntimeException("El nombre debe tener más de 4 caracteres");
        if (direccion == null || direccion.length() <= 4)
            throw new RuntimeException("La dirección debe tener más de 4 caracteres");
        if (capacidadMaxima <= 0)
            throw new RuntimeException("La capacidad debe ser positiva");
        if (asientosPorFila <= 0)
            throw new RuntimeException("Los asientos por fila deben ser positivos");
        if (cantidadPuestos <= 0)
            throw new RuntimeException("La cantidad de puestos debe ser positiva");
        if (precioConsumicion < 0)
            throw new RuntimeException("El precio de consumición debe ser no negativo");
        if (sectores == null || capacidad == null || porcentajeAdicional == null
            || sectores.length != capacidad.length || sectores.length != porcentajeAdicional.length)
            throw new RuntimeException("Datos de sectores inválidos");
        if (sedes.containsKey(nombre))
            throw new RuntimeException("Sede ya registrada");
        for (Sede s : sedes.values()) {
            if (s.getDireccion().equalsIgnoreCase(direccion))
                throw new RuntimeException("La dirección ya está registrada");
        }
        sedes.put(nombre, new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos, precioConsumicion, sectores, capacidad, porcentajeAdicional));
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
    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        // TODO: Implementar
    }

    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
        // TODO: Implementar
        return null;
    }

    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        // TODO: Implementar
        return null;
    }

    @Override
    public String listarFunciones(String nombreEspectaculo) {
        // TODO: Implementar
        return null;
    }

    @Override
    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        // TODO: Implementar
        return null;
    }
    
    @Override
    public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
        // TODO: Implementar
        return null;
    }

    @Override
    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
        // TODO: Implementar
        return null;
    }

    @Override
    public boolean anularEntrada(IEntrada entrada, String contrasenia) {
        // TODO: Implementar
        return false;
    }

    @Override
    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
        // TODO: Implementar
        return null;
    }

    @Override
    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
        // TODO: Implementar
        return null;
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
    public double totalRecaudado(String nombreEspectaculo) {
        // TODO: Implementar
        return 0;
    }

    @Override
    public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
        // TODO: Implementar
        return 0;
    }
}