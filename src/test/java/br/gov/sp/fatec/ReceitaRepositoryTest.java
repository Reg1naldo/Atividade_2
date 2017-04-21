package br.gov.sp.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Receita;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.ReceitaRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"} )
public class ReceitaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String NOMERECEITA = "Receita 1";
	private static final String DESCRICAORECEITA = "1º derreta o material no fogo; 2º enquanto esfria molde o material conforme você queira; 3º está pronto seu produto";
	private static final String NOME = "Fulano";
	private static final String SENHA = "123456";
	
	@Autowired
	private ReceitaRepository receitaRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	/**
	 * @param materialRepo the materialRepo to set
	 */
	
	public void setReceitaRepo(ReceitaRepository receitaRepo){
		this.receitaRepo = receitaRepo;
	}
	
	@Test
	public void testeCadastroReceita(){
		
		Usuario usuario = new Usuario();
		usuario.setNome(NOME);
		usuario.setSenha(SENHA);
		usuarioRepo.save(usuario);
		
		
		Receita receita = new Receita();
		receita.setNome(NOMERECEITA);
		receita.setDescricao(DESCRICAORECEITA);
		receita.setUsuario(usuario);
		receitaRepo.save(receita);
		assertTrue(receita.getId()!=null);	
		
	}
}
