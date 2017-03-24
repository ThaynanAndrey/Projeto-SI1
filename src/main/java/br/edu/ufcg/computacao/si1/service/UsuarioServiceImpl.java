package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.enumerations.UsuarioRoleEnum;
import br.edu.ufcg.computacao.si1.model.factories.UsuarioFactory;
import br.edu.ufcg.computacao.si1.model.forms.UsuarioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Serviço responsável por gerenciar as operações sobre as entidades usuários que
 * estão ou serão armazenadas no banco de dados.
 * 
 * @author Thaynan Andrey
 * 
 */
@Service
public class UsuarioServiceImpl implements IService<Usuario,UsuarioForm>{

    private UsuarioRepository usuarioRepository;
    private UsuarioFactory usuarioFactory = new UsuarioFactory();

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public Usuario getUsuarioLogado() {
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = this.getByEmail(email);
		Usuario usuario = usuarioLogado.get();
		
		return usuario;
    }
    
    @Override
    public Usuario criarNovaEntidade(UsuarioForm usuarioForm) {
    	
    	String value = usuarioForm.getRole() == 1 ?  UsuarioRoleEnum.USUARIO_FISICO.toString() : UsuarioRoleEnum.USUARIO_JURIDICO.toString();

        Usuario usuario= usuarioFactory.criarUsuario(value, usuarioForm.getNome(), usuarioForm.getEmail(), usuarioForm.getSenha());

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obterEntidadePorId(Long id) {
    	
        return Optional.ofNullable(usuarioRepository.findOne(id));
    }

    public Optional<Usuario> getByEmail(String email) {
        
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    @Override
    public Collection<Usuario> obterTodasEntidadesCadastradas() {
    	
        return usuarioRepository.findAll();
    }

    @Override
    public boolean atualizarEntidade(Usuario usuario) {
        
    	boolean existeUsuario = usuarioRepository.exists(usuario.getId());
    	
        if (existeUsuario)
            usuarioRepository.save(usuario);
        
        return existeUsuario;
    }

    @Override
    public boolean deletarEntidade(Long id) {
    	
    	boolean existeUsuario = usuarioRepository.exists(id);
    	
        if (existeUsuario) 
            usuarioRepository.delete(id);
        
        return existeUsuario;
    }
}