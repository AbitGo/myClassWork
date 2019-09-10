package com.company;

public class Exp1_5_2 {
    public static void main(String[] args) {
        int chicken_male=0;
        int chicken_famale = 0;
        int chicken_chil = 3;
        for(chicken_male=0;chicken_male <=20;chicken_male++){
            for(chicken_chil=0;chicken_chil <= 100-chicken_male;chicken_chil+=3){
                chicken_famale  = 100 - chicken_male - chicken_chil;
                int sum = chicken_male*5 + chicken_famale*3 +chicken_chil/3;
                if(sum == 100)
                {
                    System.out.println("chicken_male: "+chicken_male+" chicken_famale: "+chicken_famale+" chicken_chil: "+chicken_chil);
                }
            }
        }
    }
}
