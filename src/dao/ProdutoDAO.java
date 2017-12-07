package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.FornecedorExterno;
import model.Produto;

public class ProdutoDAO {
	
	private static ManipulaArquivos bancoDeDados = new ManipulaArquivos();
	private static File arquivo = new File("BaseDeDados/Produto.txt");
	
	public static void salvar(List<Produto> produtos){
		
		List<String> produtosTexto = new ArrayList<String>();
		
		for(Produto produto :produtos){
			produtosTexto.add(produto.toString());
		}
		
		bancoDeDados.escreveLinhasNoArquivo(produtosTexto, arquivo);
		
	}
	
	public static List<Produto> recuperar(){
		List<String> produtosTexto = bancoDeDados.lerLinhasDoArquivo(arquivo);
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		for(String linha : produtosTexto){
			String[] linhaQuebrada = linha.split(";");
			
			boolean fornecedorExterno = Boolean.parseBoolean(linhaQuebrada[3]);
			
			
			if(fornecedorExterno){
				
				int codigoProduto = Integer.parseInt(linhaQuebrada[0]);
				String nomeProduto = linhaQuebrada[1];
				String descricaoProduto = linhaQuebrada[2];
				FornecedorExterno codigoFornecedor = FornecedorExternoDAO.get(Integer.parseInt(linhaQuebrada[4]));
				
				produtos.add(new Produto(codigoProduto,
						nomeProduto,
						descricaoProduto,
						fornecedorExterno,
						codigoFornecedor
						));
			}else{
				
				int codigoProduto = Integer.parseInt(linhaQuebrada[0]);
				String nomeProduto = linhaQuebrada[1];
				String descricaoProduto = linhaQuebrada[2];
				
				produtos.add(new Produto(codigoProduto,
						nomeProduto,
						descricaoProduto,
						fornecedorExterno
						));
			}
			
		}
		
		return produtos;
		
	}
	
	public static Produto getProduto(int codigoProduto){
		List<Produto> produtos = recuperar();
		
		for(Produto prod : produtos){
			if(codigoProduto == prod.getCodigoID()){
				return prod;
			}
		}
		
		return null;
		
	}
	
}
