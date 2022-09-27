public class Vanedannende extends Legemiddel {
    //subklasse til Legemiddel

    //har i tillegg til super sine verdier også en egen: styrke
    int styrke;

    Vanedannende(String navn, int pris, double virkestoff, int styrke){
        //henter fra super
        super(navn, pris, virkestoff);
        this.styrke=styrke;
    }

    int hentVanedannendeStyrke(){
        return styrke;
    }

    @Override
    String skrivStyrke(){
        return Integer.toString(styrke);
    }

    //overskriver toString metode fra super
    //fint med @Override for å f.eks sjekke at metode navn er skrevet riktg.
    @Override
    public String toString(){
        String string = "VANEDANNENDE Legemiddel: "+hentNavn()+"\n   ID: "+hentId();
        string+= "\n   Pris: "+hentPris()+"kr\n   Virkestoff: "+hentVirkestoff();
        string+= "mg\n   Vanedannende Styrke: "+hentVanedannendeStyrke();
        return string;
    }

    @Override
    String hentType(){
        return "vanedannende";
    }

}
