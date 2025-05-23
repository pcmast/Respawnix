package com.proyecto3evaluacion.respawnix.Interfaces;

import java.util.Map;

public interface CestaCompraInterfaz<T> {


    void annadir(T elemento, int cantidad);
    void eliminar(T elemento, int cantidad);
    void eliminarDescuento(T elemento, String email);
    Map<String, Integer> todaCesta();
    void actualizar(T elemento);
}
