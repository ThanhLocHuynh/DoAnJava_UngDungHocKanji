package Class_and_controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thanh
 */
public class TaiKhoan {

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public String getTen() {
        return Ten;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public char getOtp() {
        return Otp;
    }

    public void setOtp(char Otp) {
        this.Otp = Otp;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }
    
    private String TaiKhoan;
    private String MatKhau;
    private String Ten;
    private String Email;
    private char Otp;

    public TaiKhoan() {
    }

    public TaiKhoan(String TaiKhoan, String MatKhau, String Ten, String Email) {
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Ten = Ten;
        this.Email = Email;
    }

}
