package com.proyecto3evaluacion.respawnix.utils;

public class Utilidades {

    public static boolean validarNumero(String texto){
        String regex = "^[0-9]+$";

        return texto.matches(regex);
    }




}
