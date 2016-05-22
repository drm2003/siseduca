package br.com.cdan.negocio.teste.contato;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.contato.TipoDeContato;
import br.com.cdan.negocio.contato.TipoDeContatoDao;
import br.com.cdan.negocio.contato.factory.TipoDeContatoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeContatoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeContatoDAOTeste.class);
	TipoDeContatoDao dao;

	/**
	 * <c> Ao criar um teste da camada de persist�ncia utilizando o JUnit �
	 * preciso ter acesso ao cont\exto de persist�ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execu��o dos testes fornecemos este acesso �
	 * camada de persist�ncia por meio de uma inst�ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new TipoDeContatoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeContato a = criaTipoDeContato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeContato consulta = dao.find(TipoDeContato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		TipoDeContato a = criaTipoDeContato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		TipoDeContato consulta = dao.find(TipoDeContato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		TipoDeContato a = criaTipoDeContato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeContato consulta = dao.find(TipoDeContato.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeContato.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeContato a1 = criaTipoDeContato();
		dao.persist(a1);
		TipoDeContato a2 = criaTipoDeContato();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeContato a";
		Query query = dao.getEntityManager().createQuery(sql, TipoDeContato.class);
		//
		@SuppressWarnings("unchecked")
		List<TipoDeContato> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeContato a = criaTipoDeContato();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TipoDeContato a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeContato> query = dao.getEntityManager().createQuery(sql, TipoDeContato.class);
		query.setParameter("descricao", a.getDescricao());
		TipoDeContato consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeContato a = criaTipoDeContato();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeContato a = criaTipoDeContato();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeContato a = criaTipoDeContato();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeContato a = criaTipoDeContato();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeContato a = criaTipoDeContato();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeContato criaTipoDeContato() {
		return TipoDeContatoFabricaTest.getInstance().criaTipoDeContato();
	}
}