package br.com.cdan.negocio.teste.contato;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.contato.TipoDeAgendamento;
import br.com.cdan.negocio.contato.TipoDeAgendamentoDao;
import br.com.cdan.negocio.contato.factory.TipoDeAgendamentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeAgendamentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeAgendamentoDAOTeste.class);
	TipoDeAgendamentoDao dao;

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
		dao = new TipoDeAgendamentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeAgendamento consulta = dao.find(TipoDeAgendamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		TipoDeAgendamento consulta = dao.find(TipoDeAgendamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeAgendamento consulta = dao.find(TipoDeAgendamento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeAgendamento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeAgendamento a1 = criaTipoDeAgendamento();
		dao.persist(a1);
		TipoDeAgendamento a2 = criaTipoDeAgendamento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeAgendamento a";
		Query query = dao.getEntityManager().createQuery(sql, TipoDeAgendamento.class);
		//
		@SuppressWarnings("unchecked")
		List<TipoDeAgendamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TipoDeAgendamento a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeAgendamento> query = dao.getEntityManager().createQuery(sql, TipoDeAgendamento.class);
		query.setParameter("descricao", a.getDescricao());
		TipoDeAgendamento consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeAgendamento a = criaTipoDeAgendamento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeAgendamento criaTipoDeAgendamento() {
		return TipoDeAgendamentoFabricaTest.getInstance().criaTipoDeAgendamento();
	}
}