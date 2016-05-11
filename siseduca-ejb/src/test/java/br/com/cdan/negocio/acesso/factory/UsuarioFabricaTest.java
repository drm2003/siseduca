package br.com.cdan.negocio.acesso.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.negocio.geral.factory.EmailFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.factory.CursoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

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
		cursos.add(CursoFabricaTest.getInstance().criaCurso());
		cursos.add(CursoFabricaTest.getInstance().criaCurso());
		u.setCursos(cursos);
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
		return u;
	}

}
