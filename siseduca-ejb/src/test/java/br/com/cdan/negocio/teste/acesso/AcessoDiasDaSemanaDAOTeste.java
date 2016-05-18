package br.com.cdan.negocio.teste.acesso;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.negocio.acesso.AcessoDiasDaSemanaDao;
import br.com.cdan.negocio.acesso.factory.AcessoDiasDaSemanaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class AcessoDiasDaSemanaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(AcessoDiasDaSemanaDAOTeste.class);
	AcessoDiasDaSemanaDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new AcessoDiasDaSemanaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		AcessoDiasDaSemana consulta = dao.find(AcessoDiasDaSemana.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDiaDaSemana(EnumDiaDaSemana.SABADO);
		a.setHoraEntrada(Calendar.getInstance().getTime());
		a.setHoraSaida(Calendar.getInstance().getTime());
		dao.merge(a);
		AcessoDiasDaSemana consulta = dao.find(AcessoDiasDaSemana.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		AcessoDiasDaSemana consulta = dao.find(AcessoDiasDaSemana.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(AcessoDiasDaSemana.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		AcessoDiasDaSemana a1 = criaAcessoDiasDaSemana();
		dao.persist(a1);
		AcessoDiasDaSemana a2 = criaAcessoDiasDaSemana();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM AcessoDiasDaSemana a";
		Query query = dao.getEntityManager().createQuery(sql, AcessoDiasDaSemana.class);
		//
		@SuppressWarnings("unchecked")
		List<AcessoDiasDaSemana> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_diaDaSemana() {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM AcessoDiasDaSemana a WHERE a.diaDaSemana = :diaDaSemana";
		TypedQuery<AcessoDiasDaSemana> query = dao.getEntityManager().createQuery(sql, AcessoDiasDaSemana.class);
		query.setParameter("diaDaSemana", a.getDiaDaSemana());
		AcessoDiasDaSemana consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private AcessoDiasDaSemana criaAcessoDiasDaSemana() {
		return AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiasDaSemana(getEntityManager());
	}
}