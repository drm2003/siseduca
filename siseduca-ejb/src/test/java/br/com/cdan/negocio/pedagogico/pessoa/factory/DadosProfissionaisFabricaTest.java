package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePagamento;
import br.com.cdan.model.pessoa.DadosProfissionais;
import br.com.cdan.negocio.pedagogico.pessoa.DadosProfissionaisDao;

public class DadosProfissionaisFabricaTest {
	private static DadosProfissionaisFabricaTest instance = null;

	public static synchronized DadosProfissionaisFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadosProfissionaisFabricaTest();
		}
		return instance;
	}

	public DadosProfissionais criaDadosProfissionais(EntityManager em) {
		DadosProfissionais d = new DadosProfissionais();
		d.setAtivo(Boolean.TRUE);
		d.setAulasNormais(BigDecimal.TEN);
		d.setCarteiraProfissional("carteiraProfissional");
		d.setCurriculo("curriculo");
		d.setDataDeAdmissao(Calendar.getInstance());
		d.setDataDeDemissao(null);
		d.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		d.setINSS(BigDecimal.TEN);
		d.setSalario(BigDecimal.ONE);
		d.setTipoDePagamento(EnumTipoDePagamento.HORISTA);
		return d;
	}

	public DadosProfissionais criaDadosProfissionaisPersistido(EntityManager em) {
		DadosProfissionais d = criaDadosProfissionais(em);
		DadosProfissionaisDao dao = new DadosProfissionaisDao(em);
		//
		dao.persist(d);
		return d;
	}

}
