package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personel extends Kullanici{

    @OneToOne(mappedBy = "personel")
    private Gorev gorev;

    @ManyToOne()
    @JoinColumn(name="takim_id", referencedColumnName = "id")
    private Takim takim;
}
