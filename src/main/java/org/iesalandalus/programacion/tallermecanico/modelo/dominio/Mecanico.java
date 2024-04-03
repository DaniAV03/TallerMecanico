package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
public class Mecanico extends Trabajo {

    private static final float FACTOR_HORA = 30f;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5f;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente,vehiculo,fechaInicio);
    }
    public float getPrecioEspecifico() {
        return this.horas * FACTOR_HORA + this.precioMaterial * FACTOR_PRECIO_MATERIAL;
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico.cliente,mecanico.vehiculo,mecanico.fechaInicio);
        if (mecanico == null) {
            throw new NullPointerException("El trabajo no puede ser nulo.");
        }
        this.cliente = new Cliente(mecanico.cliente);
        this.fechafin = mecanico.fechafin;
        this.horas = mecanico.getHoras();
        this.precioMaterial = mecanico.getPrecioMaterial();
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }
    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (fechafin != null) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        this.precioMaterial += precioMaterial;
    }
        @Override
        public String toString() {
            String fechaInicioString = fechaInicio != null ? fechaInicio.format(org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision.FORMATO_FECHA) : "";
            String fechaFinString = fechafin != null ? fechafin.format(org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision.FORMATO_FECHA) : "";
            String horasString = String.valueOf(horas);
            String precioMaterialString = String.format("%.2f", precioMaterial);
            String precioString = getPrecio() != 0.0 ? String.format(", %.2f € total", getPrecio()) : "";
            return "Mecánico -> "+ cliente + " - " + vehiculo + " (" + fechaInicioString + " - " + fechaFinString + "): " +
                    horasString + " horas, " + precioMaterialString + " € en material" + precioString;
        }
    }

