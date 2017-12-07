package tela;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.ProgramaController;
import model.Movimentacao;

public class TelaMovimentacao {
	private Scanner input = new Scanner(System.in);
	private ProgramaController prog; 
	
	public TelaMovimentacao(ProgramaController programaController) {
		this.prog = programaController;
	}
	
	public void menu() {
		
		int opcaoEscolhida = 0;
		
		do {
		System.out.print("==========================================================\n\n"
				+ "Digite uma das Opções:\n"
				+ "1- Buscar entradas\n"
				+ "2- Buscar saidas\n"
				+ "3- Voltar\n");
		
		opcaoEscolhida = input.nextInt();
		
		switch(opcaoEscolhida) {
		case 1:
			listarEntradas();
			break;
		case 2:
			listarSaidas();
			break;
		case 3: 
			TelaPrincipal.main(null);
			break;
		default:
			System.err.println("Opção Inválida!");
			break;
		}
		}while(opcaoEscolhida != 3);
		
	}

	private void listarSaidas() {
		
		System.out.println("Digite código do produto:");
		int codigoProd = input.nextInt();
		System.out.println("Digite código do Almoxarifado:");
		int codigoAlmo = input.nextInt();
		System.out.println("Digite dia inicial: DD");
		int diaIni = input.nextInt();
		System.out.println("Digite mês inicial: MM");
		int mesIni = input.nextInt();
		System.out.println("Digite ano inicial: YYYY");
		int anoIni = input.nextInt();
		System.out.println("Digite dia final: DD");
		int diaFin = input.nextInt();
		System.out.println("Digite mês final: MM");
		int mesFin = input.nextInt();
		System.out.println("Digite ano final: YYYY");
		int anoFin = input.nextInt();
		
		@SuppressWarnings("deprecation")
		Date dataInicial = new Date(anoFin -1900, mesFin-1, diaFin);
		@SuppressWarnings("deprecation")
		Date dataFinal = new Date(anoIni -1900, mesIni-1, diaIni);
		
		List<Movimentacao> movimentacoes = prog.movimentacoesSaida(codigoProd, codigoAlmo, dataFinal , dataInicial);
		
		for(Movimentacao movi : movimentacoes) {
			System.out.print(movi.show()+"\n");
		}
		
	}

	private void listarEntradas() {
		
		System.out.println("Digite código do produto:");
		int codigoProd = input.nextInt();
		System.out.println("Digite código do Almoxarifado:");
		int codigoAlmo = input.nextInt();
		System.out.println("Digite dia inicial: DD");
		int diaIni = input.nextInt();
		System.out.println("Digite mês inicial: MM");
		int mesIni = input.nextInt();
		System.out.println("Digite ano inicial: YYYY");
		int anoIni = input.nextInt();
		System.out.println("Digite dia final: DD");
		int diaFin = input.nextInt();
		System.out.println("Digite mês final: MM");
		int mesFin = input.nextInt();
		System.out.println("Digite ano final: YYYY");
		int anoFin = input.nextInt();
		
		@SuppressWarnings("deprecation")
		Date dataInicial = new Date(anoFin -1900, mesFin-1, diaFin);
		@SuppressWarnings("deprecation")
		Date dataFinal = new Date(anoIni -1900, mesIni-1, diaIni);
		
		List<Movimentacao> movimentacoes = prog.movimentacoesEntrada(codigoProd, codigoAlmo, dataFinal, dataInicial);
		
		for(Movimentacao movi : movimentacoes) {
			System.out.print(movi.show()+"\n");
		}
		
	}
}
