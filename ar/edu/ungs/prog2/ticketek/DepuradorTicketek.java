package ar.edu.ungs.prog2.ticketek;

import java.util.List;
import java.util.Map;

public class DepuradorTicketek {

    public static void depurarTicketek(ITicketek ticketek) {
        System.out.println("----- INICIO DEPURACION TICKETEK -----");

        // Usuarios
        System.out.println("Usuarios registrados:");
        if (ticketek instanceof Ticketek) {
            Ticketek t = (Ticketek) ticketek;
            for (Map.Entry<String, Usuario> entry : t.getUsuarios().entrySet()) {
                Usuario u = entry.getValue();
                System.out.println("Usuario: " + u.getEmail() + " - " + u.getNombre() + " " + u.getApellido());
                System.out.println("  Entradas:");
                for (IEntrada e : u.getEntradas()) {
                    System.out.println("    " + e);
                }
            }

            // Espectáculos y funciones
            System.out.println("\nEspectáculos registrados:");
            for (Map.Entry<String, Espectaculo> entry : t.getEspectaculos().entrySet()) {
                Espectaculo esp = entry.getValue();
                System.out.println("Espectáculo: " + esp.getNombre() + " (código: " + esp.getCodigoEspectaculo() + ")");
                for (Funcion f : esp.getFunciones().values()) {
                    System.out.println("  Funcion: " + f.getFecha() + " - " + f.getNombreSede() + " - Precio base: " + f.getPrecioBase());
                }
            }

            // Sedes
            System.out.println("\nSedes registradas:");
            for (Map.Entry<String, Sede> entry : t.getSedes().entrySet()) {
                Sede s = entry.getValue();
                System.out.println("Sede: " + s.getNombre() + " - Dirección: " + s.getDireccion() + " - Capacidad: " + s.getCapacidadMaxima());
            }
        }

        
        
    if (ticketek instanceof Ticketek) {
        Ticketek t = (Ticketek) ticketek;
        System.out.println("\nPorcentajes adicionales de las SedeConPlatea:");
        for (Map.Entry<String, Sede> entry : t.getSedes().entrySet()) {
            Sede s = entry.getValue();
            if (s instanceof SedeConPlatea) {
                SedeConPlatea scp = (SedeConPlatea) s;
                System.out.println("Sede: " + scp.getNombre());
                String[] pruebas = {"PLATEA VIP", "VIP", "PLATEA COMÚN", "COMUN", "PLATEA BAJA", "BAJA", "PLATEA ALTA", "ALTA"};
                for (String sector : pruebas) {
                    double porcentaje = scp.getPorcentaje(sector);
                    System.out.println("  Sector: " + sector + " -> Porcentaje: " + porcentaje);
                }
            }
        }
    }

    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
     System.out.println(ticketek.toString());
        System.out.println("\n----- FIN DEPURACION TICKETEK -----");

}
}


    //podemos agregar más métodos de depuración aquí si es necesario para comprobar muchas mas cosas
