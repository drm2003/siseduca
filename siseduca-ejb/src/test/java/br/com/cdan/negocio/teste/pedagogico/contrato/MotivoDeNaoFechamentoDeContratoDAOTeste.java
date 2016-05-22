package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.MotivoDeNaoFechamentoDeContrato;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeNaoFechamentoDeContratoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MotivoDeNaoFechamentoDeContratoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MotivoDeNaoFechamentoDeContratoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MotivoDeNaoFechamentoDeContratoDAOTeste.class);
	MotivoDeNaoFechamentoDeContratoDao dao;

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
		dao = new MotivoDeNaoFechamentoDeContratoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeNaoFechamentoDeContrato consulta = dao.find(MotivoDeNaoFechamentoDeContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MotivoDeNaoFechamentoDeContrato consulta = dao.find(MotivoDeNaoFechamentoDeContrato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeNaoFechamentoDeContrato consulta = dao.find(MotivoDeNaoFechamentoDeContrato.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MotivoDeNaoFechamentoDeContrato.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MotivoDeNaoFechamentoDeContrato a1 = criaMotivoDeNaoFechamentoDeContrato();
		dao.persist(a1);
		MotivoDeNaoFechamentoDeContrato a2 = criaMotivoDeNaoFechamentoDeContrato();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MotivoDeNaoFechamentoDeContrato a";
		TypedQuery<MotivoDeNaoFechamentoDeContrato> query = dao.getEntityManager().createQuery(sql,
				MotivoDeNaoFechamentoDeContrato.class);
		//
		List<MotivoDeNaoFechamentoDeContrato> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_maximo_permitido() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		a.setDescricao(criarStringDinamicaPorTamanho(360));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		dao.persist(a);
		//
		String sql = "SELECT a FROM MotivoDeNaoFechamentoDeContrato a WHERE a.descricao = :descricao";
		TypedQuery<MotivoDeNaoFechamentoDeContrato> query = dao.getEntityManager().createQuery(sql,
				MotivoDeNaoFechamentoDeContrato.class);
		query.setParameter("descricao", a.getDescricao());
		//
		MotivoDeNaoFechamentoDeContrato m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MotivoDeNaoFechamentoDeContrato a = criaMotivoDeNaoFechamentoDeContrato();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private MotivoDeNaoFechamentoDeContrato criaMotivoDeNaoFechamentoDeContrato() {
		return MotivoDeNaoFechamentoDeContratoFabricaTest.getInstance().criaMotivosDeNaoFechamentoDeContrato();
	}
}