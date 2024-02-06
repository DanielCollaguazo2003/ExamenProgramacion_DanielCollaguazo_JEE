package ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.service;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.bussines.GestionRecibo;
import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.model.Recarga;
import ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.model.ReciboRecarga;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("recarga")
public class RecargaServices {
	
	@Inject
	private GestionRecibo gRecibo;

	private String[] operadoras = {"tuenti", "claro", "cnt", "movistar"};
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Recarga recarga) {
		boolean operadora = false;
		for (int i = 0; i < operadoras.length; i++) {
			if (recarga.getOperadora().toLowerCase().equalsIgnoreCase(operadoras[i])) {
				operadora = true;
			}
		}
		try{
			if(recarga.getMonto() == 0 || recarga.getOperadora() == "" || recarga.getTelefono() == "") {
				ErrorMessage error = new ErrorMessage(102, "Faltan valores requeridos");
				return Response.status(Response.Status.CREATED).entity(error).build();
			}else if (recarga.getTelefono().length() != 10 || !Pattern.matches("^09\\d{8}$", recarga.getTelefono())) {
			    ErrorMessage error = new ErrorMessage(103, "Número de teléfono incorrecto. Debe comenzar con 09 y tener 10 dígitos en total.");
			    return Response.status(Response.Status.CREATED).entity(error).build();
			}else if(operadora == false) {
				ErrorMessage error = new ErrorMessage(104, "La operadora no esta en nuestra base de datos");
				return Response.status(Response.Status.CREATED).entity(error).build();
			}else if(recarga.getMonto() < 0) {
				ErrorMessage error = new ErrorMessage(105, "no puede recargar valores negativos");
				return Response.status(Response.Status.CREATED).entity(error).build();
			}else {
				gRecibo.generarRecarga(recarga);
				ErrorMessage error = new ErrorMessage(1, "OK");
				return Response.status(Response.Status.CREATED).entity(error).build();
			}
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getrecibos(){
		List<ReciboRecarga> recibo = gRecibo.getReciboRecargas();
		if(recibo.size()>0)
			return Response.ok(recibo).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran recibos");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
}
