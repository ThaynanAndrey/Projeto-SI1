package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.forms.UsuarioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

import javax.validation.Valid;

/**
 * Controller responsável por intermediar as operações de acesso aos 
 * usuários cadastrados no banco de dados do sistema. 
 * 
 * @author Thaynan Andrey
 * @author Giuseppe Mongiovi
 *
 */
@RestController
@Controller
public class UsuarioCtrl {

    @Autowired
    private UsuarioServiceImpl usuarioService;


    /**
     * Método Get que retorna o usuário logado
     * @return Usuario - usuario logado
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_RETORNAR_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> retornarUsuarioLogado() {
		
    	Usuario usuarioLogado = usuarioService.getUsuarioLogado();
    	
		return new ResponseEntity<>(usuarioLogado, HttpStatus.OK);
	}
    
    /**
     * Método Get que retorna uma collection de todos os usuários cadastrados no BD
     * @return Collection<Usuario> - Collection dos usuários cadastrados no BD
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_TODOS_USUARIOS_CADASTRADOS, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Usuario>> retornarTodosUsuarios() {
		
    	Collection<Usuario> listaDeUsuarios = usuarioService.obterTodasEntidadesCadastradas();
    	
		return new ResponseEntity<>(listaDeUsuarios, HttpStatus.OK);
	}
    
    /**
     * Método Get que retorna o usuário por um id
     * @param Long id - id do usuário a ser retornado
     * @return Usuario - Usuario encontrado através do seu id
     */
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_OBTER_USUARIO_POR_ID, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable Long id) {
		
		Usuario usuario = usuarioService.obterEntidadePorId(id).get();
		
		if(usuario == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
    
    /**
     * Método Get que redireciona a pagina de cadastro do usuário
     */
    @RequestMapping(method = RequestMethod.GET, value = Paths.PATH_CADASTRO_DE_USUARIO)
    public ModelAndView getPaginaCadastroUsuario(UsuarioForm usuarioForm){
        ModelAndView model = new ModelAndView();
        model.setViewName("cadastro");

        return model;
    }

    /**
     * Método Post que cadastra o usuário e redireciona as rotas de acordo com o resultado da operacao
     */
    @RequestMapping(method = RequestMethod.POST , value = Paths.PATH_CADASTRO_DE_USUARIO)
    public ModelAndView cadastrarUsuario(@Valid UsuarioForm usuarioForm, BindingResult resultado, RedirectAttributes atributos){
        if(resultado.hasErrors()){
            return getPaginaCadastroUsuario(usuarioForm);
        }
        if (usuarioService.getByEmail(usuarioForm.getEmail()).isPresent()){
        	atributos.addFlashAttribute("error", "Este email já esta em uso!");
            return new ModelAndView("redirect:/cadastrar-se");
        }

        usuarioService.criarNovaEntidade(usuarioForm);

        atributos.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso!");
        return new ModelAndView("redirect:/cadastrar-se");
    }
}