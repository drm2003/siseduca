package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;

public class TurmaFabricaTest {
	private static TurmaFabricaTest instance = null;

	public static synchronized TurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TurmaFabricaTest();
		}
		return instance;
	}

	public Turma criaTurma(){
		Turma t = new Turma();
		t.setAtivo(Boolean.TRUE);
		t.setCalendarioPadrao(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo();
		t.setCodigo(Long.valueOf("10"));
		//Contas a receber
		Set<ContaAReceber> contasAReceber =new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		t.setContasAReceber(contasAReceber);
		t.setCurso(CursoFabricaTest.getInstance().criaCurso());
		t.setDataInicio(Calendar.getInstance());
		t.setDataTermino(Calendar.getInstance());
		t.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAula());
		t.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		t.setNome("nome");
		//Ocorrências
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		t.setOcorrencias(ocorrencias);
		//
		t.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionario());
		t.setSalaDeAulaPadrao("salaDeAulaPadrao");
		t.setSigla("sigla");
		t.setSituacaoDaTurma(SituacaoDaTurmaFabricaTest.getInstance().criaSituacaoDaTurma());
		//
		Set<Turma_Disciplina> turmas_Disciplina = new LinkedHashSet<>();
		turmas_Disciplina.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		turmas_Disciplina.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		t.setTurma_Disciplina(turmas_Disciplina);
		return t;
	}
}
