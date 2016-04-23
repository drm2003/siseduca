package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.model.acesso.HorarioDeAcesso;
import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.pedagogico.CalendarioLetivo;

public class HorarioDeAcessoFabricaTest {
	private static HorarioDeAcessoFabricaTest instance = null;

	public static synchronized HorarioDeAcessoFabricaTest getInstance() {
		if (instance == null) {
			instance = new HorarioDeAcessoFabricaTest();
		}
		return instance;
	}

	public HorarioDeAcesso criaHorarioDeAcesso() {
		HorarioDeAcesso h = new HorarioDeAcesso();
		// Acessos do dia da semana
		Set<AcessoDiasDaSemana> acessosDiasDaSemana = new LinkedHashSet<>();
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiaDaSemana());
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiaDaSemana());
		h.setAcessoDiasDaSemana(acessosDiasDaSemana);
		// Calendário Letivo
		Set<CalendarioLetivo> calendariosLetivo = new LinkedHashSet<>();
		calendariosLetivo.add(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		calendariosLetivo.add(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		h.setCalendarioLetivo(calendarioLetivo);
		// Acessos dias da semana
		Set<AcessoDiasDaSemana> acessosDiasDaSemana = new LinkedHashSet<>();
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiaDaSemana());
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiaDaSemana());
		h.setDiasDaSemana(acessosDiasDaSemana);
		//
		Set<Usuario> usuarios = new LinkedHashSet<>();
		usuarios.add(UsuarioFabricaTest.getInstance().criaUsuario());
		usuarios.add(UsuarioFabricaTest.getInstance().criaUsuario());
		h.setUsuario(usuarios);
		//
		return h;
	}

}
