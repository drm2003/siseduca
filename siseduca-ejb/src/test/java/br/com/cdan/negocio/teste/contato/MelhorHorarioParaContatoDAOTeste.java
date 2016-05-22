package br.com.cdan.negocio.teste.contato;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.contato.MelhorHorarioParaContato;
import br.com.cdan.negocio.contato.MelhorHorarioParaContatoDao;
import br.com.cdan.negocio.contato.factory.MelhorHorarioParaContatoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MelhorHorarioParaContatoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MelhorHorarioParaContatoDAOTeste.class);
	MelhorHorarioParaContatoDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistência utilizando o JUnit é
	 * preciso ter acesso ao cont\exto de persistência fornecido pelo JPA.
	 * <c> Deste modo, antes da execução dos testes fornecemos este acesso ï¿½
	 * camada de persistência por meio de uma instância
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new MelhorHorarioParaContatoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MelhorHorarioParaContato consulta = dao.find(MelhorHorarioParaContato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		MelhorHorarioParaContato consulta = dao.find(MelhorHorarioParaContato.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MelhorHorarioParaContato consulta = dao.find(MelhorHorarioParaContato.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MelhorHorarioParaContato.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MelhorHorarioParaContato a1 = criaMelhorHorarioParaContato();
		dao.persist(a1);
		MelhorHorarioParaContato a2 = criaMelhorHorarioParaContato();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MelhorHorarioParaContato a";
		Query query = dao.getEntityManager().createQuery(sql, MelhorHorarioParaContato.class);
		//
		@SuppressWarnings("unchecked")
		List<MelhorHorarioParaContato> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM MelhorHorarioParaContato a WHERE a.descricao = :descricao";
		TypedQuery<MelhorHorarioParaContato> query = dao.getEntityManager().createQuery(sql, MelhorHorarioParaContato.class);
		query.setParameter("descricao", a.getDescricao());
		MelhorHorarioParaContato consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MelhorHorarioParaContato a = criaMelhorHorarioParaContato();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private MelhorHorarioParaContato criaMelhorHorarioParaContato() {
		return MelhorHorarioParaContatoFabricaTest.getInstance()
				.criaMelhorHorarioParaContato();
	}
}