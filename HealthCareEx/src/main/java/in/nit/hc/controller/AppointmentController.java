package in.nit.hc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Appointment;
import in.nit.hc.exception.AppointmentNotFoundException;
import in.nit.hc.service.IAppointmentService;
import in.nit.hc.service.IDoctorService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private IAppointmentService service;
	@Autowired
	private IDoctorService docService;
	/*
	A common method which sends data to create Dynamic Dropdown at UI
	in Register, Edit page.
	Call this method inside controller method where those returns
	___Register or __Edit
	*/
	private void dynamicCommonUI(Model model) {
		Map<Long,String> doctors=docService.getDocIdAndName();
		doctors.forEach((o1,o2) -> System.out.println(o1+" - "+o2));
		model.addAttribute("doctors", doctors);
	}
	
	
	//1. show register data
	@GetMapping("/register")
	public String displayRegister(@RequestParam(value="message",required=false)String message,Model model) {
		model.addAttribute("message", message);
		dynamicCommonUI(model);
		return "AppointmentRegister";
	}
	
	
	//2. save on submit
	@PostMapping("/save")
	public String save(@ModelAttribute("appoint") Appointment app,RedirectAttributes attributes) {
		Long id=service.createAppointment(app);
		String msg="Appointment is created with id: "+id;
		attributes.addAttribute("message", msg);
		return "redirect:register";
	}
	
	//3. display data
	@GetMapping("/all")
	public String display(Model model,@RequestParam(value="message",required=false)String message) {
		List<Appointment> list=service.getAllAppointment();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
	}
	
	//4. delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id,RedirectAttributes attributes) {
		String message=null;
		try {
			service.removeAppointment(id);
			message="Appointment removed";
		}
		catch(AppointmentNotFoundException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	//5. show edit
	@GetMapping("/edit")
	public String edit(@RequestParam("id")Long id,Model model,RedirectAttributes attributes) {
		String page=null;
		try {
			Appointment app=service.getOneAppointment(id);
			model.addAttribute("appointment", app);
			dynamicCommonUI(model);
			page="AppointmentEdit";
		}
		catch(AppointmentNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	//6. do update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute("app")Appointment app,RedirectAttributes attributes) {
		service.updateAppointment(app);
		attributes.addAttribute("message", app.getId()+", updated!");
		return "redirect:all";
	}
	
	//7. email and mobile duplicate validation(AJAX)
	
	//8. excel export
}
