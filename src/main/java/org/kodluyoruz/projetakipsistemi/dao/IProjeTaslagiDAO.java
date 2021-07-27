package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Musteri;
import org.kodluyoruz.projetakipsistemi.model.ProjeTaslagi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProjeTaslagiDAO extends JpaRepository<ProjeTaslagi, Integer> {
    List<ProjeTaslagi> findAllByMusteri_Id(int id);
}
