package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;

public class Fecha {
    private LocalDate fecha;

    public Fecha(int anio, int mes, int dia) {
        this.fecha = LocalDate.of(anio, mes, dia);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fecha other = (Fecha) obj;
        return fecha.equals(other.fecha);
    }

    @Override
    public int hashCode() {
        return fecha.hashCode();
    }

    @Override
    public String toString() {
        return fecha.toString();
    }
}