package com.esprit.microservice.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
//import javax.xml.ws.Response;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
//import org.omg.CORBA.IntHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.microservice.Entity.ResponsableSiege;
import com.esprit.microservice.Repository.ResponsableSiegeRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ResponsableSiegeService {
    @Autowired
    private ResponsableSiegeRepository ResponsableSiegeRepository;

    public List<ResponsableSiege> getAllReponsable(){
        List<ResponsableSiege> responsables = ResponsableSiegeRepository.findAll();
        return responsables;
    }

    public ResponsableSiege getOneResponsable(Integer id){
        ResponsableSiege ResponsableSiege = ResponsableSiegeRepository.findById(id).orElse(null);
        return ResponsableSiege;
    }

    public ResponsableSiege addResponsableSiege(ResponsableSiege ResponsableSiege) {
        return ResponsableSiegeRepository.save(ResponsableSiege);

    }

    public ResponsableSiege updateResponsableSiege(ResponsableSiege newResponsableSiege) {
        return (ResponsableSiegeRepository.save(newResponsableSiege));

    }

    public String deleteResponsableSiege(int id) {
        if (ResponsableSiegeRepository.findById(id).isPresent()) {
            ResponsableSiegeRepository.deleteById(id);
            return "Responsable supprimé";
        } else
            return "Responsabe non supprimé";
    }


}
