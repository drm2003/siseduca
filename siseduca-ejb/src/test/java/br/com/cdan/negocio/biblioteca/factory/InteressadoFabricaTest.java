package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Interessado;

public class InteressadoFabricaTest {
	private static InteressadoFabricaTest instance = null;

	public static synchronized InteressadoFabricaTest getInstance() {
		if (instance == null) {
			instance = new InteressadoFabricaTest();
		}
		return instance;
	}

	public Interessado criaInteressado() {
		Interessado i = new Interessado();
		i.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		i.setAtendente(FuncionarioFabricaTest.getInstance().criaFuncionario());
		i.setAtivo(Boolean.TRUE);
		// Cursos e Turmas de Interesse
		Set<Curso> cursos = new LinkedHashSet<>();
		cursos.add(CursoFabricaTest.getInstance().criaCurso());
		cursos.add(CursoFabricaTest.getInstance().criaCurso());
		i.setCursosTumasDeInteresse(cursos);
		// Follows do interessado
		Set<Follow> followsDoInteressado = new LinkedHashSet<>();
		followsDoInteressado.add(FollowFabricaTest.getInstance().criaFollow());
		followsDoInteressado.add(FollowFabricaTest.getInstance().criaFollow());
		i.setFollowsDoInteressado(followsDoInteressado);
		//
		i.setMelhorHorarioParaContato(MelhorHorarioParaContatoFabricaTest.getInstance().criaMelhorHorarioParaContato());
		i.setMidia(MidiaFabricaTest.getInstance().criaMidia());
		i.setMotivosDeNaoFechamentoDeContrato(
				MotivosDeNaoFechamentoDeContratoFabricaTest.getInstance().criaMotivosDeNaoFechamentoDeContrato());
		i.setObservacaoDoAluno(ObservacaoDoAlunoFabricaTest.getInstance().criaObservacaoDoAluno());
		i.setOrigem(OrigemFabricaTest.getInstance().criaOrigem());
		i.setOutraEscolaDoAluno("outraEscolaDoAluno");
		i.setPromotorDeVendas(FuncionarioFabricaTest.getInstance().criaFuncionario());
		i.setTipoDeContato(TipoDeContatoFabricaTest.getInstance().criaTipoDeContato());
		return i;
	}
}
