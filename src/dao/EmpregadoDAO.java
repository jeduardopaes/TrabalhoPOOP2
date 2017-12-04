package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Empregado;

public class EmpregadoDAO {
	
	private static ManipulaArquivos bancoDeDados = new ManipulaArquivos();
	private static File arquivo = new File("BaseDeDados/Empregado.txt");
	
	public static void salvar(List<Empregado> empregados){
		
		List<String> empregadosTexto = new ArrayList<String>();
		
		for(Empregado empregado : empregados){
			empregadosTexto.add(empregado.toString());
		}
		
		bancoDeDados.escreveLinhasNoArquivo(empregadosTexto, arquivo);
		
	}
	
	public static List<Empregado> recuperar(){
		
		List<Empregado> empregados = new ArrayList<Empregado>();
		List<String> empregadosTexto = bancoDeDados.lerLinhasDoArquivo(arquivo);
		
		for(String linha : empregadosTexto){
			String[] linhaQuebrada = linha.split(";");
			
			int codigoEmpregado = Integer.parseInt(linhaQuebrada[0]);
			String nomeEmpregado = linhaQuebrada[1];
			String funcaoEmpregado = linhaQuebrada[2];
			
			empregados.add(new Empregado( codigoEmpregado,
					nomeEmpregado,
					funcaoEmpregado
					));
			
		}
		
		return empregados;
		
	}
	
}
