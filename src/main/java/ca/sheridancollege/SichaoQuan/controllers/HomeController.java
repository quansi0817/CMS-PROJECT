package ca.sheridancollege.SichaoQuan.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.sheridancollege.SichaoQuan.beans.Customer;
import ca.sheridancollege.SichaoQuan.beans.Leads;
import ca.sheridancollege.SichaoQuan.beans.Users;
import ca.sheridancollege.SichaoQuan.database.DatabaseAccess;


@Controller
public class HomeController {
   
    @Autowired
	@Lazy
	private DatabaseAccess da;

	
	List<Leads> leadsList = new CopyOnWriteArrayList<Leads>();
	List<Customer> customerList = new CopyOnWriteArrayList<Customer>();
	// -------------------index--------------
	@GetMapping("/")
	public String index() {
		return "index"; //help user to find the page
	}
    
	
	//-------------------login---------------
	@GetMapping("/login")
	public String login() {
		return "login";
	}
  
	
    //-------------------register GET------------
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
  
	
	
	//-------------------register POST------------
	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) { //These parameters are from the form data submitted with the POST request.
		da.addUser(username, password); // calls a method in database to add the new user's username and password.
		Long userId = da.findUserAccount(username).getUserId(); // Find the new user's id

		da.addRole(userId, Long.valueOf(2));

		return "login";

	}

	
	//-------------------error/permissionDenied------------
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "error/permission-denied";
	}

	

	//-------------------leads------------
	/*
	@GetMapping("/secure") 
    public String secureIndex(Model model) {

      //  model.addAttribute(new Customer());
        model.addAttribute("leadsList", da.getLeadsList());//transfer all the leads data, using html to decide which one 
        model.addAttribute("customer", da.getCustomerList()) ; // test for if display customer info
        return "secure/index";
    }
	*/
	@GetMapping("/secure")
	public String secureIndex(Model model, Principal principal) {
	    String email = principal.getName(); 
	    Users user = da.findUserAccount(email); 

	    if (user != null) {
	        Long userId = user.getUserId();
	        List<String> roles = da.getRolesById(userId);



	        if (roles.contains("ROLE_USER")) {
	            List<Leads> leadsList = da.getLeadsListByUserId(userId);
	            model.addAttribute("leadsList", leadsList);
		        int totalLeads = da.getTotalLeadsByUserId(userId);
		        BigDecimal totalWonAmount = da.getTotalAmountWonByUserId(userId);
		        BigDecimal totalOngoingAmount = da.getTotalAmountOngoingByUserId(userId);
		        
		        totalWonAmount = totalWonAmount != null ? totalWonAmount : BigDecimal.ZERO;
		        totalOngoingAmount = totalOngoingAmount != null ? totalOngoingAmount : BigDecimal.ZERO;

		        model.addAttribute("totalLeads", totalLeads);
		        model.addAttribute("totalWonAmount", totalWonAmount);
		        model.addAttribute("totalOngoingAmount", totalOngoingAmount);

	        }

	     
	        if (roles.contains("ROLE_ADMIN")) {
	            List<Leads> leadsList = da.getLeadsList();
	            model.addAttribute("leadsList", leadsList);
	        }
	    } else {

	        return "redirect:/login"; 
	    }
	    return "secure/index";
	}
	
	
	@GetMapping("/secure/detail/{customerId}")
	public String leadDetails(@PathVariable Long customerId, Model model) {
	    Customer customer = da.getCustomerById(customerId);
	    List<Leads> leadsList = da.getLeadsListbyCustomerId(customerId);

	    model.addAttribute("customer", customer);
	    model.addAttribute("leadsList", leadsList); 

	    return "secure/detail";
	}
    
    
    @GetMapping("/secure/leadsView/{leadsId}") 
    public String leadsView(@PathVariable Long leadsId, Model model) {
        Leads leads = da.getleadsById(leadsId);
        model.addAttribute("leads", leads);
        return "secure/leadsView"; // Ensure this is the correct template path
    }

    
    @PostMapping("/secure/leadsView/addEditLeads")
    public String processEditLead(@ModelAttribute Leads leads, Model model, @RequestParam Long leadsId) {
    	  if (leadsId == 0) {
              da.insertLeads(leads); 
          } else {
              da.editLeadsById(leads, leadsId); 
          }
          return "redirect:/secure"; 
      }

    @GetMapping("/secure/customerview/{customerId}") 
    public String customerview(@PathVariable Long customerId, Model model) {
        Customer customer = da.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "secure/customerview"; // Ensure this is the correct template path
    }

    @PostMapping("/secure/customerview/addEditCustomer")
    public String processCustomer(@ModelAttribute Customer customer, Model model, @RequestParam Long customerId) {
    	      da.editCustomerById(customer, customerId); 
    		    List<Leads> leadsList = da.getLeadsListbyCustomerId(customerId);
    	        model.addAttribute("customer", customer);
    		    model.addAttribute("leadsList", leadsList); 
          return "secure/detail"; 
      }
    
    @GetMapping("/secure/detail/deleteLeadsById/{customerId}/{leadsId}")
    public String deleteLeadsById_Customer(Model model, @PathVariable Long leadsId, @PathVariable Long customerId) { 
        da.deleteLeads(leadsId); 
        Customer customer=da.getCustomerById(customerId);
        model.addAttribute("customer", customer);
	    List<Leads> leadsList = da.getLeadsListbyCustomerId(customerId);
	    model.addAttribute("leadsList", leadsList); 
        return "secure/detail";
    }

    
   @GetMapping("/secure/deleteLeadsById/{leadsId}")
   public String deleteLeadsById(Model model, @PathVariable Long leadsId) { 
       da.deleteLeads(leadsId); 
       List<Leads> lead2 = da.getLeadsList();
       model.addAttribute("leadsList", lead2);
       return "redirect:/secure";
   }

  //-------------------customer------------
  

    
    @PostMapping("/secure/index/addEditCustomer") ///secure/addEditCustomerForm
   	public String processCustomer(Model model, @ModelAttribute Customer customer, @RequestParam Long customerId) { //@RequestParam 请求中获取单个参数
           if (customerId == 0) {
               da.insertCustomer(customer); 
           } else {
               da.editCustomerById(customer, customerId);  // according to the id, update the content from customer datebase
           }
           List<Customer> customer1 = da.getCustomerList();
           model.addAttribute("CustomerList", customer1);
           model.addAttribute("customer", new Customer());
           return "secure/detail"; 
       }
    
    @GetMapping("/secure/deleteCustomerById/{customerId}")
    public String deleteCustomerById(Model model, @PathVariable Long customerId) {
        da.deleteCustomer(customerId); 
        List<Customer> customer2 = da.getCustomerList();
        model.addAttribute("customerList", customer2);
        return "secure/detail";
    }
    
    
	@GetMapping("/secure/detail")
    public String secureDetail(Model model) {

      //  model.addAttribute(new Customer());
        model.addAttribute("leadsList", da.getLeadsList());//transfer all the leads data, using html to decide which one 
        model.addAttribute("customer", da.getCustomerList()) ; // test for if display customer info
        return "secure/detail";
    }
	
	@GetMapping("/secure/detail/backToIndex")
	public String backToIndex() {
		return  "redirect:/secure"; 
	}

	
	@GetMapping("/secure/addleads")
	public String addleads(Model model) {
	 
	    model.addAttribute("leads", new Leads());
	    return "secure/addleads";
	}


	@GetMapping("/secure/api/getCustomerName/{customerId}")
	@ResponseBody
	public Map<String, String> getCustomerName(@PathVariable Long customerId) {
	    Customer customer = da.getCustomerById(customerId); 
	    Map<String, String> response = new HashMap<>();

	    if (customer != null) {
	        response.put("name", customer.getName());
	    } else {
	        response.put("name", null);
	    }
	    return response;
	}

}
