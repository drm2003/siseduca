package br.com.cdan.negocio.teste.contato;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.contato.GrausDeInteresse;
import br.com.cdan.negocio.contato.GrausDeInteresseDao;
import br.com.cdan.negocio.contato.factory.GrausDeInteresseFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class GrausDeInteresseDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(GrausDeInteresseDAOTeste.class);
	GrausDeInteresseDao dao;

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
		dao = new GrausDeInteresseDao(getEntityManager());
	}

	@Test
	public void inserir() {
		GrausDeInteresse a = criaGrausDeInteresse();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GrausDeInteresse consulta = dao.find(GrausDeInteresse.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		GrausDeInteresse a = criaGrausDeInteresse();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		GrausDeInteresse consulta = dao.find(GrausDeInteresse.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		GrausDeInteresse a = criaGrausDeInteresse();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GrausDeInteresse consulta = dao.find(GrausDeInteresse.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(GrausDeInteresse.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		GrausDeInteresse a1 = criaGrausDeInteresse();
		dao.persist(a1);
		GrausDeInteresse a2 = criaGrausDeInteresse();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM GrausDeInteresse a";
		Query query = dao.getEntityManager().createQuery(sql, GrausDeInteresse.class);
		//
		@SuppressWarnings("unchecked")
		List<GrausDeInteresse> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		GrausDeInteresse a = criaGrausDeInteresse();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM GrausDeInteresse a WHERE a.descricao = :descricao";
		TypedQuery<GrausDeInteresse> query = dao.getEntityManager().createQuery(sql, GrausDeInteresse.class);
		query.setParameter("descricao", a.getDescricao());
		GrausDeInteresse consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		GrausDeInteresse a = criaGrausDeInteresse();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		GrausDeInteresse a = criaGrausDeInteresse();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		GrausDeInteresse a = criaGrausDeInteresse();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		GrausDeInteresse a = criaGrausDeInteresse();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		GrausDeInteresse a = criaGrausDeInteresse();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private GrausDeInteresse criaGrausDeInteresse() {
		return GrausDeInteresseFabricaTest.getInstance().criaGrausDeInteresse();
	}
}