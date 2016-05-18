package br.com.cdan.negocio.teste.acesso;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.acesso.GrupoDeUsuarios;
import br.com.cdan.negocio.acesso.GrupoDeUsuariosDao;
import br.com.cdan.negocio.acesso.factory.GrupoDeUsuariosFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class GrupoDeUsuariosDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(GrupoDeUsuariosDAOTeste.class);
	GrupoDeUsuariosDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new GrupoDeUsuariosDao(getEntityManager());
	}

	@Test
	public void inserir() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GrupoDeUsuarios consulta = dao.find(GrupoDeUsuarios.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		dao.merge(a);
		GrupoDeUsuarios consulta = dao.find(GrupoDeUsuarios.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GrupoDeUsuarios consulta = dao.find(GrupoDeUsuarios.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(GrupoDeUsuarios.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		GrupoDeUsuarios a1 = criaGrupoDeUsuarios();
		dao.persist(a1);
		GrupoDeUsuarios a2 = criaGrupoDeUsuarios();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM GrupoDeUsuarios a";
		Query query = dao.getEntityManager().createQuery(sql, GrupoDeUsuarios.class);
		//
		@SuppressWarnings("unchecked")
		List<GrupoDeUsuarios> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM GrupoDeUsuarios a WHERE a.nome = :nome";
		TypedQuery<GrupoDeUsuarios> query = dao.getEntityManager().createQuery(sql, GrupoDeUsuarios.class);
		query.setParameter("nome", a.getNome());
		GrupoDeUsuarios consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		a.setNome(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_professor_nulo() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		a.setProfessor(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		GrupoDeUsuarios a = criaGrupoDeUsuarios();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private GrupoDeUsuarios criaGrupoDeUsuarios() {
		return GrupoDeUsuariosFabricaTest.getInstance().criaGrupoDeUsuarios();
	}
}