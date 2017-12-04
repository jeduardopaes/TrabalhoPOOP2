package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Movimentacao;

public class MovimentacaoDAO {
	
	private static ManipulaArquivos bancoDeDados = new ManipulaArquivos();
	private static File arquivo = new File("BaseDeDados/Movimentacao.txt");
	
	public static void salvar(List<Movimentacao> movimentacoes){
		
		List<String> movimentacoesTexto = new ArrayList<String>();
		
		for(Movimentacao movimentacao : movimentacoes){
			movimentacoesTexto.add(movimentacao.toString());
		}
		
		bancoDeDados.escreveLinhasNoArquivo(movimentacoesTexto, arquivo);
		
	}
	
	public static List<String> recuperar(){
		
		List<String> movimentacoesTexto = bancoDeDados.lerLinhasDoArquivo(arquivo);
		
		return movimentacoesTexto;
		
	}


}
