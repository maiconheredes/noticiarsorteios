package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.ResultSocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.ResultSweepstakesEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SweepstakeEntity;
import br.gov.mg.fazenda.noticiarsorteios.utils.Routes;

public class SweepstakesController extends AbstractController {
	public static ArrayList<SweepstakeEntity> findSweepstakes(
		String status, String ano, String mes,
		HttpServletRequest request
	) {
		String url = Routes.sweepstakes();
		
		url += "?status=" + status;
		url += "&ano=" + ano;
		url += "&mes=" + mes;
		
		String response = RequestController.request(url, 8087, "GET", "", request);
		
		ResultSweepstakesEntity result = new Gson().fromJson(response, ResultSweepstakesEntity.class);
		
		ArrayList<SweepstakeEntity> sweepstakes = new ArrayList<SweepstakeEntity>();
		
		for (SweepstakeEntity sweepstake : result.content) {		
			//configureSweepstake(sweepstake);
			
			sweepstakes.add(sweepstake);
		}
		
		return sweepstakes;
	}
	
	public static HashMap<String, ArrayList<SweepstakeEntity>> findSweepstakes(
		String month, String year, HttpServletRequest request
	) {
		String response = RequestController.request(
			Routes.findSweepstakes().replace("{year}", year).replace("{month}", month), 
			8087, "GET", "", request
		);

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
			
			if (sweepstake.controleBilhetagem != null) {
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
		}
		
		map.put("ESTADUAL", estadual);
		map.put("REGIONAL", regional);
		map.put("MUNICIPAL", municipal);
		map.put("ESPECIAL", especial);
		
		return map;
	}
}
