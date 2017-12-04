package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Almoxarifado;
import model.FornecedorExterno;
import model.Produto;

public class testAlmoxarifados {
	
	
	@Before
	public void setUp() throws Exception {
		Almoxarifado almoxarifado = new Almoxarifado(1, "Qualquer");
		FornecedorExterno ford = new FornecedorExterno(1, "Ford");
		FornecedorExterno fiat = new FornecedorExterno(2, "Fiat");
		Produto produto1 = new Produto(1, "Produto1", "Primeiro produto cadastrado", true, ford);
		Produto produto2 = new Produto(2, "Produto2", "Segundo produto cadastrado", false);
		Produto produto3 = new Produto(3, "Produto3", "Terceiro produto cadastrado", true, fiat);
		
	}
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
