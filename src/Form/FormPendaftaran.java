package Form;

import Main.Menu;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import klinik.CRUD;

public class FormPendaftaran extends javax.swing.JFrame {
    CRUD Data = new CRUD();
    String status = "";
    public FormPendaftaran() {
        initComponents();
        Data.Koneksi();
        TampilData();
    }
    public void TampilData(){
        String namaTable = "pendaftaran";
        String daftarField[]  = {
            "pendaftaran.id_pendaftaran",
            "pasien.nama_pasien",
            "dokter.nama_dokter",
            "tanggal_pendaftaran"
        };
        String judulKolom[] = {
            "ID Pendaftaran",            
            "Pasien",
            "Dokter",
            "Tanggal Pendaftaran"
        };
        String joinClause1 = "INNER JOIN pasien";
        String onClause1 = "pendaftaran.id_pasien = pasien.id_pasien";
        String joinClause2 = "INNER JOIN dokter";
        String onClause2 = "pendaftaran.id_dokter = dokter.id_dokter";
        tablePendaftaran.setModel(Data.TampilData(namaTable, daftarField, judulKolom, joinClause1, onClause1, joinClause2, onClause2));
    }
    public void EventTableClick(){
        DefaultTableModel tabelModel = (DefaultTableModel) tablePendaftaran.getModel();
        int indexTerpilih =  tablePendaftaran.getSelectedRow();
        txtId.setText(tabelModel.getValueAt(indexTerpilih, 0).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(tabelModel.getValueAt(indexTerpilih, 3).toString());
            txtTanggal.setDate(parsedDate);
        } catch (ParseException ex) {
            ex.printStackTrace(); 
        }
    }
    public void TambahData() {
        String idPasien = "";
        String idDokter = "";
        Date selectedDate = txtTanggal.getDate();
        
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
        status = Data.TambahData("pendaftaran", "id_pendaftaran,id_pasien,id_dokter,tanggal_pendaftaran", 
            "'" + txtId.getText() + "',"+
            "'" + idPasien + "',"+           
            "'" + idDokter + "',"+
            "'" + Data.FormatTanggal(selectedDate) + "'"
        );
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void EditData(){
        String idPasien = "";
        String idDokter = "";
        Date selectedDate = txtTanggal.getDate();
        
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
        status = Data.EditData("pendaftaran", 
                "id_pendaftaran = '" + txtId.getText() + "'," + 
                "id_dokter = '" + idDokter + "'," +                 
                "id_pasien = '" + idPasien + "'," + 
                "tanggal_pendaftaran = '" + Data.FormatTanggal(selectedDate) + "'"
        , "id_pendaftaran=" + txtId.getText());
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void HapusData(){
        status = Data.HapusData("pendaftaran", " id_pendaftaran= " + txtId.getText());
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void Kosongkan(){
        txtId.setText("");
        txtTanggal.setDate(new java.util.Date());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("klinik?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        dokterQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dokter d");
        dokterList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : dokterQuery.getResultList();
        pasienQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Pasien p");
        pasienList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : pasienQuery.getResultList();
        dokterQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dokter d");
        dokterList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : dokterQuery1.getResultList();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKosongkan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePendaftaran = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        txtPasien = new javax.swing.JComboBox<>();
        txtDokter = new javax.swing.JComboBox<>();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setText("Form Pendaftaran");

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

        tablePendaftaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePendaftaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePendaftaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePendaftaran);

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pasienList, txtPasien);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dokterList1, txtDokter);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel6.setText("Tanggal");

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
                                    .addComponent(jLabel6))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                        .addComponent(txtPasien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDokter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
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
                            .addComponent(txtPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void tablePendaftaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePendaftaranMouseClicked
        EventTableClick();
    }//GEN-LAST:event_tablePendaftaranMouseClicked

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_btnKembaliActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPendaftaran().setVisible(true);
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
    private java.util.List<Form.Dokter> dokterList1;
    private javax.persistence.Query dokterQuery;
    private javax.persistence.Query dokterQuery1;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Form.Pasien> pasienList;
    private javax.persistence.Query pasienQuery;
    private javax.swing.JTable tablePendaftaran;
    private javax.swing.JComboBox<String> txtDokter;
    private javax.swing.JTextField txtId;
    private javax.swing.JComboBox<String> txtPasien;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
