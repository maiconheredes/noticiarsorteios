package br.gov.mg.fazenda.test;

import br.gov.mg.fazenda.noticiarsorteios.controllers.SweepstakesController;

public class Test {
	public static void main(String[] args) {
		/*String body = RequestController.request("sorteios/periodo/2021/2", 8087);

		SweepstakeEntity[] sweepstakes = new Gson().fromJson(body, SweepstakeEntity[].class);

		for (SweepstakeEntity sweepstake : sweepstakes) {
			System.out.println("tpSorteio: " + sweepstake.tpSorteio);
		}*/
		SweepstakesController.findSweepstakes("02", "2021");
		System.out.println("Ok");
	}
}
