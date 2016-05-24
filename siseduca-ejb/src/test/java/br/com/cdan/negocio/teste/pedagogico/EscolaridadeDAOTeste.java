package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.Escolaridade;
import br.com.cdan.negocio.pedagogico.EscolaridadeDao;
import br.com.cdan.negocio.pedagogico.factory.EscolaridadeFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EscolaridadeDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EscolaridadeDAOTeste.class);
	EscolaridadeDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistência utilizando o JUnit é
	 * preciso ter acesso ao cont\exto de persistência fornecido pelo JPA.
	 * <c> Deste modo, antes da execução dos testes fornecemos este acesso é
	 * camada de persistência por meio de uma instância
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new EscolaridadeDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Escolaridade a = criaEscolaridade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Escolaridade consulta = dao.find(Escolaridade.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Escolaridade a = criaEscolaridade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Escolaridade consulta = dao.find(Escolaridade.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Escolaridade a = criaEscolaridade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Escolaridade consulta = dao.find(Escolaridade.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Escolaridade.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Escolaridade a1 = criaEscolaridade();
		dao.persist(a1);
		Escolaridade a2 = criaEscolaridade();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Escolaridade a";
		TypedQuery<Escolaridade> query = dao.getEntityManager().createQuery(sql, Escolaridade.class);
		//
		List<Escolaridade> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Escolaridade a = criaEscolaridade();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Escolaridade a = criaEscolaridade();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Escolaridade a = criaEscolaridade();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Escolaridade a = criaEscolaridade();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Escolaridade a = criaEscolaridade();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Escolaridade a WHERE a.descricao = :descricao";
		TypedQuery<Escolaridade> query = dao.getEntityManager().createQuery(sql, Escolaridade.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Escolaridade m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Escolaridade a = criaEscolaridade();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Escolaridade criaEscolaridade() {
		return EscolaridadeFabricaTest.getInstance().criaEscolaridade();
	}
}