package Class_and_controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




/**
 *
 * @author ADMIN
 */
public class Kanji {

    public Kanji(int Id, String phiemam, String hiragana, String mean, String kanji) {
        this.Id = Id;
        this.phiemam = phiemam;
        this.hiragana = hiragana;
        this.mean = mean;
        this.kanji = kanji;
    }
public Kanji() {
        this.Id = 0;
        this.phiemam = "";
        this.hiragana = "";
        this.mean = "";
        this.kanji = "";
    }
    public int getId() {
        return Id;
    }

    public String getPhiemam() {
        return phiemam;
    }

    public String getHiragana() {
        return hiragana;
    }

    public String getMean() {
        return mean;
    }

    public String getKanji() {
        return kanji;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setPhiemam(String phiemam) {
        this.phiemam = phiemam;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }
    private int Id;
    private String phiemam;
    private String hiragana;
    private String mean;
    private String kanji;
    

 
}
