package com.esprit.microservice.Service;

import com.esprit.microservice.Entity.Abonnement;
import com.esprit.microservice.Entity.ResponsableSiege;
import com.esprit.microservice.Repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.microservice.Entity.Abonnement;
import com.esprit.microservice.Repository.AbonnementRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


@Service
public class AbonnementService {
    @Autowired
    private AbonnementRepository AbonnementRepository;

    public List<Abonnement> getAbonnements(){
        List<Abonnement> abonnements = AbonnementRepository.findAll();
        return abonnements;
    }

    public Abonnement getOneAbonnement(Integer id) {
        Abonnement Abonnement = AbonnementRepository.findById(id).orElse(null);
        return Abonnement;
    }

    public Abonnement addAbonnement(Abonnement Abonnement) {
        return AbonnementRepository.save(Abonnement);

    }

    public Abonnement updateAbonnement(Abonnement newAbonnement) {
        return (AbonnementRepository.save(newAbonnement));

    }

    public String deleteAbonnement(int id) {
        if (AbonnementRepository.findById(id).isPresent()) {
            AbonnementRepository.deleteById(id);
            return "Abonnement supprimé";
        } else
            return "Abonnement non supprimé";
    }


}
