package com.example.lab8_20176054.dto;

import lombok.Getter;
import lombok.Setter;
import com.example.lab8_20176054.Entity.evento;

@Getter
@Setter
public class EventoDto {
    private String result;
    private String msg;
    private evento evento;
}
