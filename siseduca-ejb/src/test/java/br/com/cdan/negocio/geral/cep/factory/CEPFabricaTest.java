package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.geral.cep.EnumChaveCidade;
import br.com.cdan.model.geral.cep.EnumTipoCidade;
import br.com.cdan.negocio.geral.cep.CEPDao;
import br.com.cdan.negocio.geral.factory.EstadoUFFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.factory.AproveitamentoFabricaTest;

public class CEPFabricaTest {
	private static CEPFabricaTest instance = null;

	public static synchronized CEPFabricaTest getInstance() {
		if (instance == null) {
			instance = new CEPFabricaTest();
		}
		return instance;
	}

	public CEP criaCEP(EntityManager em) {
		CEP cep = new CEP();
		cep.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento(em));
		cep.setAtivo(Boolean.TRUE);
		cep.setCepMax("teste");
		cep.setCepmin("teste");
		cep.setChave(EnumChaveCidade.C);
		cep.setCod_cidade("teste");
		cep.setCodCidadeSub("teste");
		cep.setCodMunicicio("teste");
		cep.setTipoCidade(EnumTipoCidade.D);
		cep.setEstadoUF(EstadoUFFabricaTest.getInstance().criaEstadoUFPersistido(em));
		return cep;
	}

	public CEP criaCepPersistido(EntityManager em) {
		CEP cep = criaCEP(em);
		CEPDao dao = new CEPDao(em);
		dao.persist(cep);
		return cep;
	}

}
