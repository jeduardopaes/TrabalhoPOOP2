package dao;

import java.io.File;
import java.util.List;

public interface ProcessaArquivos {
	
	public List<String> lerLinhasDoArquivo(File ArquivoASerLido);
	
	public void escreveLinhasNoArquivo(List<String> dados, File ArquivoASerGravado);
	
	public void copiaArquivo(File arquivoRecebedor, File arquivoGravador);
}
