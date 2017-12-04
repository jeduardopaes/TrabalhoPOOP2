package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.FornecedorExterno;

public class FornecedorExternoDAO {

	private static ManipulaArquivos bancoDeDados = new ManipulaArquivos();
	private static File arquivo = new File("BaseDeDados/FornecedorExterno.txt");;
	
	
	public static void salvar(List<FornecedorExterno> fornecedoresExternos){
		List<String> fornecedoresTexto = new ArrayList<String>();
		
		for(FornecedorExterno fornecedor : fornecedoresExternos){
			fornecedoresTexto.add(fornecedor.toString());
		}
		
		bancoDeDados.escreveLinhasNoArquivo(fornecedoresTexto, arquivo);;
	}
	
	public static List<FornecedorExterno> recuperar(){
		List<String> fornecedoresTexto = bancoDeDados.lerLinhasDoArquivo(arquivo);
		
		List<FornecedorExterno> fornecedores = new ArrayList<FornecedorExterno>();
		
		for(String linha : fornecedoresTexto){
			String[] linhaQuebrada = linha.split(";");
			
				int codigoFornecedorExterno = Integer.parseInt(linhaQuebrada[0]);
				String nomeFornecedorExterno = linhaQuebrada[1];
				
				
				fornecedores.add(new FornecedorExterno(codigoFornecedorExterno,
						nomeFornecedorExterno
						));
			
			
		}
		
		return fornecedores;
	}
	
	public static FornecedorExterno get(int codigoFornecedorExterno){
		List<FornecedorExterno> fornecedores = recuperar();
		
		for(FornecedorExterno f :fornecedores){
			if(codigoFornecedorExterno == f.getCodigoID()){
				return f;
			}
		}
		
		return null;
	}
	
}
