package dev.joseluisgs.models;

import lombok.Data;

@Data
public class Producto {
    private String nombre;
    private double precioUnitario;
    private int cantidad;
    private Categoria categoria;

    public Producto(String nombre, double precioUnitario, int cantidad, Categoria categoria) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public double calcularPrecioTotal() {
        return precioUnitario * cantidad;
    }
}

