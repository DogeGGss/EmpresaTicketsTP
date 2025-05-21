package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;

public class Entrada implements IEntrada {
    private String nombreEspectaculo;
    private String fecha; // formato dd/MM/yy o dd/MM/yyyy
    private String sede;
    private String sector;
    private int nroAsiento;
    private double precio;
    private boolean valido;
    private static int contador = 1; // ID global y único para todas las entradas
    private int id;

    public Entrada(String nombreEspectaculo, String fecha, String sede, String sector, int nroAsiento, double precio) {
        this.nombreEspectaculo = nombreEspectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.sector = sector;
        this.nroAsiento = nroAsiento;
        this.precio = precio;
        this.valido = true;
        this.id = contador++; // Asigna un ID único automáticamente
    }

    @Override
    public double precio() {
        return precio;
    }

    @Override
    public String ubicacion() {
        return sede + (sector != null && !sector.isEmpty() ? " - " + sector : "") +
               (nroAsiento > 0 ? " Asiento: " + nroAsiento : "");
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- "); // <-- Agregá esto
        sb.append(nombreEspectaculo).append(" - ");
        sb.append(fecha);
        if (!esFutura()) sb.append(" P");
        sb.append(" - ").append(sede).append(" - ");
        if (sector == null || sector.isEmpty()) {
            sb.append("CAMPO");
        } else {
            sb.append(sector);
            if (nroAsiento > 0) {
                sb.append(" a:").append(nroAsiento);
            }
        }
        return sb.toString();
    }

    public boolean esFutura() {
        String[] partes = fecha.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        if (anio < 100) anio += 2000; // Para fechas tipo 25/07/25

        LocalDate fechaEntrada = LocalDate.of(anio, mes, dia);
        LocalDate hoy = LocalDate.now();

        return fechaEntrada.isAfter(hoy);
    }

    // Getters y setters si los necesitas
    public String getFecha() {
        return fecha;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getNombreEspectaculo() {
        return nombreEspectaculo;
    }

    public String getSede() {
        return sede;
    }

    public String getSector() {
        return sector;
    }
}