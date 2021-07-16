package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.projetakipsistemi.model.enums.Yetki;

import javax.persistence.*;

@Entity
@Inheritance
@Table(name="kullanici")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "kullanici_adi", nullable = false)
    private String kullaniciAdi;

    @Column(name = "sifre", nullable = false)
    private String sifre;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

//    @Column(name = "kullanici_yetki", nullable = false)
//    private int kullaniciYetki;
//
//    @Transient
//    private Yetki yetki;
//
//    @PostLoad
//    void fillTransient() {
//        if (kullaniciYetki > 0) {
//            this.yetki = Yetki.of(kullaniciYetki);
//        }
//    }
//
//    @PrePersist
//    void fillPersistent() {
//        if (yetki != null) {
//            this.kullaniciYetki = yetki.getYetki();
//        }
//    }

}
