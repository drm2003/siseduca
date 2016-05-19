package br.com.cdan.negocio.acesso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Permissao;
import br.com.cdan.negocio.acesso.PermissaoDao;

public class PermissaoFabricaTest {
	private static PermissaoFabricaTest instance = null;

	public static synchronized PermissaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PermissaoFabricaTest();
		}
		return instance;
	}

	public Permissao criaPermissao(EntityManager em) {
		Permissao p = new Permissao();
		//
		p.setAtivo(Boolean.TRUE);
		return p;
	}

	public Permissao criaPermissaoPersistido(EntityManager em) {
		Permissao p = criaPermissao(em);
		PermissaoDao dao = new PermissaoDao(em);
		//
		dao.persist(p);
		return p;
	}

}
