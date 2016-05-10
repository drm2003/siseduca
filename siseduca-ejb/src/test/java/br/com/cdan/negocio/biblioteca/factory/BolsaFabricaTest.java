package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumEspecieDesconto;
import br.com.cdan.comum.EnumTipoDeDesconto;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContaAReceber_Bolsa;
import br.com.cdan.model.financeiro.Desconto;
import br.com.cdan.model.pessoa.DadoBancario;
import br.com.cdan.negocio.biblioteca.BolsaDao;
import br.com.cdan.negocio.biblioteca.ContasAReceber_BolsaDao;
import br.com.cdan.negocio.biblioteca.DadoBancarioDao;
import br.com.cdan.negocio.biblioteca.DescontoDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;

public class BolsaFabricaTest {
	private static BolsaFabricaTest instance = null;

	public static synchronized BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new BolsaFabricaTest();
		}
		return instance;
	}

	public Bolsa criaBolsa() {
		Bolsa b = new Bolsa();
		b.setAtivo(Boolean.TRUE);
		// Contas a receber e Bolsa
		Set<ContaAReceber_Bolsa> contasAReceber_Bolsa = new LinkedHashSet<>();
		contasAReceber_Bolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_Bolsa());
		contasAReceber_Bolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_Bolsa());
		b.setContasAReceber_Bolsa(contasAReceber_Bolsa);
		//
		b.setDadoBancario(DadoBancarioFabricaTest.getInstance().criaDadoBancario());
		// Descontos
		Set<Desconto> descontos = new LinkedHashSet<>();
		descontos.add(DescontoFabricaTest.getInstance().criaDesconto());
		descontos.add(DescontoFabricaTest.getInstance().criaDesconto());
		b.setDescontos(descontos);
		//
		b.setDescricao("descricao");
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		b.setEmpresas(empresas);
		//
		b.setEspecieDesconto(EnumEspecieDesconto.PERCENTUAL);
		b.setPadraoFranquia("padraoFranquia");
		b.setTipoDeDesconto(EnumTipoDeDesconto.BOLSA);
		return b;
	}

	public Bolsa criaBolsaPersistido(EntityManager em) {
		Bolsa b = criaBolsa();
		BolsaDao dao = new BolsaDao(em);
		// contas a receber e bolsa
		ContasAReceber_BolsaDao contasAReceber_BolsaDao = new ContasAReceber_BolsaDao(em);
		Set<ContaAReceber_Bolsa> contasAReceber_Bolsas = new LinkedHashSet<>();
		b.getContasAReceber_Bolsa().forEach(cReceberBolsa -> {
			contasAReceber_BolsaDao.persist(cReceberBolsa);
			contasAReceber_Bolsas.add(cReceberBolsa);
		});
		b.setContasAReceber_Bolsa(contasAReceber_Bolsas);
		// Dado bancário
		DadoBancarioDao dadoBancarioDao = new DadoBancarioDao(em);
		DadoBancario dadoBancario = b.getDadoBancario();
		dadoBancarioDao.persist(dadoBancario);
		// Descontos
		DescontoDao descontoDao = new DescontoDao(em);
		Set<Desconto> descontos = new LinkedHashSet<>();
		b.getDescontos().forEach(desconto -> {
			descontoDao.persist(desconto);
			descontos.add(desconto);
		});
		b.setDescontos(descontos);
		// Empresas
		EmpresaDao empresaDao = new EmpresaDao(em);
		Set<Empresa> empresas = new LinkedHashSet<>();
		b.getEmpresas().forEach(empresa -> {
			empresaDao.persist(empresa);
			empresas.add(empresa);
		});
		b.setEmpresas(empresas);
		//
		dao.persist(b);
		return b;
	}
}