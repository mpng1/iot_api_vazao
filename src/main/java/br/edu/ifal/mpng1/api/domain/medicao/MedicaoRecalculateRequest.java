package br.edu.ifal.mpng1.api.domain.medicao;

import java.sql.Date;
import java.time.LocalDate;

public class MedicaoRecalculateRequest {
	
    private Date startDate;
    
    public MedicaoRecalculateRequest() {
    
    }    
    
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public MedicaoRecalculateRequest(Date startDate) {
		super();
		this.startDate = startDate;
	}
	
	

}
