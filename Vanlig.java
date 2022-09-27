public class Vanlig extends Legemiddel {
    //subklasse til Legemiddel
    Vanlig(String navn, int pris, double virkestoff){
        //arver/henter verdiene fra superklassen Legemiddel
        super(navn, pris, virkestoff);
    }

    @Override
    public String toString(){
        String string = "VANLIG Legemiddel: "+hentNavn()+"\n   ID: "+hentId();
        string+= "\n   Pris: "+hentPris()+"kr\n   Virkestoff: "+hentVirkestoff()+"mg";
        return string;
    }

    @Override
    String hentType(){
        return "vanlig";
    }

    
}
