package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.geral.TipoDeCelular;
import br.com.cdan.negocio.biblioteca.TelefoneDao;
import br.com.cdan.negocio.biblioteca.TipoDeCelularDao;

public class TipoDeCelularFabricaTest {
	private static TipoDeCelularFabricaTest instance = null;

	public static TipoDeCelularFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCelularFabricaTest();
		}
		return instance;
	}

	public TipoDeCelular criaTipoDeCelular() {
		TipoDeCelular t = new TipoDeCelular();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		Set<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		t.setTelefones(telefones);
		return t;
	}

	public TipoDeCelular criaTipoDeCelularPersistido(EntityManager em) {
		TipoDeCelular t = criaTipoDeCelular();
		TipoDeCelularDao dao = new TipoDeCelularDao(em);
		//
		Set<Telefone> telefones = new LinkedHashSet<>();
		TelefoneDao telefoneDao = new TelefoneDao(em);
		t.getTelefones().forEach(telefone -> {
			telefoneDao.persist(telefone);
			telefones.add(telefone);
		});
		t.setTelefones(telefones);
		//
		dao.persist(t);
		return t;
	}
}
