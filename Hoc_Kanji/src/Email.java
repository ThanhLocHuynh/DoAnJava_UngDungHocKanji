
import Class_and_controller.CauLenh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
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
public class Email extends javax.swing.JFrame {

    /**
     * Creates new form LayLaiMatKhau
     */
    
    void boEmailVaoTxt(){
        CauLenh connect = new CauLenh();
        var conn = connect.conn;
        try{
            var cauLenh = conn.prepareStatement("select Email from TaiKhoan where TaiKhoan = '" + TaiKhoan + "'");
            var thucThi = cauLenh.executeQuery();
            thucThi.next();
            txtemail.setText(thucThi.getString("Email"));
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Email() {
        initComponents();
        setTitle("Khôi phục mật khẩu");
        countdonw();
    }
    
    Timer timer;
    int count;
    private void countdonw() {
        count = 1;
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 0) {
                    timer.stop();
                    if(TaiKhoan != null){
                        boEmailVaoTxt();
                    }
                } else {
                    count--;
                }
            }
        });
        if (count != 0) {
            timer.start();
        }
    }
    
    String TaiKhoan;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        btnXacNhan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Email:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 80, 50));

        txtemail.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 450, 40));

        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        getContentPane().add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 142, 52));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed
    private void SendMail(String otp) {
        final String from = "tranduongtanloc222002@gmail.com";
        final String password = "cacjaaemozcbyejr";
        final String to = txtemail.getText();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kieu noi dung
            msg.addHeader("Content-type", "text/HTML;charset=UTF-");
            //Nguoi gui
            msg.setFrom(from);
            //Nguoi Nhan
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            //Tieu De
            msg.setSubject("Khôi phục mật khẩu phần mềm Luyện Kanji");
            //Quy Dinh Ngay
            msg.setSentDate(new Date());
            //Quy dinh email nhan phan bai
            msg.setReplyTo(null);
            //Noi dung
            msg.setText("Mã xác nhận của bạn là: " + otp, "UTF-8");

            //Gui email
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean KiemTraEmail(String emailAddress) {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        System.out.println("Result: " + patternMatches(emailAddress, regexPattern));
        return patternMatches(emailAddress, regexPattern);
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        CauLenh connect = new CauLenh();
        var conn = connect.conn;
        Random random = new Random();
        int value = random.nextInt((99999 - 10000) + 1) + 10000;
        String otp = Integer.toString(value);
        System.out.println(otp);
        try {
            if (KiemTraEmail(txtemail.getText()) == true) {
                var cauLenh = conn.prepareStatement("select * from TaiKhoan where email ='" + txtemail.getText() + "'");
                System.out.println(cauLenh);
                var thucThi = cauLenh.executeQuery();
                thucThi.next();
                if (txtemail.getText().equalsIgnoreCase(thucThi.getString(4))) {
                    String lenh = "UPDATE taikhoan SET OTP = '" + otp + "' Where email ='" + txtemail.getText() + "'";
                    System.out.println(lenh);
                    var Statement = conn.createStatement();
                    Statement.executeUpdate(lenh);
                    SendMail(otp);
                    JOptionPane.showMessageDialog(rootPane, "Mã xác nhận đã được gửi tới email");
                    
                    this.setVisible(false);
                    XacNhan_OTP frm = new XacNhan_OTP();
                    frm.TaiKhoan = TaiKhoan;
                    frm.Email = txtemail.getText();
                    frm.setVisible(true);
                    
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Gmail sai cú pháp");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnXacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Email().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtemail;
    // End of variables declaration//GEN-END:variables
}