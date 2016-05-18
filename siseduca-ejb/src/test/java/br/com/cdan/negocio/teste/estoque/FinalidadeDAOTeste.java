package br.com.cdan.negocio.teste.estoque;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.estoque.Finalidade;
import br.com.cdan.negocio.estoque.FinalidadeDao;
import br.com.cdan.negocio.estoque.factory.FinalidadeFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class FinalidadeDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(FinalidadeDAOTeste.class);
	FinalidadeDao dao;

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
		dao = new FinalidadeDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Finalidade a = criaFinalidade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Finalidade consulta = dao.find(Finalidade.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Finalidade a = criaFinalidade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Finalidade consulta = dao.find(Finalidade.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Finalidade a = criaFinalidade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Finalidade consulta = dao.find(Finalidade.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Finalidade.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Finalidade a1 = criaFinalidade();
		dao.persist(a1);
		Finalidade a2 = criaFinalidade();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Finalidade a";
		Query query = dao.getEntityManager().createQuery(sql, Finalidade.class);
		//
		@SuppressWarnings("unchecked")
		List<Finalidade> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Finalidade a = criaFinalidade();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Finalidade a WHERE a.descricao = :descricao";
		TypedQuery<Finalidade> query = dao.getEntityManager().createQuery(sql, Finalidade.class);
		query.setParameter("descricao", a.getDescricao());
		Finalidade consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Finalidade a = criaFinalidade();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Finalidade a = criaFinalidade();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Finalidade a = criaFinalidade();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Finalidade a = criaFinalidade();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Finalidade a = criaFinalidade();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Finalidade criaFinalidade() {
		return FinalidadeFabricaTest.getInstance().criaFinalidade();
	}
}