package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.TipoDeOcorrencia;
import br.com.cdan.negocio.pedagogico.TipoDeOcorrenciaDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeOcorrenciaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeOcorrenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeOcorrenciaDAOTeste.class);
	TipoDeOcorrenciaDao dao;

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
		dao = new TipoDeOcorrenciaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeOcorrencia consulta = dao.find(TipoDeOcorrencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		TipoDeOcorrencia consulta = dao.find(TipoDeOcorrencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeOcorrencia consulta = dao.find(TipoDeOcorrencia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeOcorrencia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeOcorrencia a1 = criaTipoDeOcorrencia();
		dao.persist(a1);
		TipoDeOcorrencia a2 = criaTipoDeOcorrencia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeOcorrencia a";
		TypedQuery<TipoDeOcorrencia> query = dao.getEntityManager().createQuery(sql, TipoDeOcorrencia.class);
		//
		List<TipoDeOcorrencia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		dao.persist(a);
		//
		String sql = "SELECT a FROM TipoDeOcorrencia a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeOcorrencia> query = dao.getEntityManager().createQuery(sql, TipoDeOcorrencia.class);
		query.setParameter("descricao", a.getDescricao());
		//
		TipoDeOcorrencia m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeOcorrencia a = criaTipoDeOcorrencia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDeOcorrencia criaTipoDeOcorrencia() {
		return TipoDeOcorrenciaFabricaTest.getInstance().criaTipoDeOcorrencia();
	}
}