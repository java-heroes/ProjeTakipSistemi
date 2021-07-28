package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Personel;
import org.kodluyoruz.projetakipsistemi.model.Yonetici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonelDAO extends JpaRepository<Personel, Integer> {
    Personel findByKullaniciAdiAndSifre(String kullaniciAdi, String sifre);
    Personel findByMail(String mail);
    Personel findByKullaniciAdiOrMail(String kullaniciAdi, String mail);
    List<Personel> findAllByTakim_Id(int id);
}
