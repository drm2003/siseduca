package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.TipoDeContrato;
import br.com.cdan.negocio.pedagogico.contrato.TipoDeContratoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.TipoDeContratoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeContratoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeContratoDAOTeste.class);
	TipoDeContratoDao dao;

	/**
	 * <c> Ao criar um teste da camada de persist�ncia utilizando o JUnit �
	 * preciso ter acesso ao cont\exto de persist�ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execu��o dos testes fornecemos este acesso �
	 * camada de persist�ncia por meio de uma inst�ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new TipoDeContratoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeContrato a = criaTipoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeContrato consulta = dao.find(TipoDeContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		TipoDeContrato a = criaTipoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		TipoDeContrato consulta = dao.find(TipoDeContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		TipoDeContrato a = criaTipoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeContrato consulta = dao.find(TipoDeContrato.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeContrato.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeContrato a1 = criaTipoDeContrato();
		dao.persist(a1);
		TipoDeContrato a2 = criaTipoDeContrato();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeContrato a";
		TypedQuery<TipoDeContrato> query = dao.getEntityManager().createQuery(sql, TipoDeContrato.class);
		//
		List<TipoDeContrato> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeContrato a = criaTipoDeContrato();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeContrato a = criaTipoDeContrato();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeContrato a = criaTipoDeContrato();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeContrato a = criaTipoDeContrato();
		dao.persist(a);
		//
		String sql = "SELECT a FROM TipoDeContrato a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeContrato> query = dao.getEntityManager().createQuery(sql, TipoDeContrato.class);
		query.setParameter("descricao", a.getDescricao());
		//
		TipoDeContrato m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeContrato a = criaTipoDeContrato();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDeContrato criaTipoDeContrato() {
		return TipoDeContratoFabricaTest.getInstance().criaTipoDeContrato(getEntityManager());
	}
}