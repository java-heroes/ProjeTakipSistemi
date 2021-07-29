package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Gorev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGorevDAO extends JpaRepository<Gorev, Integer> {
}
