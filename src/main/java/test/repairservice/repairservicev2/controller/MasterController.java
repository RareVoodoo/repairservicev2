package test.repairservice.repairservicev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.repairservice.repairservicev2.service.BaseRepairRequestService;

@Controller
@RequestMapping
public class MasterController {
    @Autowired
    private BaseRepairRequestService repairRequestService;

    @GetMapping("master-dashboard")
    public String getMasterRequests(Model model){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("repairRequest", repairRequestService.getMasterRequests(user.getName()));
        return "master-dashboard";
    }
}
