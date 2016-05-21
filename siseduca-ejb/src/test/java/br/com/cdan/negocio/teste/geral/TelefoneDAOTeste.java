package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Telefone;
import br.com.cdan.negocio.geral.TelefoneDao;
import br.com.cdan.negocio.geral.factory.TelefoneFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TelefoneDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TelefoneDAOTeste.class);
	TelefoneDao dao;

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
		dao = new T
			elefoneDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Telefone a = criaTelefone();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Telefone consulta = dao.find(Telefone.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Telefone a = criaTelefone();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNumero("complemento");
		dao.merge(a);
		Telefone consulta = dao.find(Telefone.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Telefone a = criaTelefone();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Telefone consulta = dao.find(Telefone.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Telefone.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Telefone a1 = criaTelefone();
		dao.persist(a1);
		Telefone a2 = criaTelefone();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Telefone a";
		TypedQuery<Telefone> query = dao.getEntityManager().createQuery(sql, Telefone.class);
		//
		List<Telefone> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_numero_vazio() {
		Telefone a = criaTelefone();
		a.setNumero("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_numero_nulo() {
		Telefone a = criaTelefone();
		a.setNumero(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Telefone a = criaTelefone();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Telefone criaTelefone() {
		return TelefoneFabricaTest.getInstance().criaTelefone(getEntityManager());
	}
}