package com.example.lab8_20176054.Repository;

import com.example.lab8_20176054.Entity.usuario;
import com.example.lab8_20176054.dto.cantTicketxEmpresa;
import com.example.lab8_20176054.dto.cantTicketxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<usuario,Integer> {



    @Query(value = "select count(correo) as cantidad, correo from lahaces.usuario u  left join lahaces.ticket t on t.idusuario = u.idusuario group by correo\n", nativeQuery = true)
    List<cantTicketxUser> listaCantidadTicketsxUsuario();


    @Query(value = "with tb1 as (\n" +
            "select t.*, tte.idevento from ticket t left join tipo_ticket_evento tte on t.id = tte.id)\n" +
            ",tb2 as(\n" +
            "select tb1.*,e.idlocal from tb1 left join evento e on tb1.idevento = e.id)\n" +
            ",tb3 as(\n" +
            "select tb2.*,l.idempresa from tb2 left join lahaces.local l on tb2.idlocal = l.id)\n" +
            ",tb4 as(\n" +
            "select tb3.*, emp.nombre from tb3 left join empresa emp on emp.id = tb3.id)\n" +
            ",tb5 as(\n" +
            "select * from tb4 where nombre is not null)\n" +
            "select count(nombre) as cantidad, nombre as nombreempresa from tb5 group by nombre", nativeQuery = true)
    List<cantTicketxEmpresa> listaCantidadTicketsxEmpresa();
}
