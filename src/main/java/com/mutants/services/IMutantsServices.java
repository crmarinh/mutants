package com.mutants.services;

import com.mutants.dtos.StatsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMutantsServices {
    void isMutant(List<String> adn);
    StatsDTO stats();
}
