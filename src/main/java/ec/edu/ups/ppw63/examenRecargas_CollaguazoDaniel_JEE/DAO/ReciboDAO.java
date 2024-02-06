package ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.DAO;

import java.util.List;

import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.model.ReciboRecarga;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ReciboDAO {
	
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(ReciboRecarga  reciboRecarga) {
		em.persist(reciboRecarga);
	}
		
	public void update(ReciboRecarga reciboRecarga) {
		em.merge(reciboRecarga);	
	}
	
	public void remove(int codigo) {
		ReciboRecarga reciboRecarga = em.find(ReciboRecarga.class, codigo);
		em.remove(reciboRecarga);
	}
	
	public ReciboRecarga read(int codigo) {
		ReciboRecarga reciboRecarga = em.find(ReciboRecarga.class, codigo);
		return reciboRecarga;
	}
	
	public List<ReciboRecarga> getAll(){
		String jpql = "SELECT c FROM ReciboRecarga c"; //JPQL es sensible a mayusculas --- para realizar una consulta es similar pero hay q ue tener en cuenta que se tiene una variabe en lugar del alterisco y hay que referenciar a la entidad  no a la tabla
												 // Es decir se debe consultar en la entidad mas no directamente a una tabla de la base de datos 
												 // En lugar del * se coloca una variable, esa variable hace referencia al alias de la entidad
		Query q = em.createQuery(jpql, ReciboRecarga.class);
		return q.getResultList();
	}
	
	
	public ReciboRecarga getReciboRecargaPorId(int codigo) {
		String jpql = "SELECT c FROM ReciboRecarga c WHERE c.codigo = :codigo";
		Query q = em.createQuery(jpql, ReciboRecarga.class);
		q.setParameter("codigo", codigo);
		List<ReciboRecarga> recibosRecargas = q.getResultList();
		if (recibosRecargas.size() > 0)
			return recibosRecargas.get(0);
		return null;
		}
}
