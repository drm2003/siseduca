package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.financeiro.ContasAPagar;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Responsavel;

public class AlunoFabricaTest {
	private static AlunoFabricaTest instance = null;

	public static synchronized AlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AlunoFabricaTest();
		}
		return instance;
	}

	public Aluno criaAluno() {
		Aluno aluno = new Aluno();
		aluno.setAtivo(Boolean.TRUE);
		aluno.setCodigoDeBarras("codigoDeBarras");
		// Contas a pagar
		Set<ContasAPagar> contasAPagar = new LinkedHashSet<>();
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		aluno.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContasAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		aluno.setContasAReceber(contasAReceber);
		//
		aluno.setDadoBancario(DadoBancarioFabricaTest.getInstance().criaDadoBancario());
		aluno.setDependencia(DependenciaFabricaTest.getInstance().criaDependencia());
		aluno.setDiarioDeAula(DiarioDeAulaFabricaTest.getInstance().criaDiarioDeAula());
		// Estagio Monografia
		Set<EstagioMonografia> estagioMonografias = new LinkedHashSet<>();
		estagioMonografias.add(EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografia());
		estagioMonografias.add(EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografia());
		aluno.setEstagioMonografia(estagioMonografias);
		//
		aluno.setInteressado(InteressadoFabricaTest.getInstance().criaInteressado());
		aluno.setLoginPortal("loginPortal");
		// Matrículas
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		aluno.setMatriculas(matriculas);
		//
		aluno.setNumeroMatricula("numeroMatricula");
		// Ocorrências
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		aluno.setOcorrencias(ocorrencias);
		//
		aluno.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		aluno.setRa("ra");
		aluno.setReceberEmail(Boolean.TRUE);
		aluno.setReceberSMS(Boolean.TRUE);
		// Responsáveis
		Set<Responsavel> responsaveis = new LinkedHashSet<>();
		responsaveis.add(ResponsavelFabricaTest.getInstance().criaResponsavel());
		responsaveis.add(ResponsavelFabricaTest.getInstance().criaResponsavel());
		aluno.setResponsaveis(responsaveis);
		//
		aluno.setResponsavelDidatico(ResponsavelFabricaTest.getInstance().criaResponsavelDidatico());
		aluno.setResponsavelEmpresa(ResponsavelFabricaTest.getInstance().criaResponsavelEmpresa());
		aluno.setResponsavelFinanceiro(ResponsavelFabricaTest.getInstance().criaResponsavelFinanceiro());
		aluno.setSenha("senha");
		aluno.setSituacaoDoAluno(SituacaoDoAlunoFabricaTest.getInstance().criaSituacaoDoAluno());
		aluno.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		return aluno;
	}
}
