package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.negocio.financeiro.CaixaDao;
import br.com.cdan.negocio.financeiro.factory.CaixaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CaixaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CaixaDAOTeste.class);
	CaixaDao dao;

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
		dao = new CaixaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Caixa a = criaCaixa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Caixa consulta = dao.find(Caixa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Caixa a = criaCaixa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDocumento("");
		dao.merge(a);
		Caixa consulta = dao.find(Caixa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Caixa a = criaCaixa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Caixa consulta = dao.find(Caixa.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Caixa.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Caixa a1 = criaCaixa();
		dao.persist(a1);
		Caixa a2 = criaCaixa();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Caixa a";
		Query query = dao.getEntityManager().createQuery(sql, Caixa.class);
		//
		@SuppressWarnings("unchecked")
		List<Caixa> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_documento() {
		Caixa a = criaCaixa();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Caixa a WHERE a.documento = :documento";
		TypedQuery<Caixa> query = dao.getEntityManager().createQuery(sql, Caixa.class);
		query.setParameter("documento", a.getDocumento());
		Caixa consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_documento_nulo() {
		Caixa a = criaCaixa();
		a.setDocumento(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_documento_vazio() {
		Caixa a = criaCaixa();
		a.setDocumento("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void documento_maior_que_tamanho_maximo_permitido() {
		Caixa a = criaCaixa();
		a.setDocumento(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void documento_menor_que_tamanho_minimo_permitido() {
		Caixa a = criaCaixa();
		a.setDocumento(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Caixa a = criaCaixa();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Caixa criaCaixa() {
		return CaixaFabricaTest.getInstance().criaCaixa(getEntityManager());
	}
}