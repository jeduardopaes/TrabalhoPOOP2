package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AlmoxarifadoDAO;
import dao.EmpregadoDAO;
import dao.EstoqueDAO;
import dao.FornecedorExternoDAO;
import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import model.Almoxarifado;
import model.Empregado;
import model.FornecedorExterno;
import model.Movimentacao;
import model.Produto;

public class ProgramaController {
	
	public static ProgramaController programaController = null;

	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	private List<Almoxarifado> almoxarifados;
	private List<String> estoques;
	private List<Produto> produtos;
	private List<Empregado> empregados;
	private List<FornecedorExterno> fornecedores;
	
	public static ProgramaController getProgramaController(){
		
		if(programaController == null){
			programaController = new ProgramaController();
		}
		
		return programaController;
		
	}
	
	private ProgramaController(){
		this.populateLists();
	}

	private void populateLists() {
		loadAlmoxarifados();
		loadEmpregados();
		loadFornecedores();
		loadProdutos();
		loadProdutosEmAlmoxarifados();
		loadMovimentacoes();
	}
	
	@SuppressWarnings("unused")
	private void saveLists(){
		salvarAlmoxarifados();
		salvarEmpregados();
		salvarEstoque();
		salvarFornecedorExternos();
		salvarMovimentacoes();
		salvarProdutos();
	}

	private void loadAlmoxarifados() {
		almoxarifados = AlmoxarifadoDAO.recuperar();
	}

	private void loadProdutos() {
		produtos = ProdutoDAO.recuperar();
	}

	private void loadEmpregados() {
		empregados = EmpregadoDAO.recuperar();
	}

	private void loadFornecedores() {
		fornecedores = FornecedorExternoDAO.recuperar();
	}

	private void loadMovimentacoes() {
		List<String> movimentacoesTexto = MovimentacaoDAO.recuperar();
		
		for(String linha : movimentacoesTexto){
			String[] linhaQuebrada = linha.split(";");
			
			boolean entrada = Boolean.parseBoolean(linhaQuebrada[0]);
			int codigoProduto = Integer.parseInt(linhaQuebrada[1]);
			int codigoAlmoxarifado = Integer.parseInt(linhaQuebrada[2]);
			int quantidade = Integer.parseInt(linhaQuebrada[3]);
			Date data = new Date(Long.parseLong(linhaQuebrada[4]));
			int codigoEmpregado = Integer.parseInt(linhaQuebrada[5]);
			
			//System.out.println(linha);
			
			Produto prod = produtoBuscar(codigoProduto);
			Almoxarifado almo = almoxarifadoBuscar(codigoAlmoxarifado);
			Empregado emp = empregadoBuscar(codigoEmpregado);
			
			movimentacoes.add(new Movimentacao(entrada, prod, quantidade, almo, data, emp));
			
			
		}
		
	}

