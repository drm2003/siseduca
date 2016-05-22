package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.MotivoDeDesistenciaDeTrancamento;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeDesistenciaDeTrancamentoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MotivoDeDesistenciaDeTrancamentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MotivoDeDesistenciaDeTrancamentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MotivoDeDesistenciaDeTrancamentoDAOTeste.class);
	MotivoDeDesistenciaDeTrancamentoDao dao;

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
		dao = new MotivoDeDesistenciaDeTrancamentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeDesistenciaDeTrancamento consulta = dao.find(MotivoDeDesistenciaDeTrancamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MotivoDeDesistenciaDeTrancamento consulta = dao.find(MotivoDeDesistenciaDeTrancamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeDesistenciaDeTrancamento consulta = dao.find(MotivoDeDesistenciaDeTrancamento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MotivoDeDesistenciaDeTrancamento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MotivoDeDesistenciaDeTrancamento a1 = criaMotivoDeDesistenciaDeTrancamento();
		dao.persist(a1);
		MotivoDeDesistenciaDeTrancamento a2 = criaMotivoDeDesistenciaDeTrancamento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MotivoDeDesistenciaDeTrancamento a";
		TypedQuery<MotivoDeDesistenciaDeTrancamento> query = dao.getEntityManager().createQuery(sql,
				MotivoDeDesistenciaDeTrancamento.class);
		//
		List<MotivoDeDesistenciaDeTrancamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		dao.persist(a);
		//
		String sql = "SELECT a FROM MotivoDeDesistenciaDeTrancamento a WHERE a.descricao = :descricao";
		TypedQuery<MotivoDeDesistenciaDeTrancamento> query = dao.getEntityManager().createQuery(sql,
				MotivoDeDesistenciaDeTrancamento.class);
		query.setParameter("descricao", a.getDescricao());
		//
		MotivoDeDesistenciaDeTrancamento m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MotivoDeDesistenciaDeTrancamento a = criaMotivoDeDesistenciaDeTrancamento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private MotivoDeDesistenciaDeTrancamento criaMotivoDeDesistenciaDeTrancamento() {
		return MotivoDeDesistenciaDeTrancamentoFabricaTest.getInstance().criaMotivoDeDesistenciaDeTrancamento();
	}
}