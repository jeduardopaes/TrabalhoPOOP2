package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Almoxarifado;

public class AlmoxarifadoDAO {

	private static ManipulaArquivos bancoDeDados = new ManipulaArquivos();
	private static File arquivo = new File("BaseDeDados/Almoxarifado.txt");

	public static void salvar(List<Almoxarifado> almoxarifados) {

		List<String> almoxarifadosTexto = new ArrayList<String>();

		for (Almoxarifado almoxarifado : almoxarifados) {
			almoxarifadosTexto.add(almoxarifado.toString());
		}

		bancoDeDados.escreveLinhasNoArquivo(almoxarifadosTexto, arquivo);

	}

	public static List<Almoxarifado> recuperar() {
		List<String> almoxarifadosTexto = bancoDeDados.lerLinhasDoArquivo(arquivo);

		List<Almoxarifado> almoxarifados = new ArrayList<Almoxarifado>();

		Almoxarifado almoxarifado;
		
		for (String linha : almoxarifadosTexto) {
			String[] linhaQuebrada = linha.split(";");

			int codigoAlmoxarifado = Integer.parseInt(linhaQuebrada[0]);
			String localizacaoAlmoxarifado = linhaQuebrada[1];
			boolean status = Boolean.parseBoolean(linhaQuebrada[2]);
			long dataSaida = Long.parseLong(linhaQuebrada[3]);
			long dataEntrada = Long.parseLong(linhaQuebrada[4]);
			
			
			almoxarifado = new Almoxarifado(codigoAlmoxarifado,
							localizacaoAlmoxarifado);
			
			almoxarifado.setStatus(status);
			almoxarifado.setUltimaDataDeEntrada(new Date(dataEntrada));
			almoxarifado.setUltimaDataDeSaida(new Date(dataSaida));
					
			almoxarifados.add(almoxarifado);

		}
		return almoxarifados;

	}

}
