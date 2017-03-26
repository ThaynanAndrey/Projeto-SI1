package br.edu.ufcg.computacao.si1.model.forms;


import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.utils.Constantes;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Classe para objetos do tipo AnuncioForm, representando um formulario do anuncio,
 *  onde serao contidos, valores e metodos para o mesmo.
 * @author Caio Felipe, Giuseppe Mongiovi
 */
public class AnuncioForm {

    @NotNull(message = Constantes.MESAGEM_DE_ERRO_TITULO_NULO)
    @NotEmpty(message = Constantes.MESAGEM_DE_ERRO_TITULO_VAZIO)
    @Size(min = 10, max = 100, message = Constantes.MESAGEM_DE_ERRO_TITULO_QTD_CARACTERES)
    private String titulo;
    
    @NotNull(message = Constantes.MESAGEM_DE_ERRO_PRECO_NULO)
    @DecimalMin(value = Constantes.PRECO_MINIMO_ANUNCIO, message = Constantes.MESAGEM_DE_ERRO_PRECO_MINIMO_PERMETIDO)
    private Double quantia;
    
    @NotNull(message = Constantes.MESAGEM_DE_ERRO_TIPO_ANUNCIO_NULO)
    @NotEmpty(message = Constantes.MESAGEM_DE_ERRO_TIPO_ANUNCIO_VAZIO)
    private String tipo;
    
    private Long id;
   
    private Date dataDeAgendamento;
    
    private Usuario dono;
    
    private int diasDeVidaUtil;
    
    /**
     * Retorna o id do anuncio
     * @return
     */
    public Long getId() {
		return id;
	}
    
    /**
     * Define o id do anuncio
     * @param id
     */
	public void setId(Long id) {
		this.id = id;
	}
	
	
    /**
     * Retorna a quantidade de dias úteis do anuncio
     * @return
     */
    public int getDiasDeVidaUtil() {
		return diasDeVidaUtil;
	}
    
    /**
     * Define a quantidade de dias úteis do anuncio
     * @param id
     */
	public void setDiasDeVidaUtil(int diasDeVidaUtil) {
		this.diasDeVidaUtil = diasDeVidaUtil;
	}

	/**
     * Retorna o dono do anuncio
     * @return
     */
    public Usuario getDono() {
		return dono;
	}
    
    /**
     * Define o dono do anuncio
     * @param dono
     */
	public void setDono(Usuario dono) {
		this.dono = dono;
	}	

	/**
     * Metodo para retorno do titulo do anúncio
     * @return String - Titulo do anuncio.
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Metodo para alteracao do titulo do anúncio
     * @param String titulo - Titulo do anuncio.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Metodo para retorno da data de agendamento do anuncio
     * @return Date - Data de agendamento do anuncio
     */
    public Date getDataDeAgendamento() {
        return dataDeAgendamento;
    }
    
    /**
     * Metodo para alteracao da data de agendamento do anuncio
     * @param String dataDeAgendamento - Data de agendamento do anuncio.
     */
    public void setDataDeAgendamento(Date dataDeAgendamento) {
        this.dataDeAgendamento = dataDeAgendamento;
    }
    
    /**
     * Metodo para retorno a quantia de compra do anuncio
     * @return Double - Valor ou salario referente ao anuncio.
     */
    public double getQuantia() {
        return quantia;
    }
    
    /**
	 * Define a quantia de compra do anuncio
	 * @param quantia
	 */
	public void setQuantia(Double quantia) {
		this.quantia = quantia;
	}
    
    /**
     * Metodo para alteracao do preço do anúncio
     * @param Double quantia - Valor ou salario referente ao anuncio.
     */
    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }
    
    /**
     * Retorna o tipo de anuncio
     * @return
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Define o tipo de anuncio
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

