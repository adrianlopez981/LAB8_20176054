package com.example.lab8_20176054.Repository;

import com.example.lab8_20176054.Entity.tipoticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoTicketRepository extends JpaRepository<tipoticket,Integer> {


}
