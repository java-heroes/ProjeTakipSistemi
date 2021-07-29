package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.SonucRaporu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISonucRaporuDAO extends JpaRepository<SonucRaporu, Integer> {
}
