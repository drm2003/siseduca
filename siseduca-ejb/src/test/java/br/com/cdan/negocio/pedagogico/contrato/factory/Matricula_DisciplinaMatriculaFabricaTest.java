package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Matricula_DisciplinaMatricula;
import br.com.cdan.negocio.pedagogico.contrato.Matricula_DisciplinaMatriculaDao;

public class Matricula_DisciplinaMatriculaFabricaTest {
	private static Matricula_DisciplinaMatriculaFabricaTest instance = null;

	public static synchronized Matricula_DisciplinaMatriculaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Matricula_DisciplinaMatriculaFabricaTest();
		}
		return instance;
	}

	public Matricula_DisciplinaMatricula criaMatricula_DisciplinaMatricula() {
		Matricula_DisciplinaMatricula m = new Matricula_DisciplinaMatricula();
		m.setAtivo(Boolean.TRUE);
		//
		BigDecimal[] notas = new BigDecimal[5];
		for (int i = 0; i < notas.length; i++) {
			notas[i] = BigDecimal.valueOf(Math.random() * 1000);
		}
		m.setNotas(notas);
		//
		m.setObservacao("observacao");
		m.setQuantidadeNotas(Long.valueOf(2));
		m.setQuantidadeRecuperacao(Long.valueOf(1));
		//
		BigDecimal[] recuperacao = new BigDecimal[2];
		for (int i = 0; i < recuperacao.length; i++) {
			recuperacao[i] = BigDecimal.valueOf(Math.random() * 1000);
		}
		m.setRecuperacao(recuperacao);
		//
		return m;
	}

	public Matricula_DisciplinaMatricula criaMatricula_DisciplinaMatriculaPersistido(EntityManager em) {
		Matricula_DisciplinaMatricula m = criaMatricula_DisciplinaMatricula();
		Matricula_DisciplinaMatriculaDao dao = new Matricula_DisciplinaMatriculaDao(em);
		//
		dao.persist(m);
		return m;
	}

}
