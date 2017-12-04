package model;

public class Empregado {
	
	private int matricula;
	private String nome;
	private String funcao;
	
	public Empregado(int matricula, String nome, String funcao) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.funcao = funcao;
	}

	public int getMatricula() {
		return matricula;
	}

//	public void setMatricula(int matricula) {
//		this.matricula = matricula;
//	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	@Override
	public String toString() {
		String texto = "";
		String separador = ";";
		
		texto+= getMatricula()+separador
				+getNome()+separador
				+getFuncao()+separador;
		
		return texto;
	}
	
	public String show() {
		String texto = "";
		
		
		texto+= "Matricula: "+getMatricula()+"\n"
				+"\tNome: "+getNome()+";"
				+"\tFunção: "+getFuncao()+"\n";
		
		return texto;
	}

}
