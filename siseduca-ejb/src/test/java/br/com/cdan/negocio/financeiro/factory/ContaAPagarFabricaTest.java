package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Conta;
import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.financeiro.PlanoDeConta;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.financeiro.ContaAPagarDao;
import br.com.cdan.negocio.financeiro.ContaDao;
import br.com.cdan.negocio.financeiro.PlanoDeContaDao;
import br.com.cdan.negocio.financeiro.TipoDeCobrancaRecebimentoDao;
import br.com.cdan.negocio.pedagogico.curso.InvestimentoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.InvestimentoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class ContaAPagarFabricaTest {
	private static ContaAPagarFabricaTest instance = null;

	public static synchronized ContaAPagarFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaAPagarFabricaTest();
		}
		return instance;
	}

	public ContaAPagar criaContasAPagar() {
		ContaAPagar c = new ContaAPagar();
		// Alunos
		Set<Aluno> alunos = new LinkedHashSet<>();
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		c.setAlunos(alunos);
		//
		c.setAtivo(Boolean.TRUE);
		c.setComplementoPlanoDeConta("complemento plano de conta");
		//
		c.setConta(ContaFabricaTest.getInstance().criaConta());
		c.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		c.setDataVencimento(Calendar.getInstance());
		c.setDocumentoPlanoDeConta("documentoPlanoDeConta");
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		c.setEmpresas(empresas);
		// Funcionários
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		funcionarios.add(FuncionarioFabricaTest.getInstance().criaFuncionario());
		funcionarios.add(FuncionarioFabricaTest.getInstance().criaFuncionario());
		c.setFuncionarios(funcionarios);
		//
		c.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimento());
		c.setMora(BigDecimal.ZERO);
		c.setObservacao("observacao");
		c.setPlanoDeConta(PlanoDeContaFabricaTest.getInstance().criaPlanoDeContas());
		c.setPrimeiraParcela(InvestimentoFabricaTest.getInstance().criaInvestimento());
		c.setTipoDeCobranca(TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimento());
		c.setValorDiferenciadoPrimeiraParcela(Boolean.TRUE);
		return c;
	}

	public ContaAPagar criaContasAPagarPersistido(EntityManager em) {
		ContaAPagar c = criaContasAPagar();
		ContaAPagarDao dao = new ContaAPagarDao(em);
		dao.setEntityManager(em);
		// Alunos
		AlunoDao daoAluno = new AlunoDao(em);
		Set<Aluno> alunos = new LinkedHashSet<>();
		c.getAlunos().forEach(a -> {
			daoAluno.persist(a);
			alunos.add(a);
		});
		c.setAlunos(alunos);
		// Conta
		Conta conta = c.getConta();
		ContaDao daoConta = new ContaDao(em);
		daoConta.persist(conta);
		c.setConta(conta);
		// Empresas
		EmpresaDao daoEmpresa = new EmpresaDao(em);
		Set<Empresa> empresas = new LinkedHashSet<>();
		c.getEmpresas().forEach(e -> {
			daoEmpresa.persist(e);
			empresas.add(e);
		});
		c.setEmpresas(empresas);
		// Funcionários
		FuncionarioDao daoFuncionario = new FuncionarioDao(em);
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		c.getFuncionarios().forEach(f -> {
			daoFuncionario.persist(f);
			funcionarios.add(f);
		});
		c.setFuncionarios(funcionarios);
		// Investimento
		InvestimentoDao daoInvestimento = new InvestimentoDao(em);
		Investimento investimento = c.getInvestimento();
		daoInvestimento.persist(investimento);
		c.setInvestimento(investimento);
		// Primeira parcela
		Investimento primeiraParcela = c.getPrimeiraParcela();
		daoInvestimento.persist(primeiraParcela);
		c.setPrimeiraParcela(primeiraParcela);
		// Plano de Conta
		PlanoDeContaDao daoPlanoDeContas = new PlanoDeContaDao(em);
		PlanoDeConta planoDeConta = c.getPlanoDeConta();
		daoPlanoDeContas.persist(planoDeConta);
		c.setPlanoDeConta(planoDeConta);
		// tipo de Cobrança Recebimento
		TipoDeCobrancaRecebimentoDao tipoDeCobrancaRecebimentoDao = new TipoDeCobrancaRecebimentoDao(em);
		TipoDeCobrancaRecebimento tipoDeCobranca = c.getTipoDeCobranca();
		tipoDeCobrancaRecebimentoDao.persist(tipoDeCobranca);
		c.setTipoDeCobranca(tipoDeCobranca);
		//
		dao.persist(c);
		return c;
	}

}
