package br.gov.sp.fatec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.Receita;
import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.MaterialRepository;
import br.gov.sp.fatec.repository.ReceitaRepository;
import br.gov.sp.fatec.repository.TipoMaterialRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;
import br.gov.sp.fatec.service.CadastroMaterialService;
import br.gov.sp.fatec.service.CadastroTipoMaterialService;
import br.gov.sp.fatec.service.UsuarioService;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
 
		// Recupera os repositorios
    	UsuarioRepository usuarioRepo = (UsuarioRepository) context.getBean("usuarioRepository");
    	TipoMaterialRepository tipoMaterialRepo = (TipoMaterialRepository) context.getBean("tipoMaterialRepository");
    	MaterialRepository materialRepo = (MaterialRepository) context.getBean("materialRepository");
    	ReceitaRepository receitaRepo = (ReceitaRepository) context.getBean("receitaRepository");
    	
       	// Cria um usuario
    	Usuario usuario = new Usuario();
    	usuario.setNome("Pedro");
    	usuario.setSenha("12345678");
    	usuarioRepo.save(usuario);
    	
    	System.out.println("Usuario Id: " + usuario.getId());
    	
    	//Cria um tipo de material
    	TipoMaterial tipoMaterial = new TipoMaterial();
    	tipoMaterial.setNome("pl�stico");
    	tipoMaterialRepo.save(tipoMaterial);
    	
    	System.out.println("Tipo Material Id: " + tipoMaterial.getId());
    	
    	//Cria um material
    	Material material = new Material();
    	material.setNome("Garrafa");
    	material.setDescricao("Garrafa de refrigerante");
    	material.setTipo(tipoMaterial);
    	materialRepo.save(material);
    	
    	System.out.println("Material Id: " + material.getId());
    	
    	for	(int i = 1;i<=11;i++) {
        	//Cria uma receita
        	Receita receita = new Receita();
        	receita.setNome("Vaso " + i);
        	receita.setDescricao("Como fazer um vaso " + i + " com garrafa pet!");
        	receita.setUsuario(usuario);
        	receitaRepo.save(receita);
    		
    	}
    	
    	//Consultas nos reposit�rios
    	
    	//Usu�rio Repository: Pesquisa por nome
    	System.out.println(usuarioRepo.findByNome("Pedro").getNome());
    	//Usu�rio Repository: Pesquisa usu�rio cujo o nome contenha
    	System.out.println(usuarioRepo.findTop1ByNomeContains("dro").getNome());
    	//Usu�rio Repository: Pesquisa por Id maiores que o informado
    	for(Usuario us: usuarioRepo.findByIdGreaterThan(0l)) {
			System.out.println("Usuario encontrado: " + us.getNome());
		}
    	
    	//Tipo de Material Repository: Pesquisa por nome
    	System.out.println(tipoMaterialRepo.findByNome("pl�stico").getNome());
    	//Tipo de Material Repository: Pesquisa tipo de material cujo o nome contenha
    	System.out.println(tipoMaterialRepo.findTop1ByNomeContains("sti").getNome());
    	//Tipo de Material Repository: Pesquisa por Id maiores que o informado
    	for(TipoMaterial tm: tipoMaterialRepo.findByIdGreaterThan(0l)) {
			System.out.println("Tipo de Material encontrado: " + tm.getNome());
		}
    	
    	//Material Repository: Pesquisa por nome
    	System.out.println(materialRepo.findByNome("garrafa").getNome());
    	//Material Repository: Pesquisa tipo de material cujo o nome contenha
    	System.out.println(materialRepo.findTop1ByNomeContains("ra").getNome());
    	//Material Repository: Pesquisa por Id maiores que o informado
    	for(Material mt: materialRepo.findByIdGreaterThan(0l)) {
			System.out.println("Material encontrado: " + mt.getNome());
		}
    	
    	//Receita Repository: Pesquisa por nome
    	System.out.println(receitaRepo.findByNome("Vaso 1").getNome());
    	//Receita Repository: Pesquisa receita cujo o nome contenha
    	System.out.println(receitaRepo.findTop1ByNomeContains("as").getNome());
    	//Receita Repository: Pesquisa por Id maiores que o informado
    	for(Receita rt: receitaRepo.findByIdGreaterThan(10L)) {
			System.out.println("Receita encontrado: " + rt.getNome());
		}
    	//Receita Repository: Pesquisa as �ltimas 10 receitas cadastradas
    	for(Receita rt: receitaRepo.findFirst10ByOrderByDataInclusaoDesc()) {
			System.out.println("Receita encontrado: " + rt.getNome());
		}
    	
    	//Service Cadastro Material
    	CadastroMaterialService cadMatSer = (CadastroMaterialService)context.getBean("cadastroMaterial");
    	
    	//Cria um tipo de material e nao o salva
    	TipoMaterial tipoMaterialNaoSalvoAinda = new TipoMaterial();
    	tipoMaterialNaoSalvoAinda.setNome("aluminio");
    	
    	//Cria um material com tipo nao salvo
    	Material materialServiceTeste = new Material();
    	materialServiceTeste.setNome("Lata de refrigerante");
    	materialServiceTeste.setDescricao("Uma lata de refrigerante comum, de 350ml.");
    	materialServiceTeste.setTipo(tipoMaterialNaoSalvoAinda);
		
		try {
			cadMatSer.cadastroMaterial(materialServiceTeste);
			System.out.println("Material Cadastrado atrav�s do Service");
		}
		catch(Exception e) {
			System.out.println("Cadastro Material: Erro inesperado! Mas o Rollback foi realizado!");
			tipoMaterialRepo.save(tipoMaterialNaoSalvoAinda);
			// e.printStackTrace();
		}

    	cadMatSer.cadastroMaterial(materialServiceTeste);
		System.out.println("Material Cadastrado atrav�s do Service");
		
		/*//Service Cadastro Tipo Material
		SegurancaService segSer = (SegurancaService)context.getBean("segurancaService");
		
		try {
			segSer.CadastraUsuario();
		}
		catch(Exception e) {
			System.out.println("Cadastro Usuario: Erro inesperado! Mas o Rollback foi realizado!");
			e.printStackTrace();
		}*/
		
		//exclui todas as receitas
		receitaRepo.deleteAll();
		System.out.println("Receita exclu�da!");
		// Exclui material
		materialRepo.deleteAll();
		System.out.println("Material exclu�da!");
		//exclui tipo de material
		tipoMaterialRepo.deleteAll();
		System.out.println("TipoMaterial exclu�da!");
		// Exclui usuario
		usuarioRepo.deleteAll();	
		System.out.println("Usu�rio exclu�da!");
    }
}
