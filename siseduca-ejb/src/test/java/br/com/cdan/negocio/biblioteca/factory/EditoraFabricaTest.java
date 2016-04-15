package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.negocio.biblioteca.EditoraDao;
import br.com.cdan.negocio.biblioteca.EmailDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;
import br.com.cdan.negocio.biblioteca.ObraDao;
import br.com.cdan.negocio.biblioteca.TelefoneDao;

public class EditoraFabricaTest {
	private static EditoraFabricaTest instance = null;

	public static synchronized EditoraFabricaTest getInstance() {
		if (instance == null) {
			instance = new EditoraFabricaTest();
		}
		return instance;
	}

	public Editora criaEditora() {
		Editora a = new Editora();
		a.setAtivo(Boolean.TRUE);
		a.setCompartilhado(Boolean.TRUE);
		a.setNome("Teste " + Math.random() * 1000);
		// Criando emails
		Email e1 = EmailFabricaTest.getInstance().criaEmail();
		Email e2 = EmailFabricaTest.getInstance().criaEmail();
		Set<Email> emails = new LinkedHashSet<>();
		emails.add(e1);
		emails.add(e2);
		a.setEmails(emails);
		// Criando endereços
		Endereco end1 = EnderecoFabricaTest.getInstance().criaEndereco();
		Endereco end2 = EnderecoFabricaTest.getInstance().criaEndereco();
		Set<Endereco> enderecos = new LinkedHashSet<>();
		enderecos.add(end1);
		enderecos.add(end2);
		a.setEndereco(enderecos);
		a.setHomePage("teste");
		// Criando Obras
		Obra o1 = ObraFabricaTest.getInstance().criaObra();
		Obra o2 = ObraFabricaTest.getInstance().criaObra();
		Set<Obra> obras = new LinkedHashSet<>();
		obras.add(o1);
		obras.add(o2);
		a.setObras(obras);
		a.setObservacoes("teste");

		// Criando Telefones
		Telefone t1 = TelefoneFabricaTest.getInstance().criaTelefone();
		Telefone t2 = TelefoneFabricaTest.getInstance().criaTelefone();
		Set<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(t1);
		telefones.add(t2);
		a.setTelefones(telefones);
		//
		return a;
	}

	public Editora criaEditoraPersistido(EntityManager em) {
		EditoraDao dao = new EditoraDao();
		dao.setEntityManager(em);
		//
		Editora a = criaEditora();
		// Gravando primeiramente os objetos
		// Email
		Set<Email> emails = new LinkedHashSet<>();
		EmailDao daoEmail = new EmailDao();
		daoEmail.setEntityManager(em);
		a.getEmails().forEach(e -> {
			daoEmail.persist(e);
			emails.add(e);
		});
		a.setEmails(emails);
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		EnderecoDao daoEndereco = new EnderecoDao();
		daoEndereco.setEntityManager(em);
		a.getEndereco().forEach(endereco -> {
			daoEndereco.persist(endereco);
			enderecos.add(endereco);
		});
		a.setEndereco(enderecos);
		// Obras
		Set<Obra> obras = new LinkedHashSet<>();
		ObraDao daoObra = new ObraDao();
		daoObra.setEntityManager(em);
		a.getObras().forEach(obra -> {
			daoObra.persist(obra);
			obras.add(obra);
		});
		a.setObras(obras);
		// Telefones
		Set<Telefone> telefones = new LinkedHashSet<>();
		TelefoneDao daoTelefone = new TelefoneDao();
		daoTelefone.setEntityManager(em);
		a.getTelefones().forEach(telefone -> {
			daoTelefone.persist(telefone);
			telefones.add(telefone);
		});
		a.setTelefones(telefones);
		//
		dao.persist(a);
		return a;
	}
}