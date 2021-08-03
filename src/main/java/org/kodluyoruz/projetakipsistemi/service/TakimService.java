package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.exception.TakimNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IPersonelDAO;
import org.kodluyoruz.projetakipsistemi.dao.ITakimDAO;
import org.kodluyoruz.projetakipsistemi.model.Takim;
import org.kodluyoruz.projetakipsistemi.model.dto.PersonelDTO;
import org.kodluyoruz.projetakipsistemi.model.dto.TakimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TakimService {
    @Autowired
    private ITakimDAO takimDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private IPersonelDAO personelDAO;

    public List<TakimDTO> getAll(){
        try {
            List<TakimDTO> takimDTOS = new ArrayList<>();
            takimDAO.findAll().forEach(takim -> {
                TakimDTO takimDTO = dozerMapper.map(takim,TakimDTO.class);
                List<PersonelDTO> personelDTOS = new ArrayList<>();
                personelDAO.findAllByTakim_Id(takim.getId()).forEach(personel -> {
                    PersonelDTO personelDTO = dozerMapper.map(personel,PersonelDTO.class);
                    personelDTO.setTakimId(personel.getTakim().getId());
                    personelDTOS.add(personelDTO);
                });
                takimDTO.setPersonels(personelDTOS);
                takimDTOS.add(takimDTO);
            });
            return takimDTOS;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public TakimDTO get(int id){
        Optional<Takim> takim = takimDAO.findById(id);
        if (!takim.isPresent()){
            throw new TakimNotFoundException(id);
        }
        try {
            return dozerMapper.map(takim.get(),TakimDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public TakimDTO add(TakimDTO takimDTO){
        try {
            takimDAO.save(dozerMapper.map(takimDTO,Takim.class));
            return takimDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public TakimDTO update(TakimDTO takimDTO){
        Optional<Takim> takim = takimDAO.findById(takimDTO.getId());
        if (!takim.isPresent()){
            throw new TakimNotFoundException(takimDTO.getId());
        }
        try {
            takim.get().setTakim(takimDTO.getTakim());
            takim.get().setAciklama(takimDTO.getAciklama());
            takimDAO.save(takim.get());
            return dozerMapper.map(takim.get(),TakimDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public TakimDTO delete(int id){
        Optional<Takim> takim = takimDAO.findById(id);
        if (!takim.isPresent()){
            throw new TakimNotFoundException(id);
        }
        try {
            takimDAO.delete(takim.get());
            return dozerMapper.map(takim.get(),TakimDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }
}
