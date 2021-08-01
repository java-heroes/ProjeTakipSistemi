package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Gorev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGorevDAO extends JpaRepository<Gorev, Integer> {
    List<Gorev> findAllByProje_Id(int id);
}
