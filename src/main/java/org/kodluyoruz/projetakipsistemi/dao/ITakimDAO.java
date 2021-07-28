package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Takim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITakimDAO extends JpaRepository<Takim, Integer> {
}
