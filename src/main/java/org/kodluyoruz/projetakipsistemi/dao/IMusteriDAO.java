package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.model.Teklif;
import org.kodluyoruz.projetakipsistemi.model.dto.TeklifDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusteriDAO extends JpaRepository<Musteri, Integer> {
    Musteri findByKullaniciAdiAndSifre(String kullaniciAdi, String sifre);
    Musteri findByMail(String mail);
    Musteri findByKullaniciAdiOrMail(String kullaniciAdi, String mail);
    Musteri findByTeklifs(Teklif teklif);
}
