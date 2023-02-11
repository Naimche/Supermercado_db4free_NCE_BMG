package descuentos;


import interfaces.Descuento;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "descuentos_por_categoria")
abstract class DescuentoPorCategoria implements Descuento {
    // Porcentaje de descuento para clientes afiliados
    private final double porcentajeDescuento;
    // Categoria a la que se aplica el descuento
    private final String categoria;

    // Constructor que inicializa el porcentaje de descuento y la categoria a la que se aplica
    public DescuentoPorCategoria(double porcentajeDescuento, String categoria) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.categoria = categoria;
    }

    // Método para calcular el descuento aplicado a un producto o pedido
    @Override
    public double aplicarDescuento(double monto) {
        // Aplicando el descuento del porcentaje especificado
        return monto - (monto * porcentajeDescuento / 100);
    }

}