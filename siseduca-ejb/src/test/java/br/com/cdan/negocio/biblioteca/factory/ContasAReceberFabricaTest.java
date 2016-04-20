package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.financeiro.ContasAReceber_Bolsa;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

public class ContasAReceberFabricaTest {
	private static ContasAReceberFabricaTest instance = null;

	public static synchronized ContasAReceberFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContasAReceberFabricaTest();
		}
		return instance;
	}

	public ContasAReceber criaContasAReceber() {
		ContasAReceber c = new ContasAReceber();
		// Alunos
		Set<Aluno> alunos = new LinkedHashSet<>();
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		c.setAlunos(alunos);
		//
		c.setAtivo(Boolean.TRUE);
		c.setComplementoPlanoDeConta("complementoPlanoDeConta");
		c.setConta(ContaFabricaTest.getInstance().criaConta());
		// Contas a receber
		Set<ContasAReceber_Bolsa> contasAReceberBolsa = new LinkedHashSet<>();
		contasAReceberBolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_BolsaFabricaTest());
		contasAReceberBolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_BolsaFabricaTest());
		c.setContasAReceber(contasAReceberBolsa);
		//
		c.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		c.setDataVencimento(Calendar.getInstance());
		c.setDocumentoPlanoDeConta("documentoPlanoDeConta");
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		c.setEmpresas(empresas);
		// Funcionários
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		funcionarios.add(FuncionarioFabricaTest.getInstance().criaFuncionario());
		c.setFuncionarios(funcionarios);
		//
		c.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimento());
		c.setObservacao("observacao");
		c.setPlanoDeConta(PlanoDeContasFabricaTest.getInstance().criaPlanoDeContas());
		c.setPrimeiraParcela(InvestimentoFabricaTest.getInstance().criaInvestimento());
		c.setTipoDeCobranca(TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimento());
		// Turmas
		Set<Turma> turmas = new LinkedHashSet<>();
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		c.setTurmas(turmas);
		//
		c.setValorDiferenciadoPrimeiraParcela(Boolean.TRUE);
		return c;
	}

}
