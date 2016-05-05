package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pedagogico.contrato.DadosEmpresaConcedente;
import br.com.cdan.negocio.biblioteca.DadosEmpresaConcedenteDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;

public class DadosEmpresaConcedenteFabricaTest {
	private static DadosEmpresaConcedenteFabricaTest instance = null;

	public static synchronized DadosEmpresaConcedenteFabricaTest getInstance() {
		if (instance == null) {
			instance = new DadosEmpresaConcedenteFabricaTest();
		}
		return instance;
	}

	public DadosEmpresaConcedente criaDadosEmpresaConcedente() {
		DadosEmpresaConcedente d = new DadosEmpresaConcedente();
		d.setAtivo(Boolean.TRUE);
		d.setAreaEstagioMonografia("areaEstagioMonografia");
		d.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		d.setFormacaoAcademica("formacaoAcademica");
		d.setNumeroRegistroConselho("numeroRegistroConselho");
		d.setObservacaoDoResponsavel("observacaoDoResponsavel");
		d.setRepresentanteLegal("representanteLegal");
		d.setResponsavel("responsavel");
		return d;
	}

	public DadosEmpresaConcedente criaDadosEmpresaConcedentePersistido(EntityManager em) {
		DadosEmpresaConcedente d = criaDadosEmpresaConcedente();
		DadosEmpresaConcedenteDao dao = new DadosEmpresaConcedenteDao(em);
		//
		Empresa empresa = d.getEmpresa();
		EmpresaDao empresaDao = new EmpresaDao(em);
		empresaDao.persist(empresa);
		d.setEmpresa(empresa);
		//
		dao.persist(d);
		return d;
	}
}
