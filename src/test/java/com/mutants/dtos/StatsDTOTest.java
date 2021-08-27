package com.mutants.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsDTOTest {

    @Test
    public void getRatioShouldReturnADecimalValue() {
        double ratio = new StatsDTO(1,2).getRatio();
        assertEquals(ratio, 0.5);
    }

    @Test
    public void getRatioShouldReturnADecimalDefaultValue() {
        double ratio = new StatsDTO(1,0).getRatio();
        assertEquals(ratio, 1.0);
    }
}
