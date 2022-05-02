package com.consiti.repository;

import com.consiti.entity.Reportes;
import com.consiti.entity.ReportesProductos;
import com.consiti.entity.Labels;
import com.consiti.entity.Metric;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer>{
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS\r\n"
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
			+ "AND STATUS = '0'\r\n"
			+ "GROUP BY D.STORE_NAME, YEARWEEK(B.BUSINESS_DATE), C.VALUE\r\n"
			+ "ORDER BY 2 desc;\r\n"
			+ "", nativeQuery = true)
	List<Reportes>  getTicketSemanal(String attr);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'AvgTicket'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
	Labels getLabelsTicketSemanal(String attr);
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS\r\n"
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
			+ "AND STATUS = '0'\r\n"
			+ "GROUP BY D.STORE_NAME, YEARWEEK(B.BUSINESS_DATE), C.VALUE\r\n"
			+ "ORDER BY 2 desc;\r\n"
			+ "", nativeQuery = true)
	List<Reportes>  getTicketSemanalByStore(String attr, Integer store);
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS\r\n"
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
			+ "AND STATUS = '0'\r\n"
			+ "GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE\r\n"
			+ "ORDER BY 2 desc;", nativeQuery = true)
	List<Reportes> getTicketMensual(String attr);
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS\r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'SALE'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND A.STORE = ?2 #Parametro Store\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "AND STATUS = '0'\r\n"
			+ "GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE\r\n"
			+ "ORDER BY 2 desc;", nativeQuery = true)
	List<Reportes> getTicketMensualByStore(String attr, Integer store);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'AvgTicket'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
	Labels getLabelsTicketMensual(String attr);
/********************************************************************************************************************************************** */
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
				+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc;", nativeQuery = true)
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
				+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc;", nativeQuery = true)
		List<Reportes>  getTicketByStore(String attr, Integer store);
/********************************************************************************************************************************************** */	
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
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc", nativeQuery = true)
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
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc", nativeQuery = true)
	public List<Reportes> getVentasDiariasByStore(@Param("period") String period, @Param("store") Integer store);

	/***********************************************************************************************************************************************************/
	@Query(value = "SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY'," 
				+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentaConIVA'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND D.STORE = A.STORE"
				+" AND STATUS = '0'"
				+" GROUP BY D.STORE_NAME, YEARWEEK(B.BUSINESS_DATE), C.VALUE"
				+" ORDER BY 2 desc"
				, nativeQuery = true)
	public List<Reportes> getVentasSemanales(@Param("period") String period);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsVentasSemanales(String attr);

	@Query(value = "SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY',"
				+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentaConIVA'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND A.STORE = :store"
				+" AND D.STORE = A.STORE"
				+" AND STATUS = '0'"
				+" GROUP BY D.STORE_NAME, YEARWEEK(B.BUSINESS_DATE), C.VALUE"
				+" ORDER BY 2 desc"
				, nativeQuery = true)
	public List<Reportes> getVentasSemanalesByStore(@Param("period") String period,@Param("store") Integer store);

	@Query(value = "SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY',"
					+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
					+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
					+" WHERE A.TRAN_TYPE IN ("
					+" SELECT TRAN_TYPE FROM TRAN_TYPES"
					+" WHERE TYPE_CODE = 'SALE'"
					+" )"
					+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
					+" AND C.NAME = 'VentaConIVA'"
					+" AND C.type_code = 'TM'"
					+" AND ATTR1 = :period"
					+" AND D.STORE = A.STORE"
					+" AND STATUS = '0'"
					+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
					+" ORDER BY 2 desc;", nativeQuery = true)
	public List<Reportes> getVentasMensuales(@Param("period") String period);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsVentasMensuales(String period);

	@Query(value = "SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY',"
				+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentaConIVA'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND A.STORE = :store"
				+" AND D.STORE = A.STORE"
				+" AND STATUS = '0'"
				+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
				+" ORDER BY 2 desc", nativeQuery = true)
	public List<Reportes> getVentasMensualesByStore(@Param("period") String period,@Param("store") Integer store);

	// @Query(value = "", nativeQuery = true)
	// public List<Reportes> getVentasAnuales();

	// @Query(value = "", nativeQuery = true)
	// public List<Reportes> getVentasAnualesByStore();

	/***********************************************************************************************************************************************************/

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
					+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc", nativeQuery = true)
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
					+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc", nativeQuery = true)
	public List<Reportes> getUnidadesVendidasByStore(@Param("period") String period, @Param("store") Integer store);

