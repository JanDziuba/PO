package strategie;

import wczytywanie_danych.Dane;
import wypisywanie_danych.WypisywanieDanych;

public class StrategiaEkologiczna
{
		
	public static void wykonajStrategieEkologiczna(Dane dane)
	{
		Strategia strategia = new Strategia(dane);
		
		Strategia najlepszaStrategia = new Strategia(dane);
		
		najlepszaStrategia.lacznaDlugoscOdpadow = Long.MAX_VALUE;
		
		wykonajOptymalnyPodzial(dane, strategia, najlepszaStrategia);
		
		WypisywanieDanych.wypiszRozwiazanie(dane, najlepszaStrategia);
	}
	
	public static void wykonajOptymalnyPodzial(Dane dane, Strategia strategia, Strategia najlepszaStrategia)
	{
		generujWszystkiePodzialy(dane, strategia, najlepszaStrategia);
		// Tworzy wszystkie podziały odcinków na grupy które będą w tym samym pręcie 
		//i porównuje z najlepszym dotychczasowym podziałem
	}
	
	public static void generujWszystkiePodzialy(Dane dane, Strategia strategia, Strategia najlepszaStrategia)
	{
		int indeksOdcinka = 0;
		int liczbaKupionychPretow = 0;
		int numerPretaWKtorymJestOdcinek = 0;
		long[] sumaDlugosciOdcinkowWPrecieTab = new long[dane.iloscOdcinkow()];	
		
		generujWszystkiePodzialy(dane, strategia, najlepszaStrategia, indeksOdcinka, numerPretaWKtorymJestOdcinek,
				(liczbaKupionychPretow + 1), sumaDlugosciOdcinkowWPrecieTab);
		
	}
	
	public static void generujWszystkiePodzialy(Dane dane, Strategia strategia,
			Strategia najlepszaStrategia, int indeksOdcinka, int numerPretaWKtorymJestOdcinek,
			int liczbaKupionychPretow, long[] sumaDlugosciOdcinkowWPrecieTab)
	{
		strategia.numerPretaWKtorymJestOdcinekTab[indeksOdcinka] = numerPretaWKtorymJestOdcinek;
		
		long[] sumaDlugosciOdcinkowWPrecieTabKopia = sumaDlugosciOdcinkowWPrecieTab.clone();
		
		sumaDlugosciOdcinkowWPrecieTabKopia[numerPretaWKtorymJestOdcinek] += dane.dlugoscOdcinkaTab()[indeksOdcinka];
		
		if (sumaDlugosciOdcinkowWPrecieTabKopia[numerPretaWKtorymJestOdcinek] <= dane.dlugoscPretaTab()[dane.iloscPretow() - 1])
		{
			if (indeksOdcinka < (dane.iloscOdcinkow() - 1))
			{
				for (numerPretaWKtorymJestOdcinek = 0; numerPretaWKtorymJestOdcinek < liczbaKupionychPretow; numerPretaWKtorymJestOdcinek++)
				{
					generujWszystkiePodzialy(dane, strategia, najlepszaStrategia, (indeksOdcinka + 1), numerPretaWKtorymJestOdcinek,
							liczbaKupionychPretow, sumaDlugosciOdcinkowWPrecieTabKopia);
				}
				generujWszystkiePodzialy(dane, strategia, najlepszaStrategia, (indeksOdcinka + 1), liczbaKupionychPretow,
						(liczbaKupionychPretow + 1), sumaDlugosciOdcinkowWPrecieTabKopia);
			}
			
			else if(indeksOdcinka == (dane.iloscOdcinkow() - 1))
			{
				kupOdpowiedniePretyDlaPodzialu(dane, strategia, najlepszaStrategia, sumaDlugosciOdcinkowWPrecieTabKopia);
			}
		}
	}
	
	public static void kupOdpowiedniePretyDlaPodzialu(Dane dane, Strategia strategia, Strategia najlepszaStrategia,
			long[] sumaDlugosciOdcinkowWPrecieTab)
	{
		Odpad odpad = new Odpad();
		Pret pret = new Pret();
		int numerPreta = 0;
		
		strategia.lacznaDlugoscOdpadow = 0;
		strategia.lacznyKoszPretow = 0;
		
		for (int i = 0; i < dane.iloscOdcinkow(); i++)
		{
			strategia.dlugoscNtegoKupionegoPretaTab[i] = 0;
		}
		
		while (numerPreta < dane.iloscOdcinkow())
		{
			kupOdpowiedniPret(numerPreta, dane, strategia, odpad, pret, sumaDlugosciOdcinkowWPrecieTab);
			
			strategia.lacznaDlugoscOdpadow += odpad.dlugoscOdpadu();
			strategia.lacznyKoszPretow += pret.kosztPreta();
			numerPreta++;
		}
		
		if (najlepszaStrategia.lacznaDlugoscOdpadow > strategia.lacznaDlugoscOdpadow)
		{
			najlepszaStrategia.lacznaDlugoscOdpadow = strategia.lacznaDlugoscOdpadow;
			najlepszaStrategia.lacznyKoszPretow = strategia.lacznyKoszPretow;
			
			for (int i = 0; i < dane.iloscOdcinkow(); i++)
			{
				najlepszaStrategia.dlugoscNtegoKupionegoPretaTab[i] = strategia.dlugoscNtegoKupionegoPretaTab[i];
				najlepszaStrategia.numerPretaWKtorymJestOdcinekTab[i] = strategia.numerPretaWKtorymJestOdcinekTab[i];
			}
		}
	}
	
	public static void kupOdpowiedniPret(int numerPreta, Dane dane, Strategia strategia, Odpad odpad, Pret pret,
			long[] sumaDlugosciOdcinkowWPrecieTab)
	{
		int indeksPreta = 0;
		
		if (sumaDlugosciOdcinkowWPrecieTab[numerPreta] == 0)
		{
			strategia.dlugoscNtegoKupionegoPretaTab[numerPreta] = 0;
			odpad.dlugoscOdpadu(0);
			pret.kosztPreta(0);
		}
		else
		{
			for (indeksPreta = 0; (dane.dlugoscPretaTab()[indeksPreta] < sumaDlugosciOdcinkowWPrecieTab[numerPreta]); indeksPreta++);
			
			strategia.dlugoscNtegoKupionegoPretaTab[numerPreta] = dane.dlugoscPretaTab()[indeksPreta];
			odpad.dlugoscOdpadu((dane.dlugoscPretaTab()[indeksPreta] - (int)sumaDlugosciOdcinkowWPrecieTab[numerPreta]));
			pret.kosztPreta(dane.cenaPretaTab()[indeksPreta]);

		}
	}
}
