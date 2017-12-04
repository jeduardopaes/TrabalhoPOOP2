package tela;

import java.util.Scanner;

import controller.ProgramaController;
import model.Empregado;

public class TelaEmpregado {

	private Scanner input = new Scanner(System.in);
	private ProgramaController prog; 
	
	public TelaEmpregado(ProgramaController programaController) {
		this.prog = programaController;
	}
	
	public void menu() {
		
		int opcaoEscolhida = 0;
		
		do {
		System.out.print("==========================================================\n\n"
				+ "Digite uma das Opções:\n"
				+ "1- Cadastrar\n"
				+ "2- Listar\n"
				+ "3- Buscar\n"
				+ "4- Voltar\n");
		
		opcaoEscolhida = input.nextInt();
		
		switch(opcaoEscolhida) {
		case 1:
			cadastarEmpregado();
			break;
		case 2:
			listarEmpregados();
			break;
		case 3:
			buscarEmpregado();
			break;
		case 4: 
			TelaPrincipal.main(null);
			break;
		default:
			System.err.println("Opção Inválida!");
			break;
		}
		}while(opcaoEscolhida != 4);
		
	}

	private void buscarEmpregado() {
		
		System.out.println("Digite código do Empregado:");
		int matEmp = input.nextInt();
		
		Empregado empregadoEncontrado = prog.empregadoBuscar(matEmp);
		
		if(empregadoEncontrado != null) {
			System.out.println(empregadoEncontrado.show());
		}else {
			System.err.println("Empregado não encontrado!!");
		}
		
		
	}

	private void listarEmpregados() {
		
		System.out.println(prog.empregadoLista());
		
	}

	private void cadastarEmpregado() {
		
		@SuppressWarnings("unused")
		String lixo;
		
		System.out.println("Digite código do Empregado:");
		int matEmp = input.nextInt();
		lixo = input.nextLine();
		System.out.println("Digite o nome do empregado:");
		String nome = input.nextLine();
		System.out.println("Digite a função do empregado:");
		String funcao = input.nextLine();
		
		Empregado empregado = new Empregado(matEmp, nome, funcao);
		
		prog.empregadoIncluir(empregado);
		
	}
	
	
	
}
