package com.consiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.consiti.entity.Metric;
import com.consiti.entity.Reportes;

@Repository
public interface MetricEmployeesRepository extends JpaRepository<Metric, Integer>{
	/*@Query(value="SELECT A.SALESPERSON ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS,COUNT(*)\r\n"
			+ "			FROM ConsitiPOS.TRAN_HEAD A, ConsitiPOS.STORE_DAY B, ConsitiPOS.METRIC C\r\n"
			+ "			WHERE A.TRAN_TYPE IN (\r\n"
			+ "			SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "			WHERE TYPE_CODE = 'SALE'\r\n"
			+ "			)\r\n"
			+ "			AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "			AND C.NAME = 'VentasPorEmpleado'\r\n"
			+ "			AND C.type_code = 'SP'\r\n"
			+ "			AND ATTR1 = :period\r\n"
			+ "			AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()\r\n"
			+ "			GROUP BY A.SALESPERSON, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getVentasDiarias(@Param("period") String period);
	
	@Query(value="", nativeQuery = true)
	public List<Reportes> getVentasSemanales(@Param("period") String period);

	@Query(value="", nativeQuery = true)
	public List<Reportes> getVentasMensuales(@Param("period") String period);*/


}
