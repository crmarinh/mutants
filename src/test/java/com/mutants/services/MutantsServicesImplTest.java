package com.mutants.services;

import com.mutants.dtos.StatsDTO;
import com.mutants.entities.Adn;
import com.mutants.exceptions.MutantException;
import com.mutants.repositories.IMutantRepository;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MutantsServicesImplTest {

    private final List<String> mutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private final List<String> human = Arrays.asList("GTGCTA","CAGTGC","TGAAGT","AGTGGG","CGCCTA","TCACAT");

    @InjectMocks
    private MutantsServicesImpl mutantsServices;

    @Mock
    private IMutantRepository iMutantRepository;

    @Test(expected = MutantException.class)
    public void shouldReturnThatItIsMutantWithADNPersist() {
        String sequence = String.join("-", mutant);
        when(iMutantRepository.findByAdnSec(sequence)).thenReturn(List.of(new Adn(sequence, false)));
        mutantsServices.isMutant(mutant);
    }

    @Test
    public void shouldReturnThatItIsHuman() {
        String sequence = String.join("-", human);
        when(iMutantRepository.findByAdnSec(sequence)).thenReturn(List.of());
        mutantsServices.isMutant(human);
    }

    @Test(expected = MutantException.class)
    public void shouldReturnThatItIsMutant() {
        String sequence = String.join("-", mutant);
        when(iMutantRepository.findByAdnSec(sequence)).thenReturn(List.of());
        mutantsServices.isMutant(mutant);
    }

    @Test
    public void shouldReturnStatsEmpty() {
        when(iMutantRepository.findAll()).thenReturn(List.of());
        StatsDTO stats = mutantsServices.stats();
        assertEquals(stats.getCount_human_dna(), 0);
        assertEquals(stats.getCount_mutant_dna(), 0);
        assertEquals(stats.getRatio(), 1.0);
    }
}
