package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.negocio.pedagogico.contrato.OcorrenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.OcorrenciaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class OcorrenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(OcorrenciaDAOTeste.class);
	OcorrenciaDao dao;

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
		dao = new OcorrenciaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Ocorrencia a = criaOcorrencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Ocorrencia consulta = dao.find(Ocorrencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Ocorrencia a = criaOcorrencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Ocorrencia consulta = dao.find(Ocorrencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Ocorrencia a = criaOcorrencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Ocorrencia consulta = dao.find(Ocorrencia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Ocorrencia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Ocorrencia a1 = criaOcorrencia();
		dao.persist(a1);
		Ocorrencia a2 = criaOcorrencia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Ocorrencia a";
		TypedQuery<Ocorrencia> query = dao.getEntityManager().createQuery(sql, Ocorrencia.class);
		//
		List<Ocorrencia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Ocorrencia a = criaOcorrencia();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Ocorrencia a = criaOcorrencia();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Ocorrencia a = criaOcorrencia();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Ocorrencia a = criaOcorrencia();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Ocorrencia a WHERE a.descricao = :descricao";
		TypedQuery<Ocorrencia> query = dao.getEntityManager().createQuery(sql, Ocorrencia.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Ocorrencia m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_data_nulo() {
		Ocorrencia a = criaOcorrencia();
		a.setData(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Ocorrencia a = criaOcorrencia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Ocorrencia criaOcorrencia() {
		return OcorrenciaFabricaTest.getInstance().criaOcorrencia(getEntityManager());
	}
}