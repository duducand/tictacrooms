package es.duducand.tic.tac.rooms.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;
import es.duducand.tic.tac.rooms.service.IUsuarioService;

@Controller
public class RegistroController {

    private IUsuarioService usuarioService;
    
    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public String onGet(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        return "registro";
    }
    
    @RequestMapping(value = "/registro/form", method = RequestMethod.POST)
    public String altaUsuario(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login=request.getParameter("login");
        String claveUsuario=request.getParameter("claveUsuario");
        String nombre=request.getParameter("nombre");
        String apellidos=request.getParameter("apellidos");
        String direccion=request.getParameter("direccion");
        String poblacion=request.getParameter("poblacion");
        String provincia=request.getParameter("provincia");
        String codigoPostal=request.getParameter("codigoPostal");
        String cuentaBancaria=request.getParameter("cuentaBancaria");
        String email=request.getParameter("email");
        
        PagedQuery query = new PagedQuery();
        Map<String, String> constraints = new HashMap<String, String>();
        constraints.put("claveUsuario", "{"+login+"}");
        //query.addConstraint("claveUsuario", login);
        query.setConstraints(constraints);
        PagedResult<Usuario> resultado = usuarioService.getUsuarios(query);
        if (resultado != null && resultado.getData()!=null && !resultado.getData().isEmpty()) {
        	String prouestaUsuario = nombre+"_"+apellidos;
        	constraints = new HashMap<String, String>();
            constraints.put("claveUsuario", "{"+prouestaUsuario+"}");
            //query.addConstraint("claveUsuario", login);
            query.setConstraints(constraints);
            resultado = usuarioService.getUsuarios(query);
            
            if (resultado != null && resultado.getData()!=null && !resultado.getData().isEmpty()) {
            	request.setAttribute("error", "El nombre de usuario elegido está ocupado, por favor, intente con otro");
            }else{
            	request.setAttribute("error", "El nombre de usuario elegido está ocupado, por favor, intente con otro, por ejemplo " + prouestaUsuario);
            }
        	
        	return "registro";
		}else{
			Usuario usuario= new Usuario();
	        usuario.setSocio(true);
	        usuario.setAdministrador(false);
	        usuario.setTrabajador(false);
	        usuario.setLogin(login);
	        usuario.setClaveUsuario(claveUsuario);
	        usuario.setNombre(nombre);
	        usuario.setApellidos(apellidos);
	        usuario.setDireccion(direccion);
	        usuario.setPoblacion(poblacion);
	        usuario.setProvincia(provincia);
	        usuario.setCodigoPostal(codigoPostal);
	        usuario.setCuentaBancaria(cuentaBancaria);
	        usuario.setEmail(email);
	        usuarioService.saveUsuario(usuario);
		}
        
        return "index";
    }
    
    @RequestMapping(value = "/custom_login", method = RequestMethod.GET)
    public String login(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        return "custom_login";
    }
    
    @RequestMapping(value = "/custom_login_failed", method = RequestMethod.GET)
    public String loginFailed(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setAttribute("error", "error");
        return "custom_login";
    }
}
