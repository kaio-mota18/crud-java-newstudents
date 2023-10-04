package Models;
import java.util.Scanner;

public class Student extends People {
	
	StringBuffer buffer = new StringBuffer();	
	String iteratedArrayCpf;
	Scanner input = new Scanner(System.in);	
	
	
	//MÉTODO PARA ALTERAR OS VALORES DO OBJETO STUDENT
	public void registerNewStudent() {
		
		String name;
		String cpf;
		String matricula;
		
		System.out.println("Digite o nome do aluno(a): ");
		name = input.nextLine();		
		this.setName(name);
		
		System.out.print("Digite o CPF *somente números*: ");
		cpf = input.nextLine();				
		this.setArrayCpf(cpf);
		
		System.out.println("Digite a matrícula: ");
		matricula = input.nextLine();		
		this.setEnrollment(matricula);
		
		
	}	

	
	
	//MÉTODO PARA VISUALIZAR OS DADOS NA MEMÓRIA DA ÚLTIMA VEZ QUE USOU O MÉTODOS registerNewStudent()
	public void showRegistration() {	
		
		
		buffer.setLength(0);
		String showName;
		buffer.append("Nome: ");
		buffer.append(this.getName());
		showName = buffer.toString();		
		
		buffer.setLength(0);
		String showCpf;
		buffer.append("CPF: ");
		buffer.append(iteratedArrayCpf);
		showCpf = buffer.toString();
		
		buffer.setLength(0);
		String showEnrollment;
		buffer.append("Matrícula: ");
		buffer.append(this.getEnrollment());
		showEnrollment = buffer.toString();
		
		System.out.println(showName);		
		System.out.println(showCpf);
		System.out.println(showEnrollment);
		System.out.println(" ");
		System.out.println(" _______________________ ");
		
	}

}
	
