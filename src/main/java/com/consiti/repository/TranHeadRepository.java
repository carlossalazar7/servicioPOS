package com.consiti.repository;

import java.util.List;

import com.consiti.entity.ReporteDetalleVentas;
import com.consiti.entity.TranHead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TranHeadRepository extends JpaRepository<TranHead,Integer>{
    
    @Query(value = "SELECT TRAN_DATETIME 'Fecha', '' as 'Documento',TENDER_TYPE_ID 'Id',"
                    +" C.CLASS 'Categoria', ITEM_DESC 'Producto', QTY 'Cantidad',"
                    +" (UNIT_RETAIL*0.87) as 'PrecioUnitario', 0 'Costo'"
                    +" FROM"
                    +" TRAN_HEAD A, TRAN_ITEM B, ITEM_MASTER C, TRAN_TENDER D"
                    +" WHERE A.TRAN_SEQ_NO=B.TRAN_SEQ_NO"
                    +" AND B.ITEM=C.ITEM"
                    +" AND A.TRAN_SEQ_NO = D.TRAN_SEQ_NO"
                    +" AND TRAN_DATETIME BETWEEN '2022-01-01' AND SYSDATE()"
                    +" order by 1 desc limit 10;",
            nativeQuery = true)
	public List<ReporteDetalleVentas> getReporteDetalleVentas();

}
