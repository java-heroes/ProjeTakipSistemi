package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.projetakipsistemi.model.enums.Durum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proje")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "proje")
    private String proje;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "proje_durum", nullable = false)
    private int projeDurum;

    @Transient
    private Durum durum;

    @PostLoad
    void fillTransient() {
        if (projeDurum > 0) {
            this.durum = Durum.of(projeDurum);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (durum != null) {
            this.projeDurum = durum.getDurum();
        }
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "bitis_tarihi")
    private Date bitisTarihi;

    @ManyToOne()
    @JoinColumn(name="takim_id", referencedColumnName = "id")
    private Takim takim;

    @OneToMany(targetEntity=Gorev.class, mappedBy="proje",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Gorev> gorevs = new ArrayList<>();

    @OneToMany(targetEntity=SonucRaporu.class, mappedBy="proje",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SonucRaporu> sonucRaporus;
}
