package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.BankEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PaymentRequestEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumDetailEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.utils.Routes;

public class PaymentRequestController extends AbstractController {
	public static ArrayList<BankEntity> findBanks(HttpServletRequest request) {
		ArrayList<BankEntity> banks = new ArrayList<BankEntity>();
		
		String response = RequestController.request(Routes.paymentsBanks(), 8095, "GET", "", request);
		
		BankEntity[] result = new Gson()
				.fromJson(response, BankEntity[].class);
		
		for (BankEntity bank : result) {
			banks.add(bank);
		}
		
		Collections.sort(banks, new Comparator<BankEntity>() {
	        @Override
	        public int compare(BankEntity bank1, BankEntity bank2) {
	            return  bank1.nmBanco.compareTo(bank2.nmBanco);
	        }
	    });
		
		return banks;
	}
	
	public static PremiumDetailEntity findPremiumDetail(
		String cnpj, String cdRequisicao, HttpServletRequest request
	) {
		PremiumDetailEntity premiumDetail = new PremiumDetailEntity();
		
		String response1 = RequestController.request(
			Routes.socialEntityAwarded()
				.replace("{cnpj}", cnpj)
				.replace("{cdRequisicao}", cdRequisicao),
			8087, "GET", "", request
		);
		
		SocialEEntity socialE = new Gson()
				.fromJson(response1, SocialEEntity.class);
		
		if (socialE != null) {
			String response2 = RequestController.request(
				Routes.socialEntityAwardedDetail()
					.replace("{cnpj}", cnpj)
					.replace("{idPremiado}", socialE.idPremiado),
				8087, "GET", "", request
			);
				
			premiumDetail = new Gson().fromJson(response2, PremiumDetailEntity.class);
			
			if (premiumDetail != null) {
				premiumDetail.idPremiado = socialE.idPremiado;
				premiumDetail.dtRealizSorteio = convertDate(premiumDetail.dtRealizSorteio);
				premiumDetail.vlrPremioEntidSocial = convertCurrenty(premiumDetail.vlrPremioEntidSocial);
			} else return null;
		} else return null;	
		
		return premiumDetail;
	}
	
	public static Boolean createPayment(
		String cdAgencia, String cdBanco, String dvContaBancaria, String idEntidadeSocial,
		String idPremiado, String nrContaBancaria, String tipoContaBancaria, HttpServletRequest request
	) {		
		String response = null;
		String pgtoPremioDTO = "{";
		pgtoPremioDTO += "\"cdAgencia\": \"" + cdAgencia +"\",";
		pgtoPremioDTO += "\"cdBanco\": \"" + cdBanco +"\",";
		pgtoPremioDTO += "\"dvContaBancaria\": \"" + dvContaBancaria +"\",";
		pgtoPremioDTO += "\"idEntidadeSocial\": \"" + idEntidadeSocial +"\",";
		pgtoPremioDTO += "\"idPremiado\": \"" + idPremiado +"\",";
		pgtoPremioDTO += "\"nrContaBancaria\": \"" + nrContaBancaria +"\",";
		pgtoPremioDTO += "\"tipoContaBancaria\": \"" + tipoContaBancaria +"\"";
		pgtoPremioDTO += "}";
		
		try {
			response = RequestController.request(Routes.payments(), 8095, "POST", pgtoPremioDTO, request);
		} catch (Exception exception) {
			return false;
		}
		
		if (response != null) {
			return true;
		}
		
		return false;
	}
}
