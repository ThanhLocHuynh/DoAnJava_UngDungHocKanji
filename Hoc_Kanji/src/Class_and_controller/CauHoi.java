package Class_and_controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Loc
 */
public class CauHoi {
    public CauHoi() {
    }

    public String getKanji() {
        return Kanji;
    }

    public String getTraloi1() {
        return traloi1;
    }

    public String getTraloi2() {
        return traloi2;
    }

    public String getTraloi3() {
        return traloi3;
    }

    public String getTraloi4() {
        return traloi4;
    }

    public void setKanji(String Kanji) {
        this.Kanji = Kanji;
    }

    public void setTraloi1(String traloi1) {
        this.traloi1 = traloi1;
    }

    public void setTraloi2(String traloi2) {
        this.traloi2 = traloi2;
    }

    public void setTraloi3(String traloi3) {
        this.traloi3 = traloi3;
    }

    public void setTraloi4(String traloi4) {
        this.traloi4 = traloi4;
    }

    public CauHoi(String Kanji, String traloi1, String traloi2, String traloi3, String traloi4, String dapan) {
        this.Kanji = Kanji;
        this.traloi1 = traloi1;
        this.traloi2 = traloi2;
        this.traloi3 = traloi3;
        this.traloi4 = traloi4;
        this.dapan = dapan;
    }

    
    private String Kanji;
    private String traloi1;
    private String traloi2;
    private String traloi3;
    private String traloi4;

    public CauHoi(String dapan) {
        this.dapan = dapan;
    }
    private String dapan;

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }
    
}
