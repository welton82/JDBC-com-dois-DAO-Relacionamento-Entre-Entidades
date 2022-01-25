package model.entity;

import java.util.Objects;

public class Cidade {
	private Integer id;
	private String nome;
	public Cidade() {
		super();
	}
	public Cidade(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(" Cidade: ");
		s.append(nome);
		s.append(" - Id: ");
		s.append(id);
		return s.toString();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
