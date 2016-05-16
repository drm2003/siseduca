package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.negocio.biblioteca.EditoraDao;
import br.com.cdan.negocio.biblioteca.factory.EditoraFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EditoraDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EditoraDAOTeste.class);
	EditoraDao dao;

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
		dao = new EditoraDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Editora a = criaEditora();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Editora consulta = dao.find(Editora.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void alterar() {
		Editora a = criaEditora();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("alteração");
		a.setCompartilhado(false);
		dao.merge(a);
		Editora consulta = dao.find(Editora.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Editora a = criaEditora();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Editora consulta = dao.find(Editora.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Editora.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Editora a1 = criaEditora();
		dao.persist(a1);
		Editora a2 = criaEditora();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Editora a";
		Query query = dao.getEntityManager().createQuery(sql, Editora.class);
		//
		@SuppressWarnings("unchecked")
		List<Editora> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		Editora a = criaEditora();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "SELECT a FROM Editora a WHERE a.nome = :nome";
		Query query = dao.getEntityManager().createQuery(sql);
		query.setParameter("nome", a.getNome());
		Editora consulta = (Editora) query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nula() {
		Editora a = criaEditora();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazia() {
		Editora a = criaEditora();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Editora a = criaEditora();
		a.setNome(criarStringDinamicaPorTamanho(61));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Editora a = criaEditora();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Editora a = criaEditora();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Editora criaEditora() {
		return EditoraFabricaTest.getInstance().criaEditora();
	}
}