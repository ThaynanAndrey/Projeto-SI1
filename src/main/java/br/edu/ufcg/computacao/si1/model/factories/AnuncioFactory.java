package br.edu.ufcg.computacao.si1.model.factories;

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

/**Classe para objeto do tipo AnuncioFactory, que implementa o factory method do objeto Anuncio.
 * 
 * @author Caio Felipe
 * @author Giuseppe Mongiovi
 */
public class AnuncioFactory {
	
	@Autowired
	UsuarioServiceImpl usuarioService;
	
	/**
	 * Metodo factory que cria e retorna um anuncio recebendo as informacoes do formulario referente a ele.
	 * @param AnuncioForm form - formulario do anuncio.
	 * @return Anuncio - Anuncio criado pelo factory method.
	 */
	public Anuncio criarAnuncio(AnuncioForm form){

		double quantia = form.getQuantia();	
		String titulo = form.getTitulo();
		Long dataDeCriacao =  new Date().getTime();
		int diasDeVidaUtil = form.getDiasDeVidaUtil();
		Usuario dono = form.getDono();
		String tipoDeAnuncio = form.getTipo();
		Date dataDeAgendamento = form.getDataDeAgendamento();
		
		Anuncio anuncioNovo = criandoAnuncio(quantia, titulo, dataDeCriacao, diasDeVidaUtil, dono, tipoDeAnuncio, dataDeAgendamento);
		
		return anuncioNovo;
	}

	private Anuncio criandoAnuncio(double quantia, String titulo, Long dataDeCriacao, int diasDeVidaUtil,
			Usuario dono, String tipoDeAnuncio, Date dataDeAgendamento) {
		
		Anuncio anuncioNovo;
		
		if(tipoDeAnuncio.equals(TipoDeAnuncioEnum.MOVEL.getValor())){
			anuncioNovo = new AnuncioMovel(titulo, quantia,dono,dataDeCriacao,diasDeVidaUtil);
		}
		else if(tipoDeAnuncio.equals(TipoDeAnuncioEnum.IMOVEL.getValor())){
			System.out.println("ENTROU");
			anuncioNovo = new AnuncioImovel(titulo, quantia,dono,dataDeCriacao,diasDeVidaUtil);
		}
		else if(tipoDeAnuncio.equals(TipoDeAnuncioEnum.EMPREGO.getValor())){
			anuncioNovo = new AnuncioEmprego(titulo, quantia,dono,dataDeCriacao,diasDeVidaUtil);
		}
		else if(tipoDeAnuncio.equals(TipoDeAnuncioEnum.SERVICO.getValor())){
			anuncioNovo = new AnuncioServico(titulo, quantia, dono, dataDeCriacao, dataDeAgendamento, diasDeVidaUtil);
		}
		else{
			throw new RuntimeException();
		}
		
		return anuncioNovo;
	}
}