package model;

public class FornecedorExterno {

	private int codigoID;
	private String nome;
	
	public FornecedorExterno(int codigoID, String nome) {
		super();
		this.codigoID = codigoID;
		this.nome = nome;
	}
	public int getCodigoID() {
		return codigoID;
	}
	public void setCodigoID(int codigoID) {
		this.codigoID = codigoID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		String texto = "";
		String separador = ";";
		
		texto+= getCodigoID()+separador;
		texto+= getNome()+separador;
		
		return texto;
	}
	
	public String show() {
		String texto = "";
		
		texto+= "Código Fornecedor: "+getCodigoID()+"\n"
				+ "\tNome: "+getNome()+".";
		
		return texto;
	}
	
}
