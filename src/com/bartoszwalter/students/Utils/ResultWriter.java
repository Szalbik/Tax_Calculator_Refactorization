package com.bartoszwalter.students.Utils;

import com.bartoszwalter.students.contracts.Contract;
import java.util.Map;

public class ResultWriter {

    public static void printResult() {

        for(Map.Entry<String, String> entry : Contract.calculatedContract.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }

    }
}





