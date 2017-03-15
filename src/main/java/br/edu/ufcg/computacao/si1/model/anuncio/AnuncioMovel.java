package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumeration.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

@Entity(name="AnuncioMovel")
@Table(name="tb_anuncio_movel")
public class AnuncioMovel extends Anuncio{

	public AnuncioMovel(){
		super();
	}
	
	public AnuncioMovel(String titulo, double quantia,Usuario dono, Date dataDeCriacao){
		super(titulo,quantia,dono, dataDeCriacao);
	}

	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.MOVEL.toString().toLowerCase();
	}
}
