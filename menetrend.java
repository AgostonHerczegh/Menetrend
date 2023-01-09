import java.io.*;
import java.util.Scanner;
                //----------------------------------------------------------------------------------------------------
                //    Próbáltam a lehető legtöbb dolgot kikommentelni hogy minél átláthatóbb legyen, remélem megteszi
                //----------------------------------------------------------------------------------------------------
public class menetrend {
    public static void main(String[] args) throws IOException {
            System.out.println("1. feladat");
            BufferedReader beolv = new BufferedReader(new FileReader("vonat.txt")); // beolvom a vonat.txt-t
            int[] von = new int[1000];
            int[] all = new int[1000];
            int[] ora = new int[1000];
            int[] perc = new int[1000];
            String[] ie = new String[1000]; //indulás vagy érkezés
            int db = 0;
            String sor;
            String[] dbSor;
            while
            ((sor = beolv.readLine()) != null)

            {
                dbSor = sor.split("\t");    //sorokat szétválasztja a program a tabulátorok mentén
                db++;
                von[db - 1] = Integer.parseInt(dbSor[0]); //vonatok, állomások, órák, perc és indulások/érkezések tömbjébe eltárolom az adatokat
                all[db - 1] = Integer.parseInt(dbSor[1]);
                ora[db - 1] = Integer.parseInt(dbSor[2]);
                perc[db - 1] = Integer.parseInt(dbSor[3]);
                ie[db - 1] = dbSor[4];
            }

        beolv.close();
        System.out.println("Adatok feldarabolva és tömbben eltárolva");
        System.out.println("\nMásodik feladat");
        int vondb = von[db - 1];    //a vonatok száma
        int vegall = all[db - 1];   //az utolsó állomást számolja
        System.out.println("Az állomások száma összesen: " + (vegall + 1));
        System.out.println("A vonatok száma összesen: " + vondb);
        System.out.println("\n3. feladat");
        int akterk = -1;    //aktuális keresett állomás
        int aktvar = -1;    //aktuális vonat száma
        int maxvar = -1;    //maximális vonat száma
        int maxvon = -1;    //maximális vonat száma
        int maxall = -1;    //maximális állomás száma
        int i, j, k;        //ciklus változók

        for (i = 1; i <= vondb; i++)
        {
            for (j = 1; j <= vegall; j++)
            {
                for (k = 1; k <= db; k++)
                {
                    if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("E"))

                    {
                        akterk = 60 * ora[k - 1] + perc[k - 1]; //ez pedig az aktuális keresett állomás
                    }

                    if

                    (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("I"))

                    {
                        aktvar = 60 * ora[k - 1] + perc[k - 1] - akterk;

                        if (aktvar > maxvar)

                        {
                            maxvon = i;
                            maxall = j;
                            maxvar = aktvar;
                        }
                    }
                }
            }
        }
        System.out.println("A(z) " + maxvon + ". vonat a(z) " + maxall + ". állomáson "
                + maxvar + " percet állt.");
        System.out.println("\nnegyedik feladat");
        Scanner sc1 = new Scanner(System.in);
        System.out.print("add meg egy vonat azonosítóját! ");
        int aktvon = sc1.nextInt();
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Add meg egy időpontot! \b (Előszőr órát, szóköz, utána percet) ");
        String aktido = sc2.nextLine();                                 // bekérem az időt, ebből viszem tovább
        int aktora = Integer.parseInt(aktido.split(" ")[0]);
        int aktperc = Integer.parseInt(aktido.split(" ")[1]);
        sc1.close();
        sc2.close();
        System.out.println("\n5. feladat");
        int menetido = 60 * 2 + 22;     //142 perc --> 2 óra 22 perc
        int aktind = 0;                //aktuális indulás
        akterk = 0;                    //aktuális érkezés
        int aktmenetido;
        for (i = 1; i <= db; i++)
        {
            if (von[i - 1] == aktvon && all[i - 1] == 0)

            {
                aktind = 60 * ora[i - 1] + perc[i - 1];
            }

            if (von[i - 1] == aktvon && all[i - 1] == vegall)

            {
                akterk = 60 * ora[i - 1] + perc[i - 1];
            }
        }
        aktmenetido = akterk - aktind;
        if (menetido > 0)
        {
            if (aktmenetido < menetido)

            {
                System.out.println("A(z) " + aktvon + ".vonat útja " + (menetido - aktmenetido)
                        + " perccel rövidebb volt a tervezettnél.");
            }

            else if (aktmenetido > menetido) {
                System.out.println("A(z) " + aktvon + ". vonat útja " + (aktmenetido - menetido)
                        + " perccel rövidebb volt a tervezettnél.");
            }

            else
            {
                System.out.println("A " + aktvon + ". vonat útja a tervezett ideig tartott.");
            }

        }
        else
        {
            System.out.println("Nincs ilyen vonat(Hiba).");
        }
        System.out.println("\n6. feladat");
        String cim = "halad" + aktvon + ".txt";
        PrintWriter kiir = new PrintWriter(new FileWriter(cim)); //kiírás a halad.txt fájlba
        for (i = 1; i <= db; i++) {
            if (von[i - 1] == aktvon && ie[i - 1].equals("E"))
            {
                kiir.println(all[i - 1] + ".állomás: " + ora[i - 1] + ":" + perc[i - 1]);
            }
        }
        System.out.println("A halad " + aktvon + ".txt fájl kiírása befejezodott. (.txt file a mappában)");
        kiir.close();
        System.out.println("\nHetedik feladat");
        int aktidoertek = 60 * aktora + aktperc; //aktuális időpont kiszámolása
        akterk = -1;    //aktuális érkezés
        aktind = -1;    //aktuális indulás
        for (i = 1; i <= vondb; i++)
        {
            for (j = 1; j <= vegall; j++)
            {
                for (k = 1; k <= db; k++)
                {
                    if (von[k - 1] == i && all[k - 1] == j - 1 && ie[k - 1].equals("I"))
                    {
                        aktind = 60 * ora[k - 1] +perc[k - 1];
                    }
                    if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("E"))
                    {
                        akterk = 60 * ora[k - 1] + perc[k - 1];
                        if
                        (aktind < aktidoertek && akterk > aktidoertek)
                        {
                            System.out.println("A " + i + ". vonat a "
                                    + (j - 1) + ". és a " + (j)
                                    + ". állomás között ment.");
                        }
                    }
                    if (von[k - 1] == i && all[k - 1] == j && ie[k - 1].equals("I")) {
                        aktind = 60 * ora[k - 1] + perc[k - 1];
                        if (akterk <= aktidoertek && aktind >= aktidoertek) {
                            System.out.println("A " + i + ". vonat a " + j
                                    + ". állomáson állt.");
                        }
                        //---------------------------------------------------------------------------------
                        //                              Program vége
                        // Note: A hetedik feladatnál a txt-be való kiiratás nem sikerült teljesen
                        // (csak olyankor ad vissza eredményt amikor olyan időt kérdezünk le amikor a vonat már beért az állomásra.
                        // Pl.:Az egyes vonat reggel indult ezért ha este 8-ra keresek kapok eredményt.)
                        //---------------------------------------------------------------------------------
                    }
                }
            }
        }
    }

}