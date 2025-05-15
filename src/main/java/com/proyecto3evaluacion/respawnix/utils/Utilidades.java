package com.proyecto3evaluacion.respawnix.utils;

public class Utilidades {

    public static boolean validarNumero(String texto){
        String regex = "^[0-9]+$";

        return texto.matches(regex);
    }

    public static boolean validarCorreo(String correo)  {
        boolean valido = false;
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (correo.matches(regex)) {
            valido = true;
        }

        return valido;
    }


}
