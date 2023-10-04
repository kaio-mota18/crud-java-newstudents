package DAO;

import Models.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ConnectionToMySQL.ConnectionFactory;



public class RegisterDAO {
   
	//MÉTODO CREATE
	public void createOnDB(Student newStudent, Connection conn) {
		
		String sql  = "INSERT INTO student (name, cpf, matricula) VALUES (?,?,?)";
		PreparedStatement ps = null;		
		
		
		try {
			//cria a conexão com o banco de dados
			conn = ConnectionFactory.getConnectionToMySQL();
			
			//Criado uma PreparedStatement para rodar a query INSERT
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			//Valores esperados pela query
			ps.setString(1, newStudent.getName());
			ps.setString(2, newStudent.getArrayCpf());
			ps.setString(3, newStudent.getEnrollment());
			
			//executar a query
			ps.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {		
					try {
						
				System.out.println("Query create executada");
				System.out.println(" ");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// MÉTODO READ QUE RETORNA UM ARRAYLIST COM TODOS OS CADASTROS
	public List<Student> read(Connection conn, ResultSet rset, PreparedStatement ps){
		
		String sql = "SELECT * FROM student";
				
		
		//Array que usarei para armazenar os dados que serão recebidos do banco de dados
		List <Student> allStudents = new ArrayList<Student>();
		
		
		
		try {
			//pegando conexão
			conn = ConnectionFactory.getConnectionToMySQL();
			
			//Preparando a minha query SELECT
			ps = (PreparedStatement) conn.prepareStatement(sql);			
			
			// VARIÁVEL QUE ARMAZENA O RESULTADO DA QUERY
			rset = ps.executeQuery();
			
			while(rset.next()) {
				
				Student studentOfTheList = new Student();
				
				//Instruções para capturar e setar as variáveis do Objeto Student
				studentOfTheList.setName(rset.getString("name"));
				studentOfTheList.setArrayCpf(rset.getString("cpf"));
				studentOfTheList.setEnrollment(rset.getString("matricula"));
				
				//peguei cada objeto que recebi do banco de dados, separei as informações de cada coluna e encapsulei. Depois, coloquei dentro do meu arrayList
				allStudents.add(studentOfTheList);
				
			}
			
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		
		return allStudents;
		
	}
		
		//MÉTODO USA UM ARRAY LIST COM TODOS OS CADASTRO NA TABELA STUDENTS E OS EXIBI 
        public void visualizeAll(List<Student> arrayOfStudents){
        
        	StringBuffer buffer = new StringBuffer();        	
		
		    for(Student student : arrayOfStudents) {
		    	
		    	buffer.setLength(0);
		    	String fullName;
		    	buffer.append("Aluno(a): ");
		    	buffer.append(student.getName());
		    	fullName = buffer.toString();
		    	System.out.println(fullName);
		    	
		    	buffer.setLength(0);
		    	String cpf;
		    	buffer.append("CPF: ");
		    	buffer.append(student.getArrayCpf());
		    	cpf = buffer.toString();
		    	System.out.println(cpf);
		    	
		    	buffer.setLength(0);
		    	String matricula;
		    	buffer.append("Número de matrícula: ");
		    	buffer.append(student.getEnrollment());
		    	matricula = buffer.toString();
		    	System.out.println(matricula);
		    	
		    	System.out.println("");
		    	
		}
	}
        //MÉTODO SELECT PARA CAPTURAR UM NOME EM ESPECÍFICO
        public Student selectStudentByName(String name, Connection conn, ResultSet rset, PreparedStatement ps) {
        	
        	String sql = ("SELECT * FROM student WHERE name = ?");
        	Student selectedStudent = new Student();
        	
        	//teste de conexão
        	
        	try {
        		//faz a conexão
        		conn = ConnectionFactory.getConnectionToMySQL();
        		//prepara a query
        		ps = conn.prepareStatement(sql);
        		ps.setString(1, name);
        		//recebe o retorno da query
        		rset = ps.executeQuery();
        		
        		if(rset.next()) {
        			selectedStudent.setName(rset.getString("name"));
        			selectedStudent.setArrayCpf(rset.getString("cpf"));
        			selectedStudent.setEnrollment(rset.getString("matricula"));
        		}
        	}catch(Exception e) {
        		System.out.println("Nome inválido");
        		e.printStackTrace();
        	}
        	
        	return selectedStudent;
        	
        }
        
        public void updateStudentData(Student student, int id, Connection conn, PreparedStatement ps) {
        	
        	String sql = ("UPDATE student SET name = ?, cpf = ?, matricula = ? WHERE idstudent = ?");
        	
        	try {
        		//OBJETO PREPARED STATEMENT COM A MINHA QUERY 
        		ps = conn.prepareStatement(sql);
        		
        		//VALORES QUE SERÃO ALTERADOS
        		ps.setString(1, student.getName());
        		ps.setString(2, student.getArrayCpf());
        		ps.setString(3, student.getEnrollment());
        		ps.setInt(4, id);
        		
        		//executar query
        		int executar = ps.executeUpdate();
        		
        		if(executar>0) {
        			System.out.println("UPDATE REALIZADO");
        			System.out.println("");
        		}else {
        			System.out.println("NENHUMA COLUNA FOI ATUALIZADA");
        		}
        		
        	}catch(Exception e) {        		
        		e.printStackTrace();
        	}
        	
        }
       
      public void deleteFromDB(List <Student> arrayStudent, String name, Connection conn, PreparedStatement ps) {
    	  
    	  for(Student student : arrayStudent) {
    		  String compareName = student.getName();
    		  
    		  if(compareName.equals(name)) {
    			  String sql = ("DELETE FROM student WHERE name = ?");
    			  
    			  try {
    				  //PREPARANDO CONEXÃO
    				  ps = conn.prepareStatement(sql);
    				  
    				  //ATRIBUINDO O VALOR NA QUERY
    				  ps.setString(1, compareName);
    				  
    				  //EXECUTAR QUERY
    				  ps.execute();
    				  System.out.println("DELETE EXECUTADO");
    				  System.out.println(" ");
    			  }catch(Exception e) {
    				  e.printStackTrace();
    			  }    			  
    		  }else {    			  
    			  System.out.println("");
    		  }
    	  }
    	  
    	  
    	  
      }
	
}
















