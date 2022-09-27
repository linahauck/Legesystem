abstract class Legemiddel{
    //superklasse Legemiddel
    protected String navn;
    protected int pris;
    protected double virkestoff;

    static int count=1;
    //count er en teller som skal gi unik id=1,2,... til hvert nye legemiddel objekt 
    int id;
    

    Legemiddel(String navn, int pris, double virkestoff){
        this.navn=navn;
        this.pris=pris;
        this.virkestoff=virkestoff;
        id = count; //setter id lik count for å gi den unike iden
        count++; //count+1
    }

    
    int hentId(){
        return id;
    }

    String hentNavn(){
        return navn;
    }

    int hentPris(){
        return pris;
    }

    double hentVirkestoff(){
        return virkestoff;
    }

    void settNyPris(int nyPris){
        pris=nyPris;
    }

    String hentType(){
        return "";
    }

    String skrivStyrke(){
        return "";
    }

    public abstract String toString();
}


    
