package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personel extends Kullanici{

    @OneToMany(targetEntity=Gorev.class, mappedBy="personel",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Gorev> gorevs;

    @ManyToOne()
    @JoinColumn(name="takim_id", referencedColumnName = "id")
    private Takim takim;
}
