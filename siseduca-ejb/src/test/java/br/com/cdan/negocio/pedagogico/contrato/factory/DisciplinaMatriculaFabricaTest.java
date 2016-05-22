package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.DisciplinaMatriculaDao;

public class DisciplinaMatriculaFabricaTest extends FabricaTest {
	private static DisciplinaMatriculaFabricaTest instance = null;

	public static synchronized DisciplinaMatriculaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DisciplinaMatriculaFabricaTest();
		}
		return instance;
	}

	public DisciplinaMatricula criaDisciplinaMatricula(EntityManager em) {
		DisciplinaMatricula d = new DisciplinaMatricula();
		d.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento(em));
		d.setMatricula(MatriculaFabricaTest.getInstance().criaMatriculaPersistido(em));
		d.setAtivo(Boolean.TRUE);
		d.setCargaHoraria(Calendar.getInstance().getTime());
		d.setCodigoINEP(criarStringDinamicaPorTamanho(10));
		d.setCompartilhado(Boolean.TRUE);
		d.setMatrizCurricular(Long.valueOf("10"));
		d.setNome(criarStringDinamicaPorTamanho(100));
		d.setOpcional(Boolean.FALSE);
		d.setSigla(criarStringDinamicaPorTamanho(10));
		d.setTipoDeCurso(Long.valueOf("20"));
		d.setTipoDeDisciplina(Long.valueOf("23"));
		d.setValorHoraAula(BigDecimal.ZERO);
		return d;
	}

	public DisciplinaMatricula criaDisciplinaMatriculaPersistido(EntityManager em) {
		DisciplinaMatriculaDao dao = new DisciplinaMatriculaDao(em);
		DisciplinaMatricula d = criaDisciplinaMatricula(em);
		dao.persist(d);
		return d;
	}

}
