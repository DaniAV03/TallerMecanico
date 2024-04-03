package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(1, "Insertar cliente."),
    BUSCAR_CLIENTE(2, "Buscar cliente."),
    BORRAR_CLIENTE(3, "Borrar cliente."),
    LISTAR_CLIENTES(4, "Listar clientes."),
    MODIFICAR_CLIENTE(5, "Modificar cliente."),
    INSERTAR_VEHICULO(6, "Insertar vehículo."),
    BUSCAR_VEHICULO(7, "Buscar vehículo."),
    BORRAR_VEHICULO(8, "Borrar vehículo."),
    LISTAR_VEHICULOS(9, "Listar vehículos."),
    INSERTAR_REVISION(10, "Insertar trabajo de revisión."),
    INSERTAR_MECANICO(11, "Insertar trabajo mecánico."),
    BUSCAR_TRABAJO(12, "Buscar trabajo."),
    BORRAR_TRABAJO(13, "Borrar trabajo."),
    LISTAR_TRABAJOS(14, "Listar trabajos."),
    LISTAR_TRABAJOS_CLIENTE(15, "Listar trabajos de un cliente."),
    LISTAR_TRABAJOS_VEHICULO(16, "Listar trabajos de un vehículo."),
    ANADIR_HORAS_TRABAJO(17, "Añadir horas a un trabajo."),
    ANADIR_PRECIO_MATERIAL_TRABAJO(18, "Añadir precio del material a un trabajo."),
    CERRAR_TRABAJO(19, "Cerrar trabajo."),
    SALIR(20, "Salir.");

    private final int codigo;
    private final String texto;
    private static final Map<Integer, Evento> eventos = new HashMap<>();

    static {
        for (Evento evento : values()) {
            eventos.put(evento.codigo, evento);
        }
    }

    private Evento(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {
        return codigo;
    }

    public static boolean esValido(int codigo) {
        return eventos.containsKey(codigo);
    }

    public static Evento get(int codigo) {
        if (!esValido(codigo)) {
            throw new IllegalArgumentException("El código no es correcto.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return texto;
    }
}
