package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Material;

public interface MaterialRepository extends CrudRepository<Material, Long> {
	public Material findByNome(String nome);	
	
	public Material findTop1ByNomeContains(String nome);
	
	public List<Material> findByIdGreaterThan(Long id);

}
