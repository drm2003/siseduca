package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.negocio.pedagogico.SeriePadraoDao;
import br.com.cdan.negocio.pedagogico.factory.SeriePadraoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SeriePadraoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SeriePadraoDAOTeste.class);
	SeriePadraoDao dao;

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
		dao = new SeriePadraoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SeriePadrao a = criaSeriePadrao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SeriePadrao consulta = dao.find(SeriePadrao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		SeriePadrao a = criaSeriePadrao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		SeriePadrao consulta = dao.find(SeriePadrao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		SeriePadrao a = criaSeriePadrao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SeriePadrao consulta = dao.find(SeriePadrao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SeriePadrao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SeriePadrao a1 = criaSeriePadrao();
		dao.persist(a1);
		SeriePadrao a2 = criaSeriePadrao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SeriePadrao a";
		TypedQuery<SeriePadrao> query = dao.getEntityManager().createQuery(sql, SeriePadrao.class);
		//
		List<SeriePadrao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		SeriePadrao a = criaSeriePadrao();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		SeriePadrao a = criaSeriePadrao();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		SeriePadrao a = criaSeriePadrao();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		SeriePadrao a = criaSeriePadrao();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		SeriePadrao a = criaSeriePadrao();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SeriePadrao a WHERE a.descricao = :descricao";
		TypedQuery<SeriePadrao> query = dao.getEntityManager().createQuery(sql, SeriePadrao.class);
		query.setParameter("descricao", a.getDescricao());
		//
		SeriePadrao m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_codigo_nulo() {
		SeriePadrao a = criaSeriePadrao();
		a.setCodigo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_codigo_vazio() {
		SeriePadrao a = criaSeriePadrao();
		a.setCodigo("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void codigo_menor_que_tamanho_minimo_permitido() {
		SeriePadrao a = criaSeriePadrao();
		a.setCodigo(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void codigo_maior_que_tamanho_maximo_permitido() {
		SeriePadrao a = criaSeriePadrao();
		a.setCodigo(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_codigo() {
		SeriePadrao a = criaSeriePadrao();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SeriePadrao a WHERE a.codigo = :codigo";
		TypedQuery<SeriePadrao> query = dao.getEntityManager().createQuery(sql, SeriePadrao.class);
		query.setParameter("codigo", a.getCodigo());
		//
		SeriePadrao m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SeriePadrao a = criaSeriePadrao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private SeriePadrao criaSeriePadrao() {
		return SeriePadraoFabricaTest.getInstance().criaSeriePadrao(getEntityManager());
	}
}