package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.negocio.pedagogico.contrato.AproveitamentoDao;
import br.com.cdan.negocio.pedagogico.contrato.DisciplinaMatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.MatriculaDao;

public class DisciplinaMatriculaFabricaTest {
	private static DisciplinaMatriculaFabricaTest instance = null;

	public static synchronized DisciplinaMatriculaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DisciplinaMatriculaFabricaTest();
		}
		return instance;
	}

	public DisciplinaMatricula criaDisciplinaMatricula() {
		DisciplinaMatricula d = new DisciplinaMatricula();
		d.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		d.setAtivo(Boolean.TRUE);
		d.setCargaHoraria(Calendar.getInstance().getTime());
		d.setCodigoINEP("codigoINEP");
		d.setCompartilhado(Boolean.TRUE);
		d.setMatricula(MatriculaFabricaTest.getInstance().criaMatricula());
		d.setMatrizCurricular(Long.valueOf("10"));
		d.setNome("nome");
		d.setOpcional(Boolean.FALSE);
		d.setSigla("sigla");
		d.setTipoDeCurso(Long.valueOf("20"));
		d.setTipoDeDisciplina(Long.valueOf("23"));
		d.setValorHoraAula(BigDecimal.ZERO);
		return d;
	}

	public DisciplinaMatricula criaDisciplinaMatriculaPersistido(EntityManager em) {
		DisciplinaMatriculaDao dao = new DisciplinaMatriculaDao(em);
		DisciplinaMatricula d = criaDisciplinaMatricula();
		//
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		Aproveitamento aproveitamento = d.getAproveitamento();
		aproveitamentoDao.persist(aproveitamento);
		d.setAproveitamento(aproveitamento);
		//
		MatriculaDao matriculaDao = new MatriculaDao(em);
		Matricula matricula = d.getMatricula();
		matriculaDao.persist(matricula);
		d.setMatricula(matricula);
		//
		dao.persist(d);
		return d;
	}

}
