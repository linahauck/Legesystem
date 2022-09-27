import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.NumberFormatException;

public class LegeSystem {
    public static void main(String[] args) {
       
    
//______________E1____________start___________________________________________
        // Åpne datafilen:
        Scanner fil = null;
        try {
            fil = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR]: Filen med filnavn " + args[0] + " finnes ikke");
            System.exit(1);
        }

        String linje = "";
        IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
        IndeksertListe<Legemiddel> legemideListe = new IndeksertListe<>();
        Prioritetskoe<Lege> legeListe = new Prioritetskoe<>();
        

    //--------E6------
        IndeksertListe<Lege> legeBruktNarkotisk= new IndeksertListe<>();
        IndeksertListe<Pasient> pasientBruktNarkotisk = new IndeksertListe<>();
        int bruktReseptVanedannende = 0;
        int bruktReseptNarkotisk = 0;
    //------------------

        while (fil.hasNextLine()){
            linje = fil.nextLine();

            if (linje.contains("Pasienter")){ 
                //da er vi på neste objekt type: Pasienter
                while(fil.hasNextLine()){
                    linje = fil.nextLine();
                    if (linje.contains("#"))break;
                    String[] delt = linje.split(",");
                    String navn = delt[0];
                    String foedselsnummer = delt[1];

                    Pasient pasient = new Pasient(navn, foedselsnummer);
                    pasientListe.leggTil(pasient);
                }

            }

            
            if (linje.contains("Legemidler")){
                //da er vi på neste objekt type: Legemidler
                while(fil.hasNextLine()){
                    linje = fil.nextLine();
                    if (linje.contains("#"))break;
                    String[] delt = linje.split(",");
                    String navn = delt[0];
                    String type = delt[1].toLowerCase(); 
                    int pris = Integer.parseInt(delt[2]);
                    double virkestoff = Double.parseDouble(delt[3]);
                    if (delt.length>4){
                        int styrke = Integer.parseInt(delt[4]);

                        if (type.contains("narkotisk")){
                            Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                            legemideListe.leggTil(narkotisk);
                        }

                        else if (type.contains("vanedannende")){
                            Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                            legemideListe.leggTil(vanedannende);
                        }
                    }
                    else{
                        Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                        legemideListe.leggTil(vanlig);
                    }
                }
            }
            if (linje.contains("Leger")){
                //da er vi på neste objekt type: Lege
                while(fil.hasNextLine()){
                    linje = fil.nextLine();
                    if (linje.contains("#"))break;
                    String[] delt = linje.split(",");
                    String navn = delt[0];
                    int kontrollId = Integer.parseInt(delt[1].strip());
                    if (kontrollId == 0){
                        Lege lege = new Lege(navn);
                        legeListe.leggTil(lege);
                    }
                    else {
                        Spesialist spesialist = new Spesialist(navn, Integer.toString(kontrollId));
                        legeListe.leggTil(spesialist);
                    }
                }
                
            }
            if (linje.contains("Resepter")){
                //da er vi på neste objekt type: Resepter
                while(fil.hasNextLine()){
                    linje = fil.nextLine();
                    if (linje.contains("#"))break;
                    String[] delt = linje.split(",");

                    int legemiddelNummer = Integer.parseInt(delt[0]);
                    String legeNavn = delt[1];
                    int pasientId = Integer.parseInt(delt[2]);
                    String type = delt[3];

                    int reit = 0;
                    if (delt.length==5){
                        reit = Integer.parseInt(delt[4]);//if test for tom liste
                    }
                    for (Lege lege : legeListe) {
                        if(lege.hentNavn().equals(legeNavn)){
                            try{
                            if (type.contains("hvit")){
                                lege.skrivHvitResept(legemideListe.hent(legemiddelNummer-1), pasientListe.hent(pasientId-1), reit);
                                if (legemideListe.hent(legemiddelNummer-1) instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                else if (legemideListe.hent(legemiddelNummer-1) instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(lege);
                                    pasientBruktNarkotisk.leggTil(pasientListe.hent(pasientId-1));
                                    }
                            }
                            else if (type.contains("p")){
                                lege.skrivPResept(legemideListe.hent(legemiddelNummer-1), pasientListe.hent(pasientId-1), reit);
                                if (legemideListe.hent(legemiddelNummer-1) instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                else if (legemideListe.hent(legemiddelNummer-1) instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(lege);
                                    pasientBruktNarkotisk.leggTil(pasientListe.hent(pasientId-1));
                                    }
                            }
                            else if(type.contains("blaa")){
                                lege.skrivBlaaResept(legemideListe.hent(legemiddelNummer-1), pasientListe.hent(pasientId-1), reit);
                                if (legemideListe.hent(legemiddelNummer-1) instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                else if (legemideListe.hent(legemiddelNummer-1) instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(lege);
                                    pasientBruktNarkotisk.leggTil(pasientListe.hent(pasientId-1));
                                    }
                            }
                            else if (type.contains("militaer")){
                                lege.skrivMilResept(legemideListe.hent(legemiddelNummer-1), pasientListe.hent(pasientId-1));
                                if (legemideListe.hent(legemiddelNummer-1) instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                else if (legemideListe.hent(legemiddelNummer-1) instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(lege);
                                    pasientBruktNarkotisk.leggTil(pasientListe.hent(pasientId-1));
                                    }
                            }
                        } catch (UlovligUtskrift e){
                            new UlovligUtskrift(lege, legemideListe.hent(legemiddelNummer-1));
                        }
                    } 
                }
                    
                 
            }
            
        }
    }
        
        fil.close();
        

//______________E1____________slutt___________________________________________


//--------------------E2------------Start----------------------------
        Scanner in =new Scanner(System.in); 
        String input =""; 
        while (! input.toUpperCase().equals("SLUTT")){
            hovedMeny();
            
            input = in.nextLine();
//------------E3---------------Start-----------------------------------
            if (input.toUpperCase().equals("O")){
                System.out.println("Oversikt:");
                System.out.println("______________________________________");
                System.out.println("PASIENTER:");
                for (Pasient pasient: pasientListe){
                    System.out.println(" - " +pasient + "\n");
                }
                System.out.println("______________________________________");
                System.out.println("\nLEGER:");
                for (Lege lege : legeListe){
                    System.out.println(" - " +lege + "\n");
                }
                System.out.println("______________________________________");
                System.out.println("\nLEGEMIDLER:");
                for (Legemiddel legemiddel : legemideListe){
                    System.out.println(legemiddel + "\n");
                }
                System.out.println("______________________________________");
                System.out.println("\nRESEPTER:");
                for (Lege lege : legeListe){
                    System.out.println(lege.hentResepter() +"\n");
                    
                }
                System.out.println("______________________________________");
                System.out.println("\n\n For å gå tilbake til Hovedmeny trykk enter. Eller 'SLUTT' for å avslutte");
                input = in.nextLine();
                }
                
//------------E3---------------Slutt-----------------------------------

//----------------E4--------------------Start------------------------------  

            
            if (input.toUpperCase().equals("L")){
                leggTilMeny();
                input = in.nextLine();
        //______1) OPPRETT LEGE:
                if (input.equals("1")){
                    System.out.println("Skriv inn navn: (Eks. Dr. Hansen)");
                    System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                    String navn = in.nextLine();
                    if (navn.toLowerCase().equals("hjem")){
                        hovedMeny();
                    }
                    else if (! navn.contains("Dr. ")){
                        System.out.println("[ERROR]: ugyldig legenavn. Start på nytt.");
                    }
                    
                    else{
                        System.out.println("Kontroll ID: (dersom legen ikke er spesialist legg inn '0')");
                    String kontrollID = in.nextLine();
                    if (kontrollID.equals("0")){
                        Lege lege = new Lege(navn);
                        legeListe.leggTil(lege);
                    }else{
                        Spesialist spesialist = new Spesialist(navn, kontrollID);
                        legeListe.leggTil(spesialist);
                    }
                    }
                    
                }
        //_________2) OPPRETT PASIENT
                else if (input.equals("2")){
                    System.out.println("Skriv inn navn: (Eks. Jens Olsen).");
                    System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                    String navn = in.nextLine();
                    if (navn.toLowerCase().equals("hjem")){
                        hovedMeny();
                    }
                    System.out.println("Skriv inn fodeselsnummer.");
                    System.out.println("\n Skriv 'SLUTT' for å avslutte.");
                    String foedselsnummer = in.nextLine();
                    if (foedselsnummer.toUpperCase().equals("SLUTT")){
                        return;
                    }
                    Pasient pasient = new Pasient(navn, foedselsnummer);
                    pasientListe.leggTil(pasient);
                }
        //_________3) OPPRETT LEGEMIDDEL:
                else if (input.equals("3")){
                    System.out.println("Hvilket legemiddel vil du legge til?");
                    System.out.println("Valg: \n - Narkotisk \n - Vanedannende \n - Vanlig");
                    System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                    String svar = in.nextLine();
                    if (svar.toLowerCase().equals("hjem")){
                        hovedMeny();
                    }
                    if (svar.toLowerCase().equals("narkotisk")){
                        System.out.println("Skriv inn navn på Legemidlet: (Eks. Ibux)");
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String navn = in.nextLine();
                        if (navn.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        System.out.println("Skriv inn pris til Legemidlet: (uten valutta)");
                        int pris = Integer.parseInt(in.nextLine());
                        System.out.println("Skriv inn virkestoff i Legemidlet: (oppgitt i mg)");
                        double virkestoff = Double.parseDouble(in.nextLine());
                        System.out.println("Skriv inn styrken på Legemidlet: (skala fra 0 til 10)");
                        int styrke = Integer.parseInt(in.nextLine());
                        Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                        legemideListe.leggTil(narkotisk);

                    }
                    else if (svar.toLowerCase().equals("vanedannende")){
                        System.out.println("Skriv inn navn på Legemidlet: (Eks. Ibux)");
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String navn = in.nextLine();
                            if (navn.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        System.out.println("Skriv inn pris til Legemidlet: (uten valutta)");
                        int pris = Integer.parseInt(in.nextLine());
                        System.out.println("Skriv inn virkestoff i Legemidlet: (oppgitt i mg)");
                        double virkestoff = Double.parseDouble(in.nextLine());
                        System.out.println("Skriv inn styrken på Legemidlet: (skala fra 0 til 10)");
                        int styrke = Integer.parseInt(in.nextLine());
                        Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                        legemideListe.leggTil(vanedannende);
                    }
                    else if (svar.toLowerCase().equals("vanlig")){
                        System.out.println("Skriv inn navn på Legemidlet: (Eks. Ibux)");
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String navn = in.nextLine();
                            if (navn.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        System.out.println("Skriv inn pris til Legemidlet: (uten valutta)");
                        int pris = Integer.parseInt(in.nextLine());
                        System.out.println("Skriv inn virkestoff i Legemidlet: (oppgitt i mg)");
                        double virkestoff = Double.parseDouble(in.nextLine());
                        Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                        legemideListe.leggTil(vanlig);
                    }
                    else {
                        System.out.println("[ERROR]: ugyldig legemiddel. Start på nytt.\n");
                    }

                }
        //_______4) SKRIV UT RESEPT:
                else if (input.equals("4")){
                    String tryAgain = "NEI";
                    boolean done = false;
                    do{
                    try{
                    System.out.println("Hvilken resept ønsker du å skriv ut?");
                    System.out.println("Valg: Hvit / P / Blaa / Militaer");
                    System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                    String svar = in.nextLine();
                    if (svar.toLowerCase().equals("hjem")){
                        hovedMeny();
                    }
                //_________HVIT:
                    if (svar.toLowerCase().contains("hvit")){
                        System.out.println("Hvilke legemiddel ønsker du å legge til i resepten:");
                        int teller =0;
                        for (Legemiddel legemiddel: legemideListe){
                            System.out.println(teller + " : "+ legemiddel);
                            teller ++;
                        }
                        int svarLegemiddel = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilken pasient skal ha resepten?");
                        int teller2 = 0;
                        for (Pasient pasient:pasientListe){
                            System.out.println(teller2+ " : "+ pasient);
                            teller2 ++;
                        }
                        int svarPasient = Integer.parseInt(in.nextLine());

                        System.out.println("Hvor mange reit skal på resepten?");
                        int svarReit = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilken lege skal skrive ut resepten? Skriv legens navn. Eks: 'Dr. Cox'. Tips: bruk copy & paste. ");
                        for (Lege lege:legeListe){
                            System.out.println("- " +lege);
                        }
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String svarLege = in.nextLine();
                        if (svarLege.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        else if (! svarLege.contains("Dr. ")){
                            System.out.println("[ERROR]: ugyldig legenavn. Start på nytt.\n");
                            return;
                        }
                        Lege legen = null;
                        for (Lege lege:legeListe){
                            if (lege.hentNavn().contains(svarLege)){
                                legen = lege;
                            }
                        }
                        if (legen == null){
                                System.out.println("[ERROR]: Legen finnes ikke i systemet. Start på nytt.\n");
                            
                        }
                        else{
                            Legemiddel legemiddel = legemideListe.hent(svarLegemiddel);
                            Pasient pasient = pasientListe.hent(svarPasient);
                            try {
                                legen.skrivHvitResept(legemiddel, pasient,svarReit);
                    //---------E6------------
                                if (legemiddel instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                if (legemiddel instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(legen);
                                    pasientBruktNarkotisk.leggTil(pasient);
                                }
                    //----------------------
                            } catch (UlovligUtskrift e) {
                                System.out.println("[ERROR]: Legen har ikke tilgang til å skrive ut denne resepten.\n");
                            }
                            
                        }
                        done = true;
                    }
                //_________P: 
                    else if (svar.toUpperCase().equals("P")){
                        System.out.println("Hvilke legemiddel ønsker du å legge til i resepten?");
                        int teller = 0;
                        for (Legemiddel legemiddel: legemideListe){
                            System.out.println(teller + " : "+ legemiddel);
                            teller ++;
                        }
                        int svarLegemiddel = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilken pasient skal ha resepten?");
                        int teller2 = 0;
                        for (Pasient pasient:pasientListe){
                            System.out.println(teller2+ " : "+ pasient);
                            teller2 ++;
                        }
                        int svarPasient = Integer.parseInt(in.nextLine());
                        
                        System.out.println("Hvor mange reit skal på resepten?");
                        int svarReit = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilken lege skal skrive ut resepten? Skriv legens navn. Eks: 'Dr. Cox'. Tips: bruk copy & paste.");
                        for (Lege lege:legeListe){
                            System.out.println("- " +lege);
                        }
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String svarLege = in.nextLine();
                        if (svarLege.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        else if (! svarLege.contains("Dr. ")){
                            System.out.println("[ERROR]: ugyldig legenavn. Start på nytt.\n");
                            return;
                        }
                        Lege legen = null;
                        for (Lege lege:legeListe){
                            if (lege.hentNavn().contains(svarLege)){
                                legen = lege;
                            }
                        }
                        if (legen == null){
                                System.out.println("[ERROR]: Legen finnes ikke i systemet. Start på nytt.\n");
                            
                        }
                        else{
                            Legemiddel legemiddel = legemideListe.hent(svarLegemiddel);
                            Pasient pasient = pasientListe.hent(svarPasient);
                            try {
                                legen.skrivPResept(legemiddel, pasient,svarReit);
                                if (legemiddel instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                if (legemiddel instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(legen);
                                    pasientBruktNarkotisk.leggTil(pasient);
                                }
                            } catch (UlovligUtskrift e) {
                                System.out.println("[ERROR]: Legen har ikke tilgang til å skrive ut denne resepten.\n");
                            }
                        }
                        done = true;
                    }
                //_______BLAA:
                    else if (svar.toLowerCase().equals("blaa")){

                        System.out.println("Hvilket legemiddel ønsker du å legge til resepten?");
                        int teller =0;
                        for (Legemiddel legemiddel: legemideListe){
                            System.out.println(teller + " : "+ legemiddel);
                            teller ++;
                        }
                        int svarLegemiddel = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilke pasient skal ha resepten?");
                        int teller2 = 0;
                        for (Pasient pasient:pasientListe){
                            System.out.println(teller2+ " : "+ pasient);
                            teller2 ++;
                        }
                        int svarPasient = Integer.parseInt(in.nextLine());
                        
                        System.out.println("Hvor mange reit skal på resepten?");
                        int svarReit = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilken lege skal skrive ut resepten? Skriv legens navn. Eks: 'Dr. Cox'. Tips: bruk copy & paste.");
                        for (Lege lege:legeListe){
                            System.out.println("- " +lege);
                        }
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String svarLege = in.nextLine();
                        if (svarLege.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        else if (! svarLege.contains("Dr. ")){
                            System.out.println("[ERROR]: ugyldig legenavn. Start på nytt.\n");
                            return;
                        }
                        Lege legen = null;
                        for (Lege lege:legeListe){
                            if (lege.hentNavn().contains(svarLege)){
                                legen = lege;
                            }
                        }
                        if (legen == null){
                                System.out.println("[ERROR]: Legen finnes ikke i systemet. Start på nytt.\n");
                            
                        }
                        else{
                            Legemiddel legemiddel = legemideListe.hent(svarLegemiddel);
                            Pasient pasient = pasientListe.hent(svarPasient);
                            try {
                                legen.skrivBlaaResept(legemiddel, pasient,svarReit);
                                if (legemiddel instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                if (legemiddel instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(legen);
                                    pasientBruktNarkotisk.leggTil(pasient);
                                }
                            } catch (UlovligUtskrift e) {
                                System.out.println("[ERROR]: Legen har ikke tilgang til å skrive ut denne resepten.\n");
                            }
                        }
                        done = true;
                    }
                //_______MILITAER:
                    else if (svar.toLowerCase().equals("militaer")){

                        System.out.println("Hvilke legemiddel ønsker du å legge til i resepten?");
                        int teller =0;
                        for (Legemiddel legemiddel: legemideListe){
                            System.out.println(teller + " : "+ legemiddel);
                            teller ++;
                        }
                        int svarLegemiddel = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilke pasient skal ha resepten:");
                        int teller2 = 0;
                        for (Pasient pasient:pasientListe){
                            System.out.println(teller2+ " : "+ pasient);
                            teller2 ++;
                        }
                        int svarPasient = Integer.parseInt(in.nextLine());

                        System.out.println("Hvilken lege skal skrive ut resepten: skriv legens navn. Eks: 'Dr. Cox'. Tips: bruk copy & paste.");
                        for (Lege lege:legeListe){
                            System.out.println("- " +lege);
                        }
                        System.out.println("\nSkriv 'hjem' for aa gaa tilbake til HOVEDMENY.");
                        String svarLege = in.nextLine();
                        if (svarLege.toLowerCase().equals("hjem")){
                            hovedMeny();
                        }
                        else if (! svarLege.contains("Dr. ")){
                            System.out.println("[ERROR]: ugyldig legenavn. Start på nytt.\n");
                            return;
                        }
                        Lege legen = null;
                        for (Lege lege:legeListe){
                            if (lege.hentNavn().contains(svarLege)){
                                legen = lege;
                            }
                        }
                        if (legen == null){
                                System.out.println("[ERROR]: Legen finnes ikke i systemet. Start på nytt.\n");
                            
                        }
                        else{
                            Legemiddel legemiddel = legemideListe.hent(svarLegemiddel);
                            Pasient pasient = pasientListe.hent(svarPasient);
                            try {
                                legen.skrivMilResept(legemiddel, pasient);
                                if (legemiddel instanceof Vanedannende ){
                                    bruktReseptVanedannende ++;}
                                if (legemiddel instanceof Narkotisk ){
                                    bruktReseptNarkotisk++;
                                    legeBruktNarkotisk.leggTil(legen);
                                    pasientBruktNarkotisk.leggTil(pasient);
                                }
                            } catch (UlovligUtskrift e) {
                                System.out.println("[ERROR]: Legen har ikke tilgang til å skrive ut denne resepten.\n");
                            }
                        }
                        done = true;
                    }
                    else{
                        System.out.println("[ERROR]: Resepten er ugyldig. Start på nytt.\n");
                        
                    }
                } catch (UgyldigListeindeks e){
                    System.out.println("[ERROR] : Pasient finnes ikke. Proev igjen? JA/NEI");
                    tryAgain = in.nextLine();
            } catch (NumberFormatException e){
                System.out.println("[ERROR] : Input maa vaere et tall. Proev igjen? JA/NEI");
                tryAgain = in.nextLine();
            }} while(tryAgain.toUpperCase().equals("JA") && !done);
                }
                
            }
//-------------------E4----------------slutt----------------------

//-----------------E5--------Start-----------------
            if (input.toUpperCase().equals("R")){
                String tryAgain = "JA";
                boolean brukt = false;
                do{
                int teller = 0;
                System.out.println("Hvilken pasient vil du se resepter for?"); 
                for (Pasient pasient: pasientListe){
                    //Kan også ta pasientID, men i eksemple på oppgaven startet utskrivningen på 0
                    System.out.println(teller + ":"+pasient);
                    teller ++;
                }
                try {
                    input = in.nextLine();
                    int svar = Integer.parseInt(input);
                    Pasient pasientsvart = pasientListe.hent(svar);
                    System.out.println(pasientsvart+"\n");
                    System.out.println("Hvilken resept vil du bruke?");
                    int teller2 = 0;
                    for (Resept resept: pasientsvart.hentReseptListe()){
                        System.out.println(teller2 + ":"+resept);
                        teller2 ++;
                    }

                    try {
                        input = in.nextLine();
                        int svar2 = Integer.parseInt(input);
                        Resept resept = pasientsvart.hentReseptListe().hent(svar2);
                        resept.bruk();
                        brukt = true;
                        System.out.println("______________________________________");
                        System.out.println("\nBrukte " + pasientsvart.hentNavn()
                                            + " sin resept for "+resept.hentLegemiddel().hentNavn()
                                            + ".\nAntall gjenstaaende reit på denne resepten: "
                                            + resept.hentReit());
                        System.out.println("\n\nFor å gå tilbake til Hovedmeny trykk enter. Eller 'SLUTT' for å avslutte");
                        input = in.nextLine(); 
                        } catch (UgyldigListeindeks e){
                            System.out.println("[ERROR] : Resepten finnes ikke. Proev igjen? JA/NEI");
                            tryAgain = in.nextLine();
                        } catch (NumberFormatException e){
                            System.out.println("[ERROR] : Input maa vaere et tall. Proev igjen? JA/NEI");
                            tryAgain = in.nextLine();
                        }
                } catch (UgyldigListeindeks e){
                        System.out.println("[ERROR] : Pasient finnes ikke. Proev igjen? JA/NEI");
                        tryAgain = in.nextLine();
                } catch (NumberFormatException e){
                    System.out.println("[ERROR] : Input maa vaere et tall. Proev igjen? JA/NEI");
                    tryAgain = in.nextLine();
                }}while(tryAgain.equals("JA") && !brukt);
            }
//------------------------E5------------------Slutt----------------
//--------------E6------start
            if (input.toUpperCase().equals("S")){
                
                undermeny();
                input = in.nextLine();
                
            //Statestikk for 
                if (input.equals("1")){
                    System.out.println("Totalt antall utskrevne resepter på vanedannende legemidler:\n"+ bruktReseptVanedannende);
                }
                if (input.equals("2")){
                    System.out.println("Totalt antall utskrevne resepter på narkotiske legemidler:\n"+ bruktReseptNarkotisk);
                }
                if (input.equals("3")){
                    String tryAgain = "NEI";
                    boolean done = false;
                    do{ 
                        try{
                            misbruktNarkotika();
                            input = in.nextLine();
                            if (input.toUpperCase().equals("L")){
                                int teller=0;
                                for (Lege lege: legeListe){
                                    for (Lege navn : legeBruktNarkotisk) {
                                        if(lege.equals(navn)){
                                            teller++;
                                        }
                                
                                    }
                                if (teller>0){
                                System.out.println("Lege:"+lege + "antall:"+ teller);
                                }
                                }

                            }
                            if (input.toUpperCase().equals("P")){
                                
                                int teller=0;
                                for (Pasient pasient: pasientListe){
                                    for (Pasient navn : pasientBruktNarkotisk) {
                                        if(pasient.equals(navn)){
                                            teller++;
                                        }
                                    }
                                    if (teller>0){
                                    System.out.println("Pasient:"+pasient + "antall:"+ teller);
                                    }
                                }
                            }
                        } catch(UgyldigListeindeks e){
                            System.out.println("Ingen leger har skrevet ut narkotiske resepter.");
                        } catch (NumberFormatException e){
                            System.out.println("[ERROR] : Input maa vaere et tall. Proev igjen? JA/NEI");
                            tryAgain = in.nextLine();
                        } 
                    } while(tryAgain.toUpperCase().equals("JA") && !done);
                }}

//--------------E6-------slutt

//--------------E7------start
            if (input.toUpperCase().equals("F")){
                //int teller = 0;
                System.out.println("Onsker du aa overskrive en eksisterende fil?\n");
                System.out.println("JA / NEI\n"+
                                    "- Trykk på enter for å gå tilbake til HOVEDMENY.\n"+
                                    "- Skriv 'SLUTT' for å avslutte.");
                input = in.nextLine();
                if (input.toUpperCase().equals("JA")){
                    System.out.println("Skriv inn filnavn til filen du vil overskrive: (Eks. TekstFil.txt)");
                    String filnavn = in.nextLine();
                    File f = new File(filnavn);
                    f.delete();
                    lesInnTilFil(pasientListe, legemideListe, legeListe, f);
                }
                else if (input.toUpperCase().equals("NEI")){
                    System.out.println("Skriv inn onsket filnavn: (Eks. TekstFil.txt)");
                    File f = new File(in.nextLine());
                    lesInnTilFil(pasientListe, legemideListe, legeListe, f);
                }
            }
//--------------E7------slutt
    } in.close(); 
        
}
        
        
        

    

    //TODO: Dersom dette ikke fungerer, legg det inn i whileloop og ikke i en egen klasse
    public static void hovedMeny(){
        System.out.println("______________________________________________________________________");
        System.out.println("HOVEDMENY:");
        System.out.println("----------");
        System.out.println("- Skriv 'O': For å få en fullstendig oversikt over valgt element.\n"); //E3
        System.out.println("- Skriv 'L': For å opprette og legge til nye elementer.\n"); //E4: går til leggTilMeny
        System.out.println("- Skriv 'R': For å bruke en gitt resept fra listen til en pasient.\n"); //E5
        System.out.println("- Skriv 'S': For å skrive ut Statistikk.\n"); //E6: går til undermeny
        System.out.println("- Skriv 'F': For å skrive all data til Fil.\n"); //E7
        System.out.println("- Skriv 'SLUTT': For å avslutte programmet."); //E2
        System.out.println("______________________________________________________________________");
        
    }

    //oppg E4: L fra hovedmeny
    public static void leggTilMeny(){
        System.out.println("______________________________________________________"); 
        System.out.println("MENY:");
        System.out.println("-----");
        System.out.println("- Skriv '1' for å opprette en lege.\n");
        System.out.println("- Skriv '2' for å opprette en pasient.\n");
        System.out.println("- Skriv '3' for å opprette et legemiddel.\n");
        System.out.println("- Skriv '4' for at en lege skal skriv ut en resept.\n");
        System.out.println("- Skriv 'SLUTT': For å avslutte programmet.\n"); 
        System.out.println("- Trykk på enter for å gå tilbake til HOVEDMENYEN");
        System.out.println("______________________________________________________");
        
    }

    //oppg E6: S fra hovedmeny
    public static void undermeny(){
        System.out.println("_______________________________________________________________________________");
        System.out.println("MENY:");
        System.out.println("-----");
        System.out.println("- Skriv '1' for totalt antall utskrevne resepter på vanedannende legemidler.\n");
        System.out.println("- Skriv '2' for totalt antall utskrevne resepter på narkotiske legemidler.\n");
        System.out.println("- Skriv '3' for å få en oversikt over mulig misbruk av narkotika.\n"); //går til misbrukNarkotika
        System.out.println("- Skriv 'SLUTT': For å avslutte programmet.\n"); 
        System.out.println("- Trykk på enter for å gå tilbake til HOVEDMENYEN");
        System.out.println("_______________________________________________________________________________");
    }

    //oppg E6: 3 fra undermeny
    public static void misbruktNarkotika(){
        System.out.println("______________________________________________________________________________________________________________________________");
        System.out.println("OVERSIKT OVER MULIG MISBRUK AV NARKOTISKE STOFFER:");
        System.out.println("--------------------------------------------------");
        System.out.println( "- Skriv 'L' for å få en liste over alle leger som har skrevet ut resept på narkotiske legemidler + antall slike resepter.\n");
        System.out.println( "- Skriv 'P' for å få en liste over alle pasienter som har gyldig resept på narkotiske legemidler + antall slike resepter.\n");
        System.out.println("- Skriv 'SLUTT': For å avslutte programmet.\n"); 
        System.out.println("- Trykk på enter for å gå tilbake til HOVEDMENYEN");
        System.out.println("______________________________________________________________________________________________________________________________");
    }

//--------E7-lesInnTilFil-------------Start
    public static void lesInnTilFil(IndeksertListe<Pasient> indeksertListePasient, IndeksertListe<Legemiddel> indeksertListeLegemiddel, Prioritetskoe<Lege> indeksertListeLege, File f){
        try{
            PrintWriter pw =new PrintWriter(f);
            pw.append("# Pasienter (navn, fnr)\n");
            for (Pasient pasient: indeksertListePasient){
                pw.append(pasient.hentNavn() + ","+ pasient.hentFoedselsnummer()+"\n");
            }
            pw.append("# Legemidler (navn,type,pris,virkestoff,[styrke])\n");
            for (Legemiddel legemiddel : indeksertListeLegemiddel){
                if (legemiddel instanceof Vanlig){
                pw.append(legemiddel.hentNavn() + "," + legemiddel.hentType()+ 
                ","+legemiddel.hentPris()+","+legemiddel.hentVirkestoff()+"\n");
                }
                else {
                    pw.append(legemiddel.hentNavn() + "," + legemiddel.hentType()+ 
                ","+legemiddel.hentPris()+","+legemiddel.hentVirkestoff()+","+legemiddel.skrivStyrke()+"\n");
                
                }
            } 
            pw.append("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
            for (Lege lege: indeksertListeLege){
                pw.append(lege.hentNavn()+","+lege.hentKontrollID() + "\n");
            }
            pw.append("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
            for (Lege lege: indeksertListeLege){
                if (lege.hentResepter().stoerrelse() > 0){
                    for (Resept resept : lege.hentResepter()){
                        if (resept instanceof MilResept){
                            pw.append(resept.hentLegemiddel().hentId() + ","+lege.hentNavn()
                            + ","+resept.hentPasientId()+ ","+resept.skrivType()+"\n");
                        }
                        else{
                            pw.append(resept.hentLegemiddel().hentId() + ","+lege.hentNavn()
                            + ","+resept.hentPasientId()+ ","+resept.skrivType()+ ","+resept.hentReit() +"\n");
                        }
                        
                    }
                }
            }
            pw.close();
            System.out.println("\nFilen er opprettet!\n");
        }catch(FileNotFoundException e){
            System.out.println("[ERROR] FileNotFoundExeption: Filen kunne opprettes.");
        }
       
    }
//--------E7-lesInnTilFil-------------Slutt
}

