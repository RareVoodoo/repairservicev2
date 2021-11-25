package test.repairservice.repairservicev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import test.repairservice.repairservicev2.service.BaseRepairRequestService;
import test.repairservice.repairservicev2.service.impl.RepairRequestService;

@Controller
@RequestMapping
public class ClientController {

    @Autowired
    private BaseRepairRequestService repairRequestService;

    @GetMapping("/user-dashboard")
    public String loadClientMainPage(Model model){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("repairRequest", repairRequestService.getUserRepairRequests(user.getName()));
        return "user-dashboard";
    }
}
