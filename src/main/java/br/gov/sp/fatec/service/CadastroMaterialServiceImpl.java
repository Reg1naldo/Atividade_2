package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.repository.MaterialRepository;
import br.gov.sp.fatec.repository.TipoMaterialRepository;

@Service("cadastroMaterial")
public class CadastroMaterialServiceImpl implements CadastroMaterialService{
	@Autowired
	private MaterialRepository materialRepo;
	
	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;
	
	@Transactional
	public void cadastroMaterial(Material material){
		Material nmaterial = materialRepo.findByNome(material.getNome()); 
		if (nmaterial != null){
			nmaterial.setNome(material.getNome());
			nmaterial.setDescricao(material.getDescricao());
			nmaterial.setTipo(material.getTipo());
			materialRepo.save(nmaterial);
		}
		else{		
			materialRepo.save(material);
		}
	}
	
	public void setMaterialRepository(MaterialRepository materialRepo){
		this.materialRepo = materialRepo;
	}
	
	public void setTipoMaterialRepository(TipoMaterialRepository tipoMaterialRepo){
		this.tipoMaterialRepo = tipoMaterialRepo;
	}
	

}