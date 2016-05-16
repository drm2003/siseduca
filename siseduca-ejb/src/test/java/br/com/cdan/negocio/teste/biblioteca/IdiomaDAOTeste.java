package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Idioma;
import br.com.cdan.negocio.biblioteca.IdiomaDao;
import br.com.cdan.negocio.biblioteca.factory.IdiomaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class IdiomaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(IdiomaDAOTeste.class);
	IdiomaDao dao;

	/**
	 * <c> Ao criar um teste da camada de persist�ncia utilizando o JUnit �
	 * preciso ter acesso ao cont\exto de persist�ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execu��o dos testes fornecemos este acesso
	 * � camada de persist�ncia por meio de uma inst�ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new IdiomaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Idioma a = criaIdioma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Idioma consulta = dao.find(Idioma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		Idioma a = criaIdioma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		a.setCompartilhado(false);
		dao.merge(a);
		Idioma consulta = dao.find(Idioma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		Idioma a = criaIdioma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Idioma consulta = dao.find(Idioma.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Idioma.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Idioma a1 = criaIdioma();
		dao.persist(a1);
		Idioma a2 = criaIdioma();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Idioma a";
		Query query = dao.getEntityManager().createQuery(sql, Idioma.class);
		//
		@SuppressWarnings("unchecked")
		List<Idioma> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Idioma a = criaIdioma();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Idioma a WHERE a.descricao = :descricao";
		TypedQuery<Idioma> query = dao.getEntityManager().createQuery(sql, Idioma.class);
		query.setParameter("descricao", a.getDescricao());
		Idioma consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Idioma a = criaIdioma();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Idioma a = criaIdioma();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Idioma a = criaIdioma();
		a.setDescricao(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Idioma a = criaIdioma();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Idioma a = criaIdioma();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Idioma a = criaIdioma();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Idioma criaIdioma() {
		return IdiomaFabricaTest.getInstance().criaIdioma();
	}
}