package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.ProcessoSeletivo;
import br.com.cdan.negocio.pedagogico.pessoa.ProcessoSeletivoDao;

public class ProcessoSeletivoFabricaTest {
	private static ProcessoSeletivoFabricaTest instance = null;

	public static synchronized ProcessoSeletivoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ProcessoSeletivoFabricaTest();
		}
		return instance;
	}

	public ProcessoSeletivo criaProcessoSeletivo(EntityManager em) {
		ProcessoSeletivo p = new ProcessoSeletivo();
		p.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		p.setAno(Long.valueOf("2016"));
		p.setAtivo(Boolean.TRUE);
		p.setFormaDeIngresso("formaDeIngresso");
		p.setInstituicao("instituicao");
		p.setMedia(BigDecimal.valueOf(10.0));
		p.setObservacaoDoAluno(ObservacaoDoAlunoFabricaTest.getInstance().criaObservacaoDoAlunoPersistido(em));
		p.setProva(BigDecimal.valueOf(7.0));
		p.setRedacao(BigDecimal.valueOf(8.0));
		p.setSemestre("semestre");
		return p;
	}

	public ProcessoSeletivo criaProcessoSeletivoPersistido(EntityManager em) {
		ProcessoSeletivo p = criaProcessoSeletivo(em);
		ProcessoSeletivoDao dao = new ProcessoSeletivoDao(em);
		//
		dao.persist(p);
		return p;
	}
}
