package strategie;

import wczytywanie_danych.Dane;
import wypisywanie_danych.WypisywanieDanych;

public class StrategiaMinimalistyczna
{	
	public static void wykonajStrategieMinimalistyczna(Dane dane)
	{
		Strategia strategia = new Strategia(dane);
		
		wykonajOptymalnyPodzial(dane, strategia);
		
		WypisywanieDanych.wypiszRozwiazanie(dane, strategia);
		
	}
	
	public static void wykonajOptymalnyPodzial(Dane dane, Strategia strategia)
	{
		int liczbaKupionychPretow = 0;
		int[] pozostalaDlugoscPretaTab = new int[dane.iloscOdcinkow()];
		boolean czyJesztMiejsceNaOdcinekWPretach;
		
		for (int numerOdcinka = (dane.iloscOdcinkow() - 1); numerOdcinka >= 0; numerOdcinka--)
		{
			czyJesztMiejsceNaOdcinekWPretach = false;
			
			for (int numerPreta = 0; numerPreta < liczbaKupionychPretow; numerPreta++)
			{
				if ((czyJesztMiejsceNaOdcinekWPretach == false) &&
						(pozostalaDlugoscPretaTab[numerPreta] >= dane.dlugoscOdcinkaTab()[numerOdcinka]))
				{
					pozostalaDlugoscPretaTab[numerPreta] -= dane.dlugoscOdcinkaTab()[numerOdcinka];
					strategia.numerPretaWKtorymJestOdcinekTab[numerOdcinka] = numerPreta;
					czyJesztMiejsceNaOdcinekWPretach = true;
				}
			}
			
			if (czyJesztMiejsceNaOdcinekWPretach == false)
			{
				liczbaKupionychPretow++;
				
				strategia.dlugoscNtegoKupionegoPretaTab[liczbaKupionychPretow - 1] =
						dane.dlugoscPretaTab()
						[NajkrotszyPretDluzszyOdOdcinka(dane.dlugoscOdcinkaTab()[numerOdcinka], dane.dlugoscPretaTab())];
				
				pozostalaDlugoscPretaTab[liczbaKupionychPretow - 1] = 
						(strategia.dlugoscNtegoKupionegoPretaTab[liczbaKupionychPretow - 1] - dane.dlugoscOdcinkaTab()[numerOdcinka]);
				
				strategia.numerPretaWKtorymJestOdcinekTab[numerOdcinka] = (liczbaKupionychPretow - 1);
				
				strategia.lacznyKoszPretow += dane.cenaPretaTab()
						[NajkrotszyPretDluzszyOdOdcinka(dane.dlugoscOdcinkaTab()[numerOdcinka], dane.dlugoscPretaTab())];
			}
		}
		
		for (int numerPreta = 0; numerPreta < liczbaKupionychPretow; numerPreta++)
		{
			strategia.lacznaDlugoscOdpadow += pozostalaDlugoscPretaTab[numerPreta];
		}
		
	}
	
	public static int NajkrotszyPretDluzszyOdOdcinka(int dlugoscOdcinka, int[] dlugoscPretaTab)
	{
		int numerPreta = 0;
		boolean czyPretDluzszyOdOdcinka = false;
		
		while ((numerPreta < dlugoscPretaTab.length) && (czyPretDluzszyOdOdcinka == false))
		{
			if (dlugoscOdcinka <= dlugoscPretaTab[numerPreta])
			{
				czyPretDluzszyOdOdcinka = true;
			}
			else
			{
				numerPreta++;
			}
		}
		return numerPreta;
	}
}
