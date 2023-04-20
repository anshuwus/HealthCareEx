package in.nit.hc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.entity.Appointment;
import in.nit.hc.exception.AppointmentNotFoundException;
import in.nit.hc.repository.IAppointmentRepository;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private IAppointmentRepository repo;
	
	@Override
	public Long createAppointment(Appointment appointmnt) {
		return repo.save(appointmnt).getId();
	}

	@Override
	public List<Appointment> getAllAppointment() {
		return repo.findAll();
	}

	@Override
	public void removeAppointment(Long id) {
		repo.delete(getOneAppointment(id));
	}

	@Override
	public void updateAppointment(Appointment appointmnt) {
		if(repo.existsById(appointmnt.getId()))
			repo.save(appointmnt);
		else
			throw new AppointmentNotFoundException(appointmnt.getId()+", not exist");
	}

	@Override
	public Appointment getOneAppointment(Long id) {
		return repo.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id+", not exist"));
	}

}
