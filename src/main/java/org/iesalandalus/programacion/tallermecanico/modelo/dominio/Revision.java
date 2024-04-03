package org.iesalandalus.programacion.tallermecanico.modelo.dominio;
import java.time.LocalDate;


public class Revision extends Trabajo {
  private static final float FACTOR_HORA = 35f;

  public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
    super(cliente,vehiculo,fechaInicio);
  }


  public float getPrecioEspecifico() {
    return horas * FACTOR_HORA;
  }

  public Revision(Revision revision) {
    super(Trabajo.cliente,Trabajo.vehiculo,Trabajo.fechaInicio);
    if (revision == null) {
      throw new NullPointerException("El trabajo no puede ser nulo.");
    }
    this.cliente = new Cliente(revision.cliente);
    this.fechafin = revision.fechafin;
    this.horas = revision.horas;
  }
  @Override
  public String toString() {
    String fechaInicioString = fechaInicio != null ? fechaInicio.format(Revision.FORMATO_FECHA) : "";
    String fechaFinString = fechafin != null ? fechafin.format(Revision.FORMATO_FECHA) : "";
    String horasString = String.valueOf(horas);
    String precioString = getPrecio() != 0.0 ? String.format(", %.2f € total", getPrecio()) : "";
    return "Revisión -> " + cliente + " - " + vehiculo + " (" + fechaInicioString + " - " + fechaFinString + "): " +
            horasString + " horas" + precioString;
  }
}

