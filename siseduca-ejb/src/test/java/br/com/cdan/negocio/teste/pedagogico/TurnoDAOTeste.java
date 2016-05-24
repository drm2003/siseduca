package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.Turno;
import br.com.cdan.negocio.pedagogico.TurnoDao;
import br.com.cdan.negocio.pedagogico.factory.TurnoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TurnoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TurnoDAOTeste.class);
	TurnoDao dao;

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
		dao = new TurnoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Turno a = criaTurno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Turno consulta = dao.find(Turno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Turno a = criaTurno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Turno consulta = dao.find(Turno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Turno a = criaTurno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Turno consulta = dao.find(Turno.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Turno.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Turno a1 = criaTurno();
		dao.persist(a1);
		Turno a2 = criaTurno();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Turno a";
		TypedQuery<Turno> query = dao.getEntityManager().createQuery(sql, Turno.class);
		//
		List<Turno> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Turno a = criaTurno();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Turno a = criaTurno();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Turno a = criaTurno();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Turno a = criaTurno();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Turno a = criaTurno();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Turno a WHERE a.descricao = :descricao";
		TypedQuery<Turno> query = dao.getEntityManager().createQuery(sql, Turno.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Turno m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Turno a = criaTurno();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Turno criaTurno() {
		return TurnoFabricaTest.getInstance().criaTurno();
	}
}