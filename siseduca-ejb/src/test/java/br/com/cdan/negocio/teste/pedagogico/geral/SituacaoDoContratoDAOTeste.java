package br.com.cdan.negocio.teste.pedagogico.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;
import br.com.cdan.negocio.pedagogico.geral.SituacaoDoContratoDao;
import br.com.cdan.negocio.pedagogico.geral.factory.SituacaoDoContratoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SituacaoDoContratoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SituacaoDoContratoDAOTeste.class);
	SituacaoDoContratoDao dao;

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
		dao = new SituacaoDoContratoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDoContrato consulta = dao.find(SituacaoDoContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		SituacaoDoContrato consulta = dao.find(SituacaoDoContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDoContrato consulta = dao.find(SituacaoDoContrato.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SituacaoDoContrato.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SituacaoDoContrato a1 = criaSituacaoDoContrato();
		dao.persist(a1);
		SituacaoDoContrato a2 = criaSituacaoDoContrato();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SituacaoDoContrato a";
		TypedQuery<SituacaoDoContrato> query = dao.getEntityManager().createQuery(sql, SituacaoDoContrato.class);
		//
		List<SituacaoDoContrato> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SituacaoDoContrato a WHERE a.descricao = :descricao";
		TypedQuery<SituacaoDoContrato> query = dao.getEntityManager().createQuery(sql, SituacaoDoContrato.class);
		query.setParameter("descricao", a.getDescricao());
		//
		SituacaoDoContrato m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SituacaoDoContrato a = criaSituacaoDoContrato();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private SituacaoDoContrato criaSituacaoDoContrato() {
		return SituacaoDoContratoFabricaTest.getInstance().criaSituacaoDoContrato();
	}
}