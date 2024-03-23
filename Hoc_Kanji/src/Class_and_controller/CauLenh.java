package Class_and_controller;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class CauLenh {

    //Ket noi CSDL
    public Connection conn;

    public CauLenh() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Hoc_Kanji;" + "username=sa;password=123456;encrypt=false");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Ket thuc ket noi CSDL


    //Ma hoa va giai ma Ceasar + Vigenere + Rail Fence
    //Ceasar
    private char MaHoaKT_Ceasar(char c, int k) {
        if (!Character.isLetter(c)) {
            return c;
        }
        return (char) ((((Character.toUpperCase(c) - 'A') + k) % 26 + 26) % 26 + 'A');
    }

    private String MaHoa_Ceasar(String br, int k) {
        String kq = "";
        int n = br.length();
        for (int i = 0; i < n; i++) {
            kq += MaHoaKT_Ceasar(br.charAt(i), k);
        }
        return kq;
    }

    //Vigenere
    int vig[][];

    private void KhoiTao_Vigenere() {
        vig = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                vig[i][j] = (i + j) % 26;
            }
        }
    }

    private String MaHoa_Vigenere(String banRo, String key) {
        int n = banRo.length();
        String banMa = "";
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(banRo.charAt(i))) {
                banMa += MaHoa_Vigenere(banRo.charAt(i), key.charAt(k));
                k++;
                k = k % key.length();
            } else {
                banMa += banRo.charAt(i);
            }
        }
        return banMa;
    }

    private char MaHoa_Vigenere(char x, char k) {
        int xn = Character.toUpperCase(x) - 'A';
        int kn = Character.toUpperCase(k) - 'A';
        int yn = vig[kn][xn];
        return (char) (yn + 'A');
    }

    //Phan ma hoa 3 lop
    public String MaHoa(String text) {
        int khoa_1 = 3;
        String maHoa_1 = (MaHoa_Ceasar(text, khoa_1)).toLowerCase();

        KhoiTao_Vigenere();
        String khoa_2 = "hi";
        String maHoa_2 = MaHoa_Vigenere(maHoa_1, khoa_2).toLowerCase();

        int khoa_3 = 3;
        String s = maHoa_2;
        int n = s.length();
        int sd, sc;
        sd = khoa_3;
        sc = n / sd + 1;
        char hr[][] = new char[sd][sc];
        int c, d;
        c = 0;
        d = 0;
        int soDu = n % sd;

        for (int i = 0; i < n; i++) {
            hr[d][c] = s.charAt(i);
            d++;
            if (d == khoa_3) {
                c++;
                d = 0;
            }
        }

        String kq = "";
        int soKyTu = sc;

        for (int i = 0; i < sd; i++) {
            if (i >= soDu) {
                soKyTu = sc - 1;
            }
            for (int j = 0; j < soKyTu; j++) {
                kq = kq + hr[i][j];
            }
        }
        String maHoa_3 = kq;

        return maHoa_3;
    }

    //Phan giai ma 3 lop
    public String GiaiMa(String text) {
        int khoa_1 = 3;
        String s = text;
        int n = s.length();
        int sd, sc;
        sd = khoa_1;
        sc = n / sd + 1;
        char hr[][] = new char[sd][sc];

        int soDu = n % sd;
        String kq = "";
        int soKyTu = sc;
        int t = 0;

        for (int i = 0; i < sd; i++) {
            if (i >= soDu) {
                soKyTu = sc - 1;
            }
            for (int j = 0; j < soKyTu; j++) {
                hr[i][j] = s.charAt(t);
                t++;
            }
        }

        int c, d;
        c = 0;
        d = 0;
        for (int i = 0; i < n; i++) {
            kq += hr[d][c];
            d++;
            if (d == khoa_1) {
                c++;
                d = 0;
            }
        }
        String giaiMa_1 = kq;

        KhoiTao_Vigenere();
        String khoa_2 = "hi";
        String kt1 = "";
        int kn = khoa_2.length();
        for (int i = 0; i < kn; i++) {
            kt1 += (char) (((26 - (Character.toUpperCase(khoa_2.charAt(i)) - 'A')) % 26) + 'A');
        }
        String giaiMa_2 = MaHoa_Vigenere(giaiMa_1, kt1).toLowerCase();

        int khoa_3 = 3;
        String giaiMa_3 = (MaHoa_Ceasar(giaiMa_2, -khoa_3)).toLowerCase();

        return giaiMa_3;
    }
    //Ket thuc ma hoa va giai ma Ceasar + Vigenere + rail fence

    //Phan cua thuan ne
    public ArrayList<Kanji> getListKanji() {
        ArrayList<Kanji> list = new ArrayList<>();
        String sql = "SELECT * FROM Kanji";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kanji s = new Kanji();
                s.setId(rs.getInt("Id"));
                s.setPhiemam(rs.getString("phienam"));
                s.setHiragana(rs.getString("hiragana"));
                s.setMean(rs.getString("mean"));
                s.setKanji(rs.getString("Kanji"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int[] createArr(int n){
        int a[]=new int [n+1];
        for(int i=0;i<n;i++){
            a[i]=i+1;
        }
        return a;
    }
    
    public void mix(int x[],int n){
        Random randomNum=new Random();
        for(int i=0;i<n-1;i++){
            int temp=0;
            temp=x[i];
            int value=randomNum.nextInt(n-1)+1;
            x[i]=x[value];
            x[value]=temp;
        }
    }

    public ArrayList<Kanji> getListKanjiRadom(int dokho) {
       
        int[] arr;
        int sl = 0;
        if (dokho == 1) 
        {
            sl = 40;
        }
        else if(dokho == 2)
        {
            sl=30;
        }
        else if(dokho == 3)
        {
            sl=20;
        }
        arr = createArr(sl);
        mix(arr, sl);
        int i = 0;
        
        ArrayList<Kanji> list = new ArrayList<>();
        try {
            for (int j = 0; j < arr.length; j++) {
                //System.out.println(arr[j]);
                PreparedStatement ps = conn.prepareStatement("select * from Kanji Where id = '" + arr[j] + "'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Kanji s = new Kanji();
                    s.setId(rs.getInt("Id"));
                    s.setPhiemam(rs.getString("phienam"));
                    s.setHiragana(rs.getString("hiragana"));
                    s.setMean(rs.getString("mean"));
                    s.setKanji(rs.getString("Kanji"));
                    list.add(s);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    //Ket thuc phan cua thuan

}
