package ar.edu.ungs.prog2.ticketek;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Ticketek implements ITicketek {

    private Map<String, Usuario> usuarios = new HashMap<>(); 
    private Map<String, Sede> sedes = new HashMap<>();
    private Map<String, Espectaculo> espectaculos = new HashMap<>();    
    private int proximoCodigoEspectaculo = 1;
    private Map<Integer, Usuario> entradaAUsuario = new HashMap<>();

    public Ticketek() {
        // Constructor: inicializar estructuras de datos internas si es necesario
    }

    //Estadio
    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
        //Validaciones
        sedeGeneralValidaParaRegistrar(nombre, direccion, capacidadMaxima);
        existeLaSede_Direccion(nombre, direccion);

        sedes.put(nombre, new Estadio(nombre, direccion, capacidadMaxima));
    }

    //Teatro
    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, 
    String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        //Validaciones
        sedeGeneralValidaParaRegistrar(nombre, direccion, capacidadMaxima);
        existeLaSede_Direccion(nombre, direccion);
        numAsientosValido(asientosPorFila);
        sedeTeatroValida(sectores, capacidad, porcentajeAdicional);

        sedes.put(nombre, new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional));
    }

    //Miniestadio
    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
     int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
        
        //Validaciones
        sedeGeneralValidaParaRegistrar(nombre, direccion, capacidadMaxima);
        existeLaSede_Direccion(nombre, direccion);
        numAsientosValido(asientosPorFila);
        sedeMiniestadioValida(sectores, capacidad, porcentajeAdicional, cantidadPuestos, precioConsumicion);

        sedes.put(nombre, new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos, precioConsumicion, sectores, capacidad, porcentajeAdicional));
    }

    @Override
    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
        
        //Validaciones
        validarNuevoUsuario(email, nombre, apellido, contrasenia);

        usuarios.put(email, new Usuario(email, nombre, apellido, contrasenia));
    }

    @Override
    public void registrarEspectaculo(String nombre) {

        //Validaciones
        espectaculoValidoParaRegistrar(nombre);

        espectaculos.put(nombre, new Espectaculo(nombre, proximoCodigoEspectaculo++));
    }

    @Override
    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        
        //Validaciones
        existeEspectaculo(nombreEspectaculo);
        funcionValidaParaRegistra(nombreEspectaculo, fecha, sede, precioBase);

        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        espectaculo.crearFuncion(fecha, sedes.get(sede), precioBase);
    }

    //Vende la entrada de una sede=Estadio
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, 
    int cantidadEntradas) {

        //Validaciones
        usuarioValido(email, contrasenia);
        funcionExiste(nombreEspectaculo, fecha, email, contrasenia);
        validarSedeSinPlatea(nombreEspectaculo, fecha);
        
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        Usuario usuario = usuarios.get(email);
        List<IEntrada> vendidas = new java.util.ArrayList<>();
        for (int i = 0; i < cantidadEntradas; i++) {
            Entrada entrada = new Entrada(nombreEspectaculo, fecha, funcion.getNombreSede(), null, 
            0, funcion.getPrecioBase());
            usuario.agregarEntrada(entrada);
            vendidas.add(entrada);
            entradaAUsuario.put(entrada.getId(), usuario);
            //TERMINAR
            //espectaculo.sumar(); para la recaudacion
        }
        return vendidas;
    }

    //Vende la entrada de una sede con platea
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, 
    String sector, int[] asientos) {
        
        //Validaciones
        usuarioValido(email, contrasenia);
        funcionExiste(nombreEspectaculo, fecha, email, contrasenia);
        validarSedeConPlatea(nombreEspectaculo, fecha);
        
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        Usuario usuario = usuarios.get(email);
        List<IEntrada> vendidas = new java.util.ArrayList<>();
        for (int nroAsiento : asientos) {
            Entrada entrada = new Entrada(nombreEspectaculo, fecha, funcion.getNombreSede(), sector, nroAsiento, funcion.getPrecioBase());
            usuario.agregarEntrada(entrada);
            vendidas.add(entrada);
            entradaAUsuario.put(entrada.getId(), usuario);
            //TERMINAR
            //espectaculo.sumar(); para la recaudacion
        }
        return vendidas;
    }

    @Override
    public String listarFunciones(String nombreEspectaculo) {

        //Validaciones
        espectatculoExiste(nombreEspectaculo);

        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        StringBuilder sb = new StringBuilder();

        for (Funcion funcion : espectaculo.getFunciones().values()) {
            String nombreSede = funcion.getNombreSede();
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

        //Validaciones
        espectatculoExiste(nombreEspectaculo);

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
        
        usuarioValido(email, contrasenia);

        Usuario usuario = usuarios.get(email);
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
        
        usuarioValido(email, contrasenia);
        
        Usuario usuario = usuarios.get(email);
        return new java.util.ArrayList<>(usuario.getEntradas());
    }

    @Override
    public boolean anularEntrada(IEntrada entrada, String contrasenia) {
        if(entrada==null){
            throw new RuntimeException("Entrada nula");
        }
        if (!(entrada instanceof Entrada)){
            throw new RuntimeException("No es una entrada valida");
        }
        Entrada entradaActual = (Entrada) entrada;
        Usuario usuario = entradaAUsuario.get(entradaActual.getId());
        if (usuario == null){
            throw new RuntimeException("Usuario nulo");
        }
        if (!usuario.validarContrasenia(contrasenia)){
            throw new RuntimeException("Contraseña inválida");
        }
        if (!entradaActual.isValido()){
            throw new RuntimeException("Entrada invalida");
        }
            entradaActual.setValido(false);
            return true;
    }

    @Override
    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
        /*  TODO: Implementar
            for(Funcion funcion: espectaculos.get(nombreEspectaculo).getFunciones().values()){
                if (funcion.mismaFecha(fecha)) {
                
             }
        }
        */
        return null;
        
    }

    @Override
    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
        // TODO: Implementar
        return null;
    }

    //Para estadios
    @Override
    public double costoEntrada(String nombreEspectaculo, String fecha) {
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
        Espectaculo espect= espectaculos.get(nombreEspectaculo);
        for(Funcion func: espect.getFunciones().values()){
            if(func.mismaFecha(fecha)){
                return func.getPrecioBase();
            }
        }
        throw new RuntimeException("No existe una funcion en esa fecha para el espectaculo ingresado");
    }

    @Override
    public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
            
        Espectaculo espect = espectaculos.get(nombreEspectaculo);
        for (Funcion func : espect.getFunciones().values()) {
            if (func.mismaFecha(fecha)) {
                Sede sede = sedes.get(func.getNombreSede());
                if (sede instanceof SedeConPlatea) {
                    SedeConPlatea scp = (SedeConPlatea) sede;
                    double porcentaje = scp.getPorcentaje(sector);
                    return func.getPrecioBase() * (100+porcentaje);
                } else {
                    throw new RuntimeException("La sede no es numerada");
                }
            }
        }
        throw new RuntimeException("No existe una funcion en esa fecha para el espectaculo ingresado");
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



    //Metodos auxiliares
    private void sedeGeneralValidaParaRegistrar(String nombre, String direccion, int capacidadMaxima) {
        if (nombre == null || nombre.length() <= 4)
            throw new RuntimeException("El nombre debe tener más de 4 caracteres");
        if (direccion == null || direccion.length() <= 4)
            throw new RuntimeException("La dirección debe tener más de 4 caracteres");
        if (capacidadMaxima <= 0)
            throw new RuntimeException("La capacidad debe ser positiva");
    }

    private void existeLaSede_Direccion(String nombre, String direccion) {
       if (sedes.containsKey(nombre))
            throw new RuntimeException("Sede ya registrada");
        for (Sede s : sedes.values()) {
            if (s.getDireccion().equalsIgnoreCase(direccion))
                throw new RuntimeException("La dirección ya está registrada");
        }
    }

    private void numAsientosValido(int asientosPorFila){
        if (asientosPorFila <= 0)
            throw new RuntimeException("Los asientos por fila deben ser positivos");
    }

    private void sedeConPlateaValido(String[] sectores, int[] capacidad, int[] porcentajeAdicional){
        if (sectores == null || capacidad == null || porcentajeAdicional == null
            || sectores.length != capacidad.length || sectores.length != porcentajeAdicional.length)
            throw new RuntimeException("sector,capacidad y/o porcentajeAdicional no son validos");
        if(porcentajeAdicional.length != 4)
            throw new RuntimeException("Los porcentajes adicionales deben ser 4 (VIP, COMUN, BAJA, ALTA)");
    }

    private void sedeTeatroValida(String[] sectores, int[] capacidad, int[] porcentajeAdicional){
            sedeConPlateaValido(sectores, capacidad, porcentajeAdicional);
    }

    private void sedeMiniestadioValida(String[] sectores, int[] capacidad, int[] porcentajeAdicional,
    int cantidadPuestos, double precioConsumicion){
        sedeConPlateaValido(sectores, capacidad, porcentajeAdicional);
        if (cantidadPuestos <= 0)
            throw new RuntimeException("La cantidad de puestos debe ser positiva");
        if (precioConsumicion < 0)
            throw new RuntimeException("El precio de consumición debe ser no negativo");
    }

    private void validarNuevoUsuario(String email, String nombre, String apellido, String contrasenia) {
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
    }

        private void espectaculoValidoParaRegistrar(String nombreEspectaculo) {
        if (nombreEspectaculo == null || nombreEspectaculo.trim().isEmpty())
            throw new RuntimeException("Nombre de espectáculo inválido");
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo ya esta registrado");
    }

    private void existeEspectaculo(String nombreEspectaculo) {
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
    }

    private void funcionValidaParaRegistra(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        existeEspectaculo(nombreEspectaculo);
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        if (espectaculo.tieneFuncionEnSedeYFecha(espectaculo.getFunciones().get(fecha).getNombreSede(), fecha))
            throw new RuntimeException("Ya existe una función para ese espectáculo en esa fecha");
        
        if (fecha == null || fecha.trim().isEmpty())
            throw new RuntimeException("Fecha inválida");
        if (sede == null || sede.trim().isEmpty() || !sedes.containsKey(sede))
            throw new RuntimeException("Sede inválida");
        if (precioBase < 0)
            throw new RuntimeException("Precio base inválido");
    }


    private void usuarioValido(String email, String contrasenia){
        if (!usuarios.containsKey(email)){
             throw new RuntimeException("Usuario o contrasenia incorrecto");
        }
        Usuario usuario = usuarios.get(email);
        if (!usuario.validarContrasenia(contrasenia)){
             throw new RuntimeException("Usuario o contrasenia incorrecto");
        }
    }

    private void espectatculoExiste(String nombreEspectaculo){
        if (!espectaculos.containsKey(nombreEspectaculo))
            throw new RuntimeException("El espectáculo no está registrado");
    }
    
    private void funcionExiste(String nombreEspectaculo, String fecha, String email, String contrasenia){

        espectatculoExiste(nombreEspectaculo);
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        if (funcion == null)
            throw new RuntimeException("No existe la función para esa fecha");
    }

    private void validarSedeConPlatea(String nombreEspectaculo, String fecha) {
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        Sede sede = sedes.get(funcion.getNombreSede());
        
        if (!(sede instanceof SedeConPlatea))
            throw new RuntimeException("La sede no es numerada");
    }

    private void validarSedeSinPlatea(String nombreEspectaculo, String fecha){
        Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
        Funcion funcion = espectaculo.getFunciones().get(fecha);
        Sede sede = sedes.get(funcion.getNombreSede());

        if (!(sede instanceof Estadio))
        throw new RuntimeException("La sede no es un estadio");
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