package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.comum.EnumTipoEstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.AnexoDocumentos;

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

}
