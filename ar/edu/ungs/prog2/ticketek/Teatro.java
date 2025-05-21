package ar.edu.ungs.prog2.ticketek;

public class Teatro extends SedeConPlatea {
    public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,String[] sectores,int[] capacidad, int[] porcentajeAdicional) {
    super(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);
}

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Teatro: ").append(getNombre())
      .append(" - Dirección: ").append(getDireccion())
      .append(" - Capacidad: ").append(getCapacidadMaxima())
      .append(" - Sectores: ");
    String[] sectores = getSectores();
    int[] capacidades = getCapacidades();
    for (int i = 0; i < sectores.length; i++) {
        sb.append(sectores[i]).append("(").append(capacidades[i]).append(")");
        if (i < sectores.length - 1) sb.append(", ");
    }
    return sb.toString();
}


}