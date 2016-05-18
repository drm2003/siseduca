package br.com.cdan.negocio.contato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.MelhorHorarioParaContato;
import br.com.cdan.negocio.contato.MelhorHorarioParaContatoDao;

public class MelhorHorarioParaContatoFabricaTest {
	private static MelhorHorarioParaContatoFabricaTest instance = null;

	public static synchronized MelhorHorarioParaContatoFabricaTest getInstance() {
		if (instance == null) {
			instance = new MelhorHorarioParaContatoFabricaTest();
		}
		return instance;
	}

	public MelhorHorarioParaContato criaMelhorHorarioParaContato() {
		MelhorHorarioParaContato m = new MelhorHorarioParaContato();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao" + Math.random() * 10000);
		return m;
	}

	public MelhorHorarioParaContato criaMelhorHorarioParaContatoPersistido(EntityManager em) {
		MelhorHorarioParaContato m = criaMelhorHorarioParaContato();
		MelhorHorarioParaContatoDao dao = new MelhorHorarioParaContatoDao(em);
		dao.persist(m);
		return m;
	}
}
