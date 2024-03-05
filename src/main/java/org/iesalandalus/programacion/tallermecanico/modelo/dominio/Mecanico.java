package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class Mecanico {
    public class Revision extends Trabajo {
        private static final float PRECIO_HORA = 30;
        private static final double PRECIO_MATERIAL = 1.5;
        private float precioMaterial;

        public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
            super(cliente,vehiculo,fechaInicio);
            setCliente(cliente);
            setVehiculo(vehiculo);
            setFechaInicio(fechaInicio);
        }

        public Revision(Revision revision) {
            super(revision.cliente,revision.vehiculo,revision.fechaInicio,revision.fechafin,revision.horas);
            if (revision == null) {
                throw new NullPointerException("La revisión no puede ser nula.");
            }
            this.cliente = new Cliente(revision.cliente);
            this.vehiculo = revision.vehiculo;
            this.fechaInicio = revision.fechaInicio;
            this.fechafin = revision.fechafin;
            this.horas = revision.horas;
            this.precioMaterial = revision.getPrecioMaterial();
        }

        public float getPrecioMaterial() {
            return precioMaterial;
        }

        public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
            if (precioMaterial <= 0) {
                throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
            }
            if (fechafin != null) {
                throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
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
            return cliente + " - " + vehiculo + ": (" + fechaInicioString + " - " + fechaFinString + "), " +
                    horasString + " horas, " + precioMaterialString + " € en material" + precioString;
        }



    }

}
