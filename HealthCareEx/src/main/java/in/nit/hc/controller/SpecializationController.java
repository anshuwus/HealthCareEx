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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Specialization;
import in.nit.hc.exception.SpecializationNotFoundException;
import in.nit.hc.service.ISpecializationService;
import in.nit.hc.view.SpecializationExcelView;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	@Autowired
	private ISpecializationService service;
	
	/*	1. Show Register page
	*/	
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	
	/*	2. On submit form save data
	*/	
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization spec,Model model) {
		Long id=service.saveSpecialization(spec);
		String message="Record ("+id+")is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	/*	3. display all specializations
	*/
	@GetMapping("/all")
	public String viewAll(Model model,@RequestParam(value="message",required=false) String message) {
		List<Specialization> list=service.getAllSpecialization();
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}
	
	/*4. Delete by id*/
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id,RedirectAttributes attributes) {
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message","Record ("+id+") is removed");
		}
		catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}
	
	/*5. Fetch data into edit page
	 *   End user clicks on link, may enter ID manually
	 *   If entered id is present in DB
	 *      > Load row as Object
	 *      > Send to Edit page
	 *      > Fill in Form
	 *   Else
	 *      > Redirect to all (Data Page)
	 *      > Show Error message (not found)
	 * */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id,Model model,RedirectAttributes attributes) {
		String page=null;
		try {
			Specialization spec=service.getOneSpecialization(id);
			model.addAttribute("specialization", spec);
			page = "SpecializationEdit";
		}
		catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}
	
	/*6. Update Form data and redirect to all*/
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Record ("+specialization.getId()+") is updated");
		return "redirect:all";
	}
	
	/*7. Read code and check with service
	   Return messgae back to UI*/
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code,@RequestParam Long id) {
		String message=""; // code not exist
		if(id==0 && service.isSpecCodeExit(code)) {  //for register check
			message=code+", already exist";
		}
		else if(id!=0 && service.isSpecCodeExitForEdit(code, id)) { //for edit check
			message=code+", already exist";
		}
		return message;  //this is not viewName(it's just message)
	}
	
	/*8. Read name and check with service 
	   Return message back to UI*/
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name,@RequestParam Long id) {
		String message="";  //name not exist
		if(id==0 && service.isSpecNameExit(name)) { //for register check
			message=name+", already exist";
		}
		else if(id!=0 && service.isSpecNameExitForEdit(name, id)) {  //for edit check
			message=name+", already exist";
		}
		return message;
	}
	
	/*9. Export data to excel file*/
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m=new ModelAndView();
		m.setView(new SpecializationExcelView());
		
		//read data from DB
		List<Specialization> list=service.getAllSpecialization();
		//send to Excel Impl class
		m.addObject("list",list);
		
		return m;
	}
}
