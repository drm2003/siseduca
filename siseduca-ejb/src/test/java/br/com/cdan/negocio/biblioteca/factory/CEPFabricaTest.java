package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.geral.cep.EnumChaveCidade;
import br.com.cdan.model.geral.cep.EnumTipoCidade;

public class CEPFabricaTest {
	private static CEPFabricaTest instance = null;

	public static synchronized CEPFabricaTest getInstance() {
		if (instance == null) {
			instance = new CEPFabricaTest();
		}
		return instance;
	}

	public CEP criaCEP() {
		CEP cep = new CEP();
		cep.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		cep.setAtivo(Boolean.TRUE);
		cep.setCepMax("teste");
		cep.setCepmin("teste");
		cep.setChave(EnumChaveCidade.C);
		cep.setCod_cidade("teste");
		cep.setCodCidadeSub("teste");
		cep.setCodMunicicio("teste");
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		Endereco e1 = EnderecoFabricaTest.getInstance().criaEndereco();
		Endereco e2 = EnderecoFabricaTest.getInstance().criaEndereco();
		enderecos.add(e1);
		enderecos.add(e2);
		cep.setEndereco(enderecos);
		cep.setTipoCidade(EnumTipoCidade.D);
		cep.setUF(EstadoUFFabricaTest.getInstance().criaEstadoUF());
		return cep;
	}

}
