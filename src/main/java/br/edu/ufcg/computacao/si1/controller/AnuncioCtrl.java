package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioFactory;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Paths;
import br.edu.ufcg.computacao.si1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins="*")
@Controller
@RestController
public class AnuncioCtrl {

    @Autowired
    private AnuncioServiceImpl anuncioService;

    @Autowired
    private AnuncioRepository anuncioRep;
    
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    private AnuncioFactory anuncioFactory = new AnuncioFactory();

    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_LISTAR_ANUNCIOS_DE_USUARIO, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Collection<Anuncio>> getPaginaAnunciosDeUsuario() {
   		
   		Collection<Anuncio> listaDeAnuncios= anuncioRep.findAll();
   		
   		return new ResponseEntity<>(listaDeAnuncios, HttpStatus.OK);
   	}
    //
    
    @RequestMapping(method=RequestMethod.GET, value=Paths.PATH_RETORNAR_USUARIO_LOGADO, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> retornarUsuarioLogado() {
		
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
		
		return new ResponseEntity<>(usuarioLogado.get(), HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.POST, value=Paths.PATH_CADASTRAR_ANUNCIO_USUARIO, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Anuncio> cadastrarAnuncioUsuario(@RequestBody AnuncioForm anuncioForm) {
    	System.out.println("--------form:  " + anuncioForm);
    	Anuncio anuncio = anuncioFactory.criarAnuncio(anuncioForm);
    	System.out.println("PASSOUU");
		Anuncio novoAnuncioCadastrado = anuncioService.create(anuncio);
		
		return new ResponseEntity<>(novoAnuncioCadastrado, HttpStatus.CREATED);
	}
    //
//	  PARA APAGAR
//    @RequestMapping(value = Paths.PATH_CADASTRAR_ANUNCIO_USUARIO, method = RequestMethod.GET)
//    public ModelAndView getPaginaCadastrarAnuncioUsuario(AnuncioForm anuncioForm){
//        ModelAndView model = new ModelAndView();
//
//        model.addObject("tipos", anuncioForm.getTipos());
//        model.setViewName("user/cadastrar_anuncio");
//
//        return model;
//    }
//   
//    @RequestMapping(value = Paths.cadastrarAnuncioCompanhiaPath, method = RequestMethod.GET)
//    public ModelAndView getPaginaCadastarAnuncioCompanhia(AnuncioForm anuncioForm){
//        ModelAndView model = new ModelAndView();
//
//        model.addObject("tipos", anuncioForm.getTipos());
//        model.setViewName("company/cadastrar_anuncio");
//
//        return model;
//    }

    @RequestMapping(value = Paths.listarAnunciosDeCompanhiaPath, method = RequestMethod.GET)
    public ModelAndView getPaginaAnunciosDeCompanhia(){
        ModelAndView model = new ModelAndView();

        model.addObject("anuncios", anuncioService.getAnuncioRepository().findAll());

        model.setViewName("company/listar_anuncios");

        return model;
    }

    //PARA APAGAR
//    @RequestMapping(value = Paths.cadastrarAnuncioCompanhiaPath, method = RequestMethod.POST)
//    public ModelAndView cadastrarAnuncioCompanhia(@Valid AnuncioForm anuncioForm, BindingResult resultado, RedirectAttributes atributos){
//        if(resultado.hasErrors()){
//            return getPaginaCadastarAnuncioCompanhia(anuncioForm);
//        }
//
//        Anuncio anuncio = new Anuncio();
//        anuncio.setTitulo(anuncioForm.getTitulo());
//        anuncio.setQuantia(anuncioForm.getQuantia());
//        anuncio.setTipo(anuncioForm.getTipo());
//
//        anuncioService.create(anuncio);
//
//        atributos.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
//        return new ModelAndView("redirect:/company/cadastrar/anuncio");
//    }
}
