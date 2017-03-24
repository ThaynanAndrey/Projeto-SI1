package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ufcg.computacao.si1.utils.Paths;

/**
 * Controller responsável por redenrizar as páginas de início, de login e do usuário,
 * possibilitando uma intermediação entre o Front-End e o Back-End. 
 *  
 * @author Thaynan Andrey
 *
 */
@Controller
public class PaginasWebCtrl {

	/**
	 * Rendenriza a página de inicio do programa.
	 * 
	 * @return Página index
	 */
    @RequestMapping(value = Paths.PATH_RAIZ, method = RequestMethod.GET)
    public ModelAndView rendenrizaPaginaIndex(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    /**
     * Rendenriza página de login para o usuário.
     * 
     * @return Página de Login
     */
    @RequestMapping(value = Paths.PATH_LOGIN, method = RequestMethod.GET)
    public ModelAndView rendenrizaPaginaLogin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        return model;
    }

    /**
     * Rendenriza página inicial do usuário. 
     * 
     * @return Página layout_usuario_index
     */
    @RequestMapping(value = Paths.PATH_USUARIO, method = RequestMethod.GET)
    public ModelAndView rendenrizaPaginaIndexUsuario(){
        ModelAndView model = new ModelAndView();
        model.setViewName("layout/layout_usuario_index");

        return model;
    }
}