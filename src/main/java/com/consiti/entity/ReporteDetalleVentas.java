package com.consiti.entity;

import java.sql.Date;

public interface ReporteDetalleVentas {
    /**
     * TRAN_DATETIME 'Fecha',
	   NULL 'Documento',
        TENDER_TYPE_ID 'ID',
	    C.CLASS 'Categoria',
	    ITEM_DESC 'Producto',
        QTY 'Cantidad',
        UNIT_RETAIL*0.87 'Precio Unitario', 
        0 'Costo'
     */
    Date getFecha();
    String getDocumento();
    String getId();
    String getCategoria();
    String getProducto();
    Integer getCantidad();
    Double getPrecioUnitario();
    Double getCosto();

}
