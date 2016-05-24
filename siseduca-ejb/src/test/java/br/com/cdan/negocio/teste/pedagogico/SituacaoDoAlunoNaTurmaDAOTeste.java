package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.SituacaoDoAlunoNaTurma;
import br.com.cdan.negocio.pedagogico.SituacaoDoAlunoNaTurmaDao;
import br.com.cdan.negocio.pedagogico.factory.SituacaoDoAlunoNaTurmaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SituacaoDoAlunoNaTurmaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SituacaoDoAlunoNaTurmaDAOTeste.class);
	SituacaoDoAlunoNaTurmaDao dao;

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
		dao = new SituacaoDoAlunoNaTurmaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDoAlunoNaTurma consulta = dao.find(SituacaoDoAlunoNaTurma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		SituacaoDoAlunoNaTurma consulta = dao.find(SituacaoDoAlunoNaTurma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDoAlunoNaTurma consulta = dao.find(SituacaoDoAlunoNaTurma.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SituacaoDoAlunoNaTurma.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SituacaoDoAlunoNaTurma a1 = criaSituacaoDoAlunoNaTurma();
		dao.persist(a1);
		SituacaoDoAlunoNaTurma a2 = criaSituacaoDoAlunoNaTurma();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SituacaoDoAlunoNaTurma a";
		TypedQuery<SituacaoDoAlunoNaTurma> query = dao.getEntityManager().createQuery(sql,
				SituacaoDoAlunoNaTurma.class);
		//
		List<SituacaoDoAlunoNaTurma> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setDescricao(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SituacaoDoAlunoNaTurma a WHERE a.descricao = :descricao";
		TypedQuery<SituacaoDoAlunoNaTurma> query = dao.getEntityManager().createQuery(sql,
				SituacaoDoAlunoNaTurma.class);
		query.setParameter("descricao", a.getDescricao());
		//
		SituacaoDoAlunoNaTurma m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_abreviatura_nulo() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setAbreviatura(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_abreviatura_vazio() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setAbreviatura("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void abreviatura_menor_que_tamanho_minimo_permitido() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setAbreviatura(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void abreviatura_maior_que_tamanho_maximo_permitido() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		a.setAbreviatura(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_abreviatura() {
		SituacaoDoAlunoNaTurma a = criaSituacaoDoAlunoNaTurma();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SituacaoDoAlunoNaTurma a WHERE a.abreviatura = :abreviatura";
		TypedQuery<SituacaoDoAlunoNaTurma> query = dao.getEntityManager().createQuery(sql,
				SituacaoDoAlunoNaTurma.class);
		query.setParameter("abreviatura", a.getAbreviatura());
		//
		SituacaoDoAlunoNaTurma m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	private SituacaoDoAlunoNaTurma criaSituacaoDoAlunoNaTurma() {
		return SituacaoDoAlunoNaTurmaFabricaTest.getInstance().criaSituacaoDoAlunoNaTurma();
	}
}