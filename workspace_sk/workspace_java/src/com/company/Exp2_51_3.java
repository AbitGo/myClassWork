package com.company;

public class Exp2_51_3 {

    public static void main(String[] args) {
        for(int i = 1;i<=12;i++){
            switch (i){
                case 2:{
                    System.out.println(i+"月是:"+" 28 ");
                    break;
                }
                case 4: case 6:case 9:case 11:{
                    System.out.println(i+"月是:"+" 30 ");
                    break;
                }
                default:{
                    System.out.println(i+"月是:"+" 31 ");
                    break;
                }

            }
        }
    }
}
