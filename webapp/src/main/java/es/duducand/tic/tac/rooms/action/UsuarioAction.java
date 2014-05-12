package es.duducand.tic.tac.rooms.action;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;
import es.duducand.tic.tac.rooms.service.IUsuarioService;

public class UsuarioAction extends MultiAction{
	private IUsuarioService usuarioService;

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public Event saveUsuario(Usuario usuario, RequestContext context) {
		try {
			usuarioService.saveUsuario(usuario);
			return result("GUARDADO");
		} catch (Exception ex) {
			return result("ERROR");
		} 
	}
	
	public Event deleteUsuario(Usuario usuario, RequestContext context) {
		try {
			usuarioService.deleteUsuario(usuario);
			return success();
		} catch (Exception ex) {
			return this.success();
		}
	}
	
	public Event reactivarUsuario(Usuario usuario, RequestContext context){
	    try {
            usuarioService.reactivarUsuario(usuario);
            return success();
        } catch (Exception ex) {
            return error(ex);
        } 
	}
	
}
