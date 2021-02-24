package br.gov.mg.fazenda.noticiarsorteios.utils;

public class Routes {
	public static String cities() {
		switch (Env.getEnv()) {
			case "DES":
				return "municipiosregioesfiscais";

			case "HOM":
				return "municipiosregioesfiscais";

			case "PRO":
				return "municipiosregioesfiscais";
			
			default:
				return "";
		}
	}
	
	public static String regions() {
		switch (Env.getEnv()) {
			case "DES":
				return "regioesfiscais";

			case "HOM":
				return "regioesfiscais";

			case "PRO":
				return "regioesfiscais";
			
			default:
				return "";
		}
	}
	
	public static String paymentsBanks() {
		switch (Env.getEnv()) {
			case "DES":
				return "pagamentos/bancos";

			case "HOM":
				return "pagamentos/bancos";

			case "PRO":
				return "pagamentos/bancos";
			
			default:
				return "";
		}
	}
	
	public static String payments() {
		switch (Env.getEnv()) {
			case "DES":
				return "pagamentos";

			case "HOM":
				return "pagamentos";

			case "PRO":
				return "pagamentos";
			
			default:
				return "";
		}
	}
	
	public static String socialEntityAwarded() {
		switch (Env.getEnv()) {
			case "DES":
				return "sorteios/premiado/entidadesocial/{cnpj}/{cdRequisicao}";

			case "HOM":
				return "sorteios/premiado/entidadesocial/{cnpj}/{cdRequisicao}";

			case "PRO":
				return "sorteios/premiado/entidadesocial/{cnpj}/{cdRequisicao}";
			
			default:
				return "";
		}
	}
	
	public static String socialEntityAwardedDetail() {
		switch (Env.getEnv()) {
			case "DES":
				return "sorteios/premiado/entidadesocial/detalhe/{cnpj}/{idPremiado}";

			case "HOM":
				return "sorteios/premiado/entidadesocial/detalhe/{cnpj}/{idPremiado}";

			case "PRO":
				return "sorteios/premiado/entidadesocial/detalhe/{cnpj}/{idPremiado}";
			
			default:
				return "";
		}
	}
	
	public static String premiumsByLocation() {
		switch (Env.getEnv()) {
			case "DES":
				return "sorteios/{idSweepstake}/premiacoes/{idLocation}";

			case "HOM":
				return "sorteios/{idSweepstake}/premiacoes/{idLocation}";

			case "PRO":
				return "sorteios/{idSweepstake}/premiacoes/{idLocation}";
			
			default:
				return "";
		}
	}
	
	public static String socialEntities() {
		switch (Env.getEnv()) {
			case "DES":
				return "entidadessociais";

			case "HOM":
				return "entidadessociais";

			case "PRO":
				return "entidadessociais";
			
			default:
				return "";
		}
	}
	
	public static String sweepstakes() {
		switch (Env.getEnv()) {
			case "DES":
				return "sorteios";

			case "HOM":
				return "sorteios";

			case "PRO":
				return "sorteios";
			
			default:
				return "";
		}
	}
	
	public static String findSocialEntity() {
		switch (Env.getEnv()) {
			case "DES":
				return "entidadessociais/{cnpj}";

			case "HOM":
				return "entidadessociais/{cnpj}";

			case "PRO":
				return "entidadessociais/{cnpj}";
			
			default:
				return "";
		}
	}
	
	public static String findSweepstakes() {
		switch (Env.getEnv()) {
			case "DES":
				return "sorteios/periodo/{year}/{month}";

			case "HOM":
				return "sorteios/periodo/{year}/{month}";

			case "PRO":
				return "sorteios/periodo/{year}/{month}";
			
			default:
				return "";
		}
	}
	
	public static String domain() {
        switch (Env.getEnv()) {
			case "DES":
				return "http://hesperides.fazenda.mg.gov.br";
	
			case "HOM":
				return "http://nfm.fazenda.mg.gov.br";
	
			case "PRO":
				return "http://notamineira.dnfm.fazenda.mg.gov.br";
			
			default:
				return "";
		}
    }
}
