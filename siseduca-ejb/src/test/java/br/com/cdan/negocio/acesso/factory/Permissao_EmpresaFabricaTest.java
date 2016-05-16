package br.com.cdan.negocio.acesso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Permissao_Empresa;
import br.com.cdan.negocio.acesso.PermissaoEmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;

public class Permissao_EmpresaFabricaTest {
	private static Permissao_EmpresaFabricaTest instance = null;

	public static synchronized Permissao_EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Permissao_EmpresaFabricaTest();
		}
		return instance;
	}

	public Permissao_Empresa criaPermissao_Empresa(EntityManager em) {
		Permissao_Empresa p = new Permissao_Empresa();
		p.setAtivo(Boolean.TRUE);
		p.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresaPersistido(em));
		p.setPermissao(PermissaoFabricaTest.getInstance().criaPermissaoPersistido(em));
		p.setUsuarioTemAcesso(Boolean.TRUE);
		return p;
	}

	public Permissao_Empresa criaPermissao_EmpresaPersistido(EntityManager em) {
		Permissao_Empresa p = criaPermissao_Empresa(em);
		PermissaoEmpresaDao dao = new PermissaoEmpresaDao(em);
		//
		dao.persist(p);
		return p;
	}
}
