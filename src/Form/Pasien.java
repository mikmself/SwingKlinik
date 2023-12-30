package Form;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "pasien", catalog = "klinik", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pasien.findAll", query = "SELECT p FROM Pasien p")
    , @NamedQuery(name = "Pasien.findByIdPasien", query = "SELECT p FROM Pasien p WHERE p.idPasien = :idPasien")
    , @NamedQuery(name = "Pasien.findByNamaPasien", query = "SELECT p FROM Pasien p WHERE p.namaPasien = :namaPasien")
    , @NamedQuery(name = "Pasien.findByGejala", query = "SELECT p FROM Pasien p WHERE p.gejala = :gejala")
    , @NamedQuery(name = "Pasien.findByAlamat", query = "SELECT p FROM Pasien p WHERE p.alamat = :alamat")
    , @NamedQuery(name = "Pasien.findByTelephone", query = "SELECT p FROM Pasien p WHERE p.telephone = :telephone")})
public class Pasien implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pasien")
    private Integer idPasien;
    @Basic(optional = false)
    @Column(name = "nama_pasien")
    private String namaPasien;
    @Basic(optional = false)
    @Column(name = "gejala")
    private String gejala;
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;

    public Pasien() {
    }

    public Pasien(Integer idPasien) {
        this.idPasien = idPasien;
    }

    public Pasien(Integer idPasien, String namaPasien, String gejala, String alamat, String telephone) {
        this.idPasien = idPasien;
        this.namaPasien = namaPasien;
        this.gejala = gejala;
        this.alamat = alamat;
        this.telephone = telephone;
    }

    public Integer getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Integer idPasien) {
        Integer oldIdPasien = this.idPasien;
        this.idPasien = idPasien;
        changeSupport.firePropertyChange("idPasien", oldIdPasien, idPasien);
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        String oldNamaPasien = this.namaPasien;
        this.namaPasien = namaPasien;
        changeSupport.firePropertyChange("namaPasien", oldNamaPasien, namaPasien);
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        String oldGejala = this.gejala;
        this.gejala = gejala;
        changeSupport.firePropertyChange("gejala", oldGejala, gejala);
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        String oldTelephone = this.telephone;
        this.telephone = telephone;
        changeSupport.firePropertyChange("telephone", oldTelephone, telephone);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPasien != null ? idPasien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasien)) {
            return false;
        }
        Pasien other = (Pasien) object;
        if ((this.idPasien == null && other.idPasien != null) || (this.idPasien != null && !this.idPasien.equals(other.idPasien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaPasien;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public static Pasien findByCoulmn(String column) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Pasien idPasien = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "");
            pst = conn.prepareStatement("SELECT * FROM pasien WHERE nama_pasien = ?");
            pst.setString(1, column);
            rs = pst.executeQuery();
            if (rs.next()) {
                idPasien = new Pasien();
                idPasien.setIdPasien(rs.getInt("id_pasien"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return idPasien;
    }
}
