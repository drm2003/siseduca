package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.comum.EnumTipoAquisicao;
import br.com.cdan.model.biblioteca.DadosDoExemplar;

public class DadosDoExemplarFabricaTest {
	private static DadosDoExemplarFabricaTest instance = null;

	public static synchronized DadosDoExemplarFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadosDoExemplarFabricaTest();
		}
		return instance;
	}

	public DadosDoExemplar criaDadosDoExemplar() {
		DadosDoExemplar dadosDoExemplar = new DadosDoExemplar();
		dadosDoExemplar.setCodigoDeBarras(Long.valueOf("10101010"));
		dadosDoExemplar.setEmprestar(Boolean.TRUE);
		dadosDoExemplar.setISBN("teste");
		dadosDoExemplar.setISSN("teste");
		dadosDoExemplar.setLocalizacao("teste");
		dadosDoExemplar.setObservacoes("teste");
		dadosDoExemplar.setRenovar(Boolean.TRUE);
		dadosDoExemplar.setReservar(Boolean.TRUE);
		dadosDoExemplar.setTipoAquisicao(EnumTipoAquisicao.COMPRA);
		dadosDoExemplar.setTombo(Long.valueOf("1010"));
		return dadosDoExemplar;
	}
}
