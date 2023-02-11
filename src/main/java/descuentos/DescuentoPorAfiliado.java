package descuentos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "descuentos_por_afiliado")
class DescuentoPorAfiliado extends DescuentoPorCategoria {
    public DescuentoPorAfiliado(double porcentajeDescuento) {
        super(porcentajeDescuento, "Afiliado");
    }
}
