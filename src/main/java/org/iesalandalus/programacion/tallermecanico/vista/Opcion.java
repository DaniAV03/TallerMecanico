package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Opcion {
    INSERTAR_CLIENTE("Insertar el cliente", 1),
    BUSCAR_CLIENTE("Buscar el cliente", 2),
    BORRAR_CLIENTE("Borra el cliente", 3),
    LISTAR_CLIENTES("Listar los clientes", 4),
    MODIFICAR_CLIENTE("Modificar los  Clientes", 5),
    INSERTAR_VEHICULO("Insertar el vehiculo", 6),
    BUSCAR_VEHICULO("Buscar el vehiculo", 7),
    BORRAR_VEHICULO("Borrar el  vehiculo", 8),
    LISTAR_VEHICULOS("Listar los vehiculos", 9),
    INSERTAR_REVISION("Insertar la revision", 10),
    BUSCAR_REVISION("Buscar la revision", 11),
    BORRAR_REVISION("Borrar la revision", 12),
    LISTAR_REVISIONES("Listar las revisiones", 13),
    LISTAR_REVISIONES_CLIENTES("Listar las revisiones según los clientes", 14),
    LISTAR_REVISIONES_VEHICULOS("Listar las revisiones según los vehiculos", 15),
    ANADIR_HORAS_REVISION("Añadir horas según la revision ", 16),
    ANADIR_PRECIO_MATERIAL_REVISON("Añadir precio al material según la revision ", 17),
    CERRAR_REVISION("Cerrar la revision", 18),
    SALIR("Salir", 19);


    private String nombre;
    private Integer numeroEscogido;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();

    static {
        for (Opcion opcion : values()) {
            opciones.put(opcion.numeroEscogido, opcion);
        }
    }

    private Opcion(String nombre, Integer opcion) {
        this.nombre = nombre;
        this.numeroEscogido = opcion;
    }

    public static boolean esValida(int numeroOpcion) {
        Objects.requireNonNull(numeroOpcion, "El numero no es valido");
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("Opción no válida");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s = %s", this.nombre, this.numeroEscogido);
    }
}
