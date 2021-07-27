package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Yonetici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IYoneticiDAO extends JpaRepository<Yonetici, Integer> {
    Yonetici findByKullaniciAdiAndSifre(String kullaniciAdi, String sifre);
    Yonetici findByMail(String mail);
    Yonetici findByKullaniciAdiOrMail(String kullaniciAdi, String mail);
}