	private void loadProdutosEmAlmoxarifados() {
		estoques = EstoqueDAO.recuperar();

		for (Almoxarifado almoxarifado : almoxarifados) {
			for (String linha : estoques) {
				// 1- Linha ID Almoxarifado
				// 2- Linha ID Produto
				// 3- Linha Quantidade
				String[] linhaQuebrada = linha.split(";");

				int idAlmoxarifado = Integer.parseInt(linhaQuebrada[0]);
				int idProduto = Integer.parseInt(linhaQuebrada[1]);
				int quantidade = Integer.parseInt(linhaQuebrada[2]);

				if (almoxarifado.getCodigoID() == idAlmoxarifado) {
					for (Produto produto : produtos) {
						if (produto.getCodigoID() == idProduto) {
							try {
								almoxarifado.setQuantidade(produto, quantidade);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}

			}
		}

	}

	private void salvarAlmoxarifados() {
		AlmoxarifadoDAO.salvar(almoxarifados);
	}

	private void salvarEmpregados() {
		EmpregadoDAO.salvar(empregados);
	}

	private void salvarFornecedorExternos() {
		FornecedorExternoDAO.salvar(fornecedores);
	}

	private void salvarMovimentacoes() {
		MovimentacaoDAO.salvar(movimentacoes);
	}

	private void salvarEstoque() {
		estoques.clear();
		String texto = "";
		for (Almoxarifado alm : almoxarifados) {
			for (Produto prod : alm.getProdutos()) {
				try {
					texto = alm.getCodigoID() + ";" + prod.getCodigoID() + ";" + alm.getQuantidade(prod);
				} catch (Exception e) {
					e.printStackTrace();
				}
				estoques.add(texto);
			}
		}

		EstoqueDAO.salvar(estoques);
	}

	private void salvarProdutos() {
		ProdutoDAO.salvar(produtos);
	}

	// =========================== Sobre Produtos
	
	public void produtoIncluir(Produto produto) {
		produtos.add(produto);
		salvarProdutos();
	}

	public Produto produtoBuscar(int codigoProduto) {

		for (Produto produto : produtos) {
			if (produto.getCodigoID() == codigoProduto) {
				return produto;
			}
		}

		return null;

	}

	public String produtoLista() {
		
		String textoProdutos = "";
		for(Produto prod : produtos) {
			textoProdutos += prod.show()+"\n";
		}
		
		return textoProdutos;
	}
	
	// =========================== Sobre Almoxarifados

	public void almoxarifadoIncluir(Almoxarifado almoxarifado) {
		almoxarifados.add(almoxarifado);
		salvarAlmoxarifados();
	}

	public Almoxarifado almoxarifadoBuscar(int codigoAlmoxarifado) {
		for (Almoxarifado almoxarifado : almoxarifados) {
			if (almoxarifado.getCodigoID() == codigoAlmoxarifado) {
				return almoxarifado;
			}
		}

		return null;
	}
	
	public String almoxarifadoLista() {
		String textoLista = "";
		for(Almoxarifado almo : almoxarifados) {
			textoLista += almo.toString()+"\n";
		}
		
		return textoLista;
	}

	public boolean almoxarifadoAddProduto(int codigoAlmoxarifado, int codigoProduto, int quantidade){
		Almoxarifado almoxarifado = almoxarifadoBuscar(codigoAlmoxarifado);
		Produto produto = produtoBuscar(codigoProduto);
		if(produto == null || almoxarifado == null){
			return false;
		}else{
			try {
				almoxarifado.setQuantidade(produto, quantidade);
				salvarEstoque();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	// =========================== Sobre Empregado

	public void empregadoIncluir(Empregado empregado) {
		empregados.add(empregado);
		salvarEmpregados();
	}

	public Empregado empregadoBuscar(int codigoEmpregado) {
		for (Empregado empregado : empregados) {
			if (empregado.getMatricula() == codigoEmpregado) {
				return empregado;
			}
		}

		return null;
	}
	
	public String empregadoLista() {
		
		String listaTexto = "";
		
		for(Empregado emp : empregados) {
			listaTexto += emp.show()+"\n";
		}
		
		
		return listaTexto;
	}

	// =========================== Sobre Fornecedores

	public void fornecedorIncluir(FornecedorExterno fornecedorExterno) {
		fornecedores.add(fornecedorExterno);
		salvarFornecedorExternos();
	}

	public FornecedorExterno fornecedorBuscar(int codigoFornecedor) {
		for (FornecedorExterno fornecedorExterno : fornecedores) {
			if (fornecedorExterno.getCodigoID() == codigoFornecedor) {
				return fornecedorExterno;
			}
		}
		return null;
	}
	
	public String fornecedorLista() {
		
		String textoFornecedor = "";
		
		for(FornecedorExterno fornecedor : fornecedores) {
			textoFornecedor += fornecedor.toString()+"\n";
		}
		
		return textoFornecedor;
	}

	// ============= Sobre Movimentaçoes

	public void movimentacaoIncluir(Movimentacao movimentacao) {
		movimentacoes.add(movimentacao);
		salvarMovimentacoes();
	}

	public List<Movimentacao> movimentacoesEntrada(int codigoProduto, int codigoAlmoxarifado, Date dataInicial,
			Date dataFinal) {

		List<Movimentacao> movimentacoesEncontradas = new ArrayList<Movimentacao>();

		for (Movimentacao movimentacao : movimentacoes) {
			if (movimentacao.isEntrada() && movimentacao.getCodigoDoProduto() == codigoProduto
					&& movimentacao.getCodigoAlmoxarifado() == codigoAlmoxarifado
					&& movimentacao.getData().getTime() < dataFinal.getTime()
					&& movimentacao.getData().getTime() > dataInicial.getTime()) {
				movimentacoesEncontradas.add(movimentacao);
			}
		}

		return movimentacoesEncontradas;

	}

	public List<Movimentacao> movimentacoesSaida(int codigoProduto, int codigoAlmoxarifado, Date dataInicial,
			Date dataFinal) {
		List<Movimentacao> movimentacoesEncontradas = new ArrayList<Movimentacao>();

		for (Movimentacao movimentacao : movimentacoes) {
			if (!movimentacao.isEntrada() && movimentacao.getCodigoDoProduto() == codigoProduto
					&& movimentacao.getCodigoAlmoxarifado() == codigoAlmoxarifado
					&& movimentacao.getData().getTime() < dataFinal.getTime()
					&& movimentacao.getData().getTime() > dataInicial.getTime()) {
				movimentacoesEncontradas.add(movimentacao);
			}
		}

		return movimentacoesEncontradas;
	}
	
	//

}
