import java.util.Iterator;

public abstract class Lenkeliste<T> implements Liste<T> {
    //første inn er første ut
    int stoerrelse;
    Node forste = null,siste = null; //første og siste i lista

    protected class Node{
        //intitial verdier:
        Node neste = null, forrige = null;
        T data;
        //constructor:
        Node(T data){
            this.data = data;
        }

        void settNeste(Node neste){
            //Setter inn en neste node i lista
            this.neste=neste;
            neste.settForrige(this);
        }

        void settForrige(Node forrige){
            this.forrige=forrige;
        }

        Node hentNeste(){
            return neste;
        }

        Node hentForrige(){
            return forrige;
        }

        T hentData(){
            return data;
        }
    }

    //____DEL_B_____START_____________________________________________

    class ListeIterator implements Iterator<T> {
        Node peker = forste;
        @Override
        public T next() {
            if (forste == null) throw new UgyldigListeindeks(0);

            T svar = peker.hentData();
            peker = peker.hentNeste();
            return svar;
        }

        @Override
        public boolean hasNext() { 
            if (forste == null) throw new UgyldigListeindeks(0);

            /*Node peker = forste;*/
            return peker != null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListeIterator();
    }

    //_____DEL_B___SLUTT_____________________________________________

    @Override
    public int stoerrelse(){
        return stoerrelse;
    }

    @Override
    public void leggTil(T x) {
        stoerrelse++; 
        //Nye elementer legges SIST i listen
        Node nyNode = new Node(x);
        //Hva hvis vi har en tom liste:
        if (forste==null){
            forste = nyNode;
            siste = nyNode;
            return;
        }
        //Ved ikke-tom liste: legger inn bak nåværende siste
        siste.settNeste(nyNode);
        nyNode.settForrige(siste);
        siste = nyNode; //definerer ny siste peker
    }

    @Override
    public T hent() {
        //Hva hvis vi har en tom liste: kan ikke hente element
        if (forste==null){
            throw new UgyldigListeindeks(0);
        }
        //Ved ikke-tom liste: RETURNERER det første elementet i listen
        return forste.hentData();
        //Det skal IKKE fjernes fra listen
    }

    @Override
    public T fjern() {
        if (stoerrelse()==0){//ved tom liste: får ikke lov å fjerne element
            throw new UgyldigListeindeks(0);
        }

        T verdi = forste.hentData();//henter verdi
        //FJERNER det første elementet i listen og RETURNERER det
        
        if (stoerrelse()==1){
            forste = null;
            siste = null;
        }
        else{
            forste = forste.hentNeste(); //fjerner første i lista
        }
        stoerrelse--;
        return verdi;//returnerer verdi
    }

    @Override
    public String toString(){
        //går gjennom liste, legger til hvert element i svar streng
        String svar = "";
        if (forste!=null){
            Node node = forste;
            while(node.hentNeste()!=null){
                svar += node.hentData()+"\n\n";
                node = node.hentNeste();
            }
            svar += node.hentData();
        }
        return svar;
    }
}
