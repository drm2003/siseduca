package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeTransferenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MotivoDeTransferenciaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MotivoDeTransferenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MotivoDeTransferenciaDAOTeste.class);
	MotivoDeTransferenciaDao dao;

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
		dao = new MotivoDeTransferenciaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeTransferencia consulta = dao.find(MotivoDeTransferencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MotivoDeTransferencia consulta = dao.find(MotivoDeTransferencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeTransferencia consulta = dao.find(MotivoDeTransferencia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MotivoDeTransferencia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MotivoDeTransferencia a1 = criaMotivoDeTransferencia();
		dao.persist(a1);
		MotivoDeTransferencia a2 = criaMotivoDeTransferencia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MotivoDeTransferencia a";
		TypedQuery<MotivoDeTransferencia> query = dao.getEntityManager().createQuery(sql, MotivoDeTransferencia.class);
		//
		List<MotivoDeTransferencia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		dao.persist(a);
		//
		String sql = "SELECT a FROM MotivoDeTransferencia a WHERE a.descricao = :descricao";
		TypedQuery<MotivoDeTransferencia> query = dao.getEntityManager().createQuery(sql, MotivoDeTransferencia.class);
		query.setParameter("descricao", a.getDescricao());
		//
		MotivoDeTransferencia m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MotivoDeTransferencia a = criaMotivoDeTransferencia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private MotivoDeTransferencia criaMotivoDeTransferencia() {
		return MotivoDeTransferenciaFabricaTest.getInstance().criaMotivoDeTransferencia();
	}
}