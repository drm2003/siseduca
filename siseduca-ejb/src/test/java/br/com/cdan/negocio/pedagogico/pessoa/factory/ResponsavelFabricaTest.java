package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.geral.factory.TipoDeResponsavelFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.ResponsavelDao;

public class ResponsavelFabricaTest {
	private static ResponsavelFabricaTest instance = null;

	public static synchronized ResponsavelFabricaTest getInstance() {
		if (instance == null) {
			instance = new ResponsavelFabricaTest();
		}
		return instance;
	}

	public Responsavel criaResponsavel(EntityManager em) {
		Responsavel r = new Responsavel();
		r.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		r.setAlunoInteressado(AlunoInteressadoFabricaTest.getInstance().criaAlunoInteressadoPersistido(em));
		r.setAtivo(Boolean.TRUE);
		r.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresaPersistido(em));
		//
		r.setLoginPortal("loginPortal");
		r.setObservacoes("observacoes");
		r.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		r.setSenhaPortal("senhaPortal");
		r.setResponsavelDidatico(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		r.setResponsavelEmpresa(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		r.setResponsavelFinanceiro(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		//
		r.setTipoDeResponsavel(TipoDeResponsavelFabricaTest.getInstance().criaTipoDeResponsavelPersistido(em));
		return r;
	}

	public Responsavel criaResponsavelPersistido(EntityManager em) {
		Responsavel r = criaResponsavel(em);
		ResponsavelDao dao = new ResponsavelDao(em);
		//
		dao.persist(r);
		return r;
	}
}