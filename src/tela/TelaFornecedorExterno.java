package tela;

import java.util.Scanner;

import controller.ProgramaController;
import model.FornecedorExterno;

public class TelaFornecedorExterno {

	private Scanner input = new Scanner(System.in);
	private ProgramaController prog; 
	
	public TelaFornecedorExterno(ProgramaController programaController) {
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
			cadastarFornecedor();
			break;
		case 2:
			listarFornecedor();
			break;
		case 3:
			buscarFornecedor();
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

	private void buscarFornecedor() {
		System.out.println("Digite o código do fornecedor externo:");
		int codigoID = input.nextInt();
		
		FornecedorExterno fornecedor = prog.fornecedorBuscar(codigoID);
		
		if(fornecedor!= null) {
			System.out.println(fornecedor.show());
		}else {
			System.err.println("Fornecedor não encontrado!!");
		}
		
	}

	private void listarFornecedor() {
		
		System.out.println(prog.fornecedorLista());
		
	}

	private void cadastarFornecedor() {
		
		@SuppressWarnings("unused")
		String lixo;
		
		System.out.println("Digite o código do fornecedor externo:");
		int codigoID = input.nextInt();
		lixo = input.nextLine();
		System.out.println("Digite o nome do fornecedor externo:");
		String nome = input.nextLine();
		
		FornecedorExterno fornecedorExterno = new FornecedorExterno(codigoID, nome);
		
		prog.fornecedorIncluir(fornecedorExterno);
		
	}

	
	
}
