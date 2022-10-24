package com.esprit.microservice.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ResponsableSiege implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResponsable;
    private String Prenom ;
    private String Nom ;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="ResponsableSiege")
    private Set<Abonnement> Abonnements;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "utilisateur_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public ResponsableSiege(int idResponsable, String prénom, String nom, String username, List<String> role) {
        this.idResponsable = idResponsable;
        this.Prenom = prénom;
        this.Nom = nom;
        this.username = username;
        this.role = role;
    }

    @Transient
    private List<String> role;

    /*
    public int getIdResponsable() {
        return idResponsable;
    }
    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }
    public String getPrénom() {
        return Prénom;
    }
    public void setPrénom(String prénom) {
        Prénom = prénom;
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public int getIdSiege() {
        return idSiege;
    }
    public void setIdSiege(int idSiege) {
        this.idSiege = idSiege;
    }


     */
}
