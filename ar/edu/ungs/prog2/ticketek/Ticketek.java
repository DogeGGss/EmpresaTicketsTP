package ar.edu.ungs.prog2.ticketek;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Ticketek implements ITicketek {

    private Map<String, Usuario> usuarios = new HashMap<>(); 
    private Map<String, Sede> sedes = new HashMap<>();
    private Map<String, Espectaculo> espectaculos = new HashMap<>();
    private int proximoCodigoEspectaculo = 1; 


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
        if (email == null || !email.contains("@"))
            throw new RuntimeException("Email inválido");
        if (usuarios.containsKey(email))
            throw new RuntimeException("El email ya está registrado");
        if (nombre == null || nombre.isEmpty())
            throw new RuntimeException("Nombre inválido");
        if (apellido == null || apellido.isEmpty())
            throw new RuntimeException("Apellido inválido");
        if (contrasenia == null || contrasenia.length() < 3)
            throw new RuntimeException("Contraseña inválida");
        usuarios.put(email, new Usuario(email, nombre, apellido, contrasenia));
    }

    @Override
    public void registrarEspectaculo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new RuntimeException("Nombre de espectáculo inválido");
        if (espectaculos.containsKey(nombre))
            throw new RuntimeException("El nombre del espectáculo ya está registrado");
        espectaculos.put(nombre, new Espectaculo(nombre, proximoCodigoEspectaculo++));
    }

    @Override
    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
        if (fecha == null || fecha.trim().isEmpty())
            throw new RuntimeException("Fecha inválida");
        if (sede == null || sede.trim().isEmpty() || !sedes.containsKey(sede))
            throw new RuntimeException("Sede inválida");
        if (precioBase < 0)
            throw new RuntimeException("Precio base inválido");

        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);

        // Verifica que no haya otra función en la misma fecha para este espectáculo (sin importar la sede)
        for (Funcion f : espectaculo.getFunciones().values()) {
            if (f.getFecha().equals(fecha)) {
                throw new RuntimeException("Ya existe una función para ese espectáculo en esa fecha");
            }
        }

        espectaculo.crearFuncion(fecha, sedes.get(sede), precioBase);
    }

    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas) {
        if (!usuarios.containsKey(email))
            throw new RuntimeException("El usuario no está registrado");
        Usuario usuario = usuarios.get(email);
        if (!usuario.validarContrasenia(contrasenia))
            throw new RuntimeException("Contraseña inválida");
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        if (funcion == null)
            throw new RuntimeException("No existe la función para esa fecha");
        Sede sede = sedes.get(funcion.getSede());
        if (!(sede instanceof Estadio))
            throw new RuntimeException("La sede no es un estadio");

        List<IEntrada> vendidas = new java.util.ArrayList<>();
        for (int i = 0; i < cantidadEntradas; i++) {
            Entrada entrada = new Entrada(nombreEspectaculo, fecha, funcion.getSede(), null, 0, funcion.getPrecioBase());
            usuario.agregarEntrada(entrada);
            vendidas.add(entrada);
        }
        return vendidas;
    }

    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos) {
        if (!usuarios.containsKey(email))
            throw new RuntimeException("El usuario no está registrado");
        Usuario usuario = usuarios.get(email);
        if (!usuario.validarContrasenia(contrasenia))
            throw new RuntimeException("Contraseña inválida");
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        if (funcion == null)
            throw new RuntimeException("No existe la función para esa fecha");
        Sede sede = sedes.get(funcion.getSede());
        if (!(sede instanceof SedeConPlatea))
            throw new RuntimeException("La sede no es numerada");

        List<IEntrada> vendidas = new java.util.ArrayList<>();
        for (int nroAsiento : asientos) {
            Entrada entrada = new Entrada(nombreEspectaculo, fecha, funcion.getSede(), sector, nroAsiento, funcion.getPrecioBase());
            usuario.agregarEntrada(entrada);
            vendidas.add(entrada);
        }
        return vendidas;
    }

    @Override
    public String listarFunciones(String nombreEspectaculo) {
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");

        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        StringBuilder sb = new StringBuilder();

        for (Funcion funcion : espectaculo.getFunciones().values()) {
            String nombreSede = funcion.getSede();
            String fecha = funcion.getFecha();
            Sede sede = sedes.get(nombreSede);

            if (sede instanceof SedeConPlatea) {
                SedeConPlatea scp = (SedeConPlatea) sede;
                String[] sectores = scp.getSectores();
                int[] capacidades = scp.getCapacidades();
                sb.append(" - (").append(fecha).append(") ").append(nombreSede).append(" - ");
                for (int i = 0; i < sectores.length; i++) {
                    int vendidas = 0;
                    for (Usuario usuario : usuarios.values()) {
                        for (IEntrada entrada : usuario.getEntradas()) {
                            if (entrada instanceof Entrada) {
                                Entrada e = (Entrada) entrada;
                                if (e.getNombreEspectaculo().equals(nombreEspectaculo)
                                        && e.getFecha().equals(fecha)
                                        && e.getSede().equals(nombreSede)
                                        && sectores[i].equals(e.getSector())
                                        && e.isValido()) {
                                    vendidas++;
                                }
                            }
                        }
                    }
                    sb.append(sectores[i]).append(": ").append(vendidas).append("/").append(capacidades[i]);
                    if (i < sectores.length - 1) sb.append(" | ");
                }
                sb.append("\n");
            } else if (sede instanceof Estadio) {
                int vendidas = 0;
                for (Usuario usuario : usuarios.values()) {
                    for (IEntrada entrada : usuario.getEntradas()) {
                        if (entrada instanceof Entrada) {
                            Entrada e = (Entrada) entrada;
                            if (e.getNombreEspectaculo().equals(nombreEspectaculo)
                                    && e.getFecha().equals(fecha)
                                    && e.getSede().equals(nombreSede)
                                    && e.isValido()) {
                                vendidas++;
                            }
                        }
                    }
                }
                sb.append(" - (").append(fecha).append(") ").append(nombreSede)
                .append(" - ").append(vendidas).append("/").append(sede.getCapacidadMaxima()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        List<IEntrada> resultado = new java.util.ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            for (IEntrada entrada : usuario.getEntradas()) {
                if (entrada instanceof Entrada) {
                    Entrada e = (Entrada) entrada;
                    if (e.getNombreEspectaculo().equals(nombreEspectaculo)) {
                        resultado.add(e);
                    }
                }
            }
        }
        return resultado;
    }
    
    @Override
    public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
        if (!usuarios.containsKey(email))
            throw new RuntimeException("El usuario no está registrado");
        Usuario usuario = usuarios.get(email);
        if (!usuario.validarContrasenia(contrasenia))
            throw new RuntimeException("Contraseña inválida");

        List<IEntrada> futuras = new java.util.ArrayList<>();
        for (IEntrada entrada : usuario.getEntradas()) {
            if (entrada instanceof Entrada && ((Entrada) entrada).esFutura()) {
                futuras.add(entrada);
            }
        }
        return futuras;
    }

    @Override
    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
        if (!usuarios.containsKey(email))
            throw new RuntimeException("El usuario no está registrado");
        Usuario usuario = usuarios.get(email);
        if (!usuario.validarContrasenia(contrasenia))
            throw new RuntimeException("Contraseña inválida");

        return new java.util.ArrayList<>(usuario.getEntradas());
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

    //borrar despues IMPORTANTE

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public Map<String, Espectaculo> getEspectaculos() {
        return espectaculos;
    }

    public Map<String, Sede> getSedes() {
        return sedes;
    }



}