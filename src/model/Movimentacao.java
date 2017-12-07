package model;

import java.util.Date;

import helper.DateHelper;

public class Movimentacao {
	
	private boolean entrada;
	private Produto produto;
	private int quantidade;
	private Almoxarifado almoxarifado;
	private Date data;
	private Empregado empregado;

	public Movimentacao(boolean entrada, Produto produto, int quantidade, Almoxarifado almoxarifado, Date data, Empregado empregado) {
		super();
		this.entrada = entrada;
		this.produto = produto;
		this.quantidade = quantidade;
		this.almoxarifado = almoxarifado;
		this.data = data;
		this.empregado = empregado;
		
		
	}

	public boolean isEntrada() {
		return entrada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Date getData() {
		return data;
	}
	
	public int getCodigoDoProduto() {
		return this.produto.getCodigoID();
	}
	
	public int getCodigoAlmoxarifado() {
		return this.almoxarifado.getCodigoID();
	}
	
	@Override
	public String toString() {
		
		String texto ="";
		String separador = ";";
		
		texto+= isEntrada()+separador;
		texto+= this.produto.getCodigoID()+separador;
		texto+= this.almoxarifado.getCodigoID()+separador;
		texto+= getQuantidade()+separador;
		texto+= getData().getTime()+separador;
		texto+= this.empregado.getMatricula()+separador;
		
		
		return texto;
	}
	
	public String show() {
		
		String texto ="";
		
		texto+= "Código Produto: " +this.produto.getCodigoID()
				+ " Código Almoxarifado: "+this.almoxarifado.getCodigoID()
				+ " Quantidade: "+getQuantidade()
				+ " Data: "+DateHelper.getDataFormated(getData())
				+ " Código Empregado: "+this.empregado.getMatricula()+".\n";
		
		return texto;
	}
	

}
