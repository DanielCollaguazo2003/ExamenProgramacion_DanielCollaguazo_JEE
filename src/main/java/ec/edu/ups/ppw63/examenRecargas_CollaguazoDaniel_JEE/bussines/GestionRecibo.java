package ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.bussines;

import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.DAO.ReciboDAO;
import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.model.Recarga;
import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.model.ReciboRecarga;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionRecibo {
	@Inject
	private ReciboDAO daoRecibo;
	
	public void actualizarReciboRecarga(ReciboRecarga reciboRecarga) throws Exception {
		ReciboRecarga rec = daoRecibo.read(reciboRecarga.getCodigo());
		if (rec != null){
			daoRecibo.update(reciboRecarga);
		}else {
			throw new Exception("ReciboRecarga no existe");
		}
	}
	
	public void guardarReciboRecargas(ReciboRecarga reciboRecarga) {
		System.out.println("Codigo: " + reciboRecarga.getCodigo());
		ReciboRecarga car = daoRecibo.read(reciboRecarga.getCodigo());
		if (car != null) {
			System.out.println("Este es: " + reciboRecarga);
			daoRecibo.update(reciboRecarga);
		}else {
			daoRecibo.insert(reciboRecarga);
		}
	}
	
	public ReciboRecarga getReciboRecargaPorId(int codigo){
			return daoRecibo.getReciboRecargaPorId(codigo);
	}
	
	public void borrarReciboRecarga(int codigo) {
		daoRecibo.remove(codigo);
	}
	
	public List<ReciboRecarga> getReciboRecargas(){
		return daoRecibo.getAll();
	}
	
	
	public void generarRecarga(Recarga recarga) {
		
		ReciboRecarga rec = new ReciboRecarga();
		rec.setFecha(new Date());
		rec.setMonto(recarga.getMonto());
		rec.setTelefono(rec.getTelefono());
		
		daoRecibo.insert(rec);
	}
}
