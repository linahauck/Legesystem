
public class Narkotisk extends Legemiddel {
    int styrke;

    Narkotisk(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke=styrke;
    }

    int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    String skrivStyrke(){
        return Integer.toString(styrke);
    }

    @Override
    public String toString(){
        String string = "NARKOTISK Legemiddel: "+hentNavn()+"\n   ID: "+hentId();
        string+= "\n   Pris: "+hentPris()+"kr\n   Virkestoff: "+hentVirkestoff();
        string+= "mg\n   Narkotisk Styrke: "+hentNarkotiskStyrke();
        return string;
    }
    
    @Override
    String hentType(){
        return "narkotisk";
    }
}
