package br.com.cdan.model.biblioteca;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.cdan.negocio.biblioteca.AreasDeConhecimentoDao;

@RunWith(Arquillian.class)
public class AreasDeConhecimentoBeanTest {

	@Inject
	private AreasDeConhecimentoDao service;

	/*
	 * @Before public void instantiate() { service = new
	 * AreasDeConhecimentoService(); }
	 */

	@Deployment
	public static WebArchive createTestArchive() {
		WebArchive arquivoTeste = ShrinkWrap.create(WebArchive.class, "aplicacaoTeste.war")
				.addPackages(true, "br.com.cdan").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml");
		System.out.println(arquivoTeste.toString(true));
		return arquivoTeste;
	}

	@Test
	public void testarGravar() {
		AreasDeConhecimento a = new AreasDeConhecimento();
		a.setAtivo(Boolean.TRUE);
		a.setCompartilhado(Boolean.TRUE);
		a.setDescricao("teste");
		// service.setAreasDeConhecimento(a);
		service.persist(areasDeConhecimento);
		List<AreasDeConhecimento> lista = service.lista();
		Assert.assertTrue(lista.contains(a));
	}

	private AreasDeConhecimento areasDeConhecimento = new AreasDeConhecimento();

	public AreasDeConhecimento getAreasDeConhecimento() {
		return areasDeConhecimento;
	}

	public void setAreasDeConhecimento(AreasDeConhecimento areasDeConhecimento) {
		this.areasDeConhecimento = areasDeConhecimento;
	}
}
