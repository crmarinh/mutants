package com.mutants.repositories;

import com.mutants.entities.Adn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMutantRepository extends JpaRepository<Adn, Long> {

    List<Adn> findByAdnSec(String AndSec);
}