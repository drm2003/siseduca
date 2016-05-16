package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Nivel;
import br.com.cdan.negocio.biblioteca.NivelDao;
import br.com.cdan.negocio.biblioteca.factory.NivelFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class NivelDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(NivelDAOTeste.class);
	NivelDao dao;

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
		dao = new NivelDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Nivel a = criaNivel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Nivel consulta = dao.find(Nivel.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Nivel a = criaNivel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		a.setDescricao("");
		a.setCompartilhado(Boolean.FALSE);
		dao.merge(a);
		Nivel consulta = dao.find(Nivel.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Nivel a = criaNivel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Nivel consulta = dao.find(Nivel.class, a.getId());// CONSULTA
		consulta.setAtivo(Boolean.FALSE);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Nivel.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Nivel a1 = criaNivel();
		dao.persist(a1);
		Nivel a2 = criaNivel();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Nivel a";
		Query query = dao.getEntityManager().createQuery(sql, Nivel.class);
		//
		@SuppressWarnings("unchecked")
		List<Nivel> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Nivel a = criaNivel();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Nivel a WHERE a.descricao = :descricao";
		TypedQuery<Nivel> query = dao.getEntityManager().createQuery(sql, Nivel.class);
		query.setParameter("descricao", a.getDescricao());
		Nivel consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Nivel a = criaNivel();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Nivel a = criaNivel();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Nivel a = criaNivel();
		a.setDescricao(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Nivel a = criaNivel();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Nivel a = criaNivel();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Nivel a = criaNivel();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Nivel criaNivel() {
		return NivelFabricaTest.getInstance().criaNivel();
	}
}