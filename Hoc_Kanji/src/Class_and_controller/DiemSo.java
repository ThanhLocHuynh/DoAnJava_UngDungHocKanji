/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class_and_controller;

/**
 *
 * @author thanh
 */
public class DiemSo {
    String TaiKhoan;
    String TrinhDo;
    int Diem;
    int SoCau;
    
    public DiemSo(String TaiKhoan, String TrinhDo, int Diem, int SoCau) {
        this.TaiKhoan = TaiKhoan;
        this.TrinhDo = TrinhDo;
        this.Diem = Diem;
        this.SoCau = SoCau;
    }
    
    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getTrinhDo() {
        return TrinhDo;
    }

    public void setTrinhDo(String TrinhDo) {
        this.TrinhDo = TrinhDo;
    }

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int Diem) {
        this.Diem = Diem;
    }

    public int getSoCau() {
        return SoCau;
    }

    public void setSoCau(int SoCau) {
        this.SoCau = SoCau;
    }
    
}
