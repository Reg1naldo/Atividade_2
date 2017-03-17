package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	public Usuario findByNome(String nome);
	
	public Usuario findTop1ByNomeContains(String nome);
	
	public List<Usuario> findByIdGreaterThan(Long id);
}
