package tela;

import java.util.Scanner;

import controller.ProgramaController;
import model.FornecedorExterno;
import model.Produto;

public class TelaProduto {
	private Scanner input = new Scanner(System.in);
	private ProgramaController prog;

	public TelaProduto(ProgramaController programaController) {
		this.prog = programaController;
	}

	public void menu() {

		int opcaoEscolhida = 0;

		do {
			System.out.print("==========================================================\n\n"
					+ "Digite uma das Opções:\n" + "1- Cadastrar\n" + "2- Listar\n" + "3- Buscar\n" + "4- Voltar\n");

			opcaoEscolhida = input.nextInt();

			switch (opcaoEscolhida) {
			case 1:
				cadastarProduto();
				break;
			case 2:
				listarProduto();
				break;
			case 3:
				buscarProduto();
				break;
			case 4:
				TelaPrincipal.main(null);
				break;
			default:
				System.err.println("Opção Inválida!");
				break;
			}
		} while (opcaoEscolhida != 4);

	}

	private void listarProduto() {
		
		System.out.println(prog.produtoLista());
		
	}

	private void buscarProduto() {
		System.out.println("Digite o código do produto:");
		int codigoProduto = input.nextInt();
		
		Produto prod = prog.produtoBuscar(codigoProduto);
		
		if(prod != null) {
			System.out.println(prod.show());
		}else {
			System.err.println("Produto não encontrado!!");
		}
		
	}

	private void cadastarProduto() {
		
		@SuppressWarnings("unused")
		String lixo = "";

		System.out.println("Digite o codigo do produto:");
		int codigoID = input.nextInt();
		lixo = input.nextLine();
		System.out.println("Digite o nome do produto:");
		String nome = input.nextLine();
		System.out.println("Digite a descrição do produto:");
		String descricao = input.nextLine();

		boolean efornecedor = false;
		int fornecedorExterno = 7;
		
		do {
			System.out.println("Digite se é fornecedor externo: 1-Sim 0-Não");
			
			fornecedorExterno = input.nextInt();

			switch(fornecedorExterno) {
			case 0:
				efornecedor = false;
				break;
			case 1:
				efornecedor = true;
				break;
			default:
				System.err.println("Opção inválida!!");
				fornecedorExterno = -1;
				break;
			}
			
		} while (fornecedorExterno == -1);
		
		Produto produto;
		
		if(efornecedor) {
			System.out.println("Digite o código do fornecedor:");
			int codigoFornecedor = input.nextInt();
			
			FornecedorExterno fornecedor = prog.fornecedorBuscar(codigoFornecedor);
			
			produto = new Produto(codigoID, nome, descricao, efornecedor, fornecedor);
		}else {
			produto = new Produto(codigoID, nome, descricao, efornecedor);
		}

		

		prog.produtoIncluir(produto);

	}
}
