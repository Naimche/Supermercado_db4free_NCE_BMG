package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "afiliacion")
public class Afiliacion {
    @Id
    @Column(name = "idAfiliacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
