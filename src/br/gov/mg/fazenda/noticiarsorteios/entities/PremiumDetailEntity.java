package br.gov.mg.fazenda.noticiarsorteios.entities;

import java.util.ArrayList;

public class PremiumDetailEntity {
	public String nrSorteio;
	public String tpSorteio;
	public String vlrPremioPartic;
	public String stPremioPartic;
	public String dtRealizSorteio;
	public String nrBilhete;
	public String chaveAcesso;
	public ArrayList<PremiumEntity> entidadesSociaisPremiadas;
	public ArrayList<PaymentRequestEntity> historicoRequisicoesPagamento;
}
