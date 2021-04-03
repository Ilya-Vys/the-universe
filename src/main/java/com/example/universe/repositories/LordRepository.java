package com.example.universe.repositories;

import com.example.universe.entities.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {

    @Query
    List<Lord> findTop10ByOrderByAge();

    @Query
    List<Lord> getAllByPlanetsIsNull();
}
