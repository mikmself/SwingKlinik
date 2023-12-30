package Form;

import Main.Menu;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import klinik.CRUD;

public class FormRawatJalan extends javax.swing.JFrame {
    CRUD Data = new CRUD();
    String status = "";
    public FormRawatJalan() {
        initComponents();
        Data.Koneksi();
        TampilData();
    }
    public void TampilData(){
        String namaTable = "rawat_jalan";
        String daftarField[]  = {
            "rawat_jalan.id_rawat_jalan",
            "pasien.nama_pasien",
            "dokter.nama_dokter",
            "rawat_jalan.tanggal_masuk",
            "rawat_jalan.tanggal_kembali",            
            "rawat_jalan.lama_inap",            
            "rawat_jalan.jumlah_obat",
            "rawat_jalan.total_harga",
            "rawat_jalan.status",
        };
        String judulKolom[] = {
            "ID Rekam Medis",            
            "Pasien",
            "Dokter",
            "Tanggal Masuk",            
            "Tanggal Keluar",
            "Lama Inap",
            "Jumlah Obat",            
            "Total Harga",
            "Status"
        };
        String joinClause1 = "INNER JOIN pasien";
        String onClause1 = "rawat_jalan.id_pasien = pasien.id_pasien";
        String joinClause2 = "INNER JOIN dokter";
        String onClause2 = "rawat_jalan.id_dokter = dokter.id_dokter";
        tableRawatJalan.setModel(Data.TampilData(namaTable, daftarField, judulKolom, joinClause1, onClause1, joinClause2, onClause2));
    }
    public void EventTableClick(){
        DefaultTableModel tabelModel = (DefaultTableModel) tableRawatJalan.getModel();
        int indexTerpilih =  tableRawatJalan.getSelectedRow();
        txtId.setText(tabelModel.getValueAt(indexTerpilih, 0).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(tabelModel.getValueAt(indexTerpilih, 3).toString());
            txtTanggalMasuk.setDate(parsedDate);
        } catch (ParseException ex) {
            ex.printStackTrace(); 
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(tabelModel.getValueAt(indexTerpilih, 4).toString());
            txtTanggalKeluar.setDate(parsedDate);
        } catch (ParseException ex) {
            ex.printStackTrace(); 
        }
        txtLamaInap.setText(tabelModel.getValueAt(indexTerpilih, 5).toString());        
        txtJumlahObat.setText(tabelModel.getValueAt(indexTerpilih, 6).toString());        
        txtTotalHarga.setText(tabelModel.getValueAt(indexTerpilih, 7).toString());
        txtStatus.setText(tabelModel.getValueAt(indexTerpilih, 8).toString());
    }
    public void TambahData() {
        String idPasien = "";
        String idDokter = "";
        Date selectedDate = txtTanggalMasuk.getDate();
        Date selectedDate2 = txtTanggalKeluar.getDate();
        
        try {
            String column = txtPasien.getSelectedItem().toString();
            Pasien pasien = Pasien.findByCoulmn(column);
            if (pasien != null) {
                idPasien = pasien.getIdPasien().toString();
            } else {
                idPasien = "1";
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return;
        }  
        try {
            String column = txtDokter.getSelectedItem().toString();
            Dokter dokter = Dokter.findByCoulmn(column);
            if (dokter != null) {
                idDokter = dokter.getIdDokter().toString();
            } else {
                idDokter = "1";
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return;
        } 
        status = Data.TambahData("rawat_jalan", "id_rawat_jalan,id_pasien,id_dokter,tanggal_masuk,tanggal_kembali,lama_inap,jumlah_obat,total_harga,status", 
            "'" + txtId.getText() + "',"+
            "'" + idPasien + "',"+
            "'" + idDokter + "',"+
            "'" + Data.FormatTanggal(selectedDate) + "'," +                         
            "'" + Data.FormatTanggal(selectedDate2) + "'," +              
            "'" + txtLamaInap.getText() + "'," + 
            "'" + txtJumlahObat.getText() + "'," +            
            "'" + txtTotalHarga.getText() + "'," +
            "'" + txtStatus.getText() + "'" 
        );
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void EditData(){
        String idPasien = "";
        String idDokter = "";
        Date selectedDate = txtTanggalMasuk.getDate();        
        Date selectedDate2 = txtTanggalKeluar.getDate();

        
        try {
            String column = txtPasien.getSelectedItem().toString();
            Pasien pasien = Pasien.findByCoulmn(column);
            if (pasien != null) {
                idPasien = pasien.getIdPasien().toString();
            } else {
                idPasien = "1";
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return;
        }  
        try {
            String column = txtDokter.getSelectedItem().toString();
            Dokter dokter = Dokter.findByCoulmn(column);
            if (dokter != null) {
                idDokter = dokter.getIdDokter().toString();
            } else {
                idDokter = "1";
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return;
        }
        
        status = Data.EditData("rawat_jalan", 
                "id_rawat_jalan = '" + txtId.getText() + "'," + 
                "id_pasien = '" + idPasien + "'," + 
                "id_dokter = '" + idDokter + "'," + 
                "tanggal_masuk = '" + Data.FormatTanggal(selectedDate) + "'," +                                             
                "tanggal_kembali = '" + Data.FormatTanggal(selectedDate2) + "'," +                                
                "lama_inap = '" + txtLamaInap.getText() + "'," +                              
                "jumlah_obat = '" + txtJumlahObat.getText() + "'," +                
                "total_harga = '" + txtTotalHarga.getText() + "'," +                
                "status = '" + txtStatus.getText() + "'"      
        , "id_rawat_jalan=" + txtId.getText());
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void HapusData(){
        status = Data.HapusData("rawat_jalan", " id_rawat_jalan= " + txtId.getText());
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void Kosongkan(){
        txtId.setText("");
        txtTanggalMasuk.setDate(new java.util.Date());        
        txtTanggalKeluar.setDate(new java.util.Date());
        txtLamaInap.setText("");  
        txtJumlahObat.setText("");        
        txtTotalHarga.setText("");
        txtStatus.setText("");

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("klinik?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        pasienQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Pasien p");
        pasienList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : pasienQuery.getResultList();
        dokterQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dokter d");
        dokterList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : dokterQuery.getResultList();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtLamaInap = new javax.swing.JTextField();
        txtJumlahObat = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKosongkan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRawatJalan = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        txtPasien = new javax.swing.JComboBox<>();
        txtDokter = new javax.swing.JComboBox<>();
        txtTanggalMasuk = new com.toedter.calendar.JDateChooser();
        txtTanggalKeluar = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setText("Form Rawat Jalan");

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKosongkan.setText("Kosogkan");
        btnKosongkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKosongkanActionPerformed(evt);
            }
        });

        jLabel2.setText("ID");

        jLabel3.setText("Pasien");

        jLabel4.setText("Dokter");

        jLabel5.setText("Tanggal Masuk");

        jLabel6.setText("Lama Inap");

        jLabel7.setText("Jumlah Obat");

        tableRawatJalan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableRawatJalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRawatJalanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableRawatJalan);

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pasienList, txtPasien);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dokterList, txtDokter);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel8.setText("Tanggal Keluar");

        jLabel9.setText("Total Harga");

        jLabel10.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKosongkan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtLamaInap, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtJumlahObat, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtPasien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDokter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTanggalKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtTanggalKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLamaInap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJumlahObat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKosongkan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        TambahData();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        EditData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        HapusData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKosongkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKosongkanActionPerformed
        Kosongkan();
    }//GEN-LAST:event_btnKosongkanActionPerformed

    private void tableRawatJalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRawatJalanMouseClicked
        EventTableClick();
    }//GEN-LAST:event_tableRawatJalanMouseClicked

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_btnKembaliActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRawatJalan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnKosongkan;
    private javax.swing.JButton btnTambah;
    private java.util.List<Form.Dokter> dokterList;
    private javax.persistence.Query dokterQuery;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Form.Pasien> pasienList;
    private javax.persistence.Query pasienQuery;
    private javax.swing.JTable tableRawatJalan;
    private javax.swing.JComboBox<String> txtDokter;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtJumlahObat;
    private javax.swing.JTextField txtLamaInap;
    private javax.swing.JComboBox<String> txtPasien;
    private javax.swing.JTextField txtStatus;
    private com.toedter.calendar.JDateChooser txtTanggalKeluar;
    private com.toedter.calendar.JDateChooser txtTanggalMasuk;
    private javax.swing.JTextField txtTotalHarga;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
