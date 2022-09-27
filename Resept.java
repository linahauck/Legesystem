//___DEL_C__________________________________________

public abstract class Resept {
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    Pasient pasient;
    protected int reit;
    static int count=1;
    int id;
    

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel=legemiddel;
        this.utskrivendeLege=utskrivendeLege;
        this.pasient=pasient;
        this.reit=reit;
        this.id = count;
        count++;
    }

    int hentId(){
        return id;
    }

    Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    Lege hentLege(){
        return utskrivendeLege;
    }

    int hentPasientId(){
        return pasient.hentId();
    }

    int hentReit(){
        return reit;
    }

    abstract String skrivType();

    boolean bruk(){
        //Forsøker å bruke resepten én gang
        //false: resepten er oppbrukt
        if (reit>0){
            reit--;
            return true;
        }
        return false;
    }

    abstract public String farge();//Returnerer reseptens farge: Blå eller Hvit

    abstract public int prisAaBetale();//Returnerer prisen 

    String reseptNavn() {
        return "RESEPT";
    }

    public String toString(){
        String string = " Resept type: "+reseptNavn()+"\n    Resept for: "+hentLegemiddel().hentNavn();
        string+= "\n    Utskrevet av Lege: "+hentLege().hentNavn()+"\n    Resept ID: "+hentId();
        string+= "\n    Skrevet ut til: "+ pasient.hentNavn() + "\n    Pasient ID: "+hentPasientId()+
                 "\n    Reit: "+hentReit();
        return string;
    }
}


