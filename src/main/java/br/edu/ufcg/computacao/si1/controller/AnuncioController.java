package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

import javax.validation.Valid;

@CrossOrigin(origins="*")
@Controller
@RestController
public class AnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;

    @Autowired
    private AnuncioRepository anuncioRep;

    
    // ------ INICIO THAYNAN FEZ ------- //
    
    
    
    @RequestMapping(method=RequestMethod.GET, value="/user/listar/anuncioss", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Anuncio>> retornarListaDeTarefas() {
		
		Collection<Anuncio> listaDeAnuncios= anuncioRep.findAll();
		
		return new ResponseEntity<>(listaDeAnuncios, HttpStatus.OK);
	}
    
    @RequestMapping(method=RequestMethod.POST, value="/user/cadastrar/anuncioss", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Anuncio> cadastrarTarefa(@RequestBody AnuncioForm anuncioForm) {
    	
    	System.out.println("CHAMOUUU");
    	
    	Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
    	
		Anuncio novoAnuncioCadastrado = anuncioService.create(anuncio);
		
		return new ResponseEntity<>(novoAnuncioCadastrado, HttpStatus.CREATED);
	}
    
    

    // ------ FIM THAYNAN FEZ ------- //
    
    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.GET)
    public ModelAndView getPageCadastrarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("tipos", anuncioForm.getTipos());
        model.setViewName("user/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = "/user/listar/anuncios", method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();      

        System.out.println(model.addObject("anuncios", anuncioRep.findAll()));

        model.setViewName("user/listar_anuncios");

        return model;
    }

    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastrarAnuncio(anuncioForm);
        }
        System.out.println(anuncioForm);
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());

        anuncioService.create(anuncio);

        attributes.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/user/cadastrar/anuncio");
    }
}