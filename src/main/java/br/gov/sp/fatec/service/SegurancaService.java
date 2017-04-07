package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("segurancaService")
public class SegurancaService {
	@Autowired
	private UsuarioRepository usuarioRepo;

	public void CadastraUsuario(String nome, String senha) {
		Usuario usuario1 = new Usuario();
		usuario1.setNome(nome);
		usuario1.setSenha(senha);
		usuarioRepo.save(usuario1);		
	}
	
	public boolean Login(String nome, String senha){
		return usuarioRepo.findByNome(nome).getSenha()==senha;
	}

	/**
	 * @param usuarioRepo the usuarioRepo to set
	 */
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
}