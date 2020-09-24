package wczytywanie_danych;

import java.util.Scanner;

public class Dane
{
	private int[] dlugoscPretaTab;
	private int[] cenaPretaTab;
	private int[] dlugoscOdcinkaTab;
	private String strategia;
	
	public Dane()
	{
		Scanner scanner = new Scanner(System.in);
		int iloscPretow = scanner.nextInt();
		
		this.dlugoscPretaTab = new int[iloscPretow];
		this.cenaPretaTab = new int[iloscPretow];
		
		for (int i = 0; i < iloscPretow; i++)
		{
			this.dlugoscPretaTab[i] = scanner.nextInt();
			this.cenaPretaTab[i] = scanner.nextInt();
		}
		
		int iloscOdcinkow = scanner.nextInt();
		
		this.dlugoscOdcinkaTab = new int[iloscOdcinkow];
		
		for (int i = 0; i < iloscOdcinkow; i++)
		{
			this.dlugoscOdcinkaTab[i] = scanner.nextInt();
		}
		
		scanner.nextLine();
		
		this.strategia = scanner.nextLine();
		
		scanner.close();		
	}
	
	public String strategia()
	{
		return strategia;
	}
	
	public int iloscOdcinkow()
	{
		return dlugoscOdcinkaTab.length;
	}
	
	public int iloscPretow()
	{
		return dlugoscPretaTab.length;
	}
	
	public int[] dlugoscPretaTab()
	{
		return dlugoscPretaTab.clone();
	}
	
	public int[] cenaPretaTab()
	{
		return cenaPretaTab.clone();
	}
	
	public int[] dlugoscOdcinkaTab()
	{
		return dlugoscOdcinkaTab.clone();
	}
}