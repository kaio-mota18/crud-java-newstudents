package Models;

abstract public class People {
	private String name;
	private String arrayCpf;
	private String enrollment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArrayCpf() {
		return arrayCpf;
	}
	public void setArrayCpf(String arrayCpf) {
		this.arrayCpf = arrayCpf;
	}	
	
	public String getEnrollment() {
		return enrollment;
	}
	
	public void setEnrollment(String arrayEnrollment) {
		this.enrollment = arrayEnrollment;
	}
}
