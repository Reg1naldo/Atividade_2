package br.gov.sp.fatec.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Material;

public interface MaterialRepository extends CrudRepository<Material, Long> {
	

}
