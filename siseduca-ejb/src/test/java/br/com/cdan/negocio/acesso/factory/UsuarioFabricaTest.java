package br.com.cdan.negocio.acesso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.negocio.acesso.UsuarioDao;
import br.com.cdan.negocio.comum.FabricaTest;

public class UsuarioFabricaTest extends FabricaTest {
	private static UsuarioFabricaTest instance = null;

	public static synchronized UsuarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new UsuarioFabricaTest();
		}
		return instance;
	}

	public Usuario criaUsuario(EntityManager em) {
		Usuario u = new Usuario();
		u.setAtivo(Boolean.TRUE);
		u.setCoordenadorDoCurso(Boolean.TRUE);
		//
		u.setHorarioDeAcesso(HorarioDeAcessoFabricaTest.getInstance().criaHorarioDeAcessoPersistido(em));
		u.setLogin(criarStringDinamicaPorTamanho(100));
		u.setNomeUsuario(criarStringDinamicaPorTamanho(100));
		u.setPermissao(PermissaoFabricaTest.getInstance().criaPermissaoPersistido(em));
		u.setProfessor(Boolean.TRUE);
		u.setSenha("teste");
		return u;
	}

	public Usuario criaUsuarioPersistido(EntityManager em) {
		Usuario u = criaUsuario(em);
		UsuarioDao dao = new UsuarioDao(em);
		dao.persist(u);
		return u;
	}

}