/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
	@Query(value="SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'UnidadesVendidas'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" and A.STATUS ='0'"
				+" AND D.STORE = A.STORE"
				+" AND B.BUSINESS_DATE BETWEEN yearweek(DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY)) AND YEARWEEK(SYSDATE()) "
				+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE"
				+" order by 2 desc;"
				, nativeQuery = true)
	public List<Reportes> getUnidadesVendidasSemanales(@Param("period") String period);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'UnidadesVendidas'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsUnidadesVendidasSemanales(String attr);

	@Query(value="SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'UnidadesVendidas'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND A.STORE = :store"
				+" and A.STATUS ='0'"
				+" AND D.STORE = A.STORE"
				+" AND B.BUSINESS_DATE BETWEEN yearweek(DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY)) AND YEARWEEK(SYSDATE()) "
				+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE"
				+" order by 2 desc;"
				, nativeQuery = true)
	public List<Reportes> getUnidadesVendidasSemanalesByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value=" SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY'," 
				+" SUM(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'UnidadesVendidas'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND D.STORE = A.STORE"
				+" AND STATUS = '0'"
				+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
				+" ORDER BY 2 desc"
				, nativeQuery = true)
	public List<Reportes> getUnidadesVendidasMensuales(@Param("period") String period);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'UnidadesVendidas'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsUnidadesVendidasMensuales(String attr);

	@Query(value="SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY'," 
				+" SUM(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'UnidadesVendidas'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND A.STORE = :store"
				+" AND D.STORE = A.STORE"
				+" AND STATUS = '0'"
				+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
				+" ORDER BY 2 desc"
				, nativeQuery = true)
	public List<Reportes> getUnidadesVendidasMensualesByStore(@Param("period") String period, @Param("store") Integer store);
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
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
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc;",nativeQuery=true)
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
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE order by 2 desc;", nativeQuery=true)
	public List<Reportes> getGastosByStore(String attr, Integer store);
	
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS\r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'SALE'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "AND STATUS = '0'\r\n"
			+ "GROUP BY D.STORE_NAME, YEARWEEK(B.BUSINESS_DATE), C.VALUE\r\n"
			+ "ORDER BY 2 desc;\r\n"
			+ "", nativeQuery = true)
	public List<Reportes> getGastosSemanales(String attr);
	
	@Query(value = "SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS \"KEY\", \r\n"
			+ "SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS\r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'SALE'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 #Parametro Period\r\n"
			+ "AND A.STORE = ?2 #Parametro Store\r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "AND STATUS = '0'\r\n"
			+ "GROUP BY D.STORE_NAME, YEARWEEK(B.BUSINESS_DATE), C.VALUE\r\n"
			+ "ORDER BY 2 desc;\r\n"
			+ "", nativeQuery = true)
	public List<Reportes> getGastosSemanalesByStore(String attr, Integer store);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
	public Labels getLabelsGastosSemanales(String attr);
	
	
	@Query(value="SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY'," 
			+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
			+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+" )"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'Gastos'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = ?1"
			+" AND D.STORE = A.STORE"
			+" AND STATUS = '0'"
			+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
			+" ORDER BY 2 desc"
			, nativeQuery = true)
	public List<Reportes> getGastosMensuales(String attr);
	
	@Query(value="SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY'," 
			+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
			+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+" )"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'Gastos'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = ?1"
			+" AND A.STORE = ?2"
			+" AND D.STORE = A.STORE"
			+" AND STATUS = '0'"
			+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
			+" ORDER BY 2 desc"
			, nativeQuery = true)
	public List<Reportes> getGastosMensualesByStore(String attr, Integer store);
	
	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'Gastos'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
	public Labels getLabelsGastosMensuales(String attr);
	
	/*-------------------------------------------------------------------------------------------------------------------------*/
    
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
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE"
			+" order by 2 desc;"
			, nativeQuery = true)
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
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE"
			+" order by 2 desc;"
			, nativeQuery = true)
	public List<Reportes> getAnulacionesByStore(@Param("period") String period, @Param("store") Integer store);
