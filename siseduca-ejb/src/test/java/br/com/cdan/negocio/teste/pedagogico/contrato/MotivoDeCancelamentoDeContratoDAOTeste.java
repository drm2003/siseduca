package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.MotivoDeCancelamentoDeContrato;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeCancelamentoDeContratoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MotivoDeCancelamentoDeContratoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MotivoDeCancelamentoDeContratoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MotivoDeCancelamentoDeContratoDAOTeste.class);
	MotivoDeCancelamentoDeContratoDao dao;

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
		dao = new MotivoDeCancelamentoDeContratoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeCancelamentoDeContrato consulta = dao.find(MotivoDeCancelamentoDeContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MotivoDeCancelamentoDeContrato consulta = dao.find(MotivoDeCancelamentoDeContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeCancelamentoDeContrato consulta = dao.find(MotivoDeCancelamentoDeContrato.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MotivoDeCancelamentoDeContrato.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MotivoDeCancelamentoDeContrato a1 = criaMotivoDeCancelamentoDeContrato();
		dao.persist(a1);
		MotivoDeCancelamentoDeContrato a2 = criaMotivoDeCancelamentoDeContrato();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MotivoDeCancelamentoDeContrato a";
		TypedQuery<MotivoDeCancelamentoDeContrato> query = dao.getEntityManager().createQuery(sql,
				MotivoDeCancelamentoDeContrato.class);
		//
		List<MotivoDeCancelamentoDeContrato> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		dao.persist(a);
		//
		String sql = "SELECT a FROM MotivoDeCancelamentoDeContrato a WHERE a.nome = :nome";
		TypedQuery<MotivoDeCancelamentoDeContrato> query = dao.getEntityManager().createQuery(sql,
				MotivoDeCancelamentoDeContrato.class);
		query.setParameter("nome", a.getNome());
		//
		MotivoDeCancelamentoDeContrato m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MotivoDeCancelamentoDeContrato a = criaMotivoDeCancelamentoDeContrato();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private MotivoDeCancelamentoDeContrato criaMotivoDeCancelamentoDeContrato() {
		return MotivoDeCancelamentoDeContratoFabricaTest.getInstance().criaMotivoDeCancelamentoDeContrato();
	}
}