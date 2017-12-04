package tela;

import java.util.Date;
import java.util.Scanner;

import controller.ProgramaController;
import model.Almoxarifado;
import model.Empregado;
import model.Movimentacao;
import model.Produto;

public class TelaAlmoxarifadoEspecifica {

	private Scanner input = new Scanner(System.in);
	private ProgramaController prog;
	private Almoxarifado almo;

	public TelaAlmoxarifadoEspecifica(ProgramaController programaController, Almoxarifado almoxarifado) {
		this.prog = programaController;
		this.almo = almoxarifado;
	}

	public void menu() {

		int opcaoEscolhida = 0;

		do {
			System.out.print("==========================================================\n\n"
					+ "Digite uma das Opções:\n" + "1- Entrar com Produto\n" + "2- Sair com Produto\n"
					+ "3- Listar Produtos\n" + "4- Voltar");

			opcaoEscolhida = input.nextInt();

			switch (opcaoEscolhida) {
			case 1:
				entradaDeProduto();
				break;
			case 2:
				saidaDeProduto();
				break;
			case 3:
				try {
					listarProdutos();
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
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

	private void listarProdutos() throws Exception {
		for (Produto prod : almo.getProdutos()) {
			System.out.println(prod.toString() + almo.getQuantidade(prod) +"\n");
		}

	}

	private void saidaDeProduto() {
		System.out.println("Digite código do empregado:");
		int codigoEmpregado = input.nextInt();
		System.out.println("Digite código do produto:");
		int codProd = input.nextInt();
		System.out.println("Digite quantidade:");
		int quantidade = input.nextInt();

		Produto produto = prog.produtoBuscar(codProd);
		Empregado emp = prog.empregadoBuscar(codigoEmpregado);

		if (produto == null || almo == null || emp == null) {
			System.out.println(produto.toString() + "\n" + almo.toString() + "\n" + "");
		}

		try {
			prog.almoxarifadoAddProduto(almo.getCodigoID(), codProd, -quantidade);
			Movimentacao movimentacao = new Movimentacao(false, produto, quantidade, almo,
					new Date(System.currentTimeMillis()), emp);
			prog.movimentacaoIncluir(movimentacao);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	private void entradaDeProduto() {

		System.out.println("Digite código do empregado:");
		int codigoEmpregado = input.nextInt();
		System.out.println("Digite código do produto:");
		int codProd = input.nextInt();
		System.out.println("Digite quantidade:");
		int quantidade = input.nextInt();

		Produto produto = prog.produtoBuscar(codProd);

		try {
			prog.almoxarifadoAddProduto(almo.getCodigoID(), codProd, quantidade);
			Movimentacao movimentacao = new Movimentacao(false, produto, quantidade, almo,
					new Date(System.currentTimeMillis()), prog.empregadoBuscar(codigoEmpregado));
			prog.movimentacaoIncluir(movimentacao);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

}
