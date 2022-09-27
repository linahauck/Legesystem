public class TestResepter {
    
    public static boolean testFarge(Resept resept, String forventet) {
        return (resept.farge()==forventet);
    }
    public static boolean testPrisAaBetale(Resept resept, int forventet){
        return (resept.prisAaBetale()==forventet);
    }
    public static void main(String[] args) {
        //pasienter:
        Pasient per = new Pasient("Per", "12623463");
        //legemidler:
        Legemiddel paracet = new Vanlig("Paracet", 99, 500);
        Legemiddel morfin = new Vanedannende("Morfin", 199, 50, 4);
        Legemiddel pPille = new Vanlig("P-Pille", 110, 10);
        //lege:
        Lege joahnsen = new Lege("Johansen");

        //resepter:
        HvitResept millaResept = new MilResept(morfin, joahnsen, per);
        HvitResept pResept = new PResept(paracet, joahnsen, per, 2);
        HvitResept pResept2 = new PResept(pPille, joahnsen, per,2);
        BlaaResept bResept = new BlaaResept(morfin, joahnsen, per, 4);

        //tester farge:
        System.out.println(testFarge(millaResept, "hvit"));
        System.out.println(testFarge(bResept, "blaa"));

        //tester pris å betale:
        System.out.println(testPrisAaBetale(millaResept, 0));
        System.out.println(testPrisAaBetale(pResept, 0));
        System.out.println(testPrisAaBetale(pResept2, 2));
        System.out.println(testPrisAaBetale(bResept, 50));

        //toString()
        System.out.println("\n"+millaResept.toString());

        //tester bruk
        System.out.println("\n"+millaResept.bruk());
        System.out.println("\n"+millaResept.toString());

    }
    
}
