
import Class_and_controller.CauHoi;
import Class_and_controller.CauLenh;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.accessibility.AccessibleAction;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Loc
 */
public class ChoiGame extends javax.swing.JFrame {

    int dokho;
    int countstart;
    int count;
    Timer timer;
    
    //Tổng số câu
    private int a = 0;
    int diem = 0;

    List<CauHoi> list = new ArrayList();
    

    String TaiKhoan;
    
    public ChoiGame() {
        initComponents();
        setTitle("Phần mềm học Kanji");
        SoanDe();
    }
    
    void chuyenTrang(){
        this.setVisible(false);
        Form_KetQua frm = new Form_KetQua(dokho,diem,a);
        frm.TaiKhoan = TaiKhoan;
        frm.setVisible(true);
    }

    private void Start() {
        countstart = 3;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countstart < 0) {
                    labelcauhoi.setText(list.get(a).getKanji());
                    btntraloi1.setText(list.get(a).getTraloi1());
                    btntraloi2.setText(list.get(a).getTraloi2());
                    btntraloi3.setText(list.get(a).getTraloi3());
                    btntraloi4.setText(list.get(a).getTraloi4());
                    countstart = 10;
                    if (a == 20) {

                    } else {
                        a++;
                    }

                } else {

                    lbltime.setText(String.valueOf(countstart));
                    countstart--;
                    if (a == 20 && countstart < 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(rootPane, "Trò chơi kết thúc");
                        chuyenTrang();
                    }
                }
            }
        });
        if (countstart > 0) {
            timer.start();
        }
    }

    public ChoiGame(int rank) {
        initComponents();
        setTitle("Phần mềm học Kanji");
        dokho = rank;
        SoanDe();
        Play();
    }

    public void Play() {
        Start();
        for (CauHoi temp : list) {
            System.out.println(temp.getKanji());
        }
    }

    public void SoanDe() {
        CauLenh connect = new CauLenh();
        var conn = connect.conn;
        
        Random generator = new Random();
        try{
            int[] arr;
            if (dokho == 5) {
                arr = new int[20];
                int i = 0;
                do {
                    boolean kt = true;
                    int value = generator.nextInt((40 - 1) + 1) + 1;
                    for (int j = 0; j < arr.length; j++) {
                        if (value == arr[j]) {
                            i--;
                            kt = false;
                        }
                    }
                    if (kt == true) {
                        arr[i] = value;
                        i++;
                    }
                } while (i < 20);
                for (int j = 0; j < 20; j++) {
                    int mangdapan[] = new int[4];
                    String kanji, traloi1 = null, traloi2 = null, traloi3 = null, traloi4 = null, dapancauhoi = null;
                    var cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + arr[j] + "'");
                    var thucThi = cauLenh.executeQuery();
                    while (thucThi.next()) {
                        kanji = thucThi.getString(5);
                        dapancauhoi = thucThi.getString(3);
                        int randomdapan = generator.nextInt((4 - 1) + 1) + 1;
                        if (randomdapan == 1) {
                            traloi1 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi2 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi3 = thucThi.getString(3);
                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi4 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        } else if (randomdapan == 2) {
                            traloi2 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi1 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi3 = thucThi.getString(3);;
                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi4 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        } else if (randomdapan == 3) {
                            traloi3 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi1 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi2 = thucThi.getString(3);

                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi4 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        } else if (randomdapan == 4) {
                            traloi4 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi1 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi2 = thucThi.getString(3);
                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi3 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        }
                    }
                }
            } else if (dokho == 4) {
                arr = new int[20];
                int i = 0;
                do {
                    boolean kt = true;
                    int value = generator.nextInt((40 - 1) + 1) + 1;
                    for (int j = 0; j < arr.length; j++) {
                        if (value == arr[j]) {
                            i--;
                            kt = false;
                        }
                    }
                    if (kt == true) {
                        arr[i] = value;
                        i++;
                    }
                } while (i < 20);
                for (int j = 0; j < 20; j++) {
                    int mangdapan[] = new int[4];
                    String kanji, traloi1 = null, traloi2 = null, traloi3 = null, traloi4 = null, dapancauhoi = null;
                    var cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + arr[j] + "'");
                    var thucThi = cauLenh.executeQuery();
                    while (thucThi.next()) {
                        kanji = thucThi.getString(5);
                        dapancauhoi = thucThi.getString(3);
                        int randomdapan = generator.nextInt((4 - 1) + 1) + 1;
                        if (randomdapan == 1) {
                            traloi1 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi2 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi3 = thucThi.getString(3);
                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi4 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        } else if (randomdapan == 2) {
                            traloi2 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi1 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi3 = thucThi.getString(3);;
                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi4 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        } else if (randomdapan == 3) {
                            traloi3 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi1 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi2 = thucThi.getString(3);

                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi4 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        } else if (randomdapan == 4) {
                            traloi4 = thucThi.getString(3);
                            mangdapan[0] = arr[j];
                            int k = 1;
                            do {
                                boolean kt = true;
                                int valuedapan = generator.nextInt((40 - 1) + 1) + 1;
                                for (int h = 0; h < mangdapan.length; h++) {
                                    if (mangdapan[h] == valuedapan) {
                                        if (k == 1) {
                                            kt = false;
                                        } else {
                                            kt = false;
                                            k--;
                                        }
                                    }
                                }
                                if (kt == true) {
                                    mangdapan[k] = valuedapan;
                                    k++;
                                }
                            } while (k < 4);
                            for (int dapan = 1; dapan < mangdapan.length; dapan++) {
                                if (dapan == 1) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi1 = thucThi.getString(3);
                                } else if (dapan == 2) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi2 = thucThi.getString(3);
                                } else if (dapan == 3) {
                                    cauLenh = conn.prepareStatement("select * from Kanji Where id = '" + mangdapan[dapan] + "'");
                                    thucThi = cauLenh.executeQuery();
                                    thucThi.next();
                                    traloi3 = thucThi.getString(3);
                                }
                            }
                            CauHoi a = new CauHoi(kanji, traloi1, traloi2, traloi3, traloi4, dapancauhoi);
                            list.add(a);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelcauhoi = new javax.swing.JLabel();
        btntraloi2 = new javax.swing.JButton();
        btntraloi3 = new javax.swing.JButton();
        btntraloi1 = new javax.swing.JButton();
        btntraloi4 = new javax.swing.JButton();
        btnboqua = new javax.swing.JButton();
        lbltime = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelcauhoi.setFont(new java.awt.Font("SansSerif", 0, 120)); // NOI18N
        labelcauhoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelcauhoi.setToolTipText("");
        getContentPane().add(labelcauhoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 210, 170));

        btntraloi2.setFont(new java.awt.Font("SansSerif", 0, 60)); // NOI18N
        btntraloi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntraloi2ActionPerformed(evt);
            }
        });
        getContentPane().add(btntraloi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 690, 79));

        btntraloi3.setFont(new java.awt.Font("SansSerif", 0, 60)); // NOI18N
        btntraloi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntraloi3ActionPerformed(evt);
            }
        });
        getContentPane().add(btntraloi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 690, 74));

        btntraloi1.setFont(new java.awt.Font("SansSerif", 0, 60)); // NOI18N
        btntraloi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntraloi1MouseClicked(evt);
            }
        });
        btntraloi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntraloi1ActionPerformed(evt);
            }
        });
        getContentPane().add(btntraloi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 690, 79));

        btntraloi4.setFont(new java.awt.Font("SansSerif", 0, 60)); // NOI18N
        btntraloi4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntraloi4ActionPerformed(evt);
            }
        });
        getContentPane().add(btntraloi4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 690, 74));

        btnboqua.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnboqua.setText("Bỏ qua");
        btnboqua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnboquaActionPerformed(evt);
            }
        });
        getContentPane().add(btnboqua, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 160, 80));

        lbltime.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        getContentPane().add(lbltime, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 20, 80, 70));

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 80));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnboquaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnboquaActionPerformed
        if (a == 20) {
            JOptionPane.showMessageDialog(rootPane, "Trò chơi kết thúc");
            countstart = 0;
            lbltime.setText(String.valueOf(countstart));
            timer.stop();
            chuyenTrang();
        } else {
            labelcauhoi.setText(list.get(a).getKanji());
            btntraloi1.setText(list.get(a).getTraloi1());
            btntraloi2.setText(list.get(a).getTraloi2());
            btntraloi3.setText(list.get(a).getTraloi3());
            btntraloi4.setText(list.get(a).getTraloi4());
            countstart = 10;
            a++;
            timer.start();

        }
    }//GEN-LAST:event_btnboquaActionPerformed

    private void btntraloi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntraloi1ActionPerformed
        if (a == 20) {
            System.out.println("" + list.get(a - 1).getTraloi1());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi1().equals(list.get(a - 1).getDapan())) {

                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            countstart = 0;
            lbltime.setText(String.valueOf(countstart));
            timer.stop();
            JOptionPane.showMessageDialog(rootPane, "Trò chơi kết thúc");
            chuyenTrang();
        } else {
            System.out.println("" + list.get(a - 1).getTraloi1());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi1().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            labelcauhoi.setText(list.get(a).getKanji());
            btntraloi1.setText(list.get(a).getTraloi1());
            btntraloi2.setText(list.get(a).getTraloi2());
            btntraloi3.setText(list.get(a).getTraloi3());
            btntraloi4.setText(list.get(a).getTraloi4());
            countstart = 10;
            timer.start();
            a++;
        }
    }//GEN-LAST:event_btntraloi1ActionPerformed

    private void btntraloi4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntraloi4ActionPerformed
        if (a == 20) {
            System.out.println("" + list.get(a - 1).getTraloi4());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi4().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            countstart = 0;
            lbltime.setText(String.valueOf(countstart));
            timer.stop();
            JOptionPane.showMessageDialog(rootPane, "Trò chơi kết thúc");
            chuyenTrang();
        } else {
            System.out.println("" + list.get(a - 1).getTraloi4());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi4().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            labelcauhoi.setText(list.get(a).getKanji());
            btntraloi1.setText(list.get(a).getTraloi1());
            btntraloi2.setText(list.get(a).getTraloi2());
            btntraloi3.setText(list.get(a).getTraloi3());
            btntraloi4.setText(list.get(a).getTraloi4());
            countstart = 10;
            timer.start();
            a++;
        }
    }//GEN-LAST:event_btntraloi4ActionPerformed

    private void btntraloi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntraloi1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btntraloi1MouseClicked

    
    private void btntraloi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntraloi2ActionPerformed
        if (a == 20) {
            System.out.println("" + list.get(a - 1).getTraloi2());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi2().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            countstart = 0;
            lbltime.setText(String.valueOf(countstart));
            timer.stop();
            JOptionPane.showMessageDialog(rootPane, "Trò chơi kết thúc");
            chuyenTrang();
        } else {
            System.out.println("" + list.get(a - 1).getTraloi2());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi2().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            labelcauhoi.setText(list.get(a).getKanji());
            btntraloi1.setText(list.get(a).getTraloi1());
            btntraloi2.setText(list.get(a).getTraloi2());
            btntraloi3.setText(list.get(a).getTraloi3());
            btntraloi4.setText(list.get(a).getTraloi4());
            countstart = 10;
            timer.start();
            a++;
        }
    }//GEN-LAST:event_btntraloi2ActionPerformed

    private void btntraloi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntraloi3ActionPerformed
        if (a == 20) {
            System.out.println("" + list.get(a - 1).getTraloi3());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi3().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            countstart = 0;
            lbltime.setText(String.valueOf(countstart));
            timer.stop();
            JOptionPane.showMessageDialog(rootPane, "Trò chơi kết thúc");
            chuyenTrang();
        } else {
            System.out.println("" + list.get(a - 1).getTraloi3());
            System.out.println("" + list.get(a - 1).getDapan());
            timer.stop();
            if (list.get(a - 1).getTraloi3().equals(list.get(a - 1).getDapan())) {
                JOptionPane.showMessageDialog(this, "Chính xác");
                diem++;
            } else {
                JOptionPane.showMessageDialog(this, "Sai");
            }
            labelcauhoi.setText(list.get(a).getKanji());
            btntraloi1.setText(list.get(a).getTraloi1());
            btntraloi2.setText(list.get(a).getTraloi2());
            btntraloi3.setText(list.get(a).getTraloi3());
            btntraloi4.setText(list.get(a).getTraloi4());
            countstart = 10;
            timer.start();
            a++;
        }
    }//GEN-LAST:event_btntraloi3ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        LuaChonDoKho frm = new LuaChonDoKho();
        frm.TaiKhoan = TaiKhoan;
        frm.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChoiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChoiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChoiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChoiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChoiGame().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnboqua;
    private javax.swing.JButton btntraloi1;
    private javax.swing.JButton btntraloi2;
    private javax.swing.JButton btntraloi3;
    private javax.swing.JButton btntraloi4;
    private javax.swing.JLabel labelcauhoi;
    private javax.swing.JLabel lbltime;
    // End of variables declaration//GEN-END:variables
}
