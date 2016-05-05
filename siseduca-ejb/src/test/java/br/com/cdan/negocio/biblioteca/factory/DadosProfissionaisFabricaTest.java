package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePagamento;
import br.com.cdan.model.pessoa.DadosProfissionais;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.biblioteca.DadosProfissionaisDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;

public class DadosProfissionaisFabricaTest {
	private static DadosProfissionaisFabricaTest instance = null;

	public static synchronized DadosProfissionaisFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadosProfissionaisFabricaTest();
		}
		return instance;
	}

	public DadosProfissionais criaDadosProfissionais() {
		DadosProfissionais d = new DadosProfissionais();
		d.setAtivo(Boolean.TRUE);
		d.setAulasNormais(BigDecimal.TEN);
		d.setCarteiraProfissional("carteiraProfissional");
		d.setCurriculo("curriculo");
		d.setDataDeAdmissao(Calendar.getInstance());
		d.setDataDeDemissao(null);
		d.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionario());
		d.setINSS(BigDecimal.TEN);
		d.setSalario(BigDecimal.ONE);
		d.setTipoDePagamento(EnumTipoDePagamento.HORISTA);
		return d;
	}

	public DadosProfissionais criaDadosProfissionaisPersistido(EntityManager em) {
		DadosProfissionais d = criaDadosProfissionais();
		DadosProfissionaisDao dao = new DadosProfissionaisDao(em);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario funcionario = d.getFuncionario();
		funcionarioDao.persist(funcionario);
		d.setFuncionario(funcionario);
		//
		dao.persist(d);
		return d;
	}

}
