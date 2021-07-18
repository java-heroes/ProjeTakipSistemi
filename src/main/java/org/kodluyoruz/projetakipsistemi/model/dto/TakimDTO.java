package org.kodluyoruz.projetakipsistemi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakimDTO {
    private int id;
    private String takim;
    private String aciklama;
    private List<PersonelDTO> personels;
    private List<ProjeDTO> projes;
}
