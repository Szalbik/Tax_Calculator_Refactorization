package com.bartoszwalter.students.contractFactory;

import com.bartoszwalter.students.contracts.CivilContract;
import com.bartoszwalter.students.contracts.Contract;
import com.bartoszwalter.students.contracts.WorkContract;

public class Factory {

    public static Contract createContract(double salary, char type){

        if(type == 'P'){
            return new WorkContract(salary);
        }

        else if  (type == 'Z'){
            return new CivilContract(salary);
        }
        else return null;

    }

}
