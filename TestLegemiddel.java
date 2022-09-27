public class TestLegemiddel {

    //tester ID:
    public static boolean testId(Legemiddel legemiddel, int forventetId) {
        return legemiddel.hentId() == forventetId;
    }
    //tester Navn:
    public static boolean testNavn(Legemiddel legemiddel, String forventetNavn){
        return legemiddel.hentNavn() == forventetNavn;
    }
    //tester Pris:
    public static boolean testPris(Legemiddel legemiddel, int forventetPris) {
        return legemiddel.hentPris() == forventetPris;
    }
    //tester Virkestoff:
    public static boolean testVirkestoff(Legemiddel legemiddel, double forventetVirkestoff) {
        return legemiddel.hentVirkestoff() == forventetVirkestoff;
    }
    //tester ny pris:
    public static boolean testNyPris(Legemiddel legemiddel, int forventetNyPris) {
        legemiddel.settNyPris(forventetNyPris); //setter ny pris
        return legemiddel.hentPris() == forventetNyPris; //tester om pris nå er lik nyPris.
    }
    //tester Narkotisk styrke:
    public static boolean testNarkotiskStyrke(Narkotisk legemiddel, int forventetStyrke) {
        return legemiddel.hentNarkotiskStyrke() == forventetStyrke;
    }
    public static boolean testVanedannendeStykre(Vanedannende legemiddel, int forventetStyrke) {
        return legemiddel.hentVanedannendeStyrke() == forventetStyrke;
    }
    
    public static void main(String[] args) {
        Narkotisk narko = new Narkotisk("Narko", 500, 50, 9);
        Vanedannende morfin = new Vanedannende("Morfin", 150, 50, 4);
        Vanlig paracet = new Vanlig("Paracet", 99, 500);
        
        System.out.println("NARKOTISK");
        //tester første objektet
        System.out.println(testId(narko, 1)); //id
        System.out.println(testNavn(narko, "Narko")); //navn
        System.out.println(testPris(narko, 500)); //pris
        System.out.println(testVirkestoff(narko, 50)); //virkestoff
        System.out.println(testNyPris(narko, 400));//ny pris
        System.out.println(testNarkotiskStyrke(narko, 9));//narkotisk styrke
        System.out.println(narko.toString()); //toString()
        
        System.out.println("\nVANEDANNENDE");
        //tester det andre objektet
        System.out.println(testId(morfin, 2));
        System.out.println(testNavn(morfin, "Morfin"));
        System.out.println(testPris(morfin, 150));
        System.out.println(testVirkestoff(morfin, 50));
        System.out.println(testNyPris(morfin, 200));
        System.out.println(testVanedannendeStykre(morfin, 4));
        System.out.println(morfin.toString());//toString()

        System.out.println("\nVANLIG");
        //tester det tredje objektet
        System.out.println(testId(paracet, 3));
        System.out.println(testNavn(paracet, "Paracet"));
        System.out.println(testPris(paracet, 99));
        System.out.println(testVirkestoff(paracet, 500));
        System.out.println(testNyPris(paracet, 150));
        System.out.println(paracet.toString());//toString
    }
    
}
