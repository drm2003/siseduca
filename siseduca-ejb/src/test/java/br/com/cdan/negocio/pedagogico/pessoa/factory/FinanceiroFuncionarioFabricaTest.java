package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.FinanceiroFuncionario;
import br.com.cdan.negocio.financeiro.factory.BancoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.FinanceiroFuncionarioDao;

public class FinanceiroFuncionarioFabricaTest {
	private static FinanceiroFuncionarioFabricaTest instance = null;

	public static synchronized FinanceiroFuncionarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new FinanceiroFuncionarioFabricaTest();
		}
		return instance;
	}

	public FinanceiroFuncionario criaFinanceiroFuncionario(EntityManager em) {
		FinanceiroFuncionario f = new FinanceiroFuncionario();
		f.setAtivo(Boolean.TRUE);
		f.setAgencia("agencia");
		f.setBanco(BancoFabricaTest.getInstance().criaBancoPersistido(em));
		f.setCodigoDoCliente("codigoDoCliente");
		f.setConta("conta");
		f.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		return f;
	}

	public FinanceiroFuncionario criaFinanceiroFuncionarioPersistido(EntityManager em) {
		FinanceiroFuncionario f = criaFinanceiroFuncionario(em);
		FinanceiroFuncionarioDao dao = new FinanceiroFuncionarioDao(em);
		//
		dao.persist(f);
		return f;
	}
}
