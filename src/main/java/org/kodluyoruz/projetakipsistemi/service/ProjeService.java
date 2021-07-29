package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.core.exception.ProjeNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.TakimNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IProjeDAO;
import org.kodluyoruz.projetakipsistemi.dao.ITakimDAO;
import org.kodluyoruz.projetakipsistemi.model.Proje;
import org.kodluyoruz.projetakipsistemi.model.Takim;
import org.kodluyoruz.projetakipsistemi.model.dto.ProjeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjeService {
    @Autowired
    private IProjeDAO projeDAO;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private ITakimDAO takimDAO;

    public List<ProjeDTO> getAll(){
        try {
            List<ProjeDTO> projeDTOList = new ArrayList<>();
            projeDAO.findAll().forEach(proje -> {
                ProjeDTO projeDTO = new ProjeDTO();
                projeDTO = dozerMapper.map(proje,ProjeDTO.class);
                projeDTO.setTakimId(proje.getTakim().getId());
                projeDTOList.add(projeDTO);
            });
            return projeDTOList;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public ProjeDTO get(int id){
        Optional<Proje> proje = projeDAO.findById(id);
        if (!proje.isPresent()){
            throw new ProjeNotFoundException(id);
        }
        try {
            ProjeDTO projeDTO = new ProjeDTO();
            projeDTO = dozerMapper.map(proje.get(),ProjeDTO.class);
            projeDTO.setTakimId(proje.get().getTakim().getId());
            return projeDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public ProjeDTO add(ProjeDTO projeDTO){
        try {
            Proje proje = new Proje();
            proje = dozerMapper.map(projeDTO,Proje.class);
            Optional<Takim> takim = takimDAO.findById(projeDTO.getTakimId());
            if (!takim.isPresent()){
                throw new TakimNotFoundException(projeDTO.getTakimId());
            }
            proje.setTakim(takim.get());
            projeDAO.save(proje);
            return projeDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public boolean delete(int id){
        Optional<Proje> proje = projeDAO.findById(id);
        if (!proje.isPresent()){
            throw new ProjeNotFoundException(id);
        }
        try {
            projeDAO.delete(proje.get());
            return true;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public ProjeDTO update(ProjeDTO projeDTO){
        Optional<Proje> proje = projeDAO.findById(projeDTO.getId());
        if (!proje.isPresent()){
            throw new ProjeNotFoundException(projeDTO.getId());
        }
        try{
            proje.get().setTakim(takimDAO.findById(projeDTO.getTakimId()).get());
            proje.get().setProje(projeDTO.getProje());
            proje.get().setProjeDurum(projeDTO.getProjeDurum());
            proje.get().setAciklama(projeDTO.getAciklama());
            return projeDTO;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

}
