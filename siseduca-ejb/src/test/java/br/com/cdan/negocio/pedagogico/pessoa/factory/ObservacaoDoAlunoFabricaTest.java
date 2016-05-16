package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeCor;
import br.com.cdan.model.pessoa.ObservacaoDoAluno;
import br.com.cdan.negocio.pedagogico.pessoa.ObservacaoDoAlunoDao;

public class ObservacaoDoAlunoFabricaTest {
	private static ObservacaoDoAlunoFabricaTest instance = null;

	public static synchronized ObservacaoDoAlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ObservacaoDoAlunoFabricaTest();
		}
		return instance;
	}

	public ObservacaoDoAluno criaObservacaoDoAluno(EntityManager em) {
		ObservacaoDoAluno o = new ObservacaoDoAluno();
		o.setAlergias("alergias");
		o.setAtivo(Boolean.TRUE);
		o.setCursouOutrasInstituicoesDeEnsino(Boolean.FALSE);
		o.setDoencas("doencas");
		o.setInteressado(InteressadoFabricaTest.getInstance().criaInteressadoPersistido(em));
		o.setObservacao("observacao");
		o.setPrimeirosSocorros("primeirosSocorros");
		//
		o.setTipoDeCor(EnumTipoDeCor.AMARELO);
		o.setTipoSanguineo("tipoSanguineo");
		return o;
	}

	public ObservacaoDoAluno criaObservacaoDoAlunoPersistido(EntityManager em) {
		ObservacaoDoAluno o = criaObservacaoDoAluno(em);
		ObservacaoDoAlunoDao dao = new ObservacaoDoAlunoDao(em);
		dao.persist(o);
		return o;
	}

}
