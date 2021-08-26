package com.mutants.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Adn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adnSec;
    private boolean isHuman;

    public Adn(String adnSec, boolean isHuman) {
        this.adnSec = adnSec;
        this.isHuman = isHuman;
    }
}
