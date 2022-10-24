package com.esprit.microservice.Controller;


import com.esprit.microservice.Entity.ResponsableSiege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.microservice.Entity.Abonnement;
import com.esprit.microservice.Service.AbonnementService;

import java.util.List;


@RequestMapping("/resp/abonnement")
@RestController
public class AbonnementRESTAPI {

    @Autowired
    AbonnementService AbonnementService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Abonnement> getAbonnements(){
        List<Abonnement> abonnements = AbonnementService.getAbonnements();
        System.out.println(abonnements);
        return abonnements;
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public Abonnement getOneAbonnement(@PathVariable("id") Integer idAbonnement){
        return AbonnementService.getOneAbonnement(idAbonnement);
    }

    @PostMapping("/add")	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Abonnement> createAbonnement(@RequestBody Abonnement Abonnement) {
        return new ResponseEntity<>(AbonnementService.addAbonnement(Abonnement), HttpStatus.OK) ;
    }



    @PutMapping("/updateAbo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Abonnement> updateAbonnement(@RequestBody Abonnement Abonnement){
        return new ResponseEntity<>(AbonnementService.updateAbonnement(Abonnement),HttpStatus.OK);
    }



    @DeleteMapping(value="/DeleteAbo/{idAbonnement}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteAbonnement(@PathVariable(value="idAbonnement") int  idAbonnement){
        return new ResponseEntity<>(AbonnementService.deleteAbonnement(idAbonnement),HttpStatus.OK);

    }
}
