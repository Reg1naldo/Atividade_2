package br.gov.sp.fatec.web.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.Receita;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.service.ReceitaService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/receita")
public class ReceitaController {
	
	@Autowired
	private ReceitaService receitaService;
	
	@RequestMapping(value = "/getById/{id}")
	@JsonView(View.All.class)
	public ResponseEntity<Receita> get(@PathVariable("id") Long id) {
		Receita receita = receitaService.buscar(id);
		if (receita == null) {
			return new ResponseEntity<Receita>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Receita>(receita, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllFilter")
	@JsonView(View.All.class)
	public ResponseEntity<Collection<Receita>> getAll(@RequestParam(value="nome", defaultValue="" ) String nome, @RequestParam(value="descricao", defaultValue="" ) String descricao ) {
		return new ResponseEntity<Collection<Receita>>(receitaService.buscarPorNomeContem(nome), HttpStatus.OK);
		
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Receita save(@RequestBody Usuario usuario, @RequestBody String nome, @RequestBody String descricao, @RequestBody List<Material> materiais, HttpServletRequest request, HttpServletResponse response) {
		Receita receita = receitaService.cadastraReceita(usuario, nome, descricao, materiais);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/receita/getById/" + receita.getId());
		return receita;
	}

}
