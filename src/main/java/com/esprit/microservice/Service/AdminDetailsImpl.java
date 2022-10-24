package com.esprit.microservice.Service;



import com.esprit.microservice.Entity.Abonnement;
import com.esprit.microservice.Entity.ResponsableSiege;
import com.esprit.microservice.Entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class AdminDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int idResponsable;
    private String Prenom ;
    private String Nom ;
    private String username;
    private int idSiege;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public AdminDetailsImpl(int idResponsable, String prenom, String nom, String username, String password,
                            Collection<? extends GrantedAuthority> authorities) {
        this.idResponsable = idResponsable;
        this.Nom = nom;
        this.Prenom = prenom;
        this.username = username;
        this.password = password;
        this.authorities = authorities;

    }

    public static AdminDetailsImpl build(ResponsableSiege responsableSiege) {
        List<GrantedAuthority> authorities = responsableSiege.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new AdminDetailsImpl(
                responsableSiege.getIdResponsable(),
                responsableSiege.getNom(),
                responsableSiege.getPrenom(),
                responsableSiege.getUsername(),
                responsableSiege.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return idResponsable;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AdminDetailsImpl admin = (AdminDetailsImpl) o;
        return Objects.equals(idResponsable, admin.idResponsable);
    }
}
