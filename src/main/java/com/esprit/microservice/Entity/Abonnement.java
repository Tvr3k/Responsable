package com.esprit.microservice.Entity;


import lombok.*;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Abonnement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAbonnement;

    @Enumerated(EnumType.STRING)
    private TypeAbonnement TypeAbonnement;

    @Enumerated(EnumType.STRING)
    private GammeVoiture GammeVoiture;



    @ManyToOne
    private ResponsableSiege ResponsableSiege;

}
