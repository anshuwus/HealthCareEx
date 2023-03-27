package in.nit.hc.exception;

public class PatientNotFoundException extends RuntimeException {
	
	public PatientNotFoundException() {
		super();
	}
	
	public PatientNotFoundException(String msg) {
		super(msg);
	}
}
