package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Autor;
import br.com.cdan.negocio.biblioteca.AutorDao;
import br.com.cdan.negocio.biblioteca.factory.AutorFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class AutorDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(AutorDAOTeste.class);
	AutorDao dao;

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
		dao = new AutorDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Autor a = criaAutor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Autor consulta = dao.find(Autor.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Autor a = criaAutor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		a.setCompartilhado(false);
		dao.merge(a);
		Autor consulta = dao.find(Autor.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Autor a = criaAutor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Autor consulta = dao.find(Autor.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Autor.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Autor a1 = criaAutor();
		dao.persist(a1);
		Autor a2 = criaAutor();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Autor a";
		Query query = dao.getEntityManager().createQuery(sql, Autor.class);
		//
		@SuppressWarnings("unchecked")
		List<Autor> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		Autor a = criaAutor();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Autor a WHERE a.nome = :nome";
		TypedQuery<Autor> query = dao.getEntityManager().createQuery(sql, Autor.class);
		query.setParameter("nome", a.getNome());
		Autor consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Autor a = criaAutor();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Autor a = criaAutor();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Autor a = criaAutor();
		a.setNome(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Autor a = criaAutor();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Autor a = criaAutor();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Autor a = criaAutor();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Autor criaAutor() {
		return AutorFabricaTest.getInstance().criaAutor();
	}
}