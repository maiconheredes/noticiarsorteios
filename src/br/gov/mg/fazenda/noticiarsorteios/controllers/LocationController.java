package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.CityEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.LocationEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.RegionalEntity;

public class LocationController extends AbstractController {
	public static ArrayList<LocationEntity> findLocations(String tpSorteio) {
		ArrayList<LocationEntity> locations = new ArrayList<LocationEntity>();
		
		if (tpSorteio.equals("MUNICIPAL")) {
			String response = RequestController.request("municipiosregioesfiscais", 8084, "GET", "");
			CityEntity[] cities = new Gson().fromJson(response, CityEntity[].class);
			
			for (CityEntity city : cities) {
				LocationEntity location = new LocationEntity();
				location.id = city.idLocalidade;
				location.name = city.municipio;
				location.cdRegiaoFiscal = city.cdRegiaoFiscal;
				
				locations.add(location);
			}
		} else if (tpSorteio.equals("REGIONAL")) {
			String response = RequestController.request("regioesfiscais", 8084, "GET", "");
			
			RegionalEntity[] regionals = new Gson().fromJson(response, RegionalEntity[].class);
			
			for (RegionalEntity regional : regionals) {
				LocationEntity location = new LocationEntity();
				location.id = regional.cdRegiaoFiscal;
				location.name = regional.nmRegiaoFiscal;
				
				locations.add(location);
			}			
		}
		
		Collections.sort(locations, new Comparator<LocationEntity>() {
	        @Override
	        public int compare(LocationEntity location1, LocationEntity location2) {
	            return  location1.name.compareTo(location2.name);
	        }
	    });
		
		return locations;
	}
}
