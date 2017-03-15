package br.edu.ufcg.computacao.si1.model.anuncio;


import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.utils.StringsConstantes;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AnuncioForm {

    private static final String[] tipos = new String[] {"movel", "imovel", "emprego"};

    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_TITULO_NULO)
    @NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_TITULO_VAZIO)
    @Size(min = 10, max = 100, message = StringsConstantes.MESAGEM_DE_ERRO_TITULO_QTD_CARACTERES)
    private String titulo;
    
    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_PRECO_NULO)
    @DecimalMin(value = StringsConstantes.PRECO_MINIMO_ANUNCIO, message = StringsConstantes.MESAGEM_DE_ERRO_PRECO_MINIMO_PERMETIDO)
    private Double preco;
    
    @NotNull(message = StringsConstantes.MESAGEM_DE_ERRO_TIPO_ANUNCIO_NULO)
    @NotEmpty(message = StringsConstantes.MESAGEM_DE_ERRO_TIPO_ANUNCIO_VAZIO)
    private String tipo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static String[] getTipos() {
        return tipos;
    }
}

