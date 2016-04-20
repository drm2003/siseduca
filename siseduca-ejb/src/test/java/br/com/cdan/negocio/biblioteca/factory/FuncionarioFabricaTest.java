package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.financeiro.ContasAPagar;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Funcionario;

public class FuncionarioFabricaTest {
	private static FuncionarioFabricaTest instance = null;

	public static synchronized FuncionarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new FuncionarioFabricaTest();
		}
		return instance;
	}

	public Funcionario criaFuncionario() {
		Funcionario f = new Funcionario();
		f.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		f.setAtendente(Boolean.TRUE);
		f.setAtivo(Boolean.TRUE);
		f.setCargo(CargoFabricaTest.getInstance().criaCargo());
		// Contas a pagar
		Set<ContasAPagar> contasAPagar = new LinkedHashSet<>();
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		f.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContasAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		contasAReceber.add(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		f.setContasAReceber(contasAReceber);
		//
		f.setDadosProfissionais(DadosProfissionaisFabricaTest.getInstance().criaDadosProfissionais());
		// Estagio monografia
		Set<EstagioMonografia> estagioMonografia = new LinkedHashSet<>();
		estagioMonografia.add(EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografia());
		estagioMonografia.add(EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografia());
		f.setEstagioMonografia(estagioMonografia);
		//
		f.setFinanceiroFuncionario(FinanceiroFuncionarioFabricaTest.getInstance().criaFinanceiroFuncionario());
		// Follows
		Set<Follow> follows = new LinkedHashSet<>();
		follows.add(FollowFabricaTest.getInstance().criaFollow());
		follows.add(FollowFabricaTest.getInstance().criaFollow());
		f.setFollows(follows);
		//
		f.setNomeApelido("nomeApelido");
		f.setNumeroDependentes(Long.valueOf("10"));
		f.setNumeroMatricula("numeroMatricula");
		f.setObservacoes("observacoes");
		// Ocorrências
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		f.setOcorrencias(ocorrencias);
		//
		f.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		f.setProfessor(Boolean.FALSE);
		f.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		// Turmas
		Set<Turma> turmas = new LinkedHashSet<>();
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		f.setTurmas(turmas);
		//
		f.setUsuario(UsuarioFabricaTest.getInstance().criaUsuario());
		//
		return f;
	}

}
