package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ufcg.computacao.si1.utils.Paths;

@Controller
public class WebPagesCtrl {

    @RequestMapping(value = Paths.PATH_INDEX_PRINCIPAL, method = RequestMethod.GET)
    public ModelAndView getPaginaIndex(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = Paths.PATH_LOGIN, method = RequestMethod.GET)
    public ModelAndView getPaginaLogin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = Paths.PATH_INDEX_USUARIO, method = RequestMethod.GET)
    public ModelAndView getPaginaIndexUser(){
        ModelAndView model = new ModelAndView();
        //Para testes
        System.out.println("me chamou ususario");
        model.setViewName("user/index");

        return model;
    }

    @RequestMapping(value = Paths.PATH_INDEX_COMPANHIA, method = RequestMethod.GET)
    public ModelAndView getPaginaIndexCompany(){
        ModelAndView model = new ModelAndView();
        //Para testes
        System.out.println("me chamou company");
        model.setViewName("user/index");

        return model;
    }
}
