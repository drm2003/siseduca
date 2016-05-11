package br.com.cdan.negocio.acesso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Permissao;
import br.com.cdan.model.acesso.Permissao_Empresa;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.negocio.acesso.PermissaoDao;
import br.com.cdan.negocio.acesso.PermissaoEmpresaDao;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;

public class Permissao_EmpresaFabricaTest {
	private static Permissao_EmpresaFabricaTest instance = null;

	public static synchronized Permissao_EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Permissao_EmpresaFabricaTest();
		}
		return instance;
	}

	public Permissao_Empresa criaPermissao_Empresa() {
		Permissao_Empresa p = new Permissao_Empresa();
		p.setAtivo(Boolean.TRUE);
		p.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		p.setPermissao(PermissaoFabricaTest.getInstance().criaPermissao());
		p.setUsuarioTemAcesso(Boolean.TRUE);
		return p;
	}

	public Permissao_Empresa criaPermissao_EmpresaPersistido(EntityManager em) {
		Permissao_Empresa p = criaPermissao_Empresa();
		PermissaoEmpresaDao dao = new PermissaoEmpresaDao(em);
		//
		EmpresaDao empresaDao = new EmpresaDao(em);
		Empresa empresa = p.getEmpresa();
		empresaDao.persist(empresa);
		p.setEmpresa(empresa);
		//
		PermissaoDao permissaoDao = new PermissaoDao(em);
		Permissao permissao = p.getPermissao();
		permissaoDao.persist(permissao);
		p.setPermissao(permissao);
		//
		dao.persist(p);
		return p;
	}
}