/************************************************************************************************************************************************** */
/************************************************************************************************************************************************** */
	@Query(value="SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, SUM(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentaConIVA'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" and A.STATUS ='1'"
				+" AND D.STORE = A.STORE"
				+" AND B.BUSINESS_DATE BETWEEN yearweek(DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY)) AND YEARWEEK(SYSDATE()) "
				+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE"
				+" order by 2 desc;"
	, nativeQuery = true)
	public List<Reportes> getAnulacionesSemanales(@Param("period") String period);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsAnulacionesSemanles(String attr);

	@Query(value="SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, SUM(A.VALUE)/C.VALUE PROGRESS"
			+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
			+" WHERE A.TRAN_TYPE IN ("
			+" SELECT TRAN_TYPE FROM TRAN_TYPES"
			+" WHERE TYPE_CODE = 'SALE'"
			+" )"
			+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
			+" AND C.NAME = 'VentaConIVA'"
			+" AND C.type_code = 'TM'"
			+" AND ATTR1 = :period"
			+" AND A.STORE = :store"
			+" and A.STATUS ='1'"
			+" AND D.STORE = A.STORE"
			+" AND B.BUSINESS_DATE BETWEEN yearweek(DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY)) AND YEARWEEK(SYSDATE()) "
			+" GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE"
			+" order by 2 desc;"
			, nativeQuery = true)
	public List<Reportes> getAnulacionesSemanalesByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY',"
				+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE IN ("
				+" SELECT TRAN_TYPE FROM TRAN_TYPES"
				+" WHERE TYPE_CODE = 'SALE'"
				+" )"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentaConIVA'"
				+" AND C.type_code = 'TM'"
				+" AND ATTR1 = :period"
				+" AND D.STORE = A.STORE"
				+" AND STATUS = '1'"
				+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
				+" ORDER BY 2 desc;"
			, nativeQuery = true)
	public List<Reportes> getAnulacionesMensuales(@Param("period") String period);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentaConIVA'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 =?1 ", nativeQuery = true)
		Labels getLabelsAnulacionesMensuales(String attr);

		@Query(value="SELECT D.STORE_NAME ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY',"
		+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
		+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
		+" WHERE A.TRAN_TYPE IN ("
		+" SELECT TRAN_TYPE FROM TRAN_TYPES"
		+" WHERE TYPE_CODE = 'SALE'"
		+" )"
		+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
		+" AND C.NAME = 'VentaConIVA'"
		+" AND C.type_code = 'TM'"
		+" AND ATTR1 = :period"
		+" AND A.STORE = :store"
		+" AND D.STORE = A.STORE"
		+" AND STATUS = '1'"
		+" GROUP BY D.STORE_NAME, MONTH(B.BUSINESS_DATE), C.VALUE"
		+" ORDER BY 2 desc;"
	, nativeQuery = true)
	public List<Reportes> getAnulacionesMensualesByStore(@Param("period") String period, @Param("store") Integer store);
	/************************************************************************************************************************************************** */
	/************************************************************************************************************************************************** */

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
				+" GROUP BY A.CASHIER, B.BUSINESS_DATE, C.VALUE order by 2 desc", nativeQuery = true)
	public List<Reportes> getVentasVendedorByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentasPorEmpleado'\r\n"
			+ "AND C.type_code = 'SP'\r\n"
			+ "AND ATTR1 ='DAILY'", nativeQuery = true)
		Labels getLabelsVentasByVendedor();

				@Query(value="SELECT A.SALESPERSON ENTITY, B.BUSINESS_DATE AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
				+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
				+" WHERE A.TRAN_TYPE = 'Recibo'"
				+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+" AND C.NAME = 'VentasPorEmpleado'"
				+" AND C.type_code = 'SP'"
				+" AND ATTR1 = :period"
				+" AND D.STORE = A.STORE"
				+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
				+" GROUP BY A.SALESPERSON, B.BUSINESS_DATE, C.VALUE order by 2 desc", nativeQuery = true)
		public List<Reportes> getVentasPorVendedor(@Param("period") String period);

		@Query(value="SELECT D.STORE_NAME ENTITY, YEARWEEK(B.BUSINESS_DATE) AS KEY, SUM(A.VALUE) VALUE, C.VALUE KPI, SUM(A.VALUE)/C.VALUE PROGRESS"
							+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
							+" WHERE A.TRAN_TYPE IN ("
							+" SELECT TRAN_TYPE FROM TRAN_TYPES"
							+" WHERE TYPE_CODE = 'SALE'"
							+" )"
							+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
							+" AND C.NAME = 'VentasPorVendedor'"
							+" AND C.type_code = 'TM'"
							+" AND ATTR1 = :period"
							+" AND A.STORE = :store"
							+" AND D.STORE = A.STORE"
							+" AND B.BUSINESS_DATE BETWEEN yearweek(DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY)) AND YEARWEEK(SYSDATE()) "
							+" GROUP BY A.CASHIER , B.BUSINESS_DATE, C.VALUE"
							+" order by 2 desc;", 
							nativeQuery = true)
	public List<Reportes> getVentasVendedorSemanalesByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentasPorEmpleado'\r\n"
			+ "AND C.type_code = 'SP'\r\n"
			+ "AND ATTR1 =:period", nativeQuery = true)
		Labels getLabelsVentasByVendedorSemanales(@Param("period") String period);

				@Query(value="SELECT A.SALESPERSON ENTITY, YEARWEEK(B.BUSINESS_DATE) AS 'KEY', SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
						+" FROM ConsitiPOS.TRAN_HEAD A, ConsitiPOS.STORE_DAY B, ConsitiPOS.METRIC C"
						+" WHERE A.TRAN_TYPE IN ("
						+" SELECT TRAN_TYPE FROM TRAN_TYPES"
						+" WHERE TYPE_CODE = 'SALE'"
						+" )"
						+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
						+" AND C.NAME = 'VentasPorEmpleado'"
						+" AND C.type_code = 'SP'"
						+" AND ATTR1 = :period"
						+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 WEEK) AND SYSDATE()"
						+" GROUP BY A.SALESPERSON, YEARWEEK(B.BUSINESS_DATE), C.VALUE ORDER BY YEARWEEK(B.BUSINESS_DATE) DESC", 
							nativeQuery = true)
		public List<Reportes> getVentasVendedorSemanales(@Param("period") String period);

		@Query(value="SELECT A.CASHIER ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY',"
					+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
					+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
					+" WHERE A.TRAN_TYPE IN ("
					+" SELECT TRAN_TYPE FROM TRAN_TYPES"
					+" WHERE TYPE_CODE = 'SALE'"
					+" )"
					+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
					+" AND C.NAME = 'VentasPorVendedor'"
					+" AND C.type_code = 'TM'"
					+" AND ATTR1 = :period"
					+" AND A.STORE = :store"
					+" AND D.STORE = A.STORE"
					+" GROUP BY A.CASHIER, MONTH(B.BUSINESS_DATE), C.VALUE"
					+" ORDER BY 2 desc", 
					nativeQuery = true)
	public List<Reportes> getVentasVendedorMensualesByStore(@Param("period") String period, @Param("store") Integer store);

	@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
			+ "FROM METRIC C\r\n"
			+ "WHERE \r\n"
			+ "C.NAME = 'VentasPorEmpleado'\r\n"
			+ "AND C.type_code = 'SP'\r\n"
			+ "AND ATTR1 =:period", nativeQuery = true)
		Labels getLabelsVentasByVendedorMensuales(@Param("period") String period);

		@Query(value="SELECT A.SALESPERSON ENTITY, MONTH(B.BUSINESS_DATE) AS 'KEY',"
					+" SUM(A.VALUE) VALUE, C.VALUE KPI, sum(A.VALUE)/C.VALUE PROGRESS"
					+" FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D"
					+" WHERE A.TRAN_TYPE IN ("
					+" SELECT TRAN_TYPE FROM TRAN_TYPES"
					+" WHERE TYPE_CODE = 'SALE'"
					+" )"
					+" AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
					+" AND C.NAME = 'VentasPorEmpleado'"
					+" AND C.type_code = 'SP'"
					+" AND ATTR1 = :period"
					+" AND D.STORE = A.STORE"
					+" AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
					+" GROUP BY A.SALESPERSON, MONTH(B.BUSINESS_DATE), C.VALUE"
					+" ORDER BY 2 desc", 
					nativeQuery = true)
		public List<Reportes> getVentasVendedorMensuales(@Param("period") String period);
		
		@Query(value="SELECT"
				+ " COALESCE(E.CLASS,E.SUBCLASS) ENTITY,"
				+ " BUSINESS_DATE AS 'KEY', "
				+ " SUM(F.QTY) VALUE,"
				+ " C.VALUE KPI, "
				+ " SUM(F.QTY)/C.VALUE PROGRESS"
				+ "	FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D, ITEM_MASTER E,TRAN_ITEM F"
				+ " WHERE A.TRAN_TYPE = 'Recibo'"
				+ "	AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+ "	AND C.NAME = 'VentaCategoria'"
				+ "	AND C.type_code = 'CG'"
				+ "	AND ATTR1 = :period"
				+ "	AND D.STORE = A.STORE"
				+ "	AND E.SUBCLASS IS NOT NULL"
				+ "	AND A.TRAN_SEQ_NO=F.TRAN_SEQ_NO"
				+ "	AND F.ITEM=E.ITEM"
				+ "	AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -8 DAY) AND SYSDATE()"
				+ " GROUP BY COALESCE(E.CLASS,E.SUBCLASS), B.BUSINESS_DATE, C.VALUE order by 2 DESC",nativeQuery =true)
		public List<Reportes> getVentasCategoriaDiario(@Param("period") String period);
		
		
		@Query(value="SELECT"
				+ " COALESCE(E.CLASS,E.SUBCLASS) ENTITY, "
				+ " YEARWEEK(B.BUSINESS_DATE) AS 'KEY', "
				+ " SUM(F.QTY) VALUE, "
				+ " C.VALUE KPI, "
				+ " SUM(F.QTY)/C.VALUE PROGRESS"
				+ "	FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D, ITEM_MASTER E,TRAN_ITEM F"
				+ "	WHERE A.TRAN_TYPE = 'Recibo'"
				+ "	AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+ "	AND C.NAME = 'VentaCategoria'"
				+ "	AND C.type_code = 'CG'"
				+ "	AND ATTR1 = :period"
				+ "	AND D.STORE = A.STORE"
				+ "	AND E.SUBCLASS IS NOT NULL"
				+ "	AND A.TRAN_SEQ_NO=F.TRAN_SEQ_NO"
				+ "	AND F.ITEM=E.ITEM"
				+ "	AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
				+ "GROUP BY COALESCE(E.CLASS,E.SUBCLASS), YEARWEEK(B.BUSINESS_DATE), C.VALUE order by 2 DESC",nativeQuery=true)
		public List<Reportes> getVentasCategoriaSemanal(@Param("period") String period);
		
		
		@Query(value="SELECT "
				+ " COALESCE(E.CLASS,E.SUBCLASS) ENTITY, "
				+ " MONTH(B.BUSINESS_DATE) AS 'KEY', "
				+ " SUM(F.QTY) VALUE, "
				+ " C.VALUE KPI, "
				+ " SUM(F.QTY)/C.VALUE PROGRESS\r\n"
				+ "	FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D, ITEM_MASTER E,TRAN_ITEM F"
				+ "	WHERE A.TRAN_TYPE = 'Recibo'"
				+ "	AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO"
				+ "	AND C.NAME = 'VentaCategoria'"
				+ "	AND C.type_code = 'CG'"
				+ "	AND ATTR1 = :period"
				+ "	AND D.STORE = A.STORE"
				+ "	AND E.SUBCLASS IS NOT NULL"
				+ "	AND A.TRAN_SEQ_NO=F.TRAN_SEQ_NO"
				+ "	AND F.ITEM=E.ITEM"
				+ "	AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()"
				+ "GROUP BY COALESCE(E.CLASS,E.SUBCLASS), MONTH(B.BUSINESS_DATE), C.VALUE order by 2 DESC",nativeQuery=true)
		public List<Reportes> getVentasCategoriaMensual(@Param("period") String period);
		
		@Query(value="SELECT C.LABEL1,C.LABEL2,C.LABEL3,C.LABEL4\r\n"
				+ " FROM METRIC C\r\n"
				+ " WHERE \r\n"
				+ " C.NAME = 'VentaCategoria'\r\n"
				+ " AND C.type_code = 'CG'\r\n"
				+ " AND ATTR1 =:period", nativeQuery = true)
			public Labels getLabelsVentasByCategoria(@Param("period") String period);
		
		
		
		@Query(value="SELECT \r\n"
				+ "E.ITEM_DESC PRODUCTO, \r\n"
				+ "B.BUSINESS_DATE AS 'FECHA', \r\n"
				+ "SUM(F.QTY) CANTIDAD \r\n"
				+ "	FROM TRAN_HEAD A, STORE_DAY B, STORE D, ITEM_MASTER E,TRAN_ITEM F\r\n"
				+ "WHERE A.TRAN_TYPE = 'Recibo'\r\n"
				+ "	AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
				+ "	AND D.STORE = A.STORE\r\n"
				+ "	AND A.TRAN_SEQ_NO=F.TRAN_SEQ_NO\r\n"
				+ "	AND F.ITEM=E.ITEM\r\n"
				+ "	AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -1 DAY) AND SYSDATE()\r\n"
				+ "GROUP BY E.ITEM_DESC, B.BUSINESS_DATE ORDER BY 3   DESC LIMIT 10;",nativeQuery = true)
		public List<ReportesProductos> getVentasProductosDiaria();
		
		@Query(value="SELECT \r\n"
				+ "E.ITEM_DESC PRODUCTO, \r\n"
				+ "YEARWEEK(B.BUSINESS_DATE) AS 'FECHA', \r\n"
				+ "SUM(F.QTY) CANTIDAD \r\n"
				+ "	FROM TRAN_HEAD A, STORE_DAY B, STORE D, ITEM_MASTER E,TRAN_ITEM F\r\n"
				+ "WHERE A.TRAN_TYPE = 'Recibo'\r\n"
				+ "	AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
				+ "	AND D.STORE = A.STORE\r\n"
				+ "	AND A.TRAN_SEQ_NO=F.TRAN_SEQ_NO\r\n"
				+ "	AND F.ITEM=E.ITEM\r\n"
				+ "	AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -9 DAY) AND SYSDATE()\r\n"
				+ "GROUP BY E.ITEM_DESC, YEARWEEK(B.BUSINESS_DATE) ORDER BY 3   DESC LIMIT 10",nativeQuery = true)
		public List<ReportesProductos> getVentasProductosSemanal();
		
		@Query(value="SELECT \r\n"
				+ "E.ITEM_DESC PRODUCTO, \r\n"
				+ "MONTH(B.BUSINESS_DATE) AS 'FECHA', \r\n"
				+ "SUM(F.QTY) CANTIDAD \r\n"
				+ "	FROM TRAN_HEAD A, STORE_DAY B, STORE D, ITEM_MASTER E,TRAN_ITEM F\r\n"
				+ "WHERE A.TRAN_TYPE = 'Recibo'\r\n"
				+ "	AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
				+ "	AND D.STORE = A.STORE\r\n"
				+ "	AND A.TRAN_SEQ_NO=F.TRAN_SEQ_NO\r\n"
				+ "	AND F.ITEM=E.ITEM\r\n"
				+ "	AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -30 DAY) AND SYSDATE()\r\n"
				+ "GROUP BY E.ITEM_DESC, MONTH(B.BUSINESS_DATE) ORDER BY 3   DESC LIMIT 10",nativeQuery = true)
		public List<ReportesProductos> getVentasProductosMensual();
}