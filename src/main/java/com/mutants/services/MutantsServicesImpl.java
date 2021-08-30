package com.mutants.services;

import com.mutants.dtos.StatsDTO;
import com.mutants.entities.Adn;
import com.mutants.exceptions.MutantException;
import com.mutants.repositories.IMutantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class MutantsServicesImpl implements IMutantsServices {

    private final IMutantRepository mutantRepository;
    private final List<String> base = Arrays.asList("AAAA", "TTTT", "GGGG", "CCCC");

    @Override
    public void isMutant(List<String> adn) {

        var sequence = String.join("-", adn);
        var adnFound = mutantRepository.findByAdnSec(sequence);

        if(adnFound.isEmpty()) {
            if(this.mutantSearch(0,adn,0)){
                mutantRepository.save(new Adn(sequence, false));
                throw new MutantException();
            }
            mutantRepository.save(new Adn(sequence, true));
        } else {
            if(!adnFound.get(0).isHuman()) {
                throw new MutantException();
            }
        }
    }

    private boolean mutantSearch(Integer acc, List<String> adn, Integer transformationNumber) {
        if (transformationNumber < 0 || transformationNumber > 3) {
            return false;
        }
        acc += this.getSizeList(adn, transformationNumber + 1);
        return acc > 1 || mutantSearch(acc, adn, transformationNumber + 1);
    }

    private Integer getSizeList(List<String> adn,Integer transformationNumber) {
        switch (transformationNumber) {
            case 1:
                return horizontalSearch(getMajorDiagonals(adn)).size();
            case 2:
                return horizontalSearch(adn).size();
            case 3:
                return horizontalSearch(convertToTranspose(adn)).size();
            default:
                return 0;
        }
    }

    @Override
    public StatsDTO stats() {
        var allBeings = mutantRepository.findAll();
        var numberOfHumans =  allBeings.stream().filter(Adn::isHuman).collect(Collectors.toList()).size();
        var numberOfMutants = allBeings.size() - numberOfHumans;
        return new StatsDTO(numberOfMutants, numberOfHumans);
    }

    private List<String> horizontalSearch(List<String> adn) {
        return base.stream()
                .filter(b -> adn.stream()
                        .anyMatch(i -> i.contains(b)))
                .collect(Collectors.toList());
    }


    private List<String> convertToTranspose(List<String> matrix) {
        return IntStream.range(0, matrix.size())
                    .mapToObj(index -> matrix.stream()
                                .reduce("", (acc, next) -> acc.concat(String.valueOf(next.charAt(index)))))
                    .collect(Collectors.toList());
    }

    private List<String> getMajorDiagonals(List<String> matrix) {
        return Arrays.asList(this.getDiagonalLeftRight(matrix), this.getDiagonalRightLeft(matrix));
    }

    private String getDiagonalLeftRight(List<String> matrix) {
        return this.rangeToString(matrix.size())
                .reduce("", (acc, next) -> {
                    var index = Integer.parseInt(next);
                    return acc.concat(String.valueOf(matrix.get(index).charAt(index)));
                });
    }

    private String getDiagonalRightLeft(List<String> matrix) {
        // Se invierte la matriz con el objetivo de buscar la diagonar de derecha a izquierda
        List<String> inverted = matrix.stream()
                .sorted(Collections.reverseOrder(String::lastIndexOf))
                .collect(Collectors.toList());

        return this.rangeToString(matrix.size())
                .sorted(Collections.reverseOrder())
                .reduce("", (acc, next) -> this.getDiagonal(acc,next,inverted));
    }

    private Stream<String> rangeToString(Integer endExclusive) {
        return IntStream.range(0, endExclusive)
                .mapToObj(String::valueOf);
    }

    private String getDiagonal(String acc, String next, List<String> matrix) {
        var index = Integer.parseInt(next);
        return acc.concat(String.valueOf(matrix.get(index).charAt(index)));
    }

}
