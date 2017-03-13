package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebPagesCtrl {

    @RequestMapping(value = Paths.indexPrincipalPath, method = RequestMethod.GET)
    public ModelAndView getPaginaIndex(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = Paths.loginPath, method = RequestMethod.GET)
    public ModelAndView getPaginaLogin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = Paths.indexUsuarioPath, method = RequestMethod.GET)
    public ModelAndView getPaginaIndexUser(){
        ModelAndView model = new ModelAndView();
        //Para testes
        model.setViewName("user/index");

        return model;
    }

    @RequestMapping(value = Paths.indexCompanhiaPath, method = RequestMethod.GET)
    public ModelAndView getPaginaIndexCompany(){
        ModelAndView model = new ModelAndView();
        //Para testes
        model.setViewName("user/index");

        return model;
    }
}
