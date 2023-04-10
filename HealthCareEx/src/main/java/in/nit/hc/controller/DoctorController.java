package in.nit.hc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Doctor;
import in.nit.hc.exception.DoctorNotFoundException;
import in.nit.hc.service.IDoctorService;
import in.nit.hc.service.ISpecializationService;
import in.nit.hc.util.MyMailUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private IDoctorService service;
	@Autowired
	private ISpecializationService specializationService;
	@Autowired
	private MyMailUtil mailUtil;

	/*
	A common method which sends data to create Dynamic DropDown at UI
	in Register, Edit page.
	Call this method inside controller method where those returns
	____Register or ___Edit
	*/
	private void dynamicCommonUI(Model model) {
		Map<Long,String> specializations=specializationService.getSpecIdAndName();
		//specializations.forEach((o1,o2) -> System.out.println(o1+" _ "+o2));
		model.addAttribute("specializations", specializations);
	}
	
	//1. show Regsiter page
	@GetMapping("/register")
	public String displayRegister(@RequestParam(value="message",required=false)String message,Model model) {
		model.addAttribute("message", message);
		dynamicCommonUI(model);
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
		String messgae= "Doctor ("+id+") is created";
		attributes.addAttribute("message", messgae);
		if(id!=null) {
			mailUtil.send(
					doctor.getEmail(),
					"SUCCESS",
					messgae,
					new ClassPathResource("/static/myres/FDD.pdf"));
		}//if
		
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
			dynamicCommonUI(model);
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
