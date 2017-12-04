package model;

public class Produto {
	
	private int codigoID;
	private String nome;
	private String descricao;
	private boolean fornecedorExtrerno;
	private FornecedorExterno codigoDoFornecedor;
	
	public Produto(int codigoID, String nome, String descricao, boolean fornecedorExtrerno) {
		super();
		this.codigoID = codigoID;
		this.nome = nome;
		this.descricao = descricao;
		this.fornecedorExtrerno = fornecedorExtrerno;
	}
	
	public Produto(int codigoID, String nome, String descricao, boolean fornecedorExtrerno,
			FornecedorExterno codigoDoFornecedor) {
		super();
		this.codigoID = codigoID;
		this.nome = nome;
		this.descricao = descricao;
		this.fornecedorExtrerno = fornecedorExtrerno;
		this.codigoDoFornecedor = codigoDoFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFornecedorExtrerno() {
		return fornecedorExtrerno;
	}

	public void setFornecedorExtrerno(boolean fornecedorExtrerno) {
		this.fornecedorExtrerno = fornecedorExtrerno;
	}

	public int getCodigoDoFornecedor() {
		return codigoDoFornecedor.getCodigoID();
	}

	public void setCodigoDoFornecedor(FornecedorExterno codigoDoFornecedor) {
		this.codigoDoFornecedor = codigoDoFornecedor;
	}

	public int getCodigoID() {
		return codigoID;
	}
	
	@Override
	public String toString() {
		String texto = "";
		
		texto += codigoID+";"
				+nome+";"
				+descricao+";"
				+fornecedorExtrerno+";";
		
		if(fornecedorExtrerno){
			texto += codigoDoFornecedor.getCodigoID()+";"; 
		}
		
		return texto;
	}
	
	public String show() {
	String texto = "";
		
		texto += "Código: "+codigoID+"\n"
				+"\tNome: "+nome+";"
				+"\tDescrição: "+descricao+";"
				+"\tFornecedorExterno: "+fornecedorExtrerno+";";
		
		if(fornecedorExtrerno){
			texto += "\tCódigo do Fornecedor: "+codigoDoFornecedor.getCodigoID()+";"; 
		}
		
		return texto;
	}
	

}
