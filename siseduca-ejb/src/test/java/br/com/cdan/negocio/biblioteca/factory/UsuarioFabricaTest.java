package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.pedagogico.curso.Curso;

public class UsuarioFabricaTest {
	private static UsuarioFabricaTest instance = null;

	public static synchronized UsuarioFabricaTest getInstance() {
		if (instance == null) {
			instance = new UsuarioFabricaTest();
		}
		return instance;
	}

	public Usuario criaUsuario() {
		Usuario u = new Usuario();
		u.setAtivo(Boolean.TRUE);
		u.setCoordenadorDoCurso(Boolean.TRUE);
		// Cursos
		Set<Curso> cursos = new LinkedHashSet<>();
		cursos.add(CursoFabricaTest.getIntance().criaCurso());
		cursos.add(CursoFabricaTest.getIntance().criaCurso());
		u.setCurso(cursos);
		// Emails
		Set<Email> emails = new LinkedHashSet<>();
		emails.add(EmailFabricaTest.getInstance().criaEmail());
		emails.add(EmailFabricaTest.getInstance().criaEmail());
		u.setEmails(emails);
		//
		u.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionario());
		u.setHorarioDeAcesso(HorarioDeAcessoFabricaTest.getInstance().criaHorarioDeAcesso());
		u.setLogin("teste" + Math.random() * 100000);
		u.setNomeUsuario("teste" + Math.random() * 10000);
		u.setPermissao(PermissaoFabricaTest.getInstance().criaPermissao());
		u.setProfessor(Boolean.TRUE);
		u.setSenha("teste");
		return usuario;
	}

}
