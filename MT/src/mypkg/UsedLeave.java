package mypkg;

public class UsedLeave {
	private int idLeave;
	private String userEmail;
	private String idUser;
	private String idManager;
	private String type;
	private String fromDate;
	private String toDate;
	private int days;
	private String approved;
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getDays() {
		return days;
	}
	public void setIdLeave(int idLeave) {
		this.idLeave = idLeave;
	}
	public int getIdLeave() {
		return idLeave;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdManager(String idManager) {
		this.idManager = idManager;
	}
	public String getIdManager() {
		return idManager;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getApproved() {
		return approved;
	}
}
