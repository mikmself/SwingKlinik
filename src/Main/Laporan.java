package Main;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import klinik.CRUD;

public class Laporan extends javax.swing.JFrame {
    CRUD Data = new CRUD();
    String status = "";
    public Laporan() {
        initComponents();
        Data.Koneksi();
        TampilDataDokter();
        TampilDataPasien();
        TampilDataPendaftaran();
        TampilDataRekamMedis();
        TampilDataObat();
        TampilDataRawatJalan();
    }
    public void TampilDataDokter(){
        String namaTable = "dokter";
        String daftarField[]  = {
            "id_dokter",
            "nama_dokter",
            "spesialis",
            "telephone",
            "harga",            
            "status"
        };
        String judulKolom[] = {
            "ID Dokter",            
            "Nama",
            "Spesialis",
            "Telephone",
            "Harga",
            "Status"
        };
        tableDokter.setModel(Data.TampilData(namaTable, daftarField, judulKolom, null, null, null, null));
    }
    public void TampilDataPasien(){
        String namaTable = "pasien";
        String daftarField[]  = {
            "id_pasien",
            "nama_pasien",
            "gejala",
            "alamat",
            "telephone",            
        };
        String judulKolom[] = {
            "ID Pasien",            
            "Nama",
            "Gejala",
            "Alamat",
            "Telephone",
        };
        tablePasien.setModel(Data.TampilData(namaTable, daftarField, judulKolom, null, null, null, null));
    }
    public void TampilDataPendaftaran(){
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
    public void TampilDataRekamMedis(){
        String namaTable = "rekam_medis";
        String daftarField[]  = {
            "rekam_medis.id_rekam_medis",
            "pasien.nama_pasien",
            "dokter.nama_dokter",
            "rekam_medis.tanggal_periksa",
            "rekam_medis.diagnosa",            
            "rekam_medis.resep"
        };
        String judulKolom[] = {
            "ID Rekam Medis",            
            "Pasien",
            "Dokter",
            "Tanggal Periksa",
            "Diagnosa",
            "Resep"
        };
        String joinClause1 = "INNER JOIN pasien";
        String onClause1 = "rekam_medis.id_pasien = pasien.id_pasien";
        String joinClause2 = "INNER JOIN dokter";
        String onClause2 = "rekam_medis.id_dokter = dokter.id_dokter";
        tableRekamMedis.setModel(Data.TampilData(namaTable, daftarField, judulKolom, joinClause1, onClause1, joinClause2, onClause2));
    }
    public void TampilDataObat(){
        String namaTable = "obat";
        String daftarField[]  = {
            "obat.id_obat",
            "obat.kode_obat",
            "dokter.nama_dokter",
            "pasien.nama_pasien",
            "obat.jumlah",
            "obat.harga"
        };
        String judulKolom[] = {
            "ID Obat",
            "Kode Obat",            
            "Dokter",
            "Pasien",
            "Jumlah",
            "Harga"
        };
        String joinClause1 = "INNER JOIN pasien";
        String onClause1 = "obat.id_pasien = pasien.id_pasien";
        String joinClause2 = "INNER JOIN dokter";
        String onClause2 = "obat.id_dokter = dokter.id_dokter";
        tableObat.setModel(Data.TampilData(namaTable, daftarField, judulKolom, joinClause1, onClause1, joinClause2, onClause2));
    }
    public void TampilDataRawatJalan(){
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDokter = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePasien = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePendaftaran = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableRawatJalan = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableRekamMedis = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableObat = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setText("Laporan");

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("Data Dokter");

        tableDokter.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableDokter);

        tablePasien.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablePasien);

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("Data Pasien");

        jLabel4.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel4.setText("Data Pendaftaran");

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
        jScrollPane3.setViewportView(tablePendaftaran);

        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("Data Rawat Jalan");

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
        jScrollPane4.setViewportView(tableRawatJalan);

