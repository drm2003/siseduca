package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.TipoDeEmail;
import br.com.cdan.negocio.geral.TipoDeEmailDao;
import br.com.cdan.negocio.geral.factory.TipoDeEmailFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeEmailDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeEmailDAOTeste.class);
	TipoDeEmailDao dao;

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
		dao = new TipoDeEmailDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeEmail a = criaTipoDeEmail();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeEmail consulta = dao.find(TipoDeEmail.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeEmail a = criaTipoDeEmail();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		TipoDeEmail consulta = dao.find(TipoDeEmail.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeEmail a = criaTipoDeEmail();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeEmail consulta = dao.find(TipoDeEmail.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeEmail.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeEmail a1 = criaTipoDeEmail();
		dao.persist(a1);
		TipoDeEmail a2 = criaTipoDeEmail();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeEmail a";
		TypedQuery<TipoDeEmail> query = dao.getEntityManager().createQuery(sql, TipoDeEmail.class);
		//
		List<TipoDeEmail> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeEmail a = criaTipoDeEmail();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeEmail a = criaTipoDeEmail();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeEmail a = criaTipoDeEmail();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeEmail a = criaTipoDeEmail();
		dao.persist(a);
		String sql = "SELECT a FROM TipoDeEmail a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeEmail> query = dao.getEntityManager().createQuery(sql, TipoDeEmail.class);
		query.setParameter("descricao", a.getDescricao());
		List<TipoDeEmail> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeEmail a = criaTipoDeEmail();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeEmail a = criaTipoDeEmail();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeEmail criaTipoDeEmail() {
		return TipoDeEmailFabricaTest.getInstance().criaTipoDeEmail();
	}
}