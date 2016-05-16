package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoMediaAposExameFinal;
import br.com.cdan.model.pedagogico.MediaAposExameFinal;
import br.com.cdan.negocio.pedagogico.MediaAposExameFinalDao;

public class MediaAposExameFinalFabricaTest {
	private static MediaAposExameFinalFabricaTest instance = null;

	public static synchronized MediaAposExameFinalFabricaTest getInstance() {
		if (instance == null) {
			instance = new MediaAposExameFinalFabricaTest();
		}
		return instance;
	}

	public MediaAposExameFinal criaMediaAposExameFinal(EntityManager em) {
		MediaAposExameFinal m = new MediaAposExameFinal();
		m.setAtivo(Boolean.TRUE);
		m.setConsiderarANotaDeExameFinal(Boolean.TRUE);
		m.setDesconsiderarANotaDeExameFinal(Boolean.FALSE);
		m.setEnumTipoMediaAposExameFinal(EnumTipoMediaAposExameFinal.CALCULARAMEDIA);
		m.setExameFinal(ExameFinalFabricaTest.getInstance().criaExameFinalPersistido(em));
		return m;
	}

	public MediaAposExameFinal criaMediaAposExameFinalPersistido(EntityManager em) {
		MediaAposExameFinal m = criaMediaAposExameFinal(em);
		MediaAposExameFinalDao dao = new MediaAposExameFinalDao(em);
		dao.persist(m);
		return m;
	}
}
