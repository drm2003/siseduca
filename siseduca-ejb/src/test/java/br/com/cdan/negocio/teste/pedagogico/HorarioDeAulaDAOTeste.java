package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.negocio.pedagogico.HorarioDeAulaDao;
import br.com.cdan.negocio.pedagogico.factory.HorarioDeAulaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class HorarioDeAulaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(HorarioDeAulaDAOTeste.class);
	HorarioDeAulaDao dao;

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
		dao = new HorarioDeAulaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		HorarioDeAula a = criaHorarioDeAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		HorarioDeAula consulta = dao.find(HorarioDeAula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		HorarioDeAula a = criaHorarioDeAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		HorarioDeAula consulta = dao.find(HorarioDeAula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		HorarioDeAula a = criaHorarioDeAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		HorarioDeAula consulta = dao.find(HorarioDeAula.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(HorarioDeAula.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		HorarioDeAula a1 = criaHorarioDeAula();
		dao.persist(a1);
		HorarioDeAula a2 = criaHorarioDeAula();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM HorarioDeAula a";
		TypedQuery<HorarioDeAula> query = dao.getEntityManager().createQuery(sql, HorarioDeAula.class);
		//
		List<HorarioDeAula> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		HorarioDeAula a = criaHorarioDeAula();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		HorarioDeAula a = criaHorarioDeAula();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		HorarioDeAula a = criaHorarioDeAula();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		HorarioDeAula a = criaHorarioDeAula();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		HorarioDeAula a = criaHorarioDeAula();
		dao.persist(a);
		//
		String sql = "SELECT a FROM HorarioDeAula a WHERE a.descricao = :descricao";
		TypedQuery<HorarioDeAula> query = dao.getEntityManager().createQuery(sql, HorarioDeAula.class);
		query.setParameter("descricao", a.getDescricao());
		//
		HorarioDeAula m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		HorarioDeAula a = criaHorarioDeAula();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private HorarioDeAula criaHorarioDeAula() {
		return HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAula();
	}
}