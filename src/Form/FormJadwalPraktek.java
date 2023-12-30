package Form;

import Main.Menu;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import klinik.CRUD;

public class FormJadwalPraktek extends javax.swing.JFrame {
    CRUD Data = new CRUD();
    String status = "";
    public FormJadwalPraktek() {
        initComponents();
        Data.Koneksi();
        TampilData();
    }
    public void TampilData(){
        String namaTable = "jadwal_praktek";
        String daftarField[]  = {
            "jadwal_praktek.id_jadwal",
            "dokter.nama_dokter",
            "jadwal_praktek.hari",
            "jadwal_praktek.jam"
        };
        String judulKolom[] = {
            "ID Jadwal Prakter",            
            "Dokter",
            "Hari",
            "Jam"
        };
        String joinClause1 = "INNER JOIN dokter";
        String onClause1 = "jadwal_praktek.id_dokter = dokter.id_dokter";
        tableJadwalPraktek.setModel(Data.TampilData(namaTable, daftarField, judulKolom, joinClause1, onClause1, null, null));
    }
    public void EventTableClick(){
        DefaultTableModel tabelModel = (DefaultTableModel) tableJadwalPraktek.getModel();
        int indexTerpilih =  tableJadwalPraktek.getSelectedRow();
        txtId.setText(tabelModel.getValueAt(indexTerpilih, 0).toString());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(tabelModel.getValueAt(indexTerpilih, 2).toString());
            txtTanggal.setDate(parsedDate);
        } catch (ParseException ex) {
            ex.printStackTrace(); 
        }
        txtJam.setText(tabelModel.getValueAt(indexTerpilih, 3).toString());
    }
    public void TambahData() {
        String idDokter = "";
        Date selectedDate = txtTanggal.getDate();
        
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
        status = Data.TambahData("jadwal_praktek", "id_jadwal,id_dokter,hari,jam", 
            "'" + txtId.getText() + "',"+
            "'" + idDokter + "',"+
            "'" + Data.FormatTanggal(selectedDate) + "',"+
            "'" + txtJam.getText() + "'"          
        );
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void EditData(){
        String idDokter = "";
        Date selectedDate = txtTanggal.getDate();
        
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
        status = Data.EditData("jadwal_praktek", 
                "id_jadwal = '" + txtId.getText() + "'," + 
                "id_dokter = '" + idDokter + "'," + 
                "hari = '" + Data.FormatTanggal(selectedDate) + "'," + 
                "jam = '" + txtJam.getText() + "'"                                 
        , "id_jadwal=" + txtId.getText());
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void HapusData(){
        status = Data.HapusData("jadwal_praktek", " id_jadwal= " + txtId.getText());
        JOptionPane.showMessageDialog(null, status);
        TampilData();
        Kosongkan();
    }
    public void Kosongkan(){
        txtId.setText("");
        txtTanggal.setDate(new java.util.Date());
        txtJam.setText("");        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("klinik?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        dokterQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Dokter d");
        dokterList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : dokterQuery.getResultList();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtJam = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKosongkan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableJadwalPraktek = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        txtDokter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setText("Form Jadwal Praktek");

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

        jLabel3.setText("Dokter");

        jLabel4.setText("Hari");

        jLabel5.setText("Jam");

        tableJadwalPraktek.setModel(new javax.swing.table.DefaultTableModel(
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
        tableJadwalPraktek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableJadwalPraktekMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableJadwalPraktek);

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dokterList, txtDokter);
        bindingGroup.addBinding(jComboBoxBinding);

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtJam, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDokter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKosongkan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(txtDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
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

    private void tableJadwalPraktekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableJadwalPraktekMouseClicked
        EventTableClick();
    }//GEN-LAST:event_tableJadwalPraktekMouseClicked

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_btnKembaliActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormJadwalPraktek().setVisible(true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableJadwalPraktek;
    private javax.swing.JComboBox<String> txtDokter;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtJam;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
