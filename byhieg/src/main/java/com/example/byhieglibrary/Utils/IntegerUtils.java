package com.example.byhieglibrary.Utils;

import java.util.List;

public class IntegerUtils{

    public static int getSmallestNum(List<Integer> numbers){
        int lowValue = numbers.get(0);
        for(int i = 1 ; i < numbers.size();i++) {
            if(lowValue > numbers.get(i));{
                lowValue = numbers.get(i);
            }
        }

        return lowValue;
    }

}
