package model.entity;

import java.text.SimpleDateFormat;

public class Pessoa {
	private Integer id;
	private String nome;
	private java.sql.Date birthDate;
	private Integer idCidade;
	
	private Cidade cidade;
	

	
	public Pessoa() {
		super();
	}

	public Pessoa(Integer id, String nome, java.sql.Date birthDate, Integer idCidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.birthDate = birthDate;
		
		this.idCidade = idCidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Cidade getCidade() {
		return cidade;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Id: ");
		s.append(id);
		s.append(" - Pessoa: ");
		s.append(nome);
		s.append(" - BirthDate: ");
		s.append(new SimpleDateFormat("dd/MM/yyyy").format(birthDate));
		s.append(" - ");
		s.append(cidade);
		return s.toString();
		
	}
	
	
	
	
}
