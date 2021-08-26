package com.mutants.services;

import com.mutants.dtos.Stats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMutantsServices {
    void isMutant(List<String> adn);
    Stats stats();
}
