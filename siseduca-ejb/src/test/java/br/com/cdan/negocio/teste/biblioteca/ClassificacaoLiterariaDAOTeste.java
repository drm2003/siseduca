package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.ClassificacaoLiteraria;
import br.com.cdan.negocio.biblioteca.ClassificacaoLiterariaDao;
import br.com.cdan.negocio.biblioteca.factory.ClassificacaoLiterariaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ClassificacaoLiterariaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ClassificacaoLiterariaDAOTeste.class);
	ClassificacaoLiterariaDao dao;

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
		dao = new ClassificacaoLiterariaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClassificacaoLiteraria consulta = dao.find(ClassificacaoLiteraria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("altera��o");
		a.setCompartilhado(false);
		dao.merge(a);
		ClassificacaoLiteraria consulta = dao.find(ClassificacaoLiteraria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClassificacaoLiteraria consulta = dao.find(ClassificacaoLiteraria.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ClassificacaoLiteraria.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ClassificacaoLiteraria a1 = criaClassificacaoLiterariaPersistido();
		dao.persist(a1);
		ClassificacaoLiteraria a2 = criaClassificacaoLiterariaPersistido();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ClassificacaoLiteraria a";
		TypedQuery<ClassificacaoLiteraria> query = dao.getEntityManager().createQuery(sql,
				ClassificacaoLiteraria.class);
		//
		List<ClassificacaoLiteraria> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "SELECT a FROM ClassificacaoLiteraria a WHERE a.descricao = :descricao";
		Query query = dao.getEntityManager().createQuery(sql);
		query.setParameter("descricao", a.getDescricao());
		ClassificacaoLiteraria consulta = (ClassificacaoLiteraria) query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nula() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazia() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		a.setDescricao(criarStringDinamicaPorTamanho(51));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		ClassificacaoLiteraria a = criaClassificacaoLiterariaPersistido();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ClassificacaoLiteraria criaClassificacaoLiterariaPersistido() {
		return ClassificacaoLiterariaFabricaTest.getInstance().criaClassificacaoLiterariaPersistido(getEntityManager());
	}
}