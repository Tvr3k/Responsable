package com.esprit.microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.microservice.Entity.Abonnement;


public interface AbonnementRepository extends JpaRepository <Abonnement,Integer>{
}
