package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.repository.MaterialRepository;
import br.gov.sp.fatec.repository.TipoMaterialRepository;

@Service("cadastroMaterial")
public class CadastroMaterialService {
	@Autowired
	private MaterialRepository materialRepo;
	
	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;
	
	@Transactional
	public void cadastroMaterial(Material material){
		materialRepo.save(material);
	}
	
	public void setMaterialRepository(MaterialRepository materialRepo){
		this.materialRepo = materialRepo;
	}
	
	public void setTipoMaterialRepository(TipoMaterialRepository tipoMaterialRepo){
		this.tipoMaterialRepo = tipoMaterialRepo;
	}
	

}
