package br.gov.sp.fatec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USR_USUARIO")
public class Usuario {

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USR_ID")
	private Long id;
    
    @Column(name = "USR_NOME", unique=true, length = 20, nullable = false)
    private String nome;
    
    @Column(name = "USR_SENHA", length = 50, nullable = false)
    private String senha;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID")
    private List<Receita> receitas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	

}
