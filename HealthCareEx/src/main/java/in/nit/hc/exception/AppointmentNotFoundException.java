package in.nit.hc.exception;

import org.springframework.stereotype.Component;

@Component
public class AppointmentNotFoundException extends RuntimeException{
	
	public AppointmentNotFoundException() {
		super();
	}
	public AppointmentNotFoundException(String msg) {
		super(msg);
	}
}
