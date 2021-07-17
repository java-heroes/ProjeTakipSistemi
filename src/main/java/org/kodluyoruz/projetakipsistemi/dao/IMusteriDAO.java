package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusteriDAO extends JpaRepository<Musteri, Integer> {
    Musteri findByKullaniciAdiAndSifre(String kullaniciAdi, String sifre);
    Musteri findByMail(String mail);
}
