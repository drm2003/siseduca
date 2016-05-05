package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.AlunoInteressado;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.biblioteca.AlunoInteressadoDao;
import br.com.cdan.negocio.biblioteca.FollowDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.InteressadoDao;
import br.com.cdan.negocio.biblioteca.ResponsavelDao;

public class FollowFabricaTest {
	private static FollowFabricaTest instance = null;

	public static synchronized FollowFabricaTest getInstance() {
		if (instance == null) {
			instance = new FollowFabricaTest();
		}
		return instance;
	}

	public Follow criaFollow() {
		Follow f = new Follow();
		f.setAtivo(Boolean.TRUE);
		f.setAlunoInteressado(AlunoInteressadoFabricaTest.getInstance().criaAlunoInteressado());
		f.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionario());
		f.setInteressado(InteressadoFabricaTest.getInstance().criaInteressado());
		f.setResponsavel(ResponsavelFabricaTest.getInstance().criaResponsavel());
		return f;
	}

	public Follow criaFollowPersistido(EntityManager em) {
		Follow f = criaFollow();
		FollowDao dao = new FollowDao(em);
		//
		AlunoInteressadoDao alunoInteressadoDao = new AlunoInteressadoDao(em);
		AlunoInteressado alunoInteressado = f.getAlunoInteressado();
		alunoInteressadoDao.persist(alunoInteressado);
		f.setAlunoInteressado(alunoInteressado);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario funcionario = f.getFuncionario();
		funcionarioDao.persist(funcionario);
		f.setFuncionario(funcionario);
		//
		InteressadoDao interessadoDao = new InteressadoDao(em);
		Interessado interessado = f.getInteressado();
		interessadoDao.persist(interessado);
		f.setInteressado(interessado);
		//
		ResponsavelDao responsavelDao = new ResponsavelDao(em);
		Responsavel responsavel = f.getResponsavel();
		responsavelDao.persist(responsavel);
		f.setResponsavel(responsavel);
		//
		dao.persist(f);
		return f;
	}
}
