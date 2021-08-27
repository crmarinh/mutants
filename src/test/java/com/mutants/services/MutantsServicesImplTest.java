package com.mutants.services;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import com.mutants.dtos.StatsDTO;
import com.mutants.entities.Adn;
import com.mutants.exceptions.MutantException;
import com.mutants.repositories.IMutantRepository;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MutantsServicesImplTest {

    private final List<String> mutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    private final List<String> human = Arrays.asList("GTGCTA","CAGTGC","TGAAGT","AGTGGG","CGCCTA","TCACAT");

    @InjectMocks
    private MutantsServicesImpl mutantsServices;

    @Mock
    private IMutantRepository iMutantRepository;

    @Test
    public void shouldReturnThatItIsMutantWithADNPersist() {
        String sequence = String.join("-", mutant);
        when(iMutantRepository.findByAdnSec(sequence)).thenReturn(List.of(new Adn(sequence, false)));
        assertThrows(
                MutantException.class,
                () -> mutantsServices.isMutant(mutant),
                "Expected isMutant() to throw, but it didn't");
    }

    @Test
    public void shouldReturnThatItIsHuman() {
        String sequence = String.join("-", human);
        when(iMutantRepository.findByAdnSec(sequence)).thenReturn(List.of());
        mutantsServices.isMutant(human);
    }

    @Test
    public void shouldReturnThatItIsMutant() {
        this.mutantTest(mutant);
    }

    @Test
    public void shouldReturnStatsEmpty() {
        when(iMutantRepository.findAll()).thenReturn(List.of());
        StatsDTO stats = mutantsServices.stats();
        assertEquals(stats.getCount_human_dna(), 0);
        assertEquals(stats.getCount_mutant_dna(), 0);
        assertEquals(stats.getRatio(), 1.0);
    }

    @Test
    public void mutantWithTwoHorizontalSequences() {
        List<String> mutantTwoRow = Arrays.asList("GTGCTA","CAGTGC","TGAAGT","AGTGGG","CGCCCC","TCAAAA");
        this.mutantTest(mutantTwoRow);
    }

    @Test
    public void mutantWithTwoDiagonalsSequences() {
        List<String> mutantTwoDiagonal = Arrays.asList("ATGCTG","CAGTGC","TGAGGT","AGGAGG","CGCCTA","TCACAT");
        this.mutantTest(mutantTwoDiagonal);
    }

    private void mutantTest(List<String> adn) {
        String sequence = String.join("-", adn);
        when(iMutantRepository.findByAdnSec(sequence)).thenReturn(List.of());
        assertThrows(
                MutantException.class,
                () -> mutantsServices.isMutant(adn),
                "Expected isMutant() to throw, but it didn't");
    }

}
