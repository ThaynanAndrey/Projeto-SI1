package br.edu.ufcg.computacao.si1.model.anuncio;


import javax.persistence.*;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import java.util.Date;

/**
 * Classe abstrata para objetos do tipo Anuncio, onde serão contidos valores e métodos para o mesmo.
 * 
 * @author Thaynan Andrey
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Anuncio {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "anuncio_id", nullable = false, unique = true)
    private Long _id;

    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "quantia", nullable = false)
    private double quantia;

    @Column(name = "data_criacao", nullable = false)
    private Long dataDeCriacao;
    
    private int diasDeVidaUtil;
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario dono;

    /**
	 * Construtor default
	 */
	public Anuncio() {
        titulo = "";
        dataDeCriacao = new Date().getTime();
        quantia = 0;
    }
	
    /**
     * Construtor do objeto
     */
    public Anuncio(String titulo, double quantia, Usuario dono, Long dataDeCriacao, int diasDeVidaUtil) {
		this.titulo = titulo;
		this.quantia = quantia;
		this.dataDeCriacao =  dataDeCriacao;
		this.dono = dono;
		this.diasDeVidaUtil = diasDeVidaUtil;
	}
    
    
    /**
     * Metodo para retorno da quantidade de dias de vida útil do anúncio
     * @return int - Quantidade de dias de vida útil do anúncio.
     */
    public int getDiasDeVidaUtil() {
		return diasDeVidaUtil;
	}

    /**
     * Metodo para alteracao da quantidade de dias de vida útil do anúncio
     * @param int diasDeVidaUtil - Quantidade de dias de vida útil do anúncio.
     */
	public void setDiasDeVidaUtil(int diasDeVidaUtil) {
		this.diasDeVidaUtil = diasDeVidaUtil;
	}

	/**
     * Metodo abstrato para retorno do tipo do anúncio que deve ser implementado nas subclasses.
     * @return String - Tipo do anuncio.
     */
    public abstract String getTipo();
    
    /**
     * Metodo para alteracao do dono do anúncio
     * @param Usuario dono = dono do anuncio.
     */
	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public Usuario pegueDono() {
		return this.dono;
	}
	
    /**
     * Metodo para retorno do id do anúncio
     * @return Long id - o id do anuncio
     */
    public Long get_id() {
        return _id;
    }

    /**
     * Metodo para alteracao do id do anúncio
     * @param long _id -  id a ser colocado no anuncio
     */public void set_id(Long _id) {
        this._id = _id;
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
     * Metodo para retorno da data de criação do anúncio
     * @return String - Data de criacao do anuncio.
     */
    public Long getDataDeCriacao() {
        return dataDeCriacao;
    }
    
    /**
     * Metodo para alteracao da data de criação do anúncio
     * @param String dataDeCriacao - Data de criacao do anuncio.
     */
    public void setDataDeCriacao(Long dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
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
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio)) return false;

        Anuncio anuncio = (Anuncio) o;

        if (Double.compare(anuncio.getQuantia(), getQuantia()) != 0) return false;
        if (!get_id().equals(anuncio.get_id())) return false;
        if (!getTitulo().equals(anuncio.getTitulo())) return false;
        if (!getDataDeCriacao().equals(anuncio.getDataDeCriacao())) return false;
        return getTipo().equals(anuncio.getTipo());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = get_id().hashCode();
        result = 31 * result + getTitulo().hashCode();
        result = 31 * result + getDataDeCriacao().hashCode();
        temp = Double.doubleToLongBits(getQuantia());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getTipo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "_id=" + _id +
                ", titulo='" + titulo + '\'' +
                ", dataDeCriacao=" + getDataDeCriacao() +
                ", quantia=" + quantia +
                '}';
    }
}
