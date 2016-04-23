package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.ContaAReceber_Bolsa;
import br.com.cdan.model.financeiro.PlanoDeConta;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.ContasAReceber_BolsaDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.InvestimentoDao;
import br.com.cdan.negocio.biblioteca.PlanoDeContaDao;
import br.com.cdan.negocio.biblioteca.TipoDeCobrancaRecebimentoDao;
import br.com.cdan.negocio.biblioteca.TurmaDao;

public class ContaAReceberFabricaTest {
	private static ContaAReceberFabricaTest instance = null;

	public static synchronized ContaAReceberFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaAReceberFabricaTest();
		}
		return instance;
	}

	public ContaAReceber criaContaAReceber() {
		ContaAReceber c = new ContaAReceber();
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
		Set<ContaAReceber_Bolsa> contasAReceberBolsa = new LinkedHashSet<>();
		contasAReceberBolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_Bolsa());
		contasAReceberBolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_Bolsa());
		c.setcontaAReceber_Bolsa(contasAReceberBolsa);
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

	public ContaAReceber criaContaAReceberPersistido(EntityManager em) {
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao();
		contaAReceberDao.setEntityManager(em);
		ContaAReceber c = criaContaAReceber();
		// Alunos
		Set<Aluno> alunos = new LinkedHashSet<>();
		AlunoDao alunoDao = new AlunoDao();
		alunoDao.setEntityManager(em);
		c.getAlunos().forEach(aluno -> {
			alunoDao.persist(aluno);
			alunos.add(aluno);
		});
		c.setAlunos(alunos);
		//
		c.setConta(ContaFabricaTest.getInstance().criaConta());
		// Contas a receber
		Set<ContaAReceber_Bolsa> contasAReceberBolsa = new LinkedHashSet<>();
		ContasAReceber_BolsaDao contasAReceber_BolsaDao = new ContasAReceber_BolsaDao();
		contasAReceber_BolsaDao.setEntityManager(em);
		c.getcontasAReceberBolsa().forEach(contaAReceberBolsa -> {
			contaAReceberDao.persist(contaAReceberBolsa);
			contasAReceberBolsa.add(contaAReceberBolsa);
		});
		c.setcontaAReceber_Bolsa(contasAReceberBolsa);
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		EmpresaDao empresaDao = new EmpresaDao();
		empresaDao.setEntityManager(em);
		c.getEmpresas().forEach(empresa -> {
			empresaDao.persist(empresa);
			empresas.add(empresa);
		});
		c.setEmpresas(empresas);
		// Funcionários
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		funcionarioDao.setEntityManager(em);
		c.getFuncionarios().forEach(funcionario -> {
			funcionarioDao.persist(funcionario);
			funcionarios.add(funcionario);
		});
		c.setFuncionarios(funcionarios);
		//
		Investimento investimento = new Investimento();
		InvestimentoDao investimentoDao = new InvestimentoDao();
		investimentoDao.setEntityManager(em);
		investimento = c.getInvestimento();
		investimentoDao.persist(investimento);
		c.setInvestimento(investimento);
		//
		PlanoDeConta planoDeConta = new PlanoDeConta();
		PlanoDeContaDao planoDeContaDao = new PlanoDeContaDao();
		planoDeContaDao.setEntityManager(em);
		planoDeConta = c.getPlanoDeConta();
		planoDeContaDao.persist(planoDeConta);
		c.setPlanoDeConta(planoDeConta);
		//
		investimento = c.getPrimeiraParcela();
		investimentoDao.persist(investimento);
		c.setPrimeiraParcela(investimento);
		//
		TipoDeCobrancaRecebimentoDao tipoDeCobrancaRecebimentoDao = new TipoDeCobrancaRecebimentoDao();
		tipoDeCobrancaRecebimentoDao.setEntityManager(em);
		TipoDeCobrancaRecebimento tipoDeCobrancaRecebimento = new TipoDeCobrancaRecebimento();
		tipoDeCobrancaRecebimento = c.getTipoDeCobranca();
		tipoDeCobrancaRecebimentoDao.persist(tipoDeCobrancaRecebimento);
		c.setTipoDeCobranca(tipoDeCobrancaRecebimento);
		// Turmas
		Set<Turma> turmas = new LinkedHashSet<>();
		TurmaDao turmaDao = new TurmaDao();
		turmaDao.setEntityManager(em);
		c.getTurmas().forEach(turma -> {
			turmaDao.persist(turma);
			turmas.add(turma);
		});
		c.setTurmas(turmas);
		//
		contaAReceberDao.persist(c);
		return c;
	}
}
