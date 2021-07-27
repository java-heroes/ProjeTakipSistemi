package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Inheritance
@Table(name="kullanici")
@SQLDelete(sql = "UPDATE kullanici SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "kullanici_adi", nullable = false, unique = true)
    private String kullaniciAdi;

    @Column(name = "sifre", nullable = false)
    private String sifre;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

    @Column(name = "mail",nullable = false, unique = true)
    @Email(message = "Geçerli bir e-posta alanı girin")
    private String mail;

    @Column(name = "telefon",nullable = true)
    private String telefon;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;
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
