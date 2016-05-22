package br.com.cdan.negocio.pedagogico.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.ExameFinal;
import br.com.cdan.negocio.pedagogico.ExameFinalDao;

public class ExameFinalFabricaTest {
	private static ExameFinalFabricaTest instance = null;

	public static synchronized ExameFinalFabricaTest getInstance() {
		if (instance == null) {
			instance = new ExameFinalFabricaTest();
		}
		return instance;
	}

	public ExameFinal criaExameFinal(EntityManager em) {
		ExameFinal e = new ExameFinal();
		e.setAtivo(Boolean.TRUE);
		e.setMediaAposExameFinal(MediaAposExameFinalFabricaTest.getInstance().criaMediaAposExameFinalPersistido(em));
		e.setMediaExameFinal(BigDecimal.valueOf(7.0));
		e.setPesoExameFinal(Long.valueOf(7));
		e.setUsarConselhoDeClasse(Boolean.FALSE);
		e.setValorMediaAposExameFinal(BigDecimal.valueOf(7.0));
		return e;
	}

	public ExameFinal criaExameFinalPersistido(EntityManager em) {
		ExameFinal e = criaExameFinal(em);
		ExameFinalDao dao = new ExameFinalDao(em);
		//
		dao.persist(e);
		return e;
	}

}
