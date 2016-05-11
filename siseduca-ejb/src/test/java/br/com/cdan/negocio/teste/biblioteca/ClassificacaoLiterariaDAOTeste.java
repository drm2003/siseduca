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
		dao = new ClassificacaoLiterariaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClassificacaoLiteraria consulta = dao.find(ClassificacaoLiteraria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		a.setCompartilhado(false);
		dao.merge(a);
		ClassificacaoLiteraria consulta = dao.find(ClassificacaoLiteraria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClassificacaoLiteraria consulta = dao.find(ClassificacaoLiteraria.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ClassificacaoLiteraria.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ClassificacaoLiteraria a1 = criaClassificacaoLiteraria();
		dao.persist(a1);
		ClassificacaoLiteraria a2 = criaClassificacaoLiteraria();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ClassificacaoLiteraria a";
		Query query = dao.getEntityManager().createQuery(sql, ClassificacaoLiteraria.class);
		//
		@SuppressWarnings("unchecked")
		List<ClassificacaoLiteraria> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM ClassificacaoLiteraria a WHERE a.descricao = :descricao";
		TypedQuery<ClassificacaoLiteraria> query = dao.getEntityManager().createQuery(sql,
				ClassificacaoLiteraria.class);
		query.setParameter("descricao", a.getDescricao());
		ClassificacaoLiteraria consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nula() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazia() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		a.setDescricao(criarStringDinamicaPorTamanho(51));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		ClassificacaoLiteraria a = criaClassificacaoLiteraria();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ClassificacaoLiteraria criaClassificacaoLiteraria() {
		return ClassificacaoLiterariaFabricaTest.getInstance().criaClassificacaoLiteraria();
	}
}