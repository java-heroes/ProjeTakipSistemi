package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Proje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjeDAO extends JpaRepository<Proje, Integer> {
}
