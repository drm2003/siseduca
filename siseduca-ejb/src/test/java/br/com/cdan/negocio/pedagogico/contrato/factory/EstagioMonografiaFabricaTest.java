package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoEstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.DadosEmpresaConcedente;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.AnexoDocumentos;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.pedagogico.contrato.DadosEmpresaConcedenteDao;
import br.com.cdan.negocio.pedagogico.contrato.EstagioMonografiaDao;
import br.com.cdan.negocio.pedagogico.curso.Turma_DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.Turma_DisciplinaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.AnexoDocumentosDao;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AnexoDocumentosFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class EstagioMonografiaFabricaTest {
	private static EstagioMonografiaFabricaTest instance = null;

	public static synchronized EstagioMonografiaFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstagioMonografiaFabricaTest();
		}
		return instance;
	}

	public EstagioMonografia criaEstagioMonografia() {
		EstagioMonografia e = new EstagioMonografia();
		e.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		// Anexos
		Set<AnexoDocumentos> anexos = new LinkedHashSet<>();
		anexos.add(AnexoDocumentosFabricaTest.getInstance().criaAnexoDocumentos());
		anexos.add(AnexoDocumentosFabricaTest.getInstance().criaAnexoDocumentos());
		e.setAnexos(anexos);
		//
		e.setAtivo(Boolean.TRUE);
		e.setConcluido(Boolean.FALSE);
		e.setDadosEmpresaConcedente(DadosEmpresaConcedenteFabricaTest.getInstance().criaDadosEmpresaConcedente());
		e.setDataInicio(Calendar.getInstance());
		e.setDataTermino(Calendar.getInstance());
		e.setHoraInicio(Calendar.getInstance().getTime().getTime());
		e.setHoraTermino(Calendar.getInstance().getTime().getTime());
		e.setNota(BigDecimal.TEN);
		e.setObservacao("observacao");
		e.setOrientadorSupervisor(FuncionarioFabricaTest.getInstance().criaFuncionario());
		e.setResultado("resultado");
		e.setTipoEstagioMonografia(EnumTipoEstagioMonografia.ESTAGIO);
		e.setTituloTema("tituloTema");
		// Turma_Disciplina
		Set<Turma_Disciplina> turma_disciplinas = new LinkedHashSet<>();
		turma_disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		turma_disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		e.setTurma_Disciplina(turma_disciplinas);
		return e;
	}

	public EstagioMonografia criaEstagioMonografiaPersistido(EntityManager em) {
		EstagioMonografia e = criaEstagioMonografia();
		EstagioMonografiaDao dao = new EstagioMonografiaDao(em);
		//
		Aluno aluno = e.getAluno();
		AlunoDao alunoDao = new AlunoDao(em);
		alunoDao.persist(aluno);
		e.setAluno(aluno);
		// Anexos
		Set<AnexoDocumentos> anexos = new LinkedHashSet<>();
		AnexoDocumentosDao anexoDocumentosDao = new AnexoDocumentosDao(em);
		e.getAnexos().forEach(anexoDocumento -> {
			anexoDocumentosDao.persist(anexoDocumento);
			anexos.add(anexoDocumento);
		});
		e.setAnexos(anexos);
		//
		DadosEmpresaConcedenteDao dadosEmpresaConcedenteDao = new DadosEmpresaConcedenteDao(em);
		DadosEmpresaConcedente dadosEmpresaConcedente = e.getDadosEmpresaConcedente();
		dadosEmpresaConcedenteDao.persist(dadosEmpresaConcedente);
		e.setDadosEmpresaConcedente(dadosEmpresaConcedente);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario orientadorSupervisor = e.getOrientadorSupervisor();
		funcionarioDao.persist(orientadorSupervisor);
		e.setOrientadorSupervisor(orientadorSupervisor);
		// Turma_Disciplina
		Set<Turma_Disciplina> turma_disciplinas = new LinkedHashSet<>();
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		e.getTurma_Disciplina().forEach(turma_Disciplina -> {
			turma_DisciplinaDao.persist(turma_Disciplina);
			turma_disciplinas.add(turma_Disciplina);
		});
		e.setTurma_Disciplina(turma_disciplinas);
		//
		dao.persist(e);
		return e;
	}

}
