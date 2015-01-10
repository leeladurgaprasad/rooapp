package hob.psd.todo.exception;

public class UserCreationException extends RuntimeException{
	
	private int errorCode;
	private String errorMessage;
	
	public UserCreationException(int errorCode, String errorMessage) {
		
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
