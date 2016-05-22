package br.com.cdan.negocio.comum;

public class FabricaTest {

	/**
	 * 
	 * @param quantidade
	 * @return
	 * 
	 * 		CRIAR STRING A PARTIR DE UM DETERMINADO TAMANHO SERVE PARA TESTES
	 *         DE CAMPOS
	 */
	public String criarStringDinamicaPorTamanho(Integer quantidade) {
		String s = "";
		int x = 0;
		for (int i = 0; i < quantidade; i++) {
			s += (int) (Math.random() * 10) + "";
			if (x == 10)
				x = 0;
		}
		return s;
	}

}
