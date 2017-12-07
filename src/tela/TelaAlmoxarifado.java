package tela;

import java.util.Scanner;

import controller.ProgramaController;
import model.Almoxarifado;

public class TelaAlmoxarifado {

	private Scanner input = new Scanner(System.in);
	private ProgramaController prog; 
	
	public TelaAlmoxarifado(ProgramaController programaController) {
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
			cadastarAlmoxarifado();
			break;
		case 2:
			listarAlmoxarifado();
			break;
		case 3:
			buscarAlmoxarifado();
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
	
	

	private void buscarAlmoxarifado() {
		System.out.println("Digite o código do Almoxarifado:");
		int codigoID = input.nextInt();
		
		Almoxarifado almo = prog.almoxarifadoBuscar(codigoID);
		
		if(almo != null) {
			System.out.println(almo.show());
			TelaAlmoxarifadoEspecifica TAE = new TelaAlmoxarifadoEspecifica(prog, almo);
			TAE.menu();
		}else {
			System.err.println("Almoxarifado não encontrado!!");
		}
		
	}

	private void listarAlmoxarifado() {
		System.out.println(prog.almoxarifadoLista());
	}

	private void cadastarAlmoxarifado() {
		
		@SuppressWarnings("unused")
		String lixo;
		
		System.out.println("Digite código do Almoxarifado:");
		int codigoID = input.nextInt();
		lixo = input.nextLine();
		
		System.out.println("Digite localização do Almoxarifado:");
		String localidade = input.nextLine();
		
		Almoxarifado almoxarifado = new Almoxarifado(codigoID, localidade);
		
		prog.almoxarifadoIncluir(almoxarifado);
		
	}
	
	
	
	
	
}
