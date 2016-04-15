package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.geral.Bairro;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Endereco;

public class CidadeFabricaTest {
	private static CidadeFabricaTest instance = null;

	public static synchronized CidadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new CidadeFabricaTest();
		}
		return instance;
	}

	public Cidade criaCidade() {
		Cidade cidade = new Cidade();
		cidade.setAtivo(Boolean.TRUE);
		cidade.setDescricao("teste");
		// Bairros
		Set<Bairro> bairros = new LinkedHashSet<>();
		Bairro b1 = BairroFabricaTest.getInstance().criaBairro();
		Bairro b2 = BairroFabricaTest.getInstance().criaBairro();
		bairros.add(b1);
		bairros.add(b2);
		cidade.setBairros(bairros);

		cidade.setEstadoUF(EstadoUFFabricaTest.getInstance().criaEstadoUF());
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		Endereco e1 = EnderecoFabricaTest.getInstance().criaEndereco();
		Endereco e2 = EnderecoFabricaTest.getInstance().criaEndereco();
		enderecos.add(e1);
		enderecos.add(e2);
		cidade.setEnderecos(enderecos);
		// Transferências
		cidade.setTransferencias(TransferenciaFabricaTest.getInstance().criaTransferencia());
	}

}
