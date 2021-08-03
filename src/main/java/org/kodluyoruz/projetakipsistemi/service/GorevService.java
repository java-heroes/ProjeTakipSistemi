package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.exception.GorevNotFoundException;
import org.kodluyoruz.projetakipsistemi.exception.PersonelNotFoundException;
import org.kodluyoruz.projetakipsistemi.exception.ProjeNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IGorevDAO;
import org.kodluyoruz.projetakipsistemi.dao.IPersonelDAO;
import org.kodluyoruz.projetakipsistemi.dao.IProjeDAO;
import org.kodluyoruz.projetakipsistemi.model.Gorev;
import org.kodluyoruz.projetakipsistemi.model.Personel;
import org.kodluyoruz.projetakipsistemi.model.Proje;
import org.kodluyoruz.projetakipsistemi.model.dto.GorevDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GorevService {
    @Autowired
    private IGorevDAO gorevDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private IPersonelDAO personelDAO;
    @Autowired
    private IProjeDAO projeDAO;

    public List<GorevDTO> getAll(){
        try {
            List<GorevDTO> gorevDTOList = new ArrayList<>();
            gorevDAO.findAll().forEach(gorev -> {
                gorevDTOList.add(dozerMapper.map(gorev,GorevDTO.class));
                gorevDTOList.get(gorevDTOList.size()-1).setPersonelId(gorev.getPersonel().getId());
                gorevDTOList.get(gorevDTOList.size()-1).setProjeId(gorev.getProje().getId());
            });
            return gorevDTOList;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public GorevDTO get(int id){
        Optional<Gorev> gorev = gorevDAO.findById(id);
        if (!gorev.isPresent()){
            throw new GorevNotFoundException(id);
        }
        try {
            GorevDTO gorevDTO = dozerMapper.map(gorev.get(),GorevDTO.class);
            gorevDTO.setProjeId(gorev.get().getProje().getId());
            gorevDTO.setPersonelId(gorev.get().getPersonel().getId());
            return gorevDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public GorevDTO add(GorevDTO gorevDTO){
        Optional<Personel> personel = personelDAO.findById(gorevDTO.getPersonelId());
        if (!personel.isPresent()){
            throw new PersonelNotFoundException(gorevDTO.getPersonelId());
        }
        Optional<Proje> proje = projeDAO.findById(gorevDTO.getProjeId());
        if (!proje.isPresent()){
            throw new ProjeNotFoundException(gorevDTO.getProjeId());
        }
        try {
            Gorev gorev = dozerMapper.map(gorevDTO,Gorev.class);
            gorev.setPersonel(personel.get());
            gorev.setProje(proje.get());
            gorevDAO.save(gorev);
            return gorevDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean delete(int id){
        Optional<Gorev> gorev = gorevDAO.findById(id);
        if (!gorev.isPresent()){
            throw new GorevNotFoundException(id);
        }
        try {
            gorevDAO.delete(gorev.get());
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public GorevDTO update(GorevDTO gorevDTO){
        Optional<Gorev> gorev = gorevDAO.findById(gorevDTO.getId());
        if (!gorev.isPresent()){
            throw new GorevNotFoundException(gorevDTO.getId());
        }
        try {
            gorev.get().setGorev(gorevDTO.getGorev());
            gorev.get().setProje(projeDAO.findById(gorevDTO.getProjeId()).get());
            gorev.get().setPersonel(personelDAO.findById(gorevDTO.getPersonelId()).get());
            gorev.get().setGorevDurum(gorevDTO.getGorevDurum());
            gorev.get().setAciklama(gorevDTO.getAciklama());
            gorev.get().setBaslamaTarihi(gorevDTO.getBaslamaTarihi());
            gorev.get().setBitisTarihi(gorevDTO.getBitisTarihi());
            gorevDAO.save(gorev.get());
            return gorevDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public List<GorevDTO> getGorevByProjeId(int id){
        Optional<Proje> proje = projeDAO.findById(id);
        if (!proje.isPresent()){
            throw new ProjeNotFoundException();
        }
        try{
            List<GorevDTO> gorevDTOList = new ArrayList<>();
            gorevDAO.findAllByProje_Id(id).forEach(gorev -> {
                gorevDTOList.add(dozerMapper.map(gorev,GorevDTO.class));
                gorevDTOList.get(gorevDTOList.size()-1).setPersonelId(gorev.getPersonel().getId());
                gorevDTOList.get(gorevDTOList.size()-1).setProjeId(gorev.getProje().getId());
            });
            return gorevDTOList;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

}
