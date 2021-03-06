package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.model.Teklif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITeklifDAO extends JpaRepository<Teklif,Integer> {
    List<Teklif> findAllByMusteri_Id(int id);
}
