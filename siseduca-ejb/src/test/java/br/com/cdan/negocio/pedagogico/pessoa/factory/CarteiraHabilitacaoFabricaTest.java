package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.EstadoUF;
import br.com.cdan.model.pessoa.CarteiraHabilitacao;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.geral.EstadoUFDao;
import br.com.cdan.negocio.geral.factory.EstadoUFFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.CarteiraHabilitacaoDao;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;

public class CarteiraHabilitacaoFabricaTest {
	private static CarteiraHabilitacaoFabricaTest instance = null;

	public static synchronized CarteiraHabilitacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CarteiraHabilitacaoFabricaTest();
		}
		return instance;
	}

	public CarteiraHabilitacao criaCarteiraHabilitacao() {
		CarteiraHabilitacao c = new CarteiraHabilitacao();
		c.setAtivo(Boolean.TRUE);
		c.setCategoria("categoria");
		c.setEstado(EstadoUFFabricaTest.getInstance().criaEstadoUF());
		c.setNumeroCNH("numeroCNH");
		c.setNumeroRegistro("numeroRegistro");
		c.setOrgaoEmissor("orgaoEmissor");
		c.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		c.setValidade(Calendar.getInstance());
		return c;
	}

	public CarteiraHabilitacao criaCarteiraHabilitacaoPersistido(EntityManager em) {
		CarteiraHabilitacao c = criaCarteiraHabilitacao();
		CarteiraHabilitacaoDao dao = new CarteiraHabilitacaoDao(em);
		//
		EstadoUFDao estadoUFDao = new EstadoUFDao(em);
		EstadoUF estadoUF = c.getEstado();
		estadoUFDao.persist(estadoUF);
		c.setEstado(estadoUF);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = c.getPessoa();
		pessoaDao.persist(pessoa);
		c.setPessoa(pessoa);
		//
		dao.persist(c);
		return c;
	}
}
