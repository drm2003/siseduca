package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.DadosEmpresaConcedente;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.DadosEmpresaConcedenteDao;

public class DadosEmpresaConcedenteFabricaTest {
	private static DadosEmpresaConcedenteFabricaTest instance = null;

	public static synchronized DadosEmpresaConcedenteFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadosEmpresaConcedenteFabricaTest();
		}
		return instance;
	}

	public DadosEmpresaConcedente criaDadosEmpresaConcedente(EntityManager em) {
		DadosEmpresaConcedente d = new DadosEmpresaConcedente();
		d.setAtivo(Boolean.TRUE);
		d.setAreaEstagioMonografia("areaEstagioMonografia");
		d.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresaPersistido(em));
		d.setFormacaoAcademica("formacaoAcademica");
		d.setNumeroRegistroConselho("numeroRegistroConselho");
		d.setObservacaoDoResponsavel("observacaoDoResponsavel");
		d.setRepresentanteLegal("representanteLegal");
		d.setResponsavel("responsavel");
		return d;
	}

	public DadosEmpresaConcedente criaDadosEmpresaConcedentePersistido(EntityManager em) {
		DadosEmpresaConcedente d = criaDadosEmpresaConcedente(em);
		DadosEmpresaConcedenteDao dao = new DadosEmpresaConcedenteDao(em);
		dao.persist(d);
		return d;
	}
}
