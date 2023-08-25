package com.pet.auth;

import java.util.Arrays;
import java.util.Locale;

public class Main {


    public static void main(String[] args) {
        String palabra1 = "toga";
        String palabra2 = "gato";

        if(palabra2.matches("[a-zA-ZñÑ ]")){
            System.out.println("si");
        }

        if(palabra2.matches("(.*)gato(.*)")){
            System.out.println("gato");
        }

        palabra2 = palabra2.substring(0,1).toUpperCase() + palabra2.substring(1);


        char  palabras1 [] =palabra1.toLowerCase().toCharArray();
        char  palabras2 [] = palabra2.toLowerCase().toCharArray();

        Arrays.sort(palabras1);
        Arrays.sort(palabras2);


        if(Arrays.equals(palabras1,palabras2)){
            System.out.println("Siiii son iguales");
        }





    }
}
