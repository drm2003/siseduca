package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Conta;
import br.com.cdan.model.financeiro.ContasAPagar;
import br.com.cdan.model.financeiro.PlanoDeContas;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.ContaDao;
import br.com.cdan.negocio.biblioteca.ContasAPagarDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.InvestimentoDao;
import br.com.cdan.negocio.biblioteca.PlanoDeContasDao;
import br.com.cdan.negocio.biblioteca.TipoDeCobrancaRecebimentoDao;

public class ContasAPagarFabricaTest {
	private static ContasAPagarFabricaTest instance = null;

	public static synchronized ContasAPagarFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContasAPagarFabricaTest();
		}
		return instance;
	}

	public ContasAPagar criaContasAPagar() {
		ContasAPagar c = new ContasAPagar();
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
		c.setPlanoDeConta(PlanoDeContasFabricaTest.getInstance().criaPlanoDeContas());
		c.setPrimeiraParcela(InvestimentoFabricaTest.getInstance().criaInvestimento());
		c.setTipoDeCobranca(TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimento());
		c.setValorDiferenciadoPrimeiraParcela(Boolean.TRUE);
		return c;
	}

	public ContasAPagar criaContasAPagarPersistido(EntityManager em) {
		ContasAPagar c = criaContasAPagar();
		ContasAPagarDao dao = new ContasAPagarDao();
		dao.setEntityManager(em);
		// Alunos
		AlunoDao daoAluno = new AlunoDao();
		Set<Aluno> alunos = new LinkedHashSet<>();
		c.getAlunos().forEach(a -> {
			daoAluno.persist(a);
			alunos.add(a);
		});
		c.setAlunos(alunos);
		// Conta
		Conta conta = c.getConta();
		ContaDao daoConta = new ContaDao();
		daoConta.persist(conta);
		c.setConta(conta);
		// Empresas
		EmpresaDao daoEmpresa = new EmpresaDao();
		Set<Empresa> empresas = new LinkedHashSet<>();
		c.getEmpresas().forEach(e -> {
			daoEmpresa.persist(e);
			empresas.add(e);
		});
		c.setEmpresas(empresas);
		// Funcionários
		FuncionarioDao daoFuncionario = new FuncionarioDao();
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		c.getFuncionarios().forEach(f -> {
			daoFuncionario.persist(f);
			funcionarios.add(f);
		});
		c.setFuncionarios(funcionarios);
		// Investimento
		InvestimentoDao daoInvestimento = new InvestimentoDao();
		Investimento investimento = c.getInvestimento();
		daoInvestimento.persist(investimento);
		c.setInvestimento(investimento);
		// Primeira parcela
		Investimento primeiraParcela = c.getPrimeiraParcela();
		daoInvestimento.persist(primeiraParcela);
		c.setPrimeiraParcela(primeiraParcela);
		// Plano de Conta
		PlanoDeContasDao daoPlanoDeContas = new PlanoDeContasDao();
		PlanoDeContas planoDeConta = c.getPlanoDeConta();
		daoPlanoDeContas.persist(planoDeConta);
		c.setPlanoDeConta(planoDeConta);
		// tipo de Cobrança Recebimento
		TipoDeCobrancaRecebimentoDao tipoDeCobrancaRecebimentoDao = new TipoDeCobrancaRecebimentoDao();
		TipoDeCobrancaRecebimento tipoDeCobranca = c.getTipoDeCobranca();
		tipoDeCobrancaRecebimentoDao.persist(tipoDeCobranca);
		c.setTipoDeCobranca(tipoDeCobranca);
		//
		dao.persist(c);
		return c;
	}

}
