package com.mutants.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    private double count_mutant_dna;
    private double count_human_dna;
    public double getRatio() {
        DecimalFormat df2 = new DecimalFormat("#.#");
        if(count_human_dna != 0) {
            return Double.parseDouble(df2.format(count_mutant_dna / count_human_dna).replace(",","."));
        }
        return 1.0;
    }
}
