package com.proyecto3evaluacion.respawnix.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {


    //Metodo que hace un hash de una contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //Metodo que verifica si las contraseñas coinciden
    public static boolean verificarPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
