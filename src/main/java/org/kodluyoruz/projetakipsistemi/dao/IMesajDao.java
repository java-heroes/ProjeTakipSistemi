package org.kodluyoruz.projetakipsistemi.dao;

import org.kodluyoruz.projetakipsistemi.model.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMesajDao extends JpaRepository<Mesaj,Integer> {



}
