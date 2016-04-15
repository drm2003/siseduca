package br.com.cdan.negocio.biblioteca.factory;

import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.EstadoUF;

public class EstadoUFFabricaTest {
	private static EstadoUFFabricaTest instance = null;

	public static EstadoUFFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstadoUFFabricaTest();
		}
		return instance;
	}

	public EstadoUF criaEstadoUF() {
		EstadoUF estadoUF = new EstadoUF();
		estadoUF.setAtivo(Boolean.TRUE);
		estadoUF.setCidades(CidadeFabricaTest.getInstance().criaCidade());
		estadoUF.setDescricao("teste");
		estadoUF.setEnderecos(enderecos);
		estadoUF.setPais(PaisFabricaTest.getInstance().criaPais());
		return estadoUF;
	}
}
