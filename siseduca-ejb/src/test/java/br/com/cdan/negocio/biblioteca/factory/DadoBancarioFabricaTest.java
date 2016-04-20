package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.pessoa.DadoBancario;
import br.com.cdan.negocio.biblioteca.BolsaDao;
import br.com.cdan.negocio.biblioteca.DadoBancarioDao;

public class DadoBancarioFabricaTest {
	private static DadoBancarioFabricaTest instance = null;

	public static synchronized DadoBancarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadoBancarioFabricaTest();
		}
		return instance;
	}

	public DadoBancario criaDadoBancario() {
		DadoBancario d = new DadoBancario();
		d.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		// Bolsas
		Set<Bolsa> bolsas = new LinkedHashSet<>();
		bolsas.add(BolsaFabricaTest.getInstance().criaBolsa());
		bolsas.add(BolsaFabricaTest.getInstance().criaBolsa());
		d.setBolsa(bolsas);
		//
		d.setDiaDoVencimento(Long.valueOf("1"));
		d.setInadimplente(Boolean.TRUE);
		return d;
	}

	public DadoBancario criaDadoBancarioPersistido(EntityManager em) {
		DadoBancario d = criaDadoBancario();
		DadoBancarioDao dao = new DadoBancarioDao();
		dao.setEntityManager(em);
		//
		BolsaDao bolsaDao = new BolsaDao();
		bolsaDao.setEntityManager(em);
		Set<Bolsa> bolsas = new LinkedHashSet<>();
		d.getBolsas().forEach(b -> {
			bolsaDao.persist(b);
			bolsas.add(b);
		});
		d.setBolsa(bolsas);
		//
		dao.persist(d);
		return d;
	}
}
