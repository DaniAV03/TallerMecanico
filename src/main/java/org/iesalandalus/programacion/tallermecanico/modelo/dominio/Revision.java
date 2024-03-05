package org.iesalandalus.programacion.tallermecanico.modelo.dominio;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;


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
      super(revision.cliente,revision.vehiculo,revision.fechaInicio);
      if (revision == null) {
      throw new NullPointerException("La revisión no puede ser nula.");
    }
    this.cliente = new Cliente(revision.cliente);
    this.vehiculo = revision.vehiculo;
    this.fechaInicio = revision.fechaInicio;
    this.fechafin = revision.fechafin;
    this.horas = revision.horas;
    this.precioMaterial = revision.precioMaterial;
  }
  @Override
  public String toString() {
    String fechaInicioString = fechaInicio != null ? fechaInicio.format(Revision.FORMATO_FECHA) : "";
    String fechaFinString = fechafin != null ? fechafin.format(Revision.FORMATO_FECHA) : "";
    String horasString = String.valueOf(horas);
    String precioMaterialString = String.format("%.2f", precioMaterial);
    String precioString = getPrecio() != 0.0 ? String.format(", %.2f € total", getPrecio()) : "";
    return cliente + " - " + vehiculo + ": (" + fechaInicioString + " - " + fechaFinString + "), " +
            horasString + " horas, " + precioMaterialString + " € en material" + precioString;
  }



}
