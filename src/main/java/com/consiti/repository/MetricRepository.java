package com.consiti.repository;

import com.consiti.entity.Reportes;
import com.consiti.entity.Labels;
import com.consiti.entity.Metric;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer>{
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', AVG(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS \r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'SALE'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'AvgTicket'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()\r\n"
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE;", nativeQuery = true)
	List<Reportes>  getTicket(String attr);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'AvgTicket'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
	Labels getLabelsTicket(String attr);
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', AVG(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS \r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'SALE'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'AvgTicket'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND A.STORE = ?2 #Parametro Store\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()\r\n"
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE;", nativeQuery = true)
	List<Reportes>  getTicketByStore(String attr, Integer store);
	
	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS" 
			+" FROM ConsitiPOS.TRAN_HEAD A, ConsitiPOS.STORE_DAY B, ConsitiPOS.METRIC C, ConsitiPOS.STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+")"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'VentaConIVA'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = :period"
			+" and A.STATUS ='0'"
			+" AND D.STORE = A.STORE"
			+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getVentasDiarias(@Param("period") String period);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsVentasDiarias(String attr);

	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS" 
			+" FROM ConsitiPOS.TRAN_HEAD A, ConsitiPOS.STORE_DAY B, ConsitiPOS.METRIC C, ConsitiPOS.STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+")"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'VentaConIVA'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = :period"
			+" AND A.STORE = :store"
			+" and A.STATUS ='0'"
			+" AND D.STORE = A.STORE"
			+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getVentasDiariasByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(E.QTY) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS" 
					+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D, TRAN_ITEM E"
					+" WHERE A.TRAN_TYPE IN ("
					+" SELECT TRAN_TYPE FROM TRAN_TYPES"
					+" WHERE TYPE_CODE = 'SALE'"
					+")"
					+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
					+" AND C.NAME = 'UnidadesVendidas'"
					+" AND C.type_code = 'TM'"
					+" AND ATTR1 = :period"
					+" AND D.STORE = A.STORE"
					+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
					+" AND E.TRAN_SEQ_NO = A.TRAN_SEQ_NO"
					+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getUnidadesVendidas(@Param("period") String period);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'UnidadesVendidas'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsUnidadesVendidas(String attr);
	
	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(E.QTY) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS" 
					+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D, TRAN_ITEM E"
					+" WHERE A.TRAN_TYPE IN ("
					+" SELECT TRAN_TYPE FROM TRAN_TYPES"
					+" WHERE TYPE_CODE = 'SALE'"
					+")"
					+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
					+" AND C.NAME = 'UnidadesVendidas'"
					+" AND C.type_code = 'TM'"
					+" AND ATTR1 = :period"
					+" AND A.STORE = :store"
					+" AND D.STORE = A.STORE"
					+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
					+" AND E.TRAN_SEQ_NO = A.TRAN_SEQ_NO"
					+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getUnidadesVendidasByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS \r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'MISC'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE;",nativeQuery=true)
	public List<Reportes> getGastos(String attr);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsGastos(String attr);
	
	
	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS \r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'MISC'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND A.STORE = ?2 #Parametro Store\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE;", nativeQuery=true)
	public List<Reportes> getGastosByStore(String attr, Integer store);
    
	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS" 
			+" FROM ConsitiPOS.TRAN_HEAD A, ConsitiPOS.STORE_DAY B, ConsitiPOS.METRIC C, ConsitiPOS.STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+")"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'VentaConIVA'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = :period"
			+" and A.STATUS ='1'"
			+" AND D.STORE = A.STORE"
			+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getAnulaciones(@Param("period") String period);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsAnulaciones(String attr);

	@Query(value="SELECT D.STORE_NAME ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS" 
			+" FROM ConsitiPOS.TRAN_HEAD A, ConsitiPOS.STORE_DAY B, ConsitiPOS.METRIC C, ConsitiPOS.STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+")"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'VentaConIVA'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = :period"
			+" AND A.STORE = :store"
			+" and A.STATUS ='1'"
			+" AND D.STORE = A.STORE"
			+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getAnulacionesByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT A.CASHIER ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE = 'Recibo'"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentasPorVendedor'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND A.STORE = :store"
				+" AND D.STORE = A.STORE"
				+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
				+" GROUP BY A.CASHIER, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
	public List<Reportes> getVentasVendedorByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentasPorVendedor'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 ='DAILY'", nativeQuery = true)
		Labels getLabelsVentasByVendedor();

				@Query(value="SELECT A.CASHIER ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE = 'Recibo'"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentasPorVendedor'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND D.STORE = A.STORE"
				+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
				+" GROUP BY A.CASHIER, B.BUSINESS_DATE, C.VALUE", nativeQuery = true)
		public List<Reportes> getVentasPorVendedor(@Param("period") String period);
}