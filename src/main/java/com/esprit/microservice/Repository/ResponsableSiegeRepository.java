package com.esprit.microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.microservice.Entity.ResponsableSiege;

import java.util.Optional;

public interface ResponsableSiegeRepository extends JpaRepository <ResponsableSiege,Integer>{


    Optional<ResponsableSiege> findByUsername(String username);

    Boolean existsByUsername(String username);


}
