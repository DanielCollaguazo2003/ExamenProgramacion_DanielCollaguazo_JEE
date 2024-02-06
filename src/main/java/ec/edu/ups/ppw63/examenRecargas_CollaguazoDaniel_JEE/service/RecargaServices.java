package ec.edu.ups.ppw63.examenRecargas_CollaguazoDaniel_JEE.service;

import java.util.List;

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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Recarga recarga) {
		try{
			gRecibo.generarRecarga(recarga);
			ErrorMessage error = new ErrorMessage(1, "OK");
			//return Response.ok(Cuenta).build();
			return Response.status(Response.Status.CREATED).entity(error).build();
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
