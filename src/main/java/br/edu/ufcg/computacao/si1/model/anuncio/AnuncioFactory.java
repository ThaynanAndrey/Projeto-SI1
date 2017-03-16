package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ufcg.computacao.si1.model.enumeration.TipoDeAnuncioEnum;
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
		Date dataDeCriacao =  new Date();
		
		System.out.println("PASSOU NA DATA");
		Usuario dono = form.getDono(); //TEMPORARIO
		System.out.println("PASSOU NO USUARIO");
		
		if(form.getTipo().equals(TipoDeAnuncioEnum.MOVEL.toString().toLowerCase())){
			anuncioNovo = new AnuncioMovel(titulo, quantia,dono,dataDeCriacao);
		}
		else if(form.getTipo().equals(TipoDeAnuncioEnum.IMOVEL.toString().toLowerCase())){
			System.out.println("ENTROU");
			anuncioNovo = new AnuncioImovel(titulo, quantia,dono,dataDeCriacao);
		}
		else if(form.getTipo().equals(TipoDeAnuncioEnum.EMPREGO.toString().toLowerCase())){
			anuncioNovo = new AnuncioEmprego(titulo, quantia,dono,dataDeCriacao);
		}
		else if(form.getTipo().equals(TipoDeAnuncioEnum.SERVICO.toString().toLowerCase())){
			anuncioNovo = new AnuncioServico(titulo, quantia, dono, dataDeCriacao, form.getDataDeAgendamento());
		}
		else{
			throw new RuntimeException();
		}
		return anuncioNovo;
	}
}