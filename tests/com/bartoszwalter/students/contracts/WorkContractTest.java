package com.bartoszwalter.students.contracts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkContractTest {

    @Test
    void calculateContract() {
        WorkContract workContract = new WorkContract(1000);
        assertEquals(862.9,Contract.wynagrodzenie);
    }

}