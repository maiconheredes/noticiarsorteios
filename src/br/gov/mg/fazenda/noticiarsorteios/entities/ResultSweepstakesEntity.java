package br.gov.mg.fazenda.noticiarsorteios.entities;

import java.util.ArrayList;

public class ResultSweepstakesEntity {
	public ArrayList<SweepstakeEntity> content;
	public boolean empty;
	public boolean first;
	public boolean last;
	public String number;
	public String numberOfElements;
	public String size;
	public String totalElements;
	public String totalPages;
	public PageableEntity pageable;
}
