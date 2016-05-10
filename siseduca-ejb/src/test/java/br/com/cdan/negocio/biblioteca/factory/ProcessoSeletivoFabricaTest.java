package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.ObservacaoDoAluno;
import br.com.cdan.model.pessoa.ProcessoSeletivo;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.ObservacaoDoAlunoDao;
import br.com.cdan.negocio.biblioteca.ProcessoSeletivoDao;

public class ProcessoSeletivoFabricaTest {
	private static ProcessoSeletivoFabricaTest instance = null;

	public static synchronized ProcessoSeletivoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ProcessoSeletivoFabricaTest();
		}
		return instance;
	}

	public ProcessoSeletivo criaProcessoSeletivo() {
		ProcessoSeletivo p = new ProcessoSeletivo();
		p.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		p.setAno(Long.valueOf("2016"));
		p.setAtivo(Boolean.TRUE);
		p.setFormaDeIngresso("formaDeIngresso");
		p.setInstituicao("instituicao");
		p.setMedia(BigDecimal.valueOf(10.0));
		p.setObservacaoDoAluno(ObservacaoDoAlunoFabricaTest.getInstance().criaObservacaoDoAluno());
		p.setProva(BigDecimal.valueOf(7.0));
		p.setRedacao(BigDecimal.valueOf(8.0));
		p.setSemestre("semestre");
		return p;
	}

	public ProcessoSeletivo criaProcessoSeletivoPersistido(EntityManager em) {
		ProcessoSeletivo p = criaProcessoSeletivo();
		ProcessoSeletivoDao dao = new ProcessoSeletivoDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = p.getAluno();
		alunoDao.persist(aluno);
		p.setAluno(aluno);
		//
		ObservacaoDoAlunoDao observacaoDoAlunoDao = new ObservacaoDoAlunoDao(em);
		ObservacaoDoAluno observacaoDoAluno = p.getObservacaoDoAluno();
		observacaoDoAlunoDao.persist(observacaoDoAluno);
		p.setObservacaoDoAluno(observacaoDoAluno);
		//
		dao.persist(p);
		return p;
	}
}
