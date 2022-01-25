package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexao;
import db.DbException;
import db.IntegrityDataBase;
import model.entity.Cidade;
import model.entity.Pessoa;

public class DPessoa {
	Connection cnn;
	PreparedStatement prds;
	
	Statement  st;
	ResultSet rs;

	
	public DPessoa() {
		this.cnn = Conexao.getConnection();
	}
	
		
	
	public void inserir(Pessoa pessoa) {
		
		try {
			
			String sql = "insert into pessoa(id, nome, birthDate, idCidade) values (?,?,?,?);";
			prds = cnn.prepareStatement(sql);
			
			prds.setInt(1, pessoa.getId());
			prds.setString(2, pessoa.getNome());
			prds.setDate(3, new java.sql.Date(pessoa.getBirthDate().getTime()));
			prds.setInt(4, pessoa.getIdCidade());
									

			prds.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public List<Pessoa> listar(){
		List<Pessoa>lista = new ArrayList<>();
		String sql = "SELECT pessoa .*, cidade.nome as cidnome FROM pessoa INNER JOIN "
				+ "cidade ON pessoa.idcidade = cidade.id;";
		
		try {
			/*
			 * OU:	 
			st = cnn.createStatement();
			rs = st.executeQuery(sql);
			*/
			//OU:
			prds = cnn.prepareStatement(sql);
			rs = prds.executeQuery();
			
			if(rs.next()) {
				
				while(rs.next()) {
					Pessoa p = new Pessoa();
					Cidade c = new Cidade();
					
					c.setId(rs.getInt("idcidade"));
					c.setNome(rs.getString("cidnome"));
					
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setBirthDate(rs.getDate("birthdate"));
					p.setIdCidade(c.getId());
					p.setCidade(c);
					
					lista.add(p);
				}
				
				return lista;
			}
			return null;
			
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		
	}
	
	public Pessoa consultar(Integer id) {
		try {
			String sql = "select pessoa .*, cidade.nome as CidNome from pessoa inner join "
					+ "cidade on pessoa.idcidade = cidade.id"
					+ " where pessoa.id = ?;";
			prds = cnn.prepareStatement(sql);
			prds.setInt(1, id);
			rs = prds.executeQuery();
			
			if(rs.next()) {
				
				Cidade cid = new Cidade();
				cid.setId(rs.getInt("idCidade"));
				cid.setNome(rs.getString("CidNome"));
				
				Pessoa pes = new Pessoa();
				pes.setId(rs.getInt("id"));
				pes.setNome(rs.getString("nome"));
				pes.setBirthDate(rs.getDate("birthdate"));
				pes.setCidade(cid);
				return pes;
			}
			
			return null;
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
				
		
	}
	
	public void atualizar(Pessoa pessoa) {

		try {
			String sql = "UPDATE pessoa SET id = ?, nome = ?, birthdate = ?, idcidade = ? WHERE id = ?;";
			prds = cnn.prepareStatement(sql);
			
			prds.setInt(1, pessoa.getId());
			prds.setString(2, pessoa.getNome());
			prds.setDate(3, pessoa.getBirthDate());
			prds.setInt(4, pessoa.getIdCidade());
			prds.setInt(5, pessoa.getId());
			
			prds.executeUpdate();
		
			
			
		}catch(SQLException e) {
			throw new IntegrityDataBase(e.getMessage());
		}
	}
	
	public void deletar(Integer id) {
		
		try {
			
			String sql = "DELETE FROM pessoa WHERE id = ?;";
			
			prds = cnn.prepareStatement(sql);
			prds.setInt(1, id);
			
			int line = prds.executeUpdate();
			
			if(line == 0) {
				System.out.println("NÃO EXISTE OS PESSOA NO BANCO DE DADOS com id: " + id);
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
		}
	}
}














