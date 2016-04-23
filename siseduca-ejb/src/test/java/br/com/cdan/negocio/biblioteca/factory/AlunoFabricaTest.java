package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.geral.SituacaoDoAluno;
import br.com.cdan.model.pedagogico.contrato.Dependencia;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.DadoBancario;
import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.ContaAPagarDao;
import br.com.cdan.negocio.biblioteca.DadoBancarioDao;
import br.com.cdan.negocio.biblioteca.DependenciaDao;
import br.com.cdan.negocio.biblioteca.DiarioDeAulaDao;
import br.com.cdan.negocio.biblioteca.EstagioMonografiaDao;
import br.com.cdan.negocio.biblioteca.InteressadoDao;
import br.com.cdan.negocio.biblioteca.MatriculaDao;
import br.com.cdan.negocio.biblioteca.OcorrenciaDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;
import br.com.cdan.negocio.biblioteca.ResponsavelDao;
import br.com.cdan.negocio.biblioteca.SituacaoDoAlunoDao;
import br.com.cdan.negocio.biblioteca.Turma_DisciplinaDao;

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
		Set<ContaAPagar> contasAPagar = new LinkedHashSet<>();
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		contasAPagar.add(ContasAPagarFabricaTest.getInstance().criaContasAPagar());
		aluno.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
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
		aluno.setResponsavelDidatico(ResponsavelFabricaTest.getInstance().criaResponsavel());
		aluno.setResponsavelEmpresa(ResponsavelFabricaTest.getInstance().criaResponsavel());
		aluno.setResponsavelFinanceiro(ResponsavelFabricaTest.getInstance().criaResponsavel());
		aluno.setSenha("senha");
		aluno.setSituacaoDoAluno(SituacaoDoAlunoFabricaTest.getInstance().criaSituacaoDoAluno());
		aluno.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		return aluno;
	}

	public Aluno criaAlunoPersistido(EntityManager em) {
		AlunoDao dao = new AlunoDao();
		dao.setEntityManager(em);
		Aluno aluno = criaAluno();
		// Contas a pagar
		Set<ContaAPagar> contasAPagar = new LinkedHashSet<>();
		ContaAPagarDao contasAPagarDao = new ContaAPagarDao();
		contasAPagarDao.setEntityManager(em);
		aluno.getContasAPagar().forEach(contasPagar -> {
			contasAPagarDao.persist(contasPagar);
			contasAPagar.add(contasPagar);
		});
		aluno.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		ContaAReceberDao contasAReceberDao = new ContaAReceberDao();
		contasAReceberDao.setEntityManager(em);
		aluno.getContasAReceber().forEach(contasReceber -> {
			contasAReceberDao.persist(contasReceber);
			contasAReceber.add(contasReceber);
		});
		aluno.setContasAReceber(contasAReceber);
		// Dado bancario
		DadoBancarioDao dadoBancarioDao = new DadoBancarioDao();
		dadoBancarioDao.setEntityManager(em);
		DadoBancario dadoBancario = aluno.getDadoBancario();
		dadoBancarioDao.persist(dadoBancario);
		aluno.setDadoBancario(dadoBancario);
		// Dependência
		DependenciaDao dependenciaDao = new DependenciaDao();
		dependenciaDao.setEntityManager(em);
		Dependencia dependencia = aluno.getDependencia();
		dependenciaDao.persist(dependencia);
		aluno.setDependencia(dependencia);
		// Diário de aula
		DiarioDeAulaDao diarioDeAulaDao = new DiarioDeAulaDao();
		diarioDeAulaDao.setEntityManager(em);
		DiarioDeAula diarioDeAula = aluno.getDiarioDeAula();
		diarioDeAulaDao.persist(diarioDeAula);
		aluno.setDiarioDeAula(diarioDeAula);
		// Estagio Monografia
		EstagioMonografiaDao estagioMonografiaDao = new EstagioMonografiaDao();
		estagioMonografiaDao.setEntityManager(em);
		Set<EstagioMonografia> estagiosMonografia = new LinkedHashSet<>();
		aluno.getEstagioMonografia().forEach(eMonografia -> {
			estagioMonografiaDao.persist(eMonografia);
			estagiosMonografia.add(eMonografia);
		});
		aluno.setEstagioMonografia(estagiosMonografia);
		// Interessado
		InteressadoDao interessadoDao = new InteressadoDao();
		interessadoDao.setEntityManager(em);
		Interessado interessado = aluno.getInteressado();
		interessadoDao.persist(interessado);
		aluno.setInteressado(interessado);
		// Matrículas
		Set<Matricula> matriculas = new LinkedHashSet<>();
		MatriculaDao matriculaDao = new MatriculaDao();
		matriculaDao.setEntityManager(em);
		aluno.getMatriculas().forEach(matricula -> {
			matriculaDao.persist(matricula);
			matriculas.add(matricula);
		});
		aluno.setMatriculas(matriculas);
		//
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		OcorrenciaDao ocorrenciaDao = new OcorrenciaDao();
		ocorrenciaDao.setEntityManager(em);
		aluno.getOcorrencias().forEach(ocorrencia -> {
			ocorrenciaDao.persist(ocorrencia);
			ocorrencias.add(ocorrencia);
		});
		aluno.setOcorrencias(ocorrencias);
		//
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.setEntityManager(em);
		Pessoa pessoa = aluno.getPessoa();
		pessoaDao.persist(pessoa);
		aluno.setPessoa(pessoa);
		//
		Set<Responsavel> responsaveis = new LinkedHashSet<>();
		ResponsavelDao responsavelDao = new ResponsavelDao();
		responsavelDao.setEntityManager(em);
		aluno.getResponsaveis().forEach(responsavel -> {
			responsavelDao.persist(responsavel);
			responsaveis.add(responsavel);
		});
		aluno.setResponsaveis(responsaveis);
		//
		Responsavel responsavelDidatico = aluno.getResponsavelDidatico();
		responsavelDao.persist(responsavelDidatico);
		//
		Responsavel responsavelFinanceiro = aluno.getResponsavelFinanceiro();
		responsavelDao.persist(responsavelFinanceiro);
		//
		Responsavel responsavelEmpresa = aluno.getResponsavelEmpresa();
		responsavelDao.persist(responsavelEmpresa);
		//
		SituacaoDoAlunoDao situacaoDoAlunoDao = new SituacaoDoAlunoDao();
		situacaoDoAlunoDao.setEntityManager(em);
		SituacaoDoAluno situacaoDoAluno = aluno.getSituacaoDoAluno();
		situacaoDoAlunoDao.persist(situacaoDoAluno);
		aluno.setSituacaoDoAluno(situacaoDoAluno);
		//
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao();
		turma_DisciplinaDao.setEntityManager(em);
		Turma_Disciplina turma_Disciplina = aluno.getTurma_Disciplina();
		turma_DisciplinaDao.persist(turma_Disciplina);
		aluno.setTurma_Disciplina(turma_Disciplina);
		//
		return aluno;
	}
}
