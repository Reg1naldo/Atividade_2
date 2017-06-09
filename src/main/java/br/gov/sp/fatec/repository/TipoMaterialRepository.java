package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.TipoMaterial;

public interface TipoMaterialRepository extends CrudRepository<TipoMaterial, Long>{
	public TipoMaterial findByNome(String nome);
	
	public TipoMaterial findById(Long id);
	
	public TipoMaterial findTop1ByNomeContains(String nome);
	
	public List<TipoMaterial> findByIdGreaterThan(Long id);
	
	public List<TipoMaterial> findAll();
	
	//public Long deleteById(Long id);
}
