package com.example.lab8_20176054.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;



@JsonSerialize
@Getter
@Setter
@Entity
@Table(name = "evento")
public class evento {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private Date fecha;
    private String nombre;
    private String descripcion;
    private String path_image;


    @ManyToOne
    @JoinColumn(name = "idlocal")
    private local local;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }


}
