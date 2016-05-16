package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Dependencia;
import br.com.cdan.negocio.pedagogico.contrato.DependenciaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;

public class DependenciaFabricaTest {
	private static DependenciaFabricaTest instance = null;

	public static synchronized DependenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DependenciaFabricaTest();
		}
		return instance;
	}

	public Dependencia criaDependencia(EntityManager em) {
		Dependencia d = new Dependencia();
		d.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		d.setAtivo(Boolean.TRUE);
		//
		return d;
	}

	public Dependencia criaDependenciaPersistido(EntityManager em) {
		Dependencia d = criaDependencia(em);
		DependenciaDao dependenciaDao = new DependenciaDao(em);
		dependenciaDao.persist(d);
		return d;
	}
}
