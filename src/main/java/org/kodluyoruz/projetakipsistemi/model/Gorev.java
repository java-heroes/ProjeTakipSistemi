package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.projetakipsistemi.model.enums.Durum;
import org.kodluyoruz.projetakipsistemi.model.enums.Yetki;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gorev")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gorev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "gorev", nullable = false)
    private String gorev;

    @Column(name = "aciklama", nullable = false)
    private String aciklama;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "baslama_tarihi")
    private Date baslamaTarihi;

    @Column(name = "bitis_tarihi")
    private Date bitisTarihi;

    @Column(name = "gorev_durum", nullable = false)
    private int gorevDurum;

    @Transient
    private Durum durum;

    @PostLoad
    void fillTransient() {
        if (gorevDurum > 0) {
            this.durum = Durum.of(gorevDurum);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (durum != null) {
            this.gorevDurum = durum.getDurum();
        }
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personel_id", referencedColumnName = "id")
    private Personel personel;

    @ManyToOne()
    @JoinColumn(name="proje_id", referencedColumnName = "id")
    private Proje proje;

}
