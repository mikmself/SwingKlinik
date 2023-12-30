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
@Table(name = "dokter", catalog = "klinik", schema = "")
@NamedQueries({
    @NamedQuery(name = "Dokter.findAll", query = "SELECT d FROM Dokter d")
    , @NamedQuery(name = "Dokter.findByIdDokter", query = "SELECT d FROM Dokter d WHERE d.idDokter = :idDokter")
    , @NamedQuery(name = "Dokter.findByNamaDokter", query = "SELECT d FROM Dokter d WHERE d.namaDokter = :namaDokter")
    , @NamedQuery(name = "Dokter.findBySpesialis", query = "SELECT d FROM Dokter d WHERE d.spesialis = :spesialis")
    , @NamedQuery(name = "Dokter.findByTelephone", query = "SELECT d FROM Dokter d WHERE d.telephone = :telephone")
    , @NamedQuery(name = "Dokter.findByHarga", query = "SELECT d FROM Dokter d WHERE d.harga = :harga")
    , @NamedQuery(name = "Dokter.findByStatus", query = "SELECT d FROM Dokter d WHERE d.status = :status")})
public class Dokter implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dokter")
    private Integer idDokter;
    @Column(name = "nama_dokter")
    private String namaDokter;
    @Column(name = "spesialis")
    private String spesialis;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "harga")
    private int harga;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public Dokter() {
    }

    public Dokter(Integer idDokter) {
        this.idDokter = idDokter;
    }

    public Dokter(Integer idDokter, String telephone, int harga, String status) {
        this.idDokter = idDokter;
        this.telephone = telephone;
        this.harga = harga;
        this.status = status;
    }

    public Integer getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Integer idDokter) {
        Integer oldIdDokter = this.idDokter;
        this.idDokter = idDokter;
        changeSupport.firePropertyChange("idDokter", oldIdDokter, idDokter);
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        String oldNamaDokter = this.namaDokter;
        this.namaDokter = namaDokter;
        changeSupport.firePropertyChange("namaDokter", oldNamaDokter, namaDokter);
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        String oldSpesialis = this.spesialis;
        this.spesialis = spesialis;
        changeSupport.firePropertyChange("spesialis", oldSpesialis, spesialis);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        String oldTelephone = this.telephone;
        this.telephone = telephone;
        changeSupport.firePropertyChange("telephone", oldTelephone, telephone);
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        int oldHarga = this.harga;
        this.harga = harga;
        changeSupport.firePropertyChange("harga", oldHarga, harga);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDokter != null ? idDokter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dokter)) {
            return false;
        }
        Dokter other = (Dokter) object;
        if ((this.idDokter == null && other.idDokter != null) || (this.idDokter != null && !this.idDokter.equals(other.idDokter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaDokter;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public static Dokter findByCoulmn(String column) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Dokter idDokter = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "");
            pst = conn.prepareStatement("SELECT * FROM dokter WHERE nama_dokter = ?");
            pst.setString(1, column);
            rs = pst.executeQuery();
            if (rs.next()) {
                idDokter = new Dokter();
                idDokter.setIdDokter(rs.getInt("id_dokter"));
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
        return idDokter;
    }
}
