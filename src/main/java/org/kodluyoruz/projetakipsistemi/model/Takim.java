package org.kodluyoruz.projetakipsistemi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "takim")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Takim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "takim")
    private String takim;

    @Column(name = "aciklama")
    private String aciklama;

//    @OneToMany(targetEntity=Personel.class, mappedBy="takim",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Personel> personels = new ArrayList<>();

    @OneToMany(targetEntity=Proje.class, mappedBy="takim",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Proje> projes = new ArrayList<>();
}
