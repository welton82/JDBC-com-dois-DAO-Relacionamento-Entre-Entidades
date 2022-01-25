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

public class DCidade {
	Connection cnn = null;
	PreparedStatement prds = null;
	Statement st =null;
	ResultSet rs = null;
	
	
	
	public DCidade() {
		this.cnn = Conexao.getConnection();
	}
	
	public void inseri(Cidade obj) {
		try {
			String sql = "INSERT INTO cidade(id, nome) VALUES(?,?);";
			prds = cnn.prepareStatement(sql);

			prds.setInt(1, obj.getId());
			prds.setString(2, obj.getNome());
			
			prds.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	public void atualizar(Cidade obj) {
		try {
			
			String sql = "UPDATE SET cidade id = ?, nome = ? WHERE id = ?;";
			
			prds = cnn.prepareStatement(sql);
			prds.setInt(1, obj.getId());
			prds.setString(2, obj.getNome());
			
			prds.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM cidade WHERE id = ?;";
			
			prds = cnn.prepareStatement(sql);
			
			prds.setInt(1, id);
			
			int row = prds.executeUpdate();
			if(row == 0) {
				System.out.println("NÃO EXISTE CIDADE NO BANCO DE DADOS");
			}
		}catch(SQLException e) {
			
			throw new IntegrityDataBase(e.getMessage());
		}
	}
	public List<Cidade>listar(){
		try {
			String sql = "SELECT *FROM cidade ORDER BY nome;";
			st = cnn.createStatement();
			rs = st.executeQuery(sql);
			List<Cidade> lista = new ArrayList<>();
			while(rs.next()) {
				Cidade c = new Cidade();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				lista.add(c);
			}
			return lista;
		}catch(SQLException e) {
			throw new IntegrityDataBase(e.getMessage());
		}
	}
	public Cidade consulta(Integer id) {
		
		try {
			
			String sql = "SELECT *FROM cidade WHERE id = ?;";
			
			prds = cnn.prepareStatement(sql);
			prds.setInt(1, id);
			
			rs = prds.executeQuery();
			Cidade c = new Cidade();
			if(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				return c;
			}else {
				System.out.println("NÃO EXISTE ID DA CIDADE INFORMADO!");
				return null;
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage()); 
		}
		
	}

}
