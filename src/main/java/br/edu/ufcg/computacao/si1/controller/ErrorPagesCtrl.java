package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ufcg.computacao.si1.utils.Paths;

@Controller
public class ErrorPagesCtrl {

    @RequestMapping(Paths.PATH_NAO_ENCONTRADO_ERRO)
    public String naoEncontrado() {
        return "error/404";
    }

    @RequestMapping(Paths.PATH_PROIBIDO_ERRO)
    public String proibido() {
        return "error/403";
    }

    @RequestMapping(Paths.PATH_ERRO_INTERNO_DE_SERVIDOR)
    public String erroInternoDeServidor() {
        return "error/500";
    }
    
}
