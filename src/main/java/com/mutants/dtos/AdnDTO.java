package com.mutants.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AdnDTO {
    @NotEmpty
    @NotNull
    public List<String> dna;
}
