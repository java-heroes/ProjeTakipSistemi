package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.core.exception.ProjeNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.SonucRaporuNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IProjeDAO;
import org.kodluyoruz.projetakipsistemi.dao.ISonucRaporuDAO;
import org.kodluyoruz.projetakipsistemi.model.Proje;
import org.kodluyoruz.projetakipsistemi.model.SonucRaporu;
import org.kodluyoruz.projetakipsistemi.model.dto.SonucRaporuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SonucRaporuService {
    @Autowired
    private ISonucRaporuDAO sonucRaporuDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private IProjeDAO projeDAO;

    public List<SonucRaporuDTO> getAll(){
        try {
            List<SonucRaporuDTO> sonucRaporuDTOList = new ArrayList<>();
            sonucRaporuDAO.findAll().forEach(sonucRaporu -> {
                SonucRaporuDTO sonucRaporuDTO = new SonucRaporuDTO();
                sonucRaporuDTO = dozerMapper.map(sonucRaporu,SonucRaporuDTO.class);
                sonucRaporuDTO.setProjeId(sonucRaporu.getProje().getId());
                sonucRaporuDTOList.add(sonucRaporuDTO);
            });
            return sonucRaporuDTOList;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public SonucRaporuDTO get(int id){
        Optional<SonucRaporu> sonucRaporu = sonucRaporuDAO.findById(id);
        if (!sonucRaporu.isPresent()){
            throw new SonucRaporuNotFoundException(id);
        }
        try {
            SonucRaporuDTO sonucRaporuDTO = new SonucRaporuDTO();
            sonucRaporuDTO = dozerMapper.map(sonucRaporu.get(),SonucRaporuDTO.class);
            sonucRaporuDTO.setProjeId(sonucRaporu.get().getProje().getId());
            return sonucRaporuDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public SonucRaporuDTO add(SonucRaporuDTO sonucRaporuDTO){
        Optional<Proje> proje = projeDAO.findById(sonucRaporuDTO.getProjeId());
        if (!proje.isPresent()){
            throw new ProjeNotFoundException(sonucRaporuDTO.getProjeId());
        }
        try {

            SonucRaporu save = dozerMapper.map(sonucRaporuDTO, SonucRaporu.class);
            save.setProje(proje.get());
            sonucRaporuDAO.save(save);
            return sonucRaporuDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean delete(int id){
        Optional<SonucRaporu> sonucRaporu = sonucRaporuDAO.findById(id);
        if (!sonucRaporu.isPresent()){
            throw new SonucRaporuNotFoundException(id);
        }
        try {
            sonucRaporuDAO.delete(sonucRaporu.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public SonucRaporuDTO update(SonucRaporuDTO sonucRaporuDTO){
        Optional<SonucRaporu> sonucRaporu = sonucRaporuDAO.findById(sonucRaporuDTO.getId());
        if (!sonucRaporu.isPresent()){
            throw new SonucRaporuNotFoundException(sonucRaporuDTO.getId());
        }
        Optional<Proje> proje = projeDAO.findById(sonucRaporuDTO.getProjeId());
        if (!proje.isPresent()){
            throw new ProjeNotFoundException(sonucRaporuDTO.getProjeId());
        }
        try {
            sonucRaporu.get().setAciklama(sonucRaporuDTO.getAciklama());
            sonucRaporu.get().setProje(proje.get());
            sonucRaporuDAO.save(sonucRaporu.get());
            return sonucRaporuDTO;
        }catch (Exception e){
            throw new ValidationException();
        }

    }
}
