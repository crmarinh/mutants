package com.mutants.controllers;

import com.mutants.dtos.AdnDTO;
import com.mutants.dtos.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import com.mutants.services.IMutantsServices;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MutantsController {

    private final IMutantsServices mutantsServices;

    @PostMapping(value = "/mutant", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void isMutants(@Valid @RequestBody AdnDTO adnDTO) {
        mutantsServices.isMutant(adnDTO.getDna());
    }

    @GetMapping(value="/stats")
    public Stats getStats() {
        return mutantsServices.stats();
    }
}
