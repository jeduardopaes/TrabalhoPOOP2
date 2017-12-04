package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManipulaArquivos implements ProcessaArquivos {
	
	
	@Override
	public List<String> lerLinhasDoArquivo(File ArquivoASerLido) {
		
		List<String> dados = new ArrayList<String>();
		
		try (BufferedReader leitorParaArquivo = new BufferedReader(new FileReader(ArquivoASerLido))) {
			
			String linha = leitorParaArquivo.readLine(); //Lê a primeira Linha Do arquivo!
			
			while(linha != null){
				dados.add(linha);
				linha = leitorParaArquivo.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return dados;
	}

	@Override
	public void escreveLinhasNoArquivo(List<String> dados, File ArquivoASerGravado) {
		try (BufferedWriter escritorParaArquivo = new BufferedWriter(new FileWriter(ArquivoASerGravado))) {
			int numeroDeLinhas = 0;
			
			for(String linha : dados){
				if(numeroDeLinhas++ > 0){
					escritorParaArquivo.newLine();
				}
				escritorParaArquivo.write(linha);
			}
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void copiaArquivo(File arquivoRecebedor, File arquivoGravador) {
		List<String> dados = lerLinhasDoArquivo(arquivoRecebedor);
		escreveLinhasNoArquivo(dados, arquivoGravador);
	}
	
	
	
}
