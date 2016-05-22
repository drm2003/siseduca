package br.com.cdan.negocio.teste.contato;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.contato.Midia;
import br.com.cdan.negocio.contato.MidiaDao;
import br.com.cdan.negocio.contato.factory.MidiaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MidiaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MidiaDAOTeste.class);
	MidiaDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistência utilizando o JUnit é
	 * preciso ter acesso ao cont\exto de persistência fornecido pelo JPA.
	 * <c> Deste modo, antes da execução dos testes fornecemos este acesso ï¿½
	 * camada de persistência por meio de uma instância
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new MidiaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Midia a = criaMidia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Midia consulta = dao.find(Midia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Midia a = criaMidia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Midia consulta = dao.find(Midia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Midia a = criaMidia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Midia consulta = dao.find(Midia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Midia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Midia a1 = criaMidia();
		dao.persist(a1);
		Midia a2 = criaMidia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Midia a";
		Query query = dao.getEntityManager().createQuery(sql, Midia.class);
		//
		@SuppressWarnings("unchecked")
		List<Midia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Midia a = criaMidia();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Midia a WHERE a.descricao = :descricao";
		TypedQuery<Midia> query = dao.getEntityManager().createQuery(sql, Midia.class);
		query.setParameter("descricao", a.getDescricao());
		Midia consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Midia a = criaMidia();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Midia a = criaMidia();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Midia a = criaMidia();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Midia a = criaMidia();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Midia a = criaMidia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Midia criaMidia() {
		return MidiaFabricaTest.getInstance().criaMidia();
	}
}