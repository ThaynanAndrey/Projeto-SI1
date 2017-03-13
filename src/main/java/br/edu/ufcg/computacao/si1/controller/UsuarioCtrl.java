package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.UsuarioForm;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UsuarioCtrl {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @RequestMapping(value = Paths.paginaDeCadastroDeUsuarioPath, method = RequestMethod.GET)
    public ModelAndView getPaginaCadastroUsuario(UsuarioForm usuarioForm){
        ModelAndView model = new ModelAndView();
        model.setViewName("cadastro");

        return model;
    }

    @RequestMapping(value = Paths.paginaDeCadastroDeUsuarioPath, method = RequestMethod.POST)
    public ModelAndView cadastrarUsuario(@Valid UsuarioForm usuarioForm, BindingResult resultado, RedirectAttributes atributos){
        if(resultado.hasErrors()){
            return getPaginaCadastroUsuario(usuarioForm);
        }
        if (usuarioService.getByEmail(usuarioForm.getEmail()).isPresent()){
        	atributos.addFlashAttribute("error", "Este email já esta em uso!");
            return new ModelAndView("redirect:/cadastrar-se");
        }

        usuarioService.create(usuarioForm);

        atributos.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso!");
        return new ModelAndView("redirect:/cadastrar-se");
    }


}
