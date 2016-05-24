package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.SituacaoDaTurma;
import br.com.cdan.negocio.pedagogico.SituacaoDaTurmaDao;
import br.com.cdan.negocio.pedagogico.factory.SituacaoDaTurmaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SituacaoDaTurmaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SituacaoDaTurmaDAOTeste.class);
	SituacaoDaTurmaDao dao;

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
		dao = new SituacaoDaTurmaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDaTurma consulta = dao.find(SituacaoDaTurma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		SituacaoDaTurma consulta = dao.find(SituacaoDaTurma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDaTurma consulta = dao.find(SituacaoDaTurma.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SituacaoDaTurma.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SituacaoDaTurma a1 = criaSituacaoDaTurma();
		dao.persist(a1);
		SituacaoDaTurma a2 = criaSituacaoDaTurma();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SituacaoDaTurma a";
		TypedQuery<SituacaoDaTurma> query = dao.getEntityManager().createQuery(sql, SituacaoDaTurma.class);
		//
		List<SituacaoDaTurma> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		a.setDescricao(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SituacaoDaTurma a WHERE a.descricao = :descricao";
		TypedQuery<SituacaoDaTurma> query = dao.getEntityManager().createQuery(sql, SituacaoDaTurma.class);
		query.setParameter("descricao", a.getDescricao());
		//
		SituacaoDaTurma m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SituacaoDaTurma a = criaSituacaoDaTurma();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private SituacaoDaTurma criaSituacaoDaTurma() {
		return SituacaoDaTurmaFabricaTest.getInstance().criaSituacaoDaTurma();
	}
}