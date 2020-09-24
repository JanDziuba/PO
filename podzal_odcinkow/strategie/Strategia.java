package strategie;

import wczytywanie_danych.Dane;

public class Strategia
{
	protected long lacznyKoszPretow;
	protected long lacznaDlugoscOdpadow;
	protected int[] numerPretaWKtorymJestOdcinekTab;
	protected int[] dlugoscNtegoKupionegoPretaTab;
	
	Strategia(Dane dane)
	{
		this.lacznyKoszPretow = 0;
		this.lacznaDlugoscOdpadow = 0;
		this.numerPretaWKtorymJestOdcinekTab = new int[dane.iloscOdcinkow()];
		this.dlugoscNtegoKupionegoPretaTab = new int[dane.iloscOdcinkow()];
	}
	
	public long lacznyKoszPretow()
	{
		return lacznyKoszPretow;
	}
	
	public long lacznaDlugoscOdpadow()
	{
		return lacznaDlugoscOdpadow;
	}
	
	public int[] numerPretaWKtorymJestOdcinekTab()
	{
		return numerPretaWKtorymJestOdcinekTab.clone();
	}
	
	public int[] dlugoscNtegoKupionegoPretaTab()
	{
		return dlugoscNtegoKupionegoPretaTab.clone();
	}
}
