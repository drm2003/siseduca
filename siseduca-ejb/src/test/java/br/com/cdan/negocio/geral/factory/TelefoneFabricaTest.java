package br.com.cdan.negocio.geral.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.geral.TipoDeCelular;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.biblioteca.EditoraDao;
import br.com.cdan.negocio.biblioteca.factory.EditoraFabricaTest;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.financeiro.BancoDao;
import br.com.cdan.negocio.financeiro.factory.BancoFabricaTest;
import br.com.cdan.negocio.geral.TelefoneDao;
import br.com.cdan.negocio.geral.TipoDeCelularDao;
import br.com.cdan.negocio.pedagogico.contrato.AproveitamentoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.AproveitamentoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.ResponsavelDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.ResponsavelFabricaTest;

public class TelefoneFabricaTest {
	private static TelefoneFabricaTest instance = null;

	public static TelefoneFabricaTest getInstance() {
		if (instance == null) {
			instance = new TelefoneFabricaTest();
		}
		return instance;
	}

	public Telefone criaTelefone() {
		Telefone t = new Telefone();
		t.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		t.setAtivo(Boolean.TRUE);
		t.setBanco(BancoFabricaTest.getInstance().criaBanco());
		t.setEditora(EditoraFabricaTest.getInstance().criaEditora());
		//
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		t.setEmpresas(empresas);
		//
		t.setNumero("numero");
		t.setResponsavel(ResponsavelFabricaTest.getInstance().criaResponsavel());
		t.setTelefonePrincipal(Boolean.TRUE);
		t.setTipoDeCelular(TipoDeCelularFabricaTest.getInstance().criaTipoDeCelular());
		return t;
	}

	public Telefone criaTelefonePersistido(EntityManager em) {
		Telefone t = criaTelefone();
		TelefoneDao dao = new TelefoneDao(em);
		//
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		Aproveitamento aproveitamento = t.getAproveitamento();
		aproveitamentoDao.persist(aproveitamento);
		t.setAproveitamento(aproveitamento);
		//
		BancoDao bancoDao = new BancoDao(em);
		Banco banco = t.getBanco();
		bancoDao.persist(banco);
		t.setBanco(banco);
		//
		EditoraDao editoraDao = new EditoraDao(em);
		Editora editora = t.getEditora();
		editoraDao.persist(editora);
		t.setEditora(editora);
		//
		Set<Empresa> empresas = new LinkedHashSet<>();
		EmpresaDao empresaDao = new EmpresaDao(em);
		t.getEmpresas().forEach(empresa -> {
			empresaDao.persist(empresa);
			empresas.add(empresa);
		});
		t.setEmpresas(empresas);
		//
		ResponsavelDao responsavelDao = new ResponsavelDao(em);
		Responsavel responsavel = t.getResponsavel();
		responsavelDao.persist(responsavel);
		t.setResponsavel(responsavel);
		//
		TipoDeCelularDao tipoDeCelularDao = new TipoDeCelularDao(em);
		TipoDeCelular tipoDeCelular = t.getTipoDeCelular();
		tipoDeCelularDao.persist(tipoDeCelular);
		t.setTipoDeCelular(tipoDeCelular);
		//
		dao.persist(t);
		return t;
	}

}
