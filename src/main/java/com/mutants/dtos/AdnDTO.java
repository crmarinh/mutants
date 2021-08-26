package com.mutants.dtos;

import com.mutants.validations.Length;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class AdnDTO {
    @NotEmpty
    @NotNull
    @Length
    public List<@Pattern(regexp = "(A|T|C|G)+", message = "solo se permiten las letras A,T,C o G")
            String> dna;
}
