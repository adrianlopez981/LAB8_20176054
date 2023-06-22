package com.example.lab8_20176054.Repository;

import com.example.lab8_20176054.Entity.evento;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventoRepository extends JpaRepository<evento,Integer> {
}
