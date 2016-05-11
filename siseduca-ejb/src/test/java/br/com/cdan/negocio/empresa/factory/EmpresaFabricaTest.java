package br.com.cdan.negocio.empresa.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.financeiro.BolsaDao;
import br.com.cdan.negocio.financeiro.factory.BolsaFabricaTest;
import br.com.cdan.negocio.financeiro.factory.ContaAPagarFabricaTest;
import br.com.cdan.negocio.geral.cep.CEPDao;
import br.com.cdan.negocio.geral.cep.factory.CEPFabricaTest;

public class EmpresaFabricaTest {
	private static EmpresaFabricaTest instance = null;

	public static synchronized EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new EmpresaFabricaTest();
		}
		return instance;
	}

	public Empresa criaEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setAtivo(Boolean.TRUE);
		// Bolsas
		Set<Bolsa> bolsas = new LinkedHashSet<>();
		bolsas.add(BolsaFabricaTest.getInstance().criaBolsa());
		bolsas.add(BolsaFabricaTest.getInstance().criaBolsa());
		empresa.setBolsas(bolsas);
		// CEPs
		Set<CEP> ceps = new LinkedHashSet<>();
		ceps.add(CEPFabricaTest.getInstance().criaCEP());
		ceps.add(CEPFabricaTest.getInstance().criaCEP());
		empresa.setCep(ceps);
		//
		empresa.setCnpj("cnpj");
		empresa.setCodigoArea("codigo Area");
		// Contas a pagar
		Set<ContaAPagar> contasAPagar = new LinkedHashSet<>();
		contasAPagar.add(ContaAPagarFabricaTest.getInstance().criaContasAPagar());
		empresa.setContasAPagar(contasAPagar);
		return empresa;
	}

	public Empresa criaEmpresaPersistido(EntityManager em) {
		EmpresaDao dao = new EmpresaDao(em);
		Empresa empresa = criaEmpresa();
		// Bolsas
		Set<Bolsa> bolsas = new LinkedHashSet<>();
		BolsaDao bolsaDao = new BolsaDao(em);
		empresa.getBolsas().forEach(b -> {
			bolsaDao.persist(b);
			bolsas.add(b);
		});
		empresa.setBolsas(bolsas);
		// CEPs
		Set<CEP> ceps = new LinkedHashSet<>();
		CEPDao cepDao = new CEPDao(em);
		empresa.getCep().forEach(c -> {
			cepDao.persist(c);
			ceps.add(c);
		});
		empresa.setCep(ceps);
		//
		//

		dao.persist(empresa);
		return empresa;
	}
}
