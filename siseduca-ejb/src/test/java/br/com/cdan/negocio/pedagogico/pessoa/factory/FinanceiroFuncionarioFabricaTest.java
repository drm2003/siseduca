package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.model.pessoa.FinanceiroFuncionario;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.financeiro.BancoDao;
import br.com.cdan.negocio.financeiro.factory.BancoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.FinanceiroFuncionarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;

public class FinanceiroFuncionarioFabricaTest {
	private static FinanceiroFuncionarioFabricaTest instance = null;

	public static synchronized FinanceiroFuncionarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new FinanceiroFuncionarioFabricaTest();
		}
		return instance;
	}

	public FinanceiroFuncionario criaFinanceiroFuncionario() {
		FinanceiroFuncionario f = new FinanceiroFuncionario();
		f.setAtivo(Boolean.TRUE);
		f.setAgencia("agencia");
		f.setBanco(BancoFabricaTest.getInstance().criaBanco());
		f.setCodigoDoCliente("codigoDoCliente");
		f.setConta("conta");
		f.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionario());
		return f;
	}

	public FinanceiroFuncionario criaFinanceiroFuncionarioPersistido(EntityManager em) {
		FinanceiroFuncionario f = criaFinanceiroFuncionario();
		FinanceiroFuncionarioDao dao = new FinanceiroFuncionarioDao(em);
		//
		BancoDao bancoDao = new BancoDao(em);
		Banco banco = f.getBanco();
		bancoDao.persist(banco);
		f.setBanco(banco);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario funcionario = f.getFuncionario();
		funcionarioDao.persist(funcionario);
		f.setFuncionario(funcionario);
		//
		dao.persist(f);
		return f;
	}
}
