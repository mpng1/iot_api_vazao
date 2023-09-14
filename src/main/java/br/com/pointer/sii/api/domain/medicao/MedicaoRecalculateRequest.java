package br.com.pointer.sii.api.domain.medicao;

import java.time.LocalDate;

public class MedicaoRecalculateRequest {
	
    private LocalDate startDate;
    
    public MedicaoRecalculateRequest() {
    
    }    
    
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public MedicaoRecalculateRequest(LocalDate startDate) {
		super();
		this.startDate = startDate;
	}
	
	

}
