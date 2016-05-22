package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Conteudo;
import br.com.cdan.negocio.pedagogico.curso.ConteudoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.ConteudoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ConteudoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ConteudoDAOTeste.class);
	ConteudoDao dao;

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
		dao = new ConteudoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Conteudo a = criaConteudo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Conteudo consulta = dao.find(Conteudo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Conteudo a = criaConteudo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Conteudo consulta = dao.find(Conteudo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Conteudo a = criaConteudo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Conteudo consulta = dao.find(Conteudo.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Conteudo.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Conteudo a1 = criaConteudo();
		dao.persist(a1);
		Conteudo a2 = criaConteudo();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Conteudo a";
		TypedQuery<Conteudo> query = dao.getEntityManager().createQuery(sql, Conteudo.class);
		//
		List<Conteudo> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Conteudo a = criaConteudo();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Conteudo a = criaConteudo();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Conteudo a = criaConteudo();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Conteudo a = criaConteudo();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Conteudo a = criaConteudo();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Conteudo a WHERE a.descricao = :descricao";
		TypedQuery<Conteudo> query = dao.getEntityManager().createQuery(sql, Conteudo.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Conteudo m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Conteudo a = criaConteudo();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Conteudo criaConteudo() {
		return ConteudoFabricaTest.getInstance().criaConteudo(getEntityManager());
	}
}