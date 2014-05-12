package es.duducand.tic.tac.rooms.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;
import es.duducand.tic.tac.rooms.persistance.dao.util.PagedResult;
import es.duducand.tic.tac.rooms.service.IUsuarioService;

@Controller
@RequestMapping(value = "/tabla/usuarios")
public class UsuarioListController extends BaseJsonListController<Usuario> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private IUsuarioService usuarioService;
    
    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @Override
    protected PagedResult<Usuario> getResult(PagedQuery query) {
    	return usuarioService.getUsuarios(query);
    }
    
    @Override
    protected Usuario getResultItem(String id) {
    	return usuarioService.getUsuario(Integer.parseInt(id));
    }
    
    @Override
    protected Map<String, Object> getMap(Usuario usuario) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id",    usuario.getId());
    	map.put("login", usuario.getLogin());
    	map.put("nombre", usuario.getNombre());
    	map.put("apellidos", usuario.getApellidos());
    	map.put("codigoPostal", usuario.getCodigoPostal());
    	map.put("cuentaBancaria", usuario.getCuentaBancaria());
    	map.put("direccion", usuario.getDireccion());
    	map.put("email", usuario.getEmail());
    	map.put("fechaBaja", usuario.getFechaBaja()!=null?DATE_FORMAT.format(usuario.getFechaBaja()):null);
    	map.put("poblacion", usuario.getPoblacion());
    	map.put("provincia", usuario.getProvincia());
    	StringBuilder perfiles = new StringBuilder();
    	perfiles.append(usuario.getAdministrador()?"Administrador ":"");
    	perfiles.append(usuario.getTrabajador()?"Trabajador ":"");
        perfiles.append(usuario.getSocio()?"Socio":"");
    	map.put("perfiles", perfiles.toString());
    	map.put("administrador", usuario.getAdministrador());
    	map.put("trabajador", usuario.getTrabajador());
    	map.put("socio", usuario.getSocio());
    	return map;
    }
}
