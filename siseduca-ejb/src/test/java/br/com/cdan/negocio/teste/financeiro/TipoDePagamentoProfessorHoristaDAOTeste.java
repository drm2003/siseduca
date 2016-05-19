package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.TipoDePagamentoProfessorHorista;
import br.com.cdan.negocio.financeiro.TipoDePagamentoProfessorHoristaDao;
import br.com.cdan.negocio.financeiro.factory.TipoDePagamentoProfessorHoristaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDePagamentoProfessorHoristaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDePagamentoProfessorHoristaDAOTeste.class);
	TipoDePagamentoProfessorHoristaDao dao;

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
		dao = new TipoDePagamentoProfessorHoristaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDePagamentoProfessorHorista consulta = dao.find(TipoDePagamentoProfessorHorista.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		TipoDePagamentoProfessorHorista consulta = dao.find(TipoDePagamentoProfessorHorista.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDePagamentoProfessorHorista consulta = dao.find(TipoDePagamentoProfessorHorista.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDePagamentoProfessorHorista.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDePagamentoProfessorHorista a1 = criaTipoDePagamentoProfessorHorista();
		dao.persist(a1);
		TipoDePagamentoProfessorHorista a2 = criaTipoDePagamentoProfessorHorista();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDePagamentoProfessorHorista a";
		Query query = dao.getEntityManager().createQuery(sql, TipoDePagamentoProfessorHorista.class);
		//
		@SuppressWarnings("unchecked")
		List<TipoDePagamentoProfessorHorista> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TipoDePagamentoProfessorHorista a WHERE a.descricao = :descricao";
		TypedQuery<TipoDePagamentoProfessorHorista> query = dao.getEntityManager().createQuery(sql,
				TipoDePagamentoProfessorHorista.class);
		query.setParameter("descricao", a.getDescricao());
		TipoDePagamentoProfessorHorista consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDePagamentoProfessorHorista a = criaTipoDePagamentoProfessorHorista();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDePagamentoProfessorHorista criaTipoDePagamentoProfessorHorista() {
		return TipoDePagamentoProfessorHoristaFabricaTest.getInstance().criaTipoDePagamentoProfessorHorista();
	}
}