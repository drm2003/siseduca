package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Telefone;
import br.com.cdan.negocio.biblioteca.factory.EditoraFabricaTest;
import br.com.cdan.negocio.financeiro.factory.BancoFabricaTest;
import br.com.cdan.negocio.geral.TelefoneDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.AproveitamentoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.ResponsavelFabricaTest;

public class TelefoneFabricaTest {
	private static TelefoneFabricaTest instance = null;

	public static TelefoneFabricaTest getInstance() {
		if (instance == null) {
			instance = new TelefoneFabricaTest();
		}
		return instance;
	}

	public Telefone criaTelefone(EntityManager em) {
		Telefone t = new Telefone();
		t.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento(em));
		t.setAtivo(Boolean.TRUE);
		t.setBanco(BancoFabricaTest.getInstance().criaBancoPersistido(em));
		t.setEditora(EditoraFabricaTest.getInstance().criaEditoraPersistido(em));
		t.setNumero("numero");
		t.setResponsavel(ResponsavelFabricaTest.getInstance().criaResponsavelPersistido(em));
		t.setTelefonePrincipal(Boolean.TRUE);
		t.setTipoDeCelular(TipoDeCelularFabricaTest.getInstance().criaTipoDeCelularPersistido(em));
		return t;
	}

	public Telefone criaTelefonePersistido(EntityManager em) {
		Telefone t = criaTelefone(em);
		TelefoneDao dao = new TelefoneDao(em);
		//
		dao.persist(t);
		return t;
	}

}
