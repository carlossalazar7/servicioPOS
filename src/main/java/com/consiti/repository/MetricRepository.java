package com.consiti.repository;

import com.consiti.entity.ITicket;
import com.consiti.entity.Metric;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer>{
	
	@Query(value = "SELECT D.STORE_NAME ENTITY , B.BUSINESS_DATE KEY_, AVG(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS \r\n"
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
	List<ITicket>  getTicket(String attr);
	
	
	@Query(value = "SELECT D.STORE_NAME ENTITY , B.BUSINESS_DATE KEY_, AVG(A.VALUE) VALUE, C.VALUE KPI, AVG(A.VALUE)/C.VALUE PROGRESS \r\n"
			+ "FROM TRAN_HEAD A, STORE_DAY B, METRIC C, STORE D\r\n"
			+ "WHERE A.TRAN_TYPE IN (\r\n"
			+ "SELECT TRAN_TYPE FROM TRAN_TYPES\r\n"
			+ "WHERE TYPE_CODE = 'SALE'\r\n"
			+ ")\r\n"
			+ "AND B.STORE_DAY_SEQ_NO = A.STORE_DAY_SEQ_NO\r\n"
			+ "AND C.NAME = 'AvgTicket'\r\n"
			+ "AND C.type_code = 'TM'\r\n"
			+ "AND ATTR1 = ?1 \r\n"
			+ "AND A.STORE = ?2 \r\n"
			+ "AND D.STORE = A.STORE\r\n"
			+ "AND B.BUSINESS_DATE BETWEEN DATE_ADD(SYSDATE(), INTERVAL -C.ATTR2 DAY) AND SYSDATE()\r\n"
			+ "GROUP BY D.STORE_NAME, B.BUSINESS_DATE, C.VALUE;", nativeQuery = true)
	ITicket getTicketByStore(String attr, Integer store);
	
	
    
}