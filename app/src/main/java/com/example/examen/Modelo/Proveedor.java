package com.example.examen.Modelo;

public class Proveedor {
    private String Ruc;
    private String Nombre_Comercial;
    private String Representante_Legal;
    private String Direccion;
    private String Telefono;
    private String Producto;
    private String credito;

    public Proveedor() {
    }

    public Proveedor(String ruc, String nombre_Comercial, String representante_Legal, String direccion, String telefono, String producto, String credito) {
        Ruc = ruc;
        Nombre_Comercial = nombre_Comercial;
        Representante_Legal = representante_Legal;
        Direccion = direccion;
        Telefono = telefono;
        Producto = producto;
        this.credito = credito;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getNombre_Comercial() {
        return Nombre_Comercial;
    }

    public void setNombre_Comercial(String nombre_Comercial) {
        Nombre_Comercial = nombre_Comercial;
    }

    public String getRepresentante_Legal() {
        return Representante_Legal;
    }

    public void setRepresentante_Legal(String representante_Legal) {
        Representante_Legal = representante_Legal;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }
}
