package application;

import java.util.List;

import model.dao.DCidade;
import model.entity.Cidade;



public class Application {
	public static void main(String []args) {
		
	
		
		DCidade bc = new DCidade();
		
		Cidade c = bc.consulta(8);
		
		System.out.println(c);
	}
}
