package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.geral.Cargo;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.DadosProfissionais;
import br.com.cdan.model.pessoa.FinanceiroFuncionario;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.biblioteca.AproveitamentoDao;
import br.com.cdan.negocio.biblioteca.CargoDao;
import br.com.cdan.negocio.biblioteca.ContaAPagarDao;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.DadosProfissionaisDao;
import br.com.cdan.negocio.biblioteca.EstagioMonografiaDao;
import br.com.cdan.negocio.biblioteca.FinanceiroFuncionarioDao;
import br.com.cdan.negocio.biblioteca.FollowDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.OcorrenciaDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;
import br.com.cdan.negocio.biblioteca.TurmaDao;
import br.com.cdan.negocio.biblioteca.Turma_DisciplinaDao;
import br.com.cdan.negocio.biblioteca.UsuarioDao;

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
		Set<ContaAPagar> contasAPagar = new LinkedHashSet<>();
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		f.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
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

	public Funcionario criaFuncionarioPersistido(EntityManager em) {
		Funcionario f = criaFuncionario();
		FuncionarioDao dao = new FuncionarioDao(em);
		//
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		Aproveitamento aproveitamento = f.getAproveitamento();
		aproveitamentoDao.persist(aproveitamento);
		f.setAproveitamento(aproveitamento);
		//
		CargoDao cargoDao = new CargoDao(em);
		Cargo cargo = f.getCargo();
		cargoDao.persist(cargo);
		f.setCargo(cargo);
		// Contas a pagar
		Set<ContaAPagar> contasAPagar = new LinkedHashSet<>();
		ContaAPagarDao contaAPagarDao = new ContaAPagarDao(em);
		f.getContasAPagar().forEach(contaAPagar -> {
			contaAPagarDao.persist(contasAPagar);
			contasAPagar.add(contaAPagar);
		});
		f.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao(em);
		f.getContasAReceber().forEach(contaAReceber -> {
			contaAReceberDao.persist(contasAReceber);
			contasAPagar.addAll(contasAPagar);
		});
		f.setContasAReceber(contasAReceber);
		//
		DadosProfissionaisDao dadosProfissionaisDao = new DadosProfissionaisDao(em);
		DadosProfissionais dadosProfissionais = f.getDadosProfissionais();
		dadosProfissionaisDao.persist(dadosProfissionais);
		f.setDadosProfissionais(dadosProfissionais);
		// Estagio monografia
		Set<EstagioMonografia> estagiosMonografias = new LinkedHashSet<>();
		EstagioMonografiaDao estagioMonografiaDao = new EstagioMonografiaDao(em);
		f.getEstagioMonografia().forEach(estagioMonografia -> {
			estagioMonografiaDao.persist(estagioMonografia);
			estagiosMonografias.add(estagioMonografia);
		});
		f.setEstagioMonografia(estagiosMonografias);
		//
		FinanceiroFuncionarioDao financeiroFuncionarioDao = new FinanceiroFuncionarioDao(em);
		FinanceiroFuncionario financeiroFuncionario = f.getFinanceiroFuncionario();
		financeiroFuncionarioDao.persist(financeiroFuncionario);
		f.setFinanceiroFuncionario(financeiroFuncionario);
		// Follows
		Set<Follow> follows = new LinkedHashSet<>();
		FollowDao followDao = new FollowDao(em);
		f.getFollows().forEach(follow -> {
			followDao.persist(follow);
			follows.add(follow);
		});
		f.setFollows(follows);
		// Ocorrências
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		OcorrenciaDao ocorrenciaDao = new OcorrenciaDao(em);
		f.getOcorrencias().forEach(ocorrencia -> {
			ocorrenciaDao.persist(ocorrencia);
			ocorrencias.add(ocorrencia);
		});
		f.setOcorrencias(ocorrencias);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = f.getPessoa();
		pessoaDao.persist(pessoa);
		f.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		//
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		Turma_Disciplina turma_Disciplina = f.getTurma_Disciplina();
		turma_DisciplinaDao.persist(turma_Disciplina);
		f.setTurma_Disciplina(turma_Disciplina);
		// Turmas
		TurmaDao turmaDao = new TurmaDao(em);
		Set<Turma> turmas = new LinkedHashSet<>();
		f.getTurmas().forEach(turma -> {
			turmaDao.persist(turma);
			turmas.add(turma);
		});
		f.setTurmas(turmas);
		//
		UsuarioDao usuarioDao = new UsuarioDao(em);
		Usuario usuario = f.getUsuario();
		usuarioDao.persist(usuario);
		f.setUsuario(usuario);
		//
		dao.persist(f);
		return f;
	}

}
