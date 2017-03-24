package br.edu.ufcg.computacao.si1.service;

import java.util.Collection;
import java.util.Optional;

/**
 * Definindo-se uma interface genérica, a qual reflete todas as operações báscias
 * que todo repositório deve possuir.
 * 
 * @author Thaynan Andrey
 *
 * @param <Entidade> representa a entidade que está ou será armazenada no banco de dados.
 * @param <EntidadeForm> utilizando-se o padrão DTO, a mesma reflete os dados necessários 
 * 						para a construção de uma nova entidade que será armazenada no banco de dados.
 */
public interface IService<Entidade, EntidadeForm> {
	
	public Entidade criarNovaEntidade(EntidadeForm form);

	public Optional<Entidade> obterEntidadePorId(Long id);

	public Collection<Entidade> obterTodasEntidadesCadastradas();

	public boolean atualizarEntidade(Entidade modelo);

	public boolean deletarEntidade(Long id);
}
