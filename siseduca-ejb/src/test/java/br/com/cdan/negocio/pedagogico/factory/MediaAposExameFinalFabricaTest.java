package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoMediaAposExameFinal;
import br.com.cdan.model.pedagogico.ExameFinal;
import br.com.cdan.model.pedagogico.MediaAposExameFinal;
import br.com.cdan.negocio.pedagogico.ExameFinalDao;
import br.com.cdan.negocio.pedagogico.MediaAposExameFinalDao;

public class MediaAposExameFinalFabricaTest {
	private static MediaAposExameFinalFabricaTest instance = null;

	public static synchronized MediaAposExameFinalFabricaTest getInstance() {
		if (instance == null) {
			instance = new MediaAposExameFinalFabricaTest();
		}
		return instance;
	}

	public MediaAposExameFinal criaMediaAposExameFinal() {
		MediaAposExameFinal m = new MediaAposExameFinal();
		m.setAtivo(Boolean.TRUE);
		m.setConsiderarANotaDeExameFinal(Boolean.TRUE);
		m.setDesconsiderarANotaDeExameFinal(Boolean.FALSE);
		m.setEnumTipoMediaAposExameFinal(EnumTipoMediaAposExameFinal.CALCULARAMEDIA);
		m.setExameFinal(ExameFinalFabricaTest.getInstance().criaExameFinal());
		return m;
	}

	public MediaAposExameFinal criaMediaAposExameFinalPersistido(EntityManager em) {
		MediaAposExameFinal m = criaMediaAposExameFinal();
		MediaAposExameFinalDao dao = new MediaAposExameFinalDao(em);
		//
		ExameFinalDao exameFinalDao = new ExameFinalDao(em);
		ExameFinal exameFinal = m.getExameFinal();
		exameFinalDao.persist(exameFinal);
		m.setExameFinal(exameFinal);
		//
		dao.persist(m);
		return m;
	}
}
