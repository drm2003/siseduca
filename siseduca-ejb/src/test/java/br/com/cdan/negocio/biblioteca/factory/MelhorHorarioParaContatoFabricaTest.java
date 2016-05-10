package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.MelhorHorarioParaContato;
import br.com.cdan.negocio.biblioteca.MelhorHorarioParaContatoDao;

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
		m.setDescricao("descricao");
		return m;
	}

	public MelhorHorarioParaContato criaMelhorHorarioParaContatoPersistido(EntityManager em) {
		MelhorHorarioParaContato m = criaMelhorHorarioParaContato();
		MelhorHorarioParaContatoDao dao = new MelhorHorarioParaContatoDao(em);
		dao.persist(m);
		return m;
	}
}
