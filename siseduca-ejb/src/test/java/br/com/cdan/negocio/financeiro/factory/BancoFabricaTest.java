package br.com.cdan.negocio.financeiro.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.model.financeiro.Conta;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.negocio.financeiro.BancoDao;
import br.com.cdan.negocio.financeiro.ContaDao;
import br.com.cdan.negocio.geral.TelefoneDao;
import br.com.cdan.negocio.geral.factory.TelefoneFabricaTest;

public class BancoFabricaTest {
	private static BancoFabricaTest instance = null;

	public static synchronized BancoFabricaTest getInstance() {
		if (instance == null) {
			instance = new BancoFabricaTest();
		}
		return instance;
	}

	public Banco criaBanco() {
		Banco b = new Banco();
		b.setAgencia("agencia");
		b.setAtivo(Boolean.TRUE);
		// Contas
		Set<Conta> contas = new LinkedHashSet<>();
		contas.add(ContaFabricaTest.getInstance().criaConta());
		contas.add(ContaFabricaTest.getInstance().criaConta());
		b.setContas(contas);
		//
		b.setNome("nome");
		// Telefones
		Set<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		b.setTelefones(telefones);
		//
		return b;
	}

	public Banco criaBancoPersistido(EntityManager em) {
		Banco b = criaBanco();
		BancoDao bancoDao = new BancoDao(em);
		// Contas
		Set<Conta> contas = new LinkedHashSet<>();
		ContaDao contaDao = new ContaDao(em);
		b.getContas().forEach(conta -> {
			contaDao.persist(conta);
			contas.add(conta);
		});
		b.setContas(contas);
		// Telefones
		Set<Telefone> telefones = new LinkedHashSet<>();
		TelefoneDao telefoneDao = new TelefoneDao(em);
		b.getTelefones().forEach(telefone -> {
			telefoneDao.persist(telefone);
			telefones.add(telefone);
		});
		b.setTelefones(telefones);
		//
		bancoDao.persist(b);
		return b;
	}
}
