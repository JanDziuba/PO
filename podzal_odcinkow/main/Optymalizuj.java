package main;

import strategie.StrategiaEkologiczna;
import strategie.StrategiaEkonomiczna;
import strategie.StrategiaMaksymalistyczna;
import strategie.StrategiaMinimalistyczna;
import wczytywanie_danych.Dane;

public class Optymalizuj
{

	public static void main(String[] args)
	{
		Dane dane = new Dane();
		
		Optymalizuj.wykonajStrategie(dane);

	}
	
	public static void wykonajStrategie(Dane dane)
	{
		if(dane.strategia().intern() == "minimalistyczna")
		{
			StrategiaMinimalistyczna.wykonajStrategieMinimalistyczna(dane);
		}
		else if(dane.strategia().intern() == "maksymalistyczna")
		{
			StrategiaMaksymalistyczna.wykonajStrategieMaksymalistyczna(dane);
		}
		else if(dane.strategia().intern() == "ekonomiczna")
		{
			StrategiaEkonomiczna.wykonajStrategieEkonomiczna(dane);
		}
		else if(dane.strategia().intern() == "ekologiczna")
		{
			StrategiaEkologiczna.wykonajStrategieEkologiczna(dane);
		}
	}

}
