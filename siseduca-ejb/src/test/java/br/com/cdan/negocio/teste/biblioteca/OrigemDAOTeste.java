package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Origem;
import br.com.cdan.negocio.biblioteca.OrigemDao;
import br.com.cdan.negocio.geral.factory.OrigemFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class OrigemDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(OrigemDAOTeste.class);
	OrigemDao dao;

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
		dao = new OrigemDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Origem a = criaOrigem();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Origem consulta = dao.find(Origem.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Origem a = criaOrigem();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Origem consulta = dao.find(Origem.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Origem a = criaOrigem();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Origem consulta = dao.find(Origem.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Origem.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Origem a1 = criaOrigem();
		dao.persist(a1);
		Origem a2 = criaOrigem();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Origem a";
		Query query = dao.getEntityManager().createQuery(sql, Origem.class);
		//
		@SuppressWarnings("unchecked")
		List<Origem> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Origem a = criaOrigem();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Origem a WHERE a.descricao = :descricao";
		TypedQuery<Origem> query = dao.getEntityManager().createQuery(sql, Origem.class);
		query.setParameter("descricao", a.getDescricao());
		Origem consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Origem a = criaOrigem();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Origem a = criaOrigem();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Origem a = criaOrigem();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Origem a = criaOrigem();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Origem a = criaOrigem();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Origem a = criaOrigem();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Origem criaOrigem() {
		return OrigemFabricaTest.getInstance().criaOrigem();
	}
}