package wypisywanie_danych;

import strategie.Strategia;
import wczytywanie_danych.Dane;

public class WypisywanieDanych
{
	public static void wypiszRozwiazanie(Dane dane, Strategia strategia)
	{
		System.out.println(strategia.lacznyKoszPretow());
		System.out.println(strategia.lacznaDlugoscOdpadow());
		
		for (int numerPreta = 0; numerPreta < dane.iloscOdcinkow(); numerPreta++)
		{
			if (strategia.dlugoscNtegoKupionegoPretaTab()[numerPreta] != 0)
			{
				System.out.print(strategia.dlugoscNtegoKupionegoPretaTab()[numerPreta]);
				
				for (int numerOdcinka = 0; numerOdcinka < dane.iloscOdcinkow(); numerOdcinka++)
				{
					if (strategia.numerPretaWKtorymJestOdcinekTab()[numerOdcinka] == numerPreta)
					{
						System.out.print(" " + dane.dlugoscOdcinkaTab()[numerOdcinka]);
					}
				}
				System.out.print(System.lineSeparator());
			}			
		}
	}
}
