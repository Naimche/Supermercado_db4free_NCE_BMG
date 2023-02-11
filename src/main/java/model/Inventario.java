package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "inventario")
public class Inventario {
    private List<Producto> productos;

}