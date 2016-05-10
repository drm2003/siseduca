package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Permissao;
import br.com.cdan.model.acesso.Permissao_Empresa;
import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.negocio.biblioteca.PermissaoDao;
import br.com.cdan.negocio.biblioteca.PermissaoEmpresaDao;
import br.com.cdan.negocio.biblioteca.UsuarioDao;

public class PermissaoFabricaTest {
	private static PermissaoFabricaTest instance = null;

	public static synchronized PermissaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PermissaoFabricaTest();
		}
		return instance;
	}

	public Permissao criaPermissao() {
		Permissao p = new Permissao();
		//
		Set<Permissao_Empresa> permissoesEmpresas = new LinkedHashSet<>();
		permissoesEmpresas.add(Permissao_EmpresaFabricaTest.getInstance().criaPermissao_Empresa());
		permissoesEmpresas.add(Permissao_EmpresaFabricaTest.getInstance().criaPermissao_Empresa());
		p.setPermissoesEmpresas(permissoesEmpresas);
		p.setUsuario(UsuarioFabricaTest.getInstance().criaUsuario());
		//
		return p;
	}

	public Permissao criaPermissaoPersistido(EntityManager em) {
		Permissao p = criaPermissao();
		PermissaoDao dao = new PermissaoDao(em);
		//
		PermissaoEmpresaDao permissaoEmpresaDao = new PermissaoEmpresaDao(em);
		Set<Permissao_Empresa> permissoesEmpresas = new LinkedHashSet<>();
		p.getPermissoesEmpresas().forEach(permissaoEmpresa -> {
			permissaoEmpresaDao.persist(permissaoEmpresa);
			permissoesEmpresas.add(permissaoEmpresa);
		});
		p.setPermissoesEmpresas(permissoesEmpresas);
		//
		UsuarioDao usuarioDao = new UsuarioDao(em);
		Usuario usuario = p.getUsuario();
		usuarioDao.persist(usuario);
		p.setUsuario(usuario);
		//
		dao.persist(p);
		return p;
	}

}
