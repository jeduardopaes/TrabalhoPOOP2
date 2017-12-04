package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		
		if(!this.produtos.contains(produto)){
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
			
			if(quantidade>0){
				if(isLockEntrada()) {
					throw new Exception("Almoxarifado Travado para entrada!!");
				}
				this.setUltimaDataDeEntrada(new Date(System.currentTimeMillis()));
				this.setLockEntrada(true);
			}else if(quantidade<0){
				if(isLockSaida()) {
					throw new Exception("Almoxarifado travado para Saida!!");
				}
				this.setUltimaDataDeSaida(new Date(System.currentTimeMillis()));
				this.setLockSaida(true);
			}
			
			this.quantidades.set(this.produtos.indexOf(produto), quantidade);
		}
		
		
		
	}
	
	private void adicionaNovoProduto(Produto produto, int quantidade){
		this.produtos.add(produto);
		this.quantidades.add(quantidade);
	}

	private void destravarEntrada(){
		
		// 1 dia = 86400000 milisegundos (24 * 60 * 60 * 1000).
		long dias = (new Date(System.currentTimeMillis()).getTime() - this.getUltimaDataDeEntrada().getTime()) / 86400000L;
		if(dias > 0){
			this.setLockEntrada(false);
		}
		
	}

	private void destravarSaida(){
		long dias = (new Date(System.currentTimeMillis()).getTime() - this.getUltimaDataDeSaida().getTime()) / 86400000L;
		if(dias > 0){
			this.setLockSaida(false);
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
	
}
