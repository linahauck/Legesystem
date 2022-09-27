public class Hovedprogramm {
    public static void main(String[] args) {
        //pasienter:
        Pasient per = new Pasient("Per", "12623463");

        //legemidler:
        Legemiddel narko = new Narkotisk("Narko", 500, 50, 9);
        Legemiddel morfin = new Vanedannende("Morfin", 150, 50, 4);
        Legemiddel pPille = new Vanlig("P-Pille", 110, 10);

        //Leger:
        Lege haug = new Lege("Haug");
        Lege johansen = new Spesialist("Johansen", "K32");

        //Resept:
        HvitResept millaResept = new MilResept(morfin, johansen, per);
        HvitResept pResept = new PResept(pPille, johansen, per, 2);
        BlaaResept bResept = new BlaaResept(morfin, johansen, per, 4);

        System.out.println("LEGEMIDDLER");
        System.out.println(narko.toString()+"\n\n"+morfin.toString()+"\n\n"+pPille.toString());
        System.out.println("\nLEGER");
        System.out.println(haug.toString()+"\n\n"+johansen.toString());
        System.out.println("\nRESEPT");
        System.out.println(millaResept.toString()+"\n\n"+pResept.toString()+"\n\n"+bResept.toString());
        
        
    }
}

/*
Terminal>javac *.java && java Hovedprogramm

LEGEMIDDLER
Legemiddel: Narko
ID: 1
Pris: 500kr
Virkestoff: 50.0mg
Narkotisk Styrke: 9

Legemiddel: Morfin
ID: 2
Pris: 150kr
Virkestoff: 50.0mg
Vanedannende Styrke: 4

Legemiddel: P-Pille
ID: 3
Pris: 110kr
Virkestoff: 10.0mg

LEGER
Lege: Haug
Kontroll ID: Har ikke Godkjenning

Lege: Johansen
Kontroll ID: K32

RESEPT
Resept type: HVIT RESEPT: MILITAERRESEPT
Resept for: Morfin
Utskrevet av Lege: Johansen
Resept ID: 1
Pasient ID: 1
Reit: 3

Resept type: HVIT RESEPT: P-RESEPT
Resept for: P-Pille
Utskrevet av Lege: Johansen
Resept ID: 2
Pasient ID: 2
Reit: 2

Resept type: BLAA RESEPT
Resept for: Morfin
Utskrevet av Lege: Johansen
Resept ID: 3
Pasient ID: 2
Reit: 4
*/

