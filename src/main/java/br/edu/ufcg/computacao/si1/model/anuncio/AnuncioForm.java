package br.edu.ufcg.computacao.si1.model.anuncio;


import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.utils.StringsConstantes;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Classe para objetos do tipo AnuncioForm, representando um formulario do anuncio,
 *  onde serao contidos, valores e metodos para o mesmo.
 * @author Caio Felipe
 */
public class AnuncioForm {

    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_TITULO_NULO)
    @NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_TITULO_VAZIO)
    @Size(min = 10, max = 100, message = StringsConstantes.MESAGEM_DE_ERRO_TITULO_QTD_CARACTERES)
    private String titulo;
    
    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_PRECO_NULO)
    @DecimalMin(value = StringsConstantes.PRECO_MINIMO_ANUNCIO, message = StringsConstantes.MESAGEM_DE_ERRO_PRECO_MINIMO_PERMETIDO)
    private Double quantia;
    
    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_TIPO_ANUNCIO_NULO)
    @NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_TIPO_ANUNCIO_VAZIO)
    private String tipo;
     
    private Date dataDeAgendamento;
    
    private Usuario dono;
    
    public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public void setQuantia(Double quantia) {
		this.quantia = quantia;
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
     * Metodo para retorno do preço do anúncio
     * @return Double - Valor ou salario referente ao anuncio.
     */
    public double getQuantia() {
        return quantia;
    }
    
    /**
     * Metodo para alteracao do preço do anúncio
     * @param Double quantia - Valor ou salario referente ao anuncio.
     */
    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

