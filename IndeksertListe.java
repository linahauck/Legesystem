public class IndeksertListe<T> extends Lenkeliste<T> {

 
    public void leggTil(int pos, T x) {
        //gyldig for: 0<=pos<=stoerrelse()
        //throw error: pos<0 eller pos>stoerrelse()     
        if (pos<0 || pos>stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        //legger til på spesifik posisjon
        //alle elementer forskyves med ett hakk bakover
        Node nyNode = new Node(x);
        //Ved tom lste: pos=0=stoerrelse()
        if(siste==null){
            forste = nyNode;
            siste = nyNode;
            stoerrelse++;
            //legger den inni if tester for å ikke få feil senere(*)
            return;
        }
        if(pos==0){
            //legg til forran nåværende første
            forste.settForrige(nyNode);
            nyNode.settNeste(forste);
            forste = nyNode;
            stoerrelse++;
            return;
        }
        if(pos==stoerrelse()){ // (*) denne ville ellers blitt feil
            //legg til bak siste
            siste.settNeste(nyNode);
            nyNode.settForrige(siste);
            siste = nyNode;
            stoerrelse++; 
            return;
        }
        if(pos==stoerrelse()-1){
            //legger til foran siste
            siste.hentForrige().settNeste(nyNode);;
            nyNode.settForrige(siste.hentForrige());
            nyNode.settNeste(siste);
            stoerrelse++;
            return;
        }
        //ellers: går til posisjon
        Node peker = forste;
        stoerrelse++;
        for (int i=0; i<pos-1; i++){
            peker = peker.hentNeste();//beveger oss til neste node i lista
        } //stopper i noden før posisjon
        nyNode.settNeste(peker.hentNeste());
        peker.settNeste(nyNode);//plasserer node i posisjon
        nyNode.settForrige(peker);
        //nyNode.settForrige(peker);
    }

    public void sett(int pos, T x) {
        //gyldig for: 0<=pos<stoerrelse()
        //throw error: pos<0 eller pos>=stoerrelse()
        if (pos<0 || pos>=stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        //erstatter element i posisjonen
        Node nyNode = new Node(x);
        /*Ved tom lste: så er pos=0=stoerrelse(), 
        og vi får erroren over fordi pos=stoerrelse().
        Tregner altså ikke noe if test for dette
        */
        if(pos==0){
            //setter inn som første
            forste.settForrige(nyNode);
            nyNode.settNeste(forste.hentNeste());
            forste = nyNode;
            return;
        }
        if(pos==stoerrelse()-1){
            //siste node
            Node peker = siste.hentForrige();
            peker.settNeste(nyNode);
            nyNode.settForrige(peker);
            siste=nyNode;
        }
        Node peker = forste;
        for (int i=0; i<pos-1; i++){
            peker = peker.hentNeste();//beveger oss til neste node i lista
        } //stopper i noden før posisjon
        nyNode.settNeste(peker.hentNeste().hentNeste());
        peker.settNeste(nyNode);
        nyNode.settForrige(peker);
        
    }

    public T hent(int pos) {
        //gyldig for: 0<=pos<stoerrelse()
        //throw error: pos<0 eller pos>=stoerrelse()
        if (pos<0 || pos>=stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        //henter (uten å slette) elementet i posisjonen
        if(pos==0){
            //henter første
            return forste.hentData();
        }
        if(pos==stoerrelse()-1){
            //henter siste
            return siste.hentData();
        }
        Node peker = forste;
        for (int i = 0; i<pos; i++){
            peker = peker.hentNeste();
        }
        return peker.hentData();
    }

    public T fjern(int pos) {
        //gyldig for: 0<=pos<stoerrelse()
        //throw error: pos<0 eller pos>=stoerrelse()
        if (pos<0 || pos>=stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }

        else if(pos==0) fjern();
        //fjerner og returnerer elementet i posisjonen
        //alle elemeneter bak forskyves ett hakk frem
        stoerrelse--;
        Node peker = forste;
        for (int i = 0; i<pos-1; i++){
            peker = peker.hentNeste();
        }
        T verdi = peker.hentNeste().hentData();//henter verdi
        peker.neste = peker.hentNeste().hentNeste();//fjerner node
        return verdi;//returnerer verdi
    }
}
