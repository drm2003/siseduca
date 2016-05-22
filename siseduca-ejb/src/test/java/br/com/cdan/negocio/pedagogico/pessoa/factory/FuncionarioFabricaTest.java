package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.acesso.factory.UsuarioFabricaTest;
import br.com.cdan.negocio.geral.factory.CargoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;

public class FuncionarioFabricaTest {
	private static FuncionarioFabricaTest instance = null;

	public static synchronized FuncionarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new FuncionarioFabricaTest();
		}
		return instance;
	}

	public Funcionario criaFuncionario(EntityManager em) {
		Funcionario f = new Funcionario();
		f.setAtendente(Boolean.TRUE);
		f.setAtivo(Boolean.TRUE);
		f.setCargo(CargoFabricaTest.getInstance().criaCargoPersistido(em));
		//
		f.setFinanceiroFuncionario(
				FinanceiroFuncionarioFabricaTest.getInstance().criaFinanceiroFuncionarioPersistido(em));
		f.setNomeApelido("nomeApelido");
		f.setNumeroDependentes(Long.valueOf("10"));
		f.setNumeroMatricula("numeroMatricula");
		f.setObservacoes("observacoes");
		f.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		f.setProfessor(Boolean.FALSE);
		//
		f.setUsuario(UsuarioFabricaTest.getInstance().criaUsuarioPersistido(em));
		//
		return f;
	}

	public Funcionario criaFuncionarioPersistido(EntityManager em) {
		Funcionario f = criaFuncionario(em);
		FuncionarioDao dao = new FuncionarioDao(em);
		//
		dao.persist(f);
		return f;
	}

}
