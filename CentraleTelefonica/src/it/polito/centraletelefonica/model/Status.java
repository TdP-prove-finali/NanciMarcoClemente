package it.polito.centraletelefonica.model;

import java.time.LocalDateTime;

public class Status {

	private String id;
	private LocalDateTime reportingDate;
	private LocalDateTime goalDate;
	private LocalDateTime closingDate;
	private String status;

	public Status(String id, LocalDateTime reportingDate, LocalDateTime goalDate, LocalDateTime closingDate,
			String status) {
		
		this.setId(id);
		this.setReportingDate(reportingDate);
		this.setGoalDate(goalDate);
		this.setClosingDate(closingDate);
		this.setStatus(status);
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(LocalDateTime reportingDate) {
		this.reportingDate = reportingDate;
	}

	public LocalDateTime getGoalDate() {
		return goalDate;
	}

	public void setGoalDate(LocalDateTime goalDate) {
		this.goalDate = goalDate;
	}

	public LocalDateTime getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDateTime closingDate) {
		this.closingDate = closingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
