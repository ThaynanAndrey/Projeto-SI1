package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.enumeration.UsuarioRoleEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.model.usuario.UsuarioFactory;
import br.edu.ufcg.computacao.si1.model.usuario.UsuarioForm;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IService<Usuario,UsuarioForm>{

    private UsuarioRepository usuarioRepository;
    private UsuarioFactory usuarioFactory = new UsuarioFactory();

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public Usuario create(UsuarioForm usuarioForm) {
    	
    	String value = usuarioForm.getRole() == 1 ?  UsuarioRoleEnum.USUARIO_FISICO.toString() : UsuarioRoleEnum.USUARIO_JURIDICO.toString();

        Usuario usuario= usuarioFactory.criarUsuario(value, usuarioForm.getNome(), usuarioForm.getEmail(), usuarioForm.getSenha());

        System.out.println(usuario + "estah sendo criado");
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        return Optional.ofNullable(usuarioRepository.findOne(id));
    }

    public Optional<Usuario> getByEmail(String email) {
        System.out.println(email + "estah sendo retornado");
        
        System.out.println("procurando email " + usuarioRepository.findByEmail(email));
        
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    @Override
    public Collection<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean update(Usuario usuario) {
        System.out.println(usuario + "estah sendo atualizado");

        if (usuarioRepository.exists(usuario.getId())) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (usuarioRepository.exists(id)) {
            usuarioRepository.delete(id);
            return true;
        }
        return false;
    }
}
