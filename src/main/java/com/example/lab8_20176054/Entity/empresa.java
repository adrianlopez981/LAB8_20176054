package com.example.lab8_20176054.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class empresa {

    @Id
    private int id;
    private String nombre;
    private String ruc;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
