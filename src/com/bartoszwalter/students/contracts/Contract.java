package com.bartoszwalter.students.contracts;

public class Contract {
    private Contract contract;

    public Contract() {}

    public Contract(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
