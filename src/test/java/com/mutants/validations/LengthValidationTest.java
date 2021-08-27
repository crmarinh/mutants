package com.mutants.validations;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LengthValidationTest {
    @Test
    public void shouldReturnTrue() {
        boolean isValid = new LengthValidation()
                .isValid(Lists.list("GTGCTA","CAGTGC","TGAAGT","AGTGGG","CGCCTA","TCACAT"), null);
        assertTrue(isValid);
    }

    @Test
    public void shouldReturnFalse() {
        boolean isValid = new LengthValidation()
                .isValid(Lists.list("GTGCT","CAGTGC","TGAAGT","AGTGGG","CGCCTA","TCACAT"), null);
        assertFalse(isValid);
    }
}
