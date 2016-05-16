package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.DadoBancario;
import br.com.cdan.negocio.pedagogico.pessoa.DadoBancarioDao;

public class DadoBancarioFabricaTest {
	private static DadoBancarioFabricaTest instance = null;

	public static synchronized DadoBancarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadoBancarioFabricaTest();
		}
		return instance;
	}

	public DadoBancario criaDadoBancario(EntityManager em) {
		DadoBancario d = new DadoBancario();
		d.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		//
		d.setDiaDoVencimento(Long.valueOf("1"));
		d.setInadimplente(Boolean.TRUE);
		return d;
	}

	public DadoBancario criaDadoBancarioPersistido(EntityManager em) {
		DadoBancario d = criaDadoBancario(em);
		DadoBancarioDao dao = new DadoBancarioDao(em);
		//
		dao.persist(d);
		return d;
	}
}
