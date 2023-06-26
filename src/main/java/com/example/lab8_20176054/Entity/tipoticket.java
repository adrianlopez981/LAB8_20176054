package com.example.lab8_20176054.Entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@Getter
@Setter
@Entity
@Table(name = "tipo_ticket_evento")
public class tipoticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int precio;


    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "idevento")
    private evento evento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public com.example.lab8_20176054.Entity.evento getEvento() {
        return evento;
    }

    public void setEvento(com.example.lab8_20176054.Entity.evento evento) {
        this.evento = evento;
    }
}
