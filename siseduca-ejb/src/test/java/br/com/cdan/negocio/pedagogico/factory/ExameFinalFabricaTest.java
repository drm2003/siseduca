package br.com.cdan.negocio.pedagogico.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.ExameFinal;
import br.com.cdan.model.pedagogico.MediaAposExameFinal;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.negocio.pedagogico.ExameFinalDao;
import br.com.cdan.negocio.pedagogico.MediaAposExameFinalDao;
import br.com.cdan.negocio.pedagogico.SistemaDeAvaliacaoDao;

public class ExameFinalFabricaTest {
	private static ExameFinalFabricaTest instance = null;

	public static synchronized ExameFinalFabricaTest getInstance() {
		if (instance == null) {
			instance = new ExameFinalFabricaTest();
		}
		return instance;
	}

	public ExameFinal criaExameFinal() {
		ExameFinal e = new ExameFinal();
		e.setAtivo(Boolean.TRUE);
		e.setMediaAposExameFinal(MediaAposExameFinalFabricaTest.getInstance().criaMediaAposExameFinal());
		e.setMediaExameFinal(BigDecimal.valueOf(7.0));
		e.setPesoExameFinal(Long.valueOf(7));
		e.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacao());
		e.setUsarConselhoDeClasse(Boolean.FALSE);
		e.setValorMediaAposExameFinal(BigDecimal.valueOf(7.0));
		return e;
	}

	public ExameFinal criaExameFinalPersistido(EntityManager em) {
		ExameFinal e = criaExameFinal();
		ExameFinalDao dao = new ExameFinalDao(em);
		//
		MediaAposExameFinalDao mediaAposExameFinalDao = new MediaAposExameFinalDao(em);
		MediaAposExameFinal mediaAposExameFinal = e.getMediaAposExameFinal();
		mediaAposExameFinalDao.persist(mediaAposExameFinal);
		e.setMediaAposExameFinal(mediaAposExameFinal);
		//
		SistemaDeAvaliacaoDao sistemaDeAvaliacaoDao = new SistemaDeAvaliacaoDao(em);
		SistemaDeAvaliacao sistemaDeAvaliacao = e.getSistemaDeAvaliacao();
		sistemaDeAvaliacaoDao.persist(sistemaDeAvaliacao);
		e.setSistemaDeAvaliacao(sistemaDeAvaliacao);
		//
		dao.persist(e);
		return e;
	}

}
