package br.com.cdan.negocio.financeiro.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.OperadoraCartao;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.financeiro.OperadoraCartaoDao;

public class OperadoraCartaoFabricaTest {
	private static OperadoraCartaoFabricaTest instance = null;

	public static synchronized OperadoraCartaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new OperadoraCartaoFabricaTest();
		}
		return instance;
	}

	public OperadoraCartao criaOperadoraCartao() {
		OperadoraCartao o = new OperadoraCartao();
		o.setAtivo(Boolean.TRUE);
		//
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		o.setEmpresas(empresas);
		//
		o.setNome("nome");
		return o;
	}

	public OperadoraCartao criaOperadoraCartaoPersistido(EntityManager em) {
		OperadoraCartao o = criaOperadoraCartao();
		OperadoraCartaoDao dao = new OperadoraCartaoDao(em);
		//
		Set<Empresa> empresas = new LinkedHashSet<>();
		EmpresaDao empresaDao = new EmpresaDao(em);
		o.getEmpresas().forEach(empresa -> {
			empresaDao.persist(empresa);
		});
		o.setEmpresas(empresas);
		//
		dao.persist(o);
		return o;
	}
}