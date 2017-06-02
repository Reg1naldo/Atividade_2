package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public Usuario buscar(String nome) {
		return usuarioRepo.findByNome(nome);
	}
	
	
	public Usuario buscar(Long id) {
		return usuarioRepo.findOne(id);
	}

	public List<Usuario> todos() {
		List<Usuario> retorno = new ArrayList<Usuario>();
		for(Usuario usuario: usuarioRepo.findAll()) {
			retorno.add(usuario);
		}
		return retorno;
	}

	public Usuario CadastraUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);		
	}
	
	/**
	 * @param usuarioRepo the usuarioRepo to set
	 */
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
}