        tableRekamMedis.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tableRekamMedis);

        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setText("Data Rekam Medis");

        tableObat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tableObat);

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setText("Data Obat");

        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        updateLaporan();
    }//GEN-LAST:event_btnCetakActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }
     private void updateLaporan() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "");
            PreparedStatement dokterStmt = conn.prepareStatement("SELECT * FROM dokter");
            ResultSet dokterResult = dokterStmt.executeQuery();
            PreparedStatement pasienStmt = conn.prepareStatement("SELECT * FROM pasien");
            ResultSet pasienResult = pasienStmt.executeQuery();
            PreparedStatement pendaftaranStmt = conn.prepareStatement("SELECT * FROM pendaftaran");
            ResultSet pendaftaranResult = pendaftaranStmt.executeQuery();
            PreparedStatement rekamMedisStmt = conn.prepareStatement("SELECT * FROM rekam_medis");
            ResultSet rekamMedisResult = rekamMedisStmt.executeQuery();
            PreparedStatement obatStmt = conn.prepareStatement("SELECT * FROM obat");
            ResultSet obatResult = obatStmt.executeQuery();
            PreparedStatement rawatJalanStmt = conn.prepareStatement("SELECT * FROM rawat_jalan");
            ResultSet rawatJalanResult = rawatJalanStmt.executeQuery();

            PdfPTable tableDokter = new PdfPTable(6);
            tableDokter.addCell("ID Dokter");
            tableDokter.addCell("Nama Dokter");
            tableDokter.addCell("Spesialisasi");
            tableDokter.addCell("Jenis Kelamin");
            tableDokter.addCell("Alamat");
            tableDokter.addCell("No. Telepon");
            while (dokterResult.next()) {
                tableDokter.addCell(dokterResult.getString(1));  
                tableDokter.addCell(dokterResult.getString(2));  
                tableDokter.addCell(dokterResult.getString(3));  
                tableDokter.addCell(dokterResult.getString(4));  
                tableDokter.addCell(dokterResult.getString(5));  
                tableDokter.addCell(dokterResult.getString(6));  
            }

            PdfPTable tablePasien = new PdfPTable(5);
            tablePasien.addCell("ID Pasien");
            tablePasien.addCell("Nama Pasien");
            tablePasien.addCell("Umur");
            tablePasien.addCell("Jenis Kelamin");
            tablePasien.addCell("Alamat");
            while (pasienResult.next()) {
                tablePasien.addCell(pasienResult.getString(1));  
                tablePasien.addCell(pasienResult.getString(2));  
                tablePasien.addCell(pasienResult.getString(3));  
                tablePasien.addCell(pasienResult.getString(4));  
                tablePasien.addCell(pasienResult.getString(5));  
            }

            PdfPTable tablePendaftaran = new PdfPTable(4);
            tablePendaftaran.addCell("ID Pendaftaran");
            tablePendaftaran.addCell("ID Pasien");
            tablePendaftaran.addCell("ID Dokter");
            tablePendaftaran.addCell("Tgl Pendaftaran");
            while (pendaftaranResult.next()) {
                tablePendaftaran.addCell(pendaftaranResult.getString(1)); 
                tablePendaftaran.addCell(pendaftaranResult.getString(2)); 
                tablePendaftaran.addCell(pendaftaranResult.getString(3));  
                tablePendaftaran.addCell(pendaftaranResult.getString(4)); 
            }

            PdfPTable tableRekamMedis = new PdfPTable(6);
            tableRekamMedis.addCell("ID Rekam Medis");
            tableRekamMedis.addCell("ID Pasien");
            tableRekamMedis.addCell("ID Dokter");
            tableRekamMedis.addCell("Tgl Periksa");
            tableRekamMedis.addCell("Diagnosa");
            tableRekamMedis.addCell("Resep Obat");
            while (rekamMedisResult.next()) {
                tableRekamMedis.addCell(rekamMedisResult.getString(1));  
                tableRekamMedis.addCell(rekamMedisResult.getString(2));  
                tableRekamMedis.addCell(rekamMedisResult.getString(3)); 
                tableRekamMedis.addCell(rekamMedisResult.getString(4));  
                tableRekamMedis.addCell(rekamMedisResult.getString(5)); 
                tableRekamMedis.addCell(rekamMedisResult.getString(6)); 
            }

            PdfPTable tableObat = new PdfPTable(6);
            tableObat.addCell("ID Obat");
            tableObat.addCell("Nama Obat");
            tableObat.addCell("Jenis Obat");
            tableObat.addCell("Jumlah Stok");
            tableObat.addCell("Harga");
            tableObat.addCell("Kadaluarsa");
            while (obatResult.next()) {
                tableObat.addCell(obatResult.getString(1));  // Kolom 1
                tableObat.addCell(obatResult.getString(2));  // Kolom 2
                tableObat.addCell(obatResult.getString(3));  // Kolom 3
                tableObat.addCell(obatResult.getString(4));  // Kolom 4
                tableObat.addCell(obatResult.getString(5));  // Kolom 5
                tableObat.addCell(obatResult.getString(6));  // Kolom 6
            }

            PdfPTable tableRawatJalan = new PdfPTable(7);
            tableRawatJalan.addCell("ID Rawat Jalan");
            tableRawatJalan.addCell("ID Pasien");
            tableRawatJalan.addCell("ID Dokter");
            tableRawatJalan.addCell("Tgl Rawat Jalan");
            tableRawatJalan.addCell("Diagnosa");
            tableRawatJalan.addCell("Resep Obat");
            tableRawatJalan.addCell("Biaya");
            while (rawatJalanResult.next()) {
                tableRawatJalan.addCell(rawatJalanResult.getString(1));  
                tableRawatJalan.addCell(rawatJalanResult.getString(2));  
                tableRawatJalan.addCell(rawatJalanResult.getString(3)); 
                tableRawatJalan.addCell(rawatJalanResult.getString(4));  
                tableRawatJalan.addCell(rawatJalanResult.getString(5));  
                tableRawatJalan.addCell(rawatJalanResult.getString(6));  
                tableRawatJalan.addCell(rawatJalanResult.getString(7));  
            }

            createPDF(tableDokter, tablePasien, tablePendaftaran, tableRekamMedis, tableObat, tableRawatJalan);

        } catch (SQLException | DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createPDF(PdfPTable tableDokter, PdfPTable tablePasien, PdfPTable tablePendaftaran, PdfPTable tableRekamMedis, PdfPTable tableObat, PdfPTable tableRawatJalan) throws DocumentException, FileNotFoundException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, new FileOutputStream("laporan.pdf"));
        doc.open();

        Paragraph title = new Paragraph("Laporan Klinik");
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        doc.add(title);

        doc.add(new Paragraph("\n"));

        // Menambahkan judul dan tabel untuk data dokter
        Paragraph judulDokter = new Paragraph("Data Dokter");
        judulDokter.setSpacingAfter(10);
        doc.add(judulDokter);
        tableDokter.setWidthPercentage(100);
        doc.add(tableDokter);

        // Menambahkan judul dan tabel untuk data pasien
        Paragraph judulPasien = new Paragraph("Data Pasien");
        judulPasien.setSpacingAfter(10);
        doc.add(judulPasien);
        tablePasien.setWidthPercentage(100);
        doc.add(tablePasien);

        // Menambahkan judul dan tabel untuk data pendaftaran
        Paragraph judulPendaftaran = new Paragraph("Data Pendaftaran");
        judulPendaftaran.setSpacingAfter(10);
        doc.add(judulPendaftaran);
        tablePendaftaran.setWidthPercentage(100);
        doc.add(tablePendaftaran);

        // Menambahkan judul dan tabel untuk data rekam medis
        Paragraph judulRekamMedis = new Paragraph("Data Rekam Medis");
        judulRekamMedis.setSpacingAfter(10);
        doc.add(judulRekamMedis);
        tableRekamMedis.setWidthPercentage(100);
        doc.add(tableRekamMedis);

        // Menambahkan judul dan tabel untuk data obat
        Paragraph judulObat = new Paragraph("Data Obat");
        judulObat.setSpacingAfter(10);
        doc.add(judulObat);
        tableObat.setWidthPercentage(100);
        doc.add(tableObat);

        // Menambahkan judul dan tabel untuk data rawat jalan
        Paragraph judulRawatJalan = new Paragraph("Data Rawat Jalan");
        judulRawatJalan.setSpacingAfter(10);
        doc.add(judulRawatJalan);
        tableRawatJalan.setWidthPercentage(100);
        doc.add(tableRawatJalan);

        doc.close();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tableDokter;
    private javax.swing.JTable tableObat;
    private javax.swing.JTable tablePasien;
    private javax.swing.JTable tablePendaftaran;
    private javax.swing.JTable tableRawatJalan;
    private javax.swing.JTable tableRekamMedis;
    // End of variables declaration//GEN-END:variables
}
