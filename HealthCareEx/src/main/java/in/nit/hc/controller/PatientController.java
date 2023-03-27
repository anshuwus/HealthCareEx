package in.nit.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Patient;
import in.nit.hc.exception.PatientNotFoundException;
import in.nit.hc.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private IPatientService service;
	
	//1. show Register page
	@GetMapping("/register")
	public String displayRegister(@RequestParam(value="message",required=false)  String message,Model model){
		model.addAttribute("message", message);
		return "PatientRegister";
	}
	
	//2. save on submit
	@PostMapping("/save")
	public String save(@ModelAttribute Patient patient,RedirectAttributes attributes) {
		Long id=service.savePatient(patient);
		attributes.addAttribute("message","Patient ("+id+") is created");
		return "redirect:register";
	}
	
	//3. display data
	@GetMapping("/all")
	public String display(Model model,@RequestParam(name="message",required=false)String message) {
		List<Patient> list=service.getAllPatient();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "PatientData";
	}
	
	//4. delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id,RedirectAttributes attributes) {
		String message=null;
		try {
			service.removePatient(id);
			message="Patient removed";
		}
		catch(PatientNotFoundException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	//5. show edit
	@GetMapping("/edit")
	public String edit(@RequestParam("id")Long id,RedirectAttributes attributes,Model model) {
		String page=null;
		try {
			Patient patient=service.getOnePatient(id);
			model.addAttribute("patient", patient);
			page="PatientEdit";
		}
		catch(PatientNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	//6. do update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Patient patient,RedirectAttributes attributes) {
		service.updatePatient(patient);
		attributes.addAttribute("message", patient.getId()+", updated!");
		return "redirect:all";
	}
	
	//7. email and mobile duplicate valiation(AJAX)
	
	//8. excel export
}
