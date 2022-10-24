package com.esprit.microservice.Controller;

import com.esprit.microservice.Entity.ResponsableSiege;
import com.esprit.microservice.Entity.ERole;
import com.esprit.microservice.Entity.Role;
import com.esprit.microservice.Repository.ResponsableSiegeRepository;
import com.esprit.microservice.Repository.RoleRepository;
import com.esprit.microservice.security.jwt.JwtUtils;
import com.esprit.microservice.Service.AdminDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/resp/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  ResponsableSiegeRepository responsabeSiegeRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody ResponsableSiege loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    AdminDetailsImpl adminDetails = (AdminDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(adminDetails);

    List<String> roles = adminDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());


      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
              .body(new ResponsableSiege(adminDetails.getId(),
                      adminDetails.getNom(),
                      adminDetails.getPrenom(),
                      adminDetails.getUsername(),
                      roles));

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody ResponsableSiege signUpRequest) {
    if (responsabeSiegeRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body("Error: Username is already taken!");
    }

    // Create new ResponsableSiege's account
    ResponsableSiege ResponsableSiege = new ResponsableSiege();
    ResponsableSiege.setUsername( signUpRequest.getUsername());
    ResponsableSiege.setPassword( encoder.encode(signUpRequest.getPassword()));
    ResponsableSiege.setPrenom( signUpRequest.getPrenom());
    ResponsableSiege.setNom( signUpRequest.getNom());



    List<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      throw new ResponseStatusException(
              HttpStatus.NOT_ACCEPTABLE, "Empty");


    } else {
      strRoles.forEach(role -> {
        if(role.equals("ROLE_RESPONSABLE"))
            {
               Role adminRole = roleRepository.findByName(ERole.ROLE_RESPONSABLE)
                     .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
               roles.add(adminRole);
            }
        else{
          throw new ResponseStatusException(
                  HttpStatus.NOT_ACCEPTABLE, "Erreur : Vous n'avez pas l'authorisation pour accéder à cette page");
        }


      });
    }

    ResponsableSiege.setRoles(roles);
    responsabeSiegeRepository.save(ResponsableSiege);

    return ResponseEntity.ok("User registered successfully!");
  }


  @PostMapping("/signout")
  public ResponseEntity<?> logoutUtilisateur() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("You've been signed out!");
  }
}
