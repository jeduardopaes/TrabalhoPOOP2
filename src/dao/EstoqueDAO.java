package dao;

import java.io.File;
import java.util.List;

public class EstoqueDAO {

	private static ManipulaArquivos bancoDeDados = new ManipulaArquivos();
	private static File arquivo =  new File("BaseDeDados/Estoque.txt");;
	
	public static void salvar(List<String> estoques){
		
		bancoDeDados.escreveLinhasNoArquivo(estoques, arquivo);
		
	}
	
	public static List<String> recuperar(){
		
		return bancoDeDados.lerLinhasDoArquivo(arquivo);
		
	}
	
}
