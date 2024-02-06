package ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ReciboRecarga {
	
	@Id
	@GeneratedValue
	private int codigo;
	
	private String telefono;
	private Date fecha;
	private String estado;
	private double monto;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	@Override
	public String toString() {
		return "ReciboRecarga [codigo=" + codigo + ", telefono=" + telefono + ", fecha=" + fecha + ", estado=" + estado
				+ ", monto=" + monto + "]";
	}
	
	
	
}
