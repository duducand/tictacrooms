package es.duducand.tic.tac.rooms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import es.duducand.tic.tac.rooms.persistance.dao.UsuarioDAO;
import es.duducand.tic.tac.rooms.persistance.dao.model.Usuario;

public class LoginController implements AuthenticationProvider{

    private UsuarioDAO usuarioDao;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Usuario usuario = usuarioDao.getUsuarioByLogin(authentication.getPrincipal().toString());
        if(usuario !=null){
            if(!usuario.getClaveUsuario().equals(authentication.getCredentials().toString())){
                usuario=null;
            }
        }
        if(usuario ==null){
            return null;
        }else{
            return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getClaveUsuario());
        }
        
    }

    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);   
    }

    @Autowired
    public void setUsuarioDao(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
}
