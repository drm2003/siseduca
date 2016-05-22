package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.negocio.pedagogico.contrato.TransferenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.TransferenciaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TransferenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TransferenciaDAOTeste.class);
	TransferenciaDao dao;

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
		dao = new TransferenciaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Transferencia a = criaTransferencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Transferencia consulta = dao.find(Transferencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Transferencia a = criaTransferencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Transferencia consulta = dao.find(Transferencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Transferencia a = criaTransferencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Transferencia consulta = dao.find(Transferencia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Transferencia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Transferencia a1 = criaTransferencia();
		dao.persist(a1);
		Transferencia a2 = criaTransferencia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Transferencia a";
		TypedQuery<Transferencia> query = dao.getEntityManager().createQuery(sql, Transferencia.class);
		//
		List<Transferencia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_curso_nulo() {
		Transferencia a = criaTransferencia();
		a.setCurso(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_curso_vazio() {
		Transferencia a = criaTransferencia();
		a.setCurso("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void curso_menor_que_tamanho_minimo_permitido() {
		Transferencia a = criaTransferencia();
		a.setCurso(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void curso_maior_que_tamanho_maximo_permitido() {
		Transferencia a = criaTransferencia();
		a.setCurso(criarStringDinamicaPorTamanho(250));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_curso() {
		Transferencia a = criaTransferencia();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Transferencia a WHERE a.curso = :curso";
		TypedQuery<Transferencia> query = dao.getEntityManager().createQuery(sql, Transferencia.class);
		query.setParameter("curso", a.getCurso());
		//
		Transferencia m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	/*
	 * 
	 */

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_estabelecimentoProcedencia_nulo() {
		Transferencia a = criaTransferencia();
		a.setEstabelecimentoProcedencia(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_estabelecimentoProcedencia_vazio() {
		Transferencia a = criaTransferencia();
		a.setEstabelecimentoProcedencia("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void estabelecimentoProcedencia_menor_que_tamanho_minimo_permitido() {
		Transferencia a = criaTransferencia();
		a.setEstabelecimentoProcedencia(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void estabelecimentoProcedencia_maior_que_tamanho_maximo_permitido() {
		Transferencia a = criaTransferencia();
		a.setEstabelecimentoProcedencia(criarStringDinamicaPorTamanho(250));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_estabelecimentoProcedencia() {
		Transferencia a = criaTransferencia();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Transferencia a WHERE a.estabelecimentoProcedencia = :estabelecimentoProcedencia";
		TypedQuery<Transferencia> query = dao.getEntityManager().createQuery(sql, Transferencia.class);
		query.setParameter("estabelecimentoProcedencia", a.getEstabelecimentoProcedencia());
		//
		Transferencia m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_data_nulo() {
		Transferencia a = criaTransferencia();
		a.setData(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Transferencia a = criaTransferencia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Transferencia criaTransferencia() {
		return TransferenciaFabricaTest.getInstance().criaTransferencia(getEntityManager());
	}
}