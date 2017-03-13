package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AnuncioCtrl {

    @Autowired
    private AnuncioServiceImpl anuncioService;

    @Autowired
    private AnuncioRepository anuncioRep;

    @RequestMapping(value = Paths.paginaCadastrarAnuncioUsuarioPath, method = RequestMethod.GET)
    public ModelAndView getPaginaCadastrarAnuncioUsuario(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("tipos", anuncioForm.getTipos());
        model.setViewName("user/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = Paths.getPaginaAnunciosDeUsuarioPath, method = RequestMethod.GET)
    public ModelAndView getPaginaAnunciosDeUsuario(){
        ModelAndView model = new ModelAndView();

        model.addObject("anuncios", anuncioRep.findAll());

        model.setViewName("user/listar_anuncios");

        return model;
    }

    @RequestMapping(value = Paths.paginaCadastrarAnuncioUsuarioPath, method = RequestMethod.POST)
    public ModelAndView cadastrarAnuncioUsuario(@Valid AnuncioForm anuncioForm, BindingResult resultado, RedirectAttributes atributos){
        if(resultado.hasErrors()){
            return getPaginaCadastrarAnuncioUsuario(anuncioForm);
        }

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());

        anuncioService.create(anuncio);

        atributos.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/user/cadastrar/anuncio");
    }
   
    @RequestMapping(value = Paths.paginaCadastrarAnuncioCompanhiaPath, method = RequestMethod.GET)
    public ModelAndView getPaginaCadastarAnuncioCompanhia(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("tipos", anuncioForm.getTipos());
        model.setViewName("company/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = Paths.paginaAnunciosDeCompanhiaPath, method = RequestMethod.GET)
    public ModelAndView getPaginaAnunciosDeCompanhia(){
        ModelAndView model = new ModelAndView();

        model.addObject("anuncios", anuncioService.getAnuncioRepository().findAll());

        model.setViewName("company/listar_anuncios");

        return model;
    }

    @RequestMapping(value = Paths.paginaCadastrarAnuncioCompanhiaPath, method = RequestMethod.POST)
    public ModelAndView cadastrarAnuncioCompanhia(@Valid AnuncioForm anuncioForm, BindingResult resultado, RedirectAttributes atributos){
        if(resultado.hasErrors()){
            return getPaginaCadastarAnuncioCompanhia(anuncioForm);
        }

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());

        anuncioService.create(anuncio);

        atributos.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/company/cadastrar/anuncio");
    }
    


}
