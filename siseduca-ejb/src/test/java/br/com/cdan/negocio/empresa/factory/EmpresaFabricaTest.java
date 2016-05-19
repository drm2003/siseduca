package br.com.cdan.negocio.empresa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.empresa.EmpresaDao;

public class EmpresaFabricaTest extends FabricaTest {
	private static EmpresaFabricaTest instance = null;

	public static synchronized EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new EmpresaFabricaTest();
		}
		return instance;
	}

	public Empresa criaEmpresa(EntityManager em) {
		Empresa e = new Empresa();
		e.setAtivo(Boolean.TRUE);
		e.setCnpj(criarStringDinamicaPorTamanho(14));
		e.setCodigoArea("codigo Area");
		e.setFax("fax");
		e.setHomePage("homePage");
		e.setInscricaoEstadual("inscricaoEstadual");
		e.setInscricaoMunicipal("inscricaoMunicipal");
		e.setNome(criarStringDinamicaPorTamanho(50));
		e.setOptanteSimplesNacional(Boolean.TRUE);
		e.setRazaoSocial("razaoSocial");
		e.setSigla(criarStringDinamicaPorTamanho(10));
		return e;
	}

	public Empresa criaEmpresaPersistido(EntityManager em) {
		EmpresaDao dao = new EmpresaDao(em);
		Empresa empresa = criaEmpresa(em);

		dao.persist(empresa);
		return empresa;
	}
}
