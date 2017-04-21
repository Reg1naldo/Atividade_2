package br.gov.sp.fatec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.service.CadastroMaterialService;

@RestController
@RequestMapping(value = "/material")
public class MaterialController {
	
	@Autowired
	private CadastroMaterialService cadastroMaterialService;
	
	public void setCadastroMaterialService(CadastroMaterialService cadastroMaterialService){
		this.cadastroMaterialService = cadastroMaterialService;
	}
	
	
}
