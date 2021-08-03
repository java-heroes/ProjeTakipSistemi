package org.kodluyoruz.projetakipsistemi.service;

import com.github.dozermapper.core.Mapper;
import org.kodluyoruz.projetakipsistemi.exception.MesajNotFoundException;
import org.kodluyoruz.projetakipsistemi.core.exception.ValidationException;
import org.kodluyoruz.projetakipsistemi.dao.IMesajDAO;
import org.kodluyoruz.projetakipsistemi.model.Mesaj;
import org.kodluyoruz.projetakipsistemi.model.dto.MesajDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MesajService {
    @Autowired
    private IMesajDAO mesajDao;
    @Autowired
    private Mapper dozerMapper;

    public List<MesajDTO> getAll () {
        List<MesajDTO> mesajList= new ArrayList<>();
        mesajDao.findAll().forEach(mesaj -> {
            mesajList.add(dozerMapper.map(mesaj, MesajDTO.class));
        });
        return mesajList ;
    }

    public MesajDTO add (MesajDTO mesajDto) {
        try{
            Mesaj mesaj = dozerMapper.map(mesajDto,Mesaj.class);
            mesajDao.save(mesaj);
            return mesajDto ;
        }catch (Exception e){
            throw new ValidationException();
        }
    }


    public MesajDTO update(MesajDTO mesajDto) {
        Optional<Mesaj> mesaj = mesajDao.findById(mesajDto.getId());
        if (!mesaj.isPresent()){
            throw new MesajNotFoundException(mesajDto.getId());
        }
        try {
            mesaj.get().setAlici(mesajDto.getAlici());
            mesaj.get().setGonderen(mesajDto.getGonderen());
            mesaj.get().setBaslik(mesajDto.getBaslik());
            mesaj.get().setMesajinIcerigi(mesajDto.getMesajinIcerigi());
            mesajDao.save(mesaj.get());
            return mesajDto;
        }catch (Exception e){
            throw new ValidationException();
        }
    }


    public MesajDTO delete(MesajDTO mesajDto) {
        Optional<Mesaj> mesaj = mesajDao.findById(mesajDto.getId());
        if (!mesaj.isPresent()){
            throw new MesajNotFoundException(mesajDto.getId());
        }
        try {
            mesajDao.delete(dozerMapper.map(mesajDto,Mesaj.class));
            return mesajDto;
        }catch (Exception e){
            throw new ValidationException();
        }
    }

    public MesajDTO get(int id) {
        Optional<Mesaj> mesaj = mesajDao.findById(id);
        if (!mesaj.isPresent()){
            throw new MesajNotFoundException(id);
        }
        try {
            return dozerMapper.map(mesaj, MesajDTO.class);
        }catch (Exception e){
            throw new ValidationException();
        }
    }
}
