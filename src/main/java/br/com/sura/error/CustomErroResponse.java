package br.com.sura.error;

public class CustomErroResponse {

	private String mensage;
	private int code;
	private String status;
	private String error;
	

	public CustomErroResponse(String error) {
		this.setMensage("Requisição encontrou um problema");
		this.setCode(500);
		this.status = "Internal Server Error";
		this.error = error;
	}

	public CustomErroResponse() {

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}