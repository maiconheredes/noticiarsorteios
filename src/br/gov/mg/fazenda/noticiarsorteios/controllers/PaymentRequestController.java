package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.BankEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PaymentRequestEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumDetailEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SocialEEntity;

public class PaymentRequestController extends AbstractController {
	public static ArrayList<BankEntity> findBanks() {
		ArrayList<BankEntity> banks = new ArrayList<BankEntity>();
		
		String response = RequestController.request("pagamentos/bancos", 8095, "GET", "");
		
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
	
	public static PremiumDetailEntity findPremiumDetail(String cnpj, String cdRequisicao) {
		PremiumDetailEntity premiumDetail = new PremiumDetailEntity();
		
		String response1 = RequestController.request(
			"sorteios/premiado/entidadesocial/" + cnpj + "/" + cdRequisicao,
			8087, "GET", ""
		);
		
		SocialEEntity socialE = new Gson()
				.fromJson(response1, SocialEEntity.class);
		
		if (socialE != null) {
			String response2 = RequestController.request(
				"sorteios/premiado/entidadesocial/detalhe/" + cnpj + "/" + socialE.idPremiado,
				8087, "GET", ""
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
		String idPremiado, String nrContaBancaria, String tipoContaBancaria
	) {		
		String pgtoPremioDTO = "{";
		pgtoPremioDTO += "\"cdAgencia\": \"" + cdAgencia +"\",";
		pgtoPremioDTO += "\"cdBanco\": \"" + cdBanco +"\",";
		pgtoPremioDTO += "\"dvContaBancaria\": \"" + dvContaBancaria +"\",";
		pgtoPremioDTO += "\"idEntidadeSocial\": \"" + idEntidadeSocial +"\",";
		pgtoPremioDTO += "\"idPremiado\": \"" + idPremiado +"\",";
		pgtoPremioDTO += "\"nrContaBancaria\": \"" + nrContaBancaria +"\",";
		pgtoPremioDTO += "\"tipoContaBancaria\": \"" + tipoContaBancaria +"";
		pgtoPremioDTO = "}";
		
		try {
			RequestController.request("pagamentos", 8095, "POST", pgtoPremioDTO);	
		} catch (Exception exception) {
			return false;
		}
		
		return true;
	}
}
