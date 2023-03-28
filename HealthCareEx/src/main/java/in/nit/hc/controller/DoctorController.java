package in.nit.hc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Doctor;
import in.nit.hc.exception.DoctorNotFoundException;
import in.nit.hc.service.IDoctorService;
import in.nit.hc.utility.FileUploadUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private IDoctorService service;
	
	//1. show Regsiter page
	@GetMapping("/register")
	public String displayRegister(@RequestParam(value="message",required=false)String message,Model model) {
		model.addAttribute("message", message);
		return "DoctorRegister";
	}
	
	//2. save on submit
	@PostMapping("/save")
	public String save(@ModelAttribute Doctor doctor,RedirectAttributes attributes
			                            //   ,@RequestParam("docImg") MultipartFile multipartFile
			                               ) {
		//String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		//doctor.setPhotos(fileName);
		Long id=service.saveDoctor(doctor);
		attributes.addAttribute("message", "Doctor ("+id+") is created");
		//String uploadDir="user-photos/"+id;
		/*try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}*/
		return"redirect:register";
	}
	
	//3. display data
	@GetMapping("/all")
	public String display(@RequestParam(value="message",required=false)String message, Model model) {
		List<Doctor> list=service.getAllDoctors();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "DoctorData";
	}
	
	//4. delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id,RedirectAttributes attributes) {
		String message=null;
		try {
			service.removeDoctor(id);
			message="Doctor removed";
		}catch(DoctorNotFoundException e) {
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
			Doctor doc=service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			page="DoctorEdit";
		}
		catch(DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	//6. do update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Doctor doctor, RedirectAttributes attributes) {
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId()+", updated!");
		return "redirect:all";
	}
	
	//7. email and mobile duplicate validation(AJAX)
	
	//8. excel export
}
