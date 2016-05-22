package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.negocio.pedagogico.CalendarioLetivoDao;
import br.com.cdan.negocio.pedagogico.factory.CalendarioLetivoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CalendarioLetivoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CalendarioLetivoDAOTeste.class);
	CalendarioLetivoDao dao;

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
		dao = new CalendarioLetivoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		CalendarioLetivo a = criaCalendarioLetivo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CalendarioLetivo consulta = dao.find(CalendarioLetivo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		CalendarioLetivo a = criaCalendarioLetivo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		CalendarioLetivo consulta = dao.find(CalendarioLetivo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		CalendarioLetivo a = criaCalendarioLetivo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CalendarioLetivo consulta = dao.find(CalendarioLetivo.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(CalendarioLetivo.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		CalendarioLetivo a1 = criaCalendarioLetivo();
		dao.persist(a1);
		CalendarioLetivo a2 = criaCalendarioLetivo();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM CalendarioLetivo a";
		TypedQuery<CalendarioLetivo> query = dao.getEntityManager().createQuery(sql, CalendarioLetivo.class);
		//
		List<CalendarioLetivo> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		CalendarioLetivo a = criaCalendarioLetivo();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		CalendarioLetivo a = criaCalendarioLetivo();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		CalendarioLetivo a = criaCalendarioLetivo();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		CalendarioLetivo a = criaCalendarioLetivo();
		a.setNome(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		CalendarioLetivo a = criaCalendarioLetivo();
		dao.persist(a);
		//
		String sql = "SELECT a FROM CalendarioLetivo a WHERE a.nome = :nome";
		TypedQuery<CalendarioLetivo> query = dao.getEntityManager().createQuery(sql, CalendarioLetivo.class);
		query.setParameter("nome", a.getNome());
		//
		CalendarioLetivo m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		CalendarioLetivo a = criaCalendarioLetivo();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private CalendarioLetivo criaCalendarioLetivo() {
		return CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo(getEntityManager());
	}
}