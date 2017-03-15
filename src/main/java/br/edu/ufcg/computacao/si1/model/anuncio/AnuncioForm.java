package br.edu.ufcg.computacao.si1.model.anuncio;


import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.utils.StringsConstantes;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    
    public Date getDataDeAgendamento() {
		return dataDeAgendamento;
	}

	public void setDataDeAgendamento(Date dataDeAgendamento) {
		this.dataDeAgendamento = dataDeAgendamento;
	}

	public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setQuantia(Double quantia) {
        this.quantia = quantia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

