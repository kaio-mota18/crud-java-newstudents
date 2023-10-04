import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Models.Student;
import DAO.RegisterDAO;
import ConnectionToMySQL.ConnectionFactory;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        //OBJETOS PARA CRIAR UM NOVO CADASTRO E MANDAR PARA O DB
        Student student = new Student();
        RegisterDAO registerDAO = new RegisterDAO();
        
        //LISTA PARA ARMAZENAR TODOS OS CADASTROS E EXIBIR
        List <Student> registeredStudents = new ArrayList<Student>();
        
        
        //OBJETOS para criar a conexão com o SQL, RECEBER O RETORNO DA QUERY E PREPARAR A STRING-SQL
        PreparedStatement ps = null;
        ResultSet rset = null;   
        Connection conn = ConnectionFactory.getConnectionToMySQL();
        
        //PROMPT DE MENU
        System.out.println("Bem-vindo ao gestor de alunos e funcionários!");
        System.out.println(" ");
        System.out.println("__ __ __ __ __ __ __");

        int option;
        
        try {
        	 do {
                 System.out.println("O que deseja fazer? ");
                 System.out.println("1 - Cadastrar um(a) novo(a) aluno(a) ");            
                 System.out.println("2 - Visualizar último cadastro realizado hoje ");
                 System.out.println("3 - Visualizar cadastros ");
                 System.out.println("4 - Atualizar cadastro ");
                 System.out.println("5 - Deletar cadastro");
                 System.out.println("6 - Sair ");

                 while (!input.hasNextInt()) {
                     System.out.println("Entrada inválida");
                     System.out.println("__ __ __ __ __ __ __");
                     System.out.println("Digite uma das opções de menu:");
                     input.nextLine(); // Adicione esta linha para consumir a entrada inválida
                 }

                 option = input.nextInt();
                 input.nextLine(); // Adicione esta linha para consumir a quebra de linha após a opção

                 switch (option) {
                     case 1:
                         student.registerNewStudent();                         
                         registerDAO.createOnDB(student, conn);
                         break;
                     case 2:
                         student.showRegistration();
                         break;
                     case 3:
                    	    int control;
                    	    do {
                    	        System.out.println("DIGITE");
                    	        System.out.println("");
                    	        System.out.println("1- Visualizar todos os cadastros");
                    	        System.out.println("2 - Selecionar um cadastro");
                    	        System.out.println("3 - Voltar");

                    	        while (!input.hasNextInt()) {
                    	            System.out.println("Entrada inválida");
                    	            System.out.println("__ __ __ __ __ __ __");
                    	            System.out.println("Digite uma das opções de menu:");
                    	            input.nextLine(); 
                    	        }

                    	        control = input.nextInt();
                    	        input.nextLine(); 

                    	        switch (control) { 
                    	            case 1:
                    	                registeredStudents = registerDAO.read(conn, rset, ps);
                    	                registerDAO.visualizeAll(registeredStudents);
                    	                break;
                    	            case 2:
                    	            	String nameToSelect;
                    	            	
                    	            	System.out.println("Insira o nome");
                    	            	nameToSelect = input.nextLine();
                    	            	
                    	                student = registerDAO.selectStudentByName(nameToSelect, conn, rset, ps);
                    	                
                    	                System.out.println(student.getName());
                    	                System.out.println(student.getArrayCpf());
                    	                System.out.println(student.getEnrollment());
                    	                System.out.println("");
                    	                System.out.println("__________________________________________________");
                    	                System.out.println("");
                    	                break;
                    	            case 3:
                    	                break;
	                    	            default:
	                    	                System.out.println("Opção inválida");
			                    	        }
						            } while (control != 3); // Continue o loop com base na variável "control"
						            break;
                     case 4:
                    	 int id;
                    	 int controlOfUpdate;   	 
                    	 
                    	 System.out.println("Digite o ID do cadastro que deseja alterar ");
                    	 id = input.nextInt();
                    	 input.nextLine();
                    	 
                    	 do {
                    		 System.out.println("O que deseja alterar?");
                    		 System.out.println("");
                    		 System.out.println("1 - Nome");
                    		 System.out.println("2 - CPF");
                    		 System.out.println("3 - Matrícula");
                    		 System.out.println("4 - Voltar");                    		 
                    		                    		 
                    		 while(!input.hasNextInt()) {
                    			 System.out.println("Entrada inválida. Digite uma das opções do menu");
                    			 input.nextLine();
                    		 }
                    		 
                    		 controlOfUpdate = input.nextInt();
                    		 input.nextLine();
                    		 
                    		 switch (controlOfUpdate) {
                    		 	case 1:                    		 		
                    		 		String updateName;
                    		 		System.out.println("Digite o novo nome: ");
                    		 		updateName = input.nextLine();
                    		 		
                    		 		//alteração do valor name no OBJETO STUDENT
                    		 		student.setName(updateName);
                    		 		
                    		 		//ALTERAÇÃO NO DB
                    		 		registerDAO.updateStudentData(student, id, conn, ps);                    		 		
                    		 		break;
                    		 	case 2:                    		 		
                    		 		String updateCPF;
                    		 		System.out.println("Digite o novo CPF: ");
                    		 		updateCPF = input.nextLine();
                    		 		
                    		 		//alteração do valor CPF no OBJETO STUDENT
                    		 		student.setArrayCpf(updateCPF);                    		 		
                    		 		
                    		 		//ALTERAÇÃO NO DB
                    		 		registerDAO.updateStudentData(student, id, conn, ps);            		 		
                    		 		break;
                    		 	case 3:                    		 		
                    		 		String updateEnrollment;
                    		 		System.out.println("Digite a nova matrícula: ");
                    		 		updateEnrollment = input.nextLine();
                    		 		
                    		 		//alteração do valor name no OBJETO STUDENT
                    		 		student.setEnrollment(updateEnrollment);
                    		 		
                    		 		//ALTERAÇÃO NO DB
                    		 		registerDAO.updateStudentData(student, id, conn, ps);
                    		 		break; 
                    		 	case 4:
                    		 		break;
                    		 	default:
                    		 		System.out.println("Opção inválida");
                    		 }
                    	 }while(controlOfUpdate!=4);
                    	 break;
                     case 5:
                    	 //LEITURA 
                    	 String deleteByName;
                    	 System.out.println("Digite o nome do cadastro a ser deletado");
                    	 deleteByName = input.nextLine();
                    	 
                    	 //LEITURA DE TODOS OS NOMES DO DB PARA SEREM CHECADOS
                    	 registeredStudents = registerDAO.read(conn, rset, ps);
                    	 
                    	 //MÉTODO PARA DELETAR
                    	 registerDAO.deleteFromDB(registeredStudents, deleteByName, conn, ps);
                    	 
                    	 break;
                     case 6:
                    	 System.out.println("Obrigado por usar nosso sistema");
                    	 break;
                     default:
                    	 System.out.println("Opção inválida.");
                    	 System.out.println("");
                 }
                 

             } while (option != 6);
             
             input.close();
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }finally{
			try {
				if (rset != null) {
					rset.close();
				}
				
				if(ps != null) {
					ps.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

       
    }
}
