package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesCtrl {

    @RequestMapping(Paths.naoEncontradoErroPath)
    public String naoEncontrado() {
        return "error/404";
    }

    @RequestMapping(Paths.proibidoErroPath)
    public String proibido() {
        return "error/403";
    }

    @RequestMapping(Paths.erroInternoDeServidorPath)
    public String erroInternoDeServidor() {
        return "error/500";
    }
    
}
