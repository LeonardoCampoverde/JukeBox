package com.example.jokeboxleonardocampoverde;

public class Canzone {


    String nome;
    String link;
    int path;
    int img;
    static int numCanzoni=20;


    public Canzone(String nome, String link, int path,int img) {
        this.nome = nome;
        this.link = link;
        this.path= path;
        this.img=img;

    }

    public static Canzone[] init(){
        Canzone c[] = new Canzone[numCanzoni];
        c[0]=new Canzone("Brividi","https://www.youtube.com/watch?v=MA_5P3u0apQ",R.raw.brividi,R.raw.brividiimg);
        c[1]=new Canzone("As it was","https://www.youtube.com/watch?v=H5v3kku4y6Q",R.raw.asitwas,R.raw.asitwasimg);
        c[2]=new Canzone("Giovani Wannabe","https://www.youtube.com/watch?v=4GXDFtuG9Xo",R.raw.giovanniwannabe,R.raw.giovanniwannabeimg);
        c[3]=new Canzone("Cuff it","https://www.youtube.com/watch?v=yrtWLyp5gLI",R.raw.cuffit,R.raw.cuffitimg);
        c[4]=new Canzone("Shakerando","https://www.youtube.com/watch?v=U6VMjHJ18a0",R.raw.shakerando,R.raw.shakerandoimg);
        c[5]=new Canzone("Importante","https://www.youtube.com/watch?v=tTaEfEyEGrQ",R.raw.importante,R.raw.importanteimg);
        c[6]=new Canzone("The Loneliest","https://www.youtube.com/watch?v=odWKEfp2QMY",R.raw.loneliest,R.raw.loneliestimg);
        c[7]=new Canzone("Non lo sai","https://www.youtube.com/watch?v=JQgHPTTXr0I",R.raw.nonolosai,R.raw.nonlosaiimg);
        c[8]=new Canzone("Le pietre non volano","https://www.youtube.com/watch?v=kEn6YsuH0KE",R.raw.lepietrenonvolano,R.raw.pietrenonvolanoimg);
        c[9]=new Canzone("Bam bam","https://www.youtube.com/watch?v=-8VfKZCOo_I",R.raw.bambam,R.raw.bambam);
        c[10]=new Canzone("Bellissima","https://www.youtube.com/watch?v=qz88Dx-_lA4",R.raw.bellissima,R.raw.bellissimaimg);
        c[11]=new Canzone("Bloody Mary","https://www.youtube.com/watch?v=MsXdUtlDVhk",R.raw.bloodymary,R.raw.bloodymaryimg);
        c[12]=new Canzone("Buonanotte","https://www.youtube.com/watch?v=osuKmCiGy1w",R.raw.buonanotte,R.raw.buonanotteimg);
        c[13]=new Canzone("Despechà","https://www.youtube.com/watch?v=5g2hT4GmAGU",R.raw.despecha,R.raw.despechaimg);
        c[14]=new Canzone("Titi me preguntò","https://www.youtube.com/watch?v=Cr8K88UcO0s",R.raw.pregunto,R.raw.titimepreguntoimg);
        c[15]=new Canzone("BZRP Music Sessions #52","https://www.youtube.com/watch?v=A_g3lMcWVy0",R.raw.bzrp52,R.raw.bzrp52img);
        c[16]=new Canzone("Bagno a mezzanotte","https://www.youtube.com/watch?v=XSHcZ4rcBDk",R.raw.bagnoamezzanotte,R.raw.bagnoamezzanotteimg);
        c[17]=new Canzone("Running up that hill","https://www.youtube.com/watch?v=wp43OdtAAkM",R.raw.runningup,R.raw.runningupthathillimg);
        c[18]=new Canzone("Cold Heart","https://www.youtube.com/watch?v=qod03PVTLqk",R.raw.coldheart,R.raw.coldheartimg);
        c[19]=new Canzone("La dolce vita","https://www.youtube.com/watch?v=TX_csdgqhxA",R.raw.dolcevita,R.raw.dolcevitaimg);


        return c;



    }





}
