package br.gov.sp.fatec.model;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "RCT_RECEITA")
public class Receita {
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "RCT_ID")
	@JsonView(View.All.class)
	private Long id;
    
    @Column(name = "RCT_NOME", length = 50, nullable = false)
    @JsonView(View.All.class)
    private String nome;
    
    @Column(name = "RCT_DESCRICAO", length = 750, nullable = false)
    @JsonView(View.All.class)
    private String descricao;
    
    @Column(name = "RCT_DATAINCLUSAO")
    private Date dataInclusao = new Date();
    
    @ManyToOne
    @JoinColumn(name = "USR_ID")
    @JsonView(View.All.class)
    private Usuario usuario;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RCM_RECEITAMATERIAL", 
    	joinColumns = { @JoinColumn(name = "RCT_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "MTR_ID") })
    @JsonView(View.All.class)
    private List<Material> materiais;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}
    
}
