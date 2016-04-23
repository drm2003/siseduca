package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.financeiro.Conta;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.negocio.biblioteca.BancoDao;
import br.com.cdan.negocio.biblioteca.CaixaDao;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.ContaDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;

public class ContaFabricaTest {
	private static ContaFabricaTest instance = null;

	public static synchronized ContaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaFabricaTest();
		}
		return instance;
	}

	public Conta criaConta() {
		Conta c = new Conta();
		c.setAtivo(Boolean.TRUE);
		c.setBanco(BancoFabricaTest.getInstance().criaBanco());
		// Caixas
		Set<Caixa> caixas = new LinkedHashSet<>();
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		c.setCaixas(caixas);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		c.setContasAReceber(contasAReceber);
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		c.setEmpresas(empresas);
		//
		c.setEncerrada(Boolean.FALSE);
		c.setNumero("numero");
		c.setTitular("titular");
		//
		return c;
	}

	public Conta criaContaPersistido(EntityManager em) {
		ContaDao contaDao = new ContaDao();
		contaDao.setEntityManager(em);
		Conta c = criaConta();
		// Banco
		BancoDao bancoDao = new BancoDao();
		bancoDao.setEntityManager(em);
		Banco banco = c.getBanco();
		bancoDao.persist(banco);
		c.setBanco(banco);
		// Caixas
		CaixaDao caixaDao = new CaixaDao();
		caixaDao.setEntityManager(em);
		Set<Caixa> caixas = new LinkedHashSet<>();
		c.getCaixas().forEach(caixa -> {
			caixaDao.persist(caixa);
			caixas.add(caixa);
		});
		c.setCaixas(caixas);
		// Contas a receber
		ContaAReceberDao contasAReceberDao = new ContaAReceberDao();
		contasAReceberDao.setEntityManager(em);
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		c.getContasAReceber().forEach(contaAReceber -> {
			contasAReceberDao.persist(contaAReceber);
			contasAReceber.add(contaAReceber);
		});
		c.setContasAReceber(contasAReceber);
		// Empresas
		EmpresaDao empresaDao = new EmpresaDao();
		empresaDao.setEntityManager(em);
		Set<Empresa> empresas = new LinkedHashSet<>();
		c.getEmpresas().forEach(empresa -> {
			empresaDao.persist(empresa);
			empresas.add(empresa);
		});
		c.setEmpresas(empresas);
		//
		return c;
	}
}
