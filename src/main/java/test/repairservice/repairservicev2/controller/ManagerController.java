package test.repairservice.repairservicev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.repairservice.repairservicev2.service.BaseRepairRequestService;
import test.repairservice.repairservicev2.service.impl.UserService;

@Controller
@RequestMapping
public class ManagerController {
    @Autowired
    private BaseRepairRequestService repairRequestService;

    @Autowired
    private UserService userService;

    @GetMapping("manager-dashboard")
    public String getManagerRequests(Model model){
        model.addAttribute("repairRequest", repairRequestService.findAll());
        model.addAttribute("masters", userService.getMasters());
        return "manager-dashboard";
    }

    @GetMapping("manager-dashboard-table")
    public String getManagerTable(Model model){
        model.addAttribute("masters", userService.getAllUsers());
        return "manager-dashboard-table";
    }
}
