package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.financeiro.ContaAReceber;
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
import br.com.cdan.negocio.financeiro.ContaAPagarDao;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.financeiro.factory.ContaAPagarFabricaTest;
import br.com.cdan.negocio.financeiro.factory.ContaAReceberFabricaTest;
import br.com.cdan.negocio.geral.SituacaoDoAlunoDao;
import br.com.cdan.negocio.geral.factory.SituacaoDoAlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.DependenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.EstagioMonografiaDao;
import br.com.cdan.negocio.pedagogico.contrato.MatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.OcorrenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.DependenciaFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.factory.EstagioMonografiaFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.factory.MatriculaFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.factory.OcorrenciaFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.Turma_DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.Turma_DisciplinaFabricaTest;
import br.com.cdan.negocio.pedagogico.diario.DiarioDeAulaDao;
import br.com.cdan.negocio.pedagogico.diario.factory.DiarioDeAulaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.DadoBancarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.InteressadoDao;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;
import br.com.cdan.negocio.pedagogico.pessoa.ResponsavelDao;

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
		contasAPagar.add(ContaAPagarFabricaTest.getInstance().criaContasAPagar());
		contasAPagar.add(ContaAPagarFabricaTest.getInstance().criaContasAPagar());
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
		// Matr�culas
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		aluno.setMatriculas(matriculas);
		//
		aluno.setNumeroMatricula("numeroMatricula");
		// Ocorr�ncias
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		aluno.setOcorrencias(ocorrencias);
		//
		aluno.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		aluno.setRa("ra");
		aluno.setReceberEmail(Boolean.TRUE);
		aluno.setReceberSMS(Boolean.TRUE);
		// Respons�veis
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
		AlunoDao dao = new AlunoDao(em);
		Aluno aluno = criaAluno();
		// Contas a pagar
		Set<ContaAPagar> contasAPagar = new LinkedHashSet<>();
		ContaAPagarDao contasAPagarDao = new ContaAPagarDao(em);
		aluno.getContasAPagar().forEach(contasPagar -> {
			contasAPagarDao.persist(contasPagar);
			contasAPagar.add(contasPagar);
		});
		aluno.setContasAPagar(contasAPagar);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		ContaAReceberDao contasAReceberDao = new ContaAReceberDao(em);
		aluno.getContasAReceber().forEach(contasReceber -> {
			contasAReceberDao.persist(contasReceber);
			contasAReceber.add(contasReceber);
		});
		aluno.setContasAReceber(contasAReceber);
		// Dado bancario
		DadoBancarioDao dadoBancarioDao = new DadoBancarioDao(em);
		DadoBancario dadoBancario = aluno.getDadoBancario();
		dadoBancarioDao.persist(dadoBancario);
		aluno.setDadoBancario(dadoBancario);
		// Depend�ncia
		DependenciaDao dependenciaDao = new DependenciaDao(em);
		Dependencia dependencia = aluno.getDependencia();
		dependenciaDao.persist(dependencia);
		aluno.setDependencia(dependencia);
		// Di�rio de aula
		DiarioDeAulaDao diarioDeAulaDao = new DiarioDeAulaDao(em);
		DiarioDeAula diarioDeAula = aluno.getDiarioDeAula();
		diarioDeAulaDao.persist(diarioDeAula);
		aluno.setDiarioDeAula(diarioDeAula);
		// Estagio Monografia
		EstagioMonografiaDao estagioMonografiaDao = new EstagioMonografiaDao(em);
		Set<EstagioMonografia> estagiosMonografia = new LinkedHashSet<>();
		aluno.getEstagioMonografia().forEach(eMonografia -> {
			estagioMonografiaDao.persist(eMonografia);
			estagiosMonografia.add(eMonografia);
		});
		aluno.setEstagioMonografia(estagiosMonografia);
		// Interessado
		InteressadoDao interessadoDao = new InteressadoDao(em);
		Interessado interessado = aluno.getInteressado();
		interessadoDao.persist(interessado);
		aluno.setInteressado(interessado);
		// Matr�culas
		Set<Matricula> matriculas = new LinkedHashSet<>();
		MatriculaDao matriculaDao = new MatriculaDao(em);
		aluno.getMatriculas().forEach(matricula -> {
			matriculaDao.persist(matricula);
			matriculas.add(matricula);
		});
		aluno.setMatriculas(matriculas);
		//
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		OcorrenciaDao ocorrenciaDao = new OcorrenciaDao(em);
		aluno.getOcorrencias().forEach(ocorrencia -> {
			ocorrenciaDao.persist(ocorrencia);
			ocorrencias.add(ocorrencia);
		});
		aluno.setOcorrencias(ocorrencias);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = aluno.getPessoa();
		pessoaDao.persist(pessoa);
		aluno.setPessoa(pessoa);
		//
		Set<Responsavel> responsaveis = new LinkedHashSet<>();
		ResponsavelDao responsavelDao = new ResponsavelDao(em);
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
		SituacaoDoAlunoDao situacaoDoAlunoDao = new SituacaoDoAlunoDao(em);
		SituacaoDoAluno situacaoDoAluno = aluno.getSituacaoDoAluno();
		situacaoDoAlunoDao.persist(situacaoDoAluno);
		aluno.setSituacaoDoAluno(situacaoDoAluno);
		//
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		Turma_Disciplina turma_Disciplina = aluno.getTurma_Disciplina();
		turma_DisciplinaDao.persist(turma_Disciplina);
		aluno.setTurma_Disciplina(turma_Disciplina);
		//
		dao.persist(aluno);
		return aluno;
	}
}