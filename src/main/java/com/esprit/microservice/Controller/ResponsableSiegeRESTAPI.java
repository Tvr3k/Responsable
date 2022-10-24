package com.esprit.microservice.Controller;

//import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.microservice.Entity.ResponsableSiege;
import com.esprit.microservice.Service.ResponsableSiegeService;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;

@RequestMapping("/resp/responsable")
@RestController
public class ResponsableSiegeRESTAPI {

    @Autowired
    ResponsableSiegeService ResponsableSiegeService;



    @PostMapping("/addResp")	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponsableSiege> createResponsableSiege(@RequestBody ResponsableSiege ResponsableSiege) {
        return new ResponseEntity<>(ResponsableSiegeService.addResponsableSiege(ResponsableSiege), HttpStatus.OK) ;

    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<ResponsableSiege> getResponsables(){
        List<ResponsableSiege> responsables = ResponsableSiegeService.getAllReponsable();
        System.out.println(responsables);
        return responsables;
    }

    @GetMapping("/getOne/{id}")
    @ResponseBody
    public ResponsableSiege getOneResponsable(@PathVariable("id") Integer idResponsable){
        return ResponsableSiegeService.getOneResponsable(idResponsable);
    }

    @PutMapping("/updateResp/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponsableSiege> updateResponsableSiege(@RequestBody ResponsableSiege ResponsableSiege){
        return new ResponseEntity<>(ResponsableSiegeService.updateResponsableSiege(ResponsableSiege),HttpStatus.OK);
    }



    @DeleteMapping(value="/DeleteID/{idResponsable}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteResponsableSiege(@PathVariable(value="idResponsable") int  idResponsable){
        return new ResponseEntity<>(ResponsableSiegeService.deleteResponsableSiege(idResponsable),HttpStatus.OK);

    }
}
