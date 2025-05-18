package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class SedeConPlatea extends Sede {
    protected int[] porcentajeAdicional;
    protected int[] asientos;
    protected int[] codigos_entradas;
    protected int[] porcentajePlateas;


public SedeConPlatea(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores,int[] Capacidad, int[] porcentajeAdicional){
    super(nombre, direccion, capacidadMaxima);


    this.asientos = new int[asientosPorFila*4];
    this.codigos_entradas = new int[asientosPorFila*4];
    this.porcentajeAdicional=porcentajeAdicional;
}
}