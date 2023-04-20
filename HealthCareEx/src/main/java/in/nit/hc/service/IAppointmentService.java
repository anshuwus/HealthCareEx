package in.nit.hc.service;

import java.util.List;

import in.nit.hc.entity.Appointment;

public interface IAppointmentService {
	
	public Long createAppointment(Appointment appointmnt);
	public List<Appointment> getAllAppointment();
	public void removeAppointment(Long id);
	public void updateAppointment(Appointment appointmnt);
	public Appointment getOneAppointment(Long id);
}
