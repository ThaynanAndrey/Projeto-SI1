package br.edu.ufcg.computacao.si1.controller;

/**
 * Controller responsável por redenrizar, em caso de acontecer um erro no programa,
 * as páginas de erro para a interface do usuário, notificando tal erro.
 * 
 * @author Thaynan Andrey
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ufcg.computacao.si1.utils.Paths;

@Controller
public class PaginasDeErroCtrl {

	/**
	 * Em caso de ocorrer um erro 404 no programa, ou seja, o usuário
	 * tentar acessar uma rota que não existe.
	 * 
	 * @return Redenriza página de erro 404
	 */
    @RequestMapping(Paths.PATH_404)
    public String rendenrizarPaginaNaoEncotrada() {
        return Paths.PATH_ERRO_404;
    }

    /**
	 * Em caso de ocorrer um erro 403 no programa, ou seja, o usuário
	 * tentar acessar uma rota a qual não tem permissão.
	 * 
	 * @return Redenriza página de erro 403
	 */
    @RequestMapping(Paths.PATH_403)
    public String rendenrizarPaginaSemPermissao() {
        return Paths.PATH_ERRO_403;
    }

    /**
	 * Em caso de ocorrer um erro 500 no programa, ou seja, laguma operação do usuáro
	 * acarretou em uma quebra no Back-End, então, o programa apresenta página 500 para o usuário.
	 * 
	 * @return Redenriza página de erro 500
	 */
    @RequestMapping(Paths.PATH_500)
    public String rendenrizarPaginaErroInternoDeServidor() {
        return Paths.PATH_ERRO_500;
    }   
}