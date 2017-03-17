package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.model.usuario.UsuarioForm;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;
import br.edu.ufcg.computacao.si1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class UsuarioCtrl {

    @Autowired
    private UsuarioServiceImpl usuarioService;


    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_RETORNAR_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> retornarUsuarioLogado() {
		
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
		
		return new ResponseEntity<>(usuarioLogado.get(), HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/todos/usuarios", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Usuario>> retornarTodosUsuarios() {
		
    	Collection<Usuario> listaDeUsuarios = usuarioService.getAll();
    	
		return new ResponseEntity<>(listaDeUsuarios, HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.GET, value="/usuario/usuario/id/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable Long id) {
		
		Usuario usuario = usuarioService.getById(id).get();
		
		if(usuario == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
    
    @RequestMapping(value = Paths.PATH_CADASTRO_DE_USUARIO, method = RequestMethod.GET)
    public ModelAndView getPaginaCadastroUsuario(UsuarioForm usuarioForm){
        ModelAndView model = new ModelAndView();
        model.setViewName("cadastro");

        return model;
    }

    @RequestMapping(value = Paths.PATH_CADASTRO_DE_USUARIO, method = RequestMethod.POST)
    public ModelAndView cadastrarUsuario(@Valid UsuarioForm usuarioForm, BindingResult resultado, RedirectAttributes atributos){
        if(resultado.hasErrors()){
            return getPaginaCadastroUsuario(usuarioForm);
        }
        if (usuarioService.getByEmail(usuarioForm.getEmail()).isPresent()){
        	atributos.addFlashAttribute("error", "Este email já esta em uso!");
            return new ModelAndView("redirect:/cadastrar-se");
        }

        usuarioService.create(usuarioForm);

        atributos.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso!");
        return new ModelAndView("redirect:/cadastrar-se");
    }
}