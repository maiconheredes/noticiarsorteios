package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.SweepstakeEntity;

public class SweepstakesController extends AbstractController {
	public static HashMap<String, ArrayList<SweepstakeEntity>> findSweepstakes(String month, String year) {
		String response = RequestController.request("sorteios/periodo/" + year + "/" + month, 8087);
		//String response = RequestController.request("sorteiodoc/sorteios/periodo/" + year + "/" + month, 80);

		SweepstakeEntity[] sweepstakes = new Gson().fromJson(response, SweepstakeEntity[].class);
		
		if (sweepstakes == null) {
			sweepstakes = new SweepstakeEntity[0];
		}
		
		HashMap<String, ArrayList<SweepstakeEntity>> map = new HashMap<>();
		ArrayList<SweepstakeEntity> estadual = new ArrayList<SweepstakeEntity>();
		ArrayList<SweepstakeEntity> regional = new ArrayList<SweepstakeEntity>();
		ArrayList<SweepstakeEntity> municipal = new ArrayList<SweepstakeEntity>();
		ArrayList<SweepstakeEntity> especial = new ArrayList<SweepstakeEntity>();
		
		for (SweepstakeEntity sweepstake : sweepstakes) {
			configureSweepstake(sweepstake);
			
			switch (sweepstake.tpSorteio) {
				case "ESTADUAL":
					estadual.add(sweepstake);
					break;
				case "REGIONAL":
					regional.add(sweepstake);
					break;
				case "MUNICIPAL":
					municipal.add(sweepstake);
					break;
				case "ESPECIAL":
				default:
					especial.add(sweepstake);
					break;
			}
		}
		
		map.put("ESTADUAL", estadual);
		map.put("REGIONAL", regional);
		map.put("MUNICIPAL", municipal);
		map.put("ESPECIAL", especial);
		
		return map;
	}
}
