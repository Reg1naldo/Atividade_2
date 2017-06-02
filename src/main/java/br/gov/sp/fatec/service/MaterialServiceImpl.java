package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.repository.MaterialRepository;

@Service("cadastroMaterial")
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private MaterialRepository materialRepo;

	@Transactional
	public Material cadastroMaterial(Material material) {
		Material nmaterial = new Material();
		if (materialRepo.findByNome(material.getNome()) != null) {
			nmaterial = materialRepo.findByNome(material.getNome());
		} else {
			nmaterial = material;
		}
		
		return materialRepo.save(nmaterial);
	}

	public Material buscar(Long id) {
		return materialRepo.findById(id);
	}
	
	public List<Material> buscarPorNome(String nome) {
		return materialRepo.findByNomeContains(nome);
	}

	public void setMaterialRepository(MaterialRepository materialRepo) {
		this.materialRepo = materialRepo;
	}

}