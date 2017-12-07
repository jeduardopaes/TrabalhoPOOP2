package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import helper.DateHelper;

public class Almoxarifado {
	
	private boolean lockEntrada;
	private boolean lockSaida;
	private Date ultimaDataDeEntrada;
	private Date ultimaDataDeSaida;
	private boolean status;
	private int codigoID;
	private String localizacao;
	private List<Produto> produtos;
	private List<Integer> quantidades;
	
	public Almoxarifado(int codigo, String localizacao){
		this.localizacao = localizacao;
		this.codigoID = codigo;
		this.produtos = new ArrayList<Produto>();
		this.quantidades = new ArrayList<Integer>();
		this.status = true;
		
		this.ultimaDataDeEntrada = new Date(000000000);
		this.ultimaDataDeSaida = new Date(000000000);
		
	}
	
	public Almoxarifado(int codigo, String localizacao, Date entrada, Date saida) {
		this.localizacao = localizacao;
		this.codigoID = codigo;
		this.produtos = new ArrayList<Produto>();
		this.quantidades = new ArrayList<Integer>();
		this.status = true;
		
		this.ultimaDataDeEntrada = entrada;
		this.ultimaDataDeSaida = saida;
		
	}

	public boolean isLockEntrada() {
		return lockEntrada;
	}

	private void setLockEntrada(boolean lockEntrada) {
		this.lockEntrada = lockEntrada;
	}

	public boolean isLockSaida() {
		return lockSaida;
	}

	private void setLockSaida(boolean lockSaida) {
		this.lockSaida = lockSaida;
	}

	public Date getUltimaDataDeEntrada() {
		return ultimaDataDeEntrada;
	}

	public void setUltimaDataDeEntrada(Date ultimaDataDeEntrada) {
		this.ultimaDataDeEntrada = ultimaDataDeEntrada;
	}

	public Date getUltimaDataDeSaida() {
		return ultimaDataDeSaida;
	}

	public void setUltimaDataDeSaida(Date ultimaDataDeSaida) {
		this.ultimaDataDeSaida = ultimaDataDeSaida;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCodigoID() {
		return codigoID;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Integer> getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(List<Integer> quantidades) {
		this.quantidades = quantidades;
	}
	
	public int getQuantidade(Produto produto) throws Exception{
		if(!this.produtos.contains(produto)){
			throw new Exception("Produto Não Encontrado");
		}
		
		return this.quantidades.get(this.produtos.indexOf(produto));
		
	}
	
	public void setQuantidade(Produto produto, int quantidade) throws Exception{
		
		this.destravarEntrada();
		this.destravarSaida();
		
		
		if(quantidade == 0){
			throw new Exception("Quantidade Inválida!!");
		}
		
		if(this.produtos.contains(produto) == false){
			if(quantidade<0){
				throw new Exception("Produto não encontrado!!");
			}else{
				if(isLockEntrada()) {
					throw new Exception("Almoxarifado Travado para entrada!!");
				}
				this.setUltimaDataDeEntrada(new Date(System.currentTimeMillis()));
				this.adicionaNovoProduto(produto, quantidade);
			}
		}else {
			
			int index = this.produtos.indexOf(produto);
			
			if(quantidade>0){
				if(isLockEntrada()) {
					throw new Exception("Almoxarifado Travado para entrada!!");
				}
				this.setUltimaDataDeEntrada(new Date(System.currentTimeMillis()));
				quantidade+= this.quantidades.get(index);
				this.setLockEntrada(true);
				this.quantidades.set(index, quantidade);
				
			}else if(quantidade<0){
				if(isLockSaida()) {
					throw new Exception("Almoxarifado travado para Saida!!");
				}
				this.setUltimaDataDeSaida(new Date(System.currentTimeMillis()));
				quantidade+= this.quantidades.get(index);
				if(quantidade<0) {
					throw new Exception("Capacidade indispoível!!");
				}
				this.setLockSaida(true);
				this.quantidades.set(index, quantidade);
			}
			
			
		}
		
		
		
	}
	
	private void adicionaNovoProduto(Produto produto, int quantidade){
		this.produtos.add(produto);
		this.quantidades.add(quantidade);
	}

	private void destravarEntrada(){
		
		// 1 dia = 86400000 milisegundos (24 * 60 * 60 * 1000).
		long dias = (new Date(System.currentTimeMillis()).getTime() - this.getUltimaDataDeEntrada().getTime()) / 86400000L;
		
		//==========================================================================================================================================================
//		System.err.println(DateHelper.getDataFormated(new Date(System.currentTimeMillis()))+"\t"
//				+ ""+DateHelper.getDataFormated(this.getUltimaDataDeEntrada())+"\t"+dias+"\t"+isLockEntrada());
		
		if(dias > 0){
			this.setLockEntrada(false);
		}
		
	}

	private void destravarSaida(){
		long dias = (new Date(System.currentTimeMillis()).getTime() - this.getUltimaDataDeSaida().getTime()) / 86400000L;

		//==========================================================================================================================================================
//		System.err.println(DateHelper.getDataFormated(new Date(System.currentTimeMillis()))+"\t"
//				+ ""+DateHelper.getDataFormated(this.getUltimaDataDeSaida())+"\t"+dias+"\t"+isLockSaida());
		
		if(dias > 0){
			this.setLockSaida(false);
		}
	}
	
	public void verificaTravas() {
		long diasSaida = (new Date(System.currentTimeMillis()).getTime() - this.getUltimaDataDeSaida().getTime()) / 86400000L;
		long diasEntrada = (new Date(System.currentTimeMillis()).getTime() - this.getUltimaDataDeEntrada().getTime()) / 86400000L;
		
		if(diasSaida<=0) {
			this.setLockSaida(true);
		}
		
		if(diasEntrada<=0) {
			this.setLockEntrada(true);
		}
		
	}
	
	@Override
	public String toString() {
		String texto ="";
		String separador = ";";
		
		texto += codigoID+separador
				+ localizacao+separador
				+ status+separador
				+ ultimaDataDeSaida.getTime()+separador
				+ ultimaDataDeEntrada.getTime()+separador;
		
		return texto;
	}
	
	public String show() {
		String texto ="";
		
		texto+= "Código: "+codigoID+"\n"
				+"\tLocalização: "+localizacao
				+"\tStatus: "+status
				+"\tData última entrada: "+DateHelper.getDataFormated(ultimaDataDeEntrada)
				+"\tData última saida: "+DateHelper.getDataFormated(ultimaDataDeSaida);
		
		return texto;
	}
	
}
