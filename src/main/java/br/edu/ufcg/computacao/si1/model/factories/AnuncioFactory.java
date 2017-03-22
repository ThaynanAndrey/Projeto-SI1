package br.edu.ufcg.computacao.si1.model.factories;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioEmprego;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioImovel;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioMovel;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioServico;
import br.edu.ufcg.computacao.si1.model.enumerations.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.forms.AnuncioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.utils.Utils;

/**Classe para objeto do tipo AnuncioFactory, que implementa o factory method do objeto Anuncio.
 * @author Caio Felipe
 */
public class AnuncioFactory {
	
	@Autowired
	UsuarioServiceImpl usuarioService;
	
	/**
	 * Metodo que retorna o usuario logado.
	 * @return Usuario - usuario logado.
	 */
	private Usuario getUsuarioLogado() {
		String emailUsuario = Utils.userNameUsuarioLogado();
		return usuarioService.getByEmail(emailUsuario).get();
	}
	
	/**
	 * Metodo factory que cria e retorna um anuncio recebendo as informacoes do formulario referente a ele.
	 * @param AnuncioForm form - formulario do anuncio.
	 * @return Anuncio - Anuncio criado pelo factory method.
	 */
	public Anuncio criarAnuncio(AnuncioForm form){
		Anuncio anuncioNovo;
		double quantia = form.getQuantia();	
		String titulo = form.getTitulo();
		Long dataDeCriacao =  new Date().getTime();
		
		System.out.println("PASSOU NA DATA");
		Usuario dono = form.getDono(); //TEMPORARIO
		System.out.println("PASSOU NO USUARIO");
		
		if(form.getTipo().equals(TipoDeAnuncioEnum.MOVEL.getValor())){
			anuncioNovo = new AnuncioMovel(titulo, quantia,dono,dataDeCriacao);
		}
		else if(form.getTipo().equals(TipoDeAnuncioEnum.IMOVEL.getValor())){
			System.out.println("ENTROU");
			anuncioNovo = new AnuncioImovel(titulo, quantia,dono,dataDeCriacao);
		}
		else if(form.getTipo().equals(TipoDeAnuncioEnum.EMPREGO.getValor())){
			anuncioNovo = new AnuncioEmprego(titulo, quantia,dono,dataDeCriacao);
		}
		else if(form.getTipo().equals(TipoDeAnuncioEnum.SERVICO.getValor())){
			anuncioNovo = new AnuncioServico(titulo, quantia, dono, dataDeCriacao, form.getDataDeAgendamento());
		}
		else{
			throw new RuntimeException();
		}
		return anuncioNovo;
	}
}