package tela;

import java.util.Scanner;

import controller.ProgramaController;

public class TelaPrincipal {

	Scanner input = new Scanner(System.in);

	ProgramaController programaController = ProgramaController.getProgramaController();

	TelaEmpregado telaEmpregado = new TelaEmpregado(programaController);
	TelaFornecedorExterno telaFornecedorExterno = new TelaFornecedorExterno(programaController);
	TelaProduto telaProduto = new TelaProduto(programaController);
	TelaAlmoxarifado telaAlmoxarifado = new TelaAlmoxarifado(programaController);
	TelaMovimentacao telaMovimentacao = new TelaMovimentacao(programaController);

	public static void main(String[] args) {

		TelaPrincipal telaPrincipal = new TelaPrincipal();
		try {
			telaPrincipal.menuPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void menuPrincipal() {

		int opcaoEscolhida = 0;

		do {
			System.out.print("=====================================================\n\n" + "Digite uma das opções:\n"
					+ "1- Empregado\n" + "2- FornecedorExterno\n" + "3- Produto\n" + "4- Almoxarifado\n"
					+ "5- Movimentações\n" + "6- Sair\n");

			opcaoEscolhida = input.nextInt();

			switch (opcaoEscolhida) {
			case 1:
				telaEmpregado.menu();
				break;
			case 2:
				telaFornecedorExterno.menu();
				break;
			case 3:
				 telaProduto.menu();
				break;
			case 4:
				 telaAlmoxarifado.menu();
				break;
			case 5:
				 telaMovimentacao.menu();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.err.println("Opção inválida!");
				break;
			}
		} while (opcaoEscolhida != 6);

	}

}
