//package com.company.project.controller;
//
//import io.swagger.annotations.Api;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 视图
// *
// * @author wenbin
// * @version V1.0
// * @date 2020年3月18日
// */
//@Api(tags = "视图")
//@Controller
//@RequestMapping("/index")
//public class IndexController0 {
//
//    @GetMapping("/login")
//    public String logout() {
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return "redirect:/index/home" ;
//        }
//        return "login" ;
//    }
//
//    @GetMapping("/home")
//    public String home() {
//        return "home" ;
//    }
//
//    @GetMapping("/users/password")
//    public String updatePassword() {
//        return "users/update_password" ;
//    }
//
//    @GetMapping("/users/info")
//    public String userDetail(Model model) {
//        model.addAttribute("flagType", "edit");
//        return "users/user_edit" ;
//    }
//
//    @GetMapping("/menus")
//    public String menusList() {
//
//        return "menus/menu_list" ;
//    }
//
//    @GetMapping("/roles")
//    public String roleList() {
//        return "roles/role_list" ;
//    }
//
//    @GetMapping("/assay")
//    public String assayList() {
//        return "assay/assay_list" ;
//    }
//
//    @GetMapping("/chest")
//    public String chestList() {
//        return "chest/chest_list" ;
//    }
//
//    @GetMapping("/manage")
//    public String manageList() {
//        return "manage/manage_list" ;
//    }
//    @GetMapping("/manage2")
//    public String manageList2() {
//        return "manage/manage_list2" ;
//    }
//
//    @GetMapping("/pdf")
//    public String pdfList2() {
//        return "pdf/pdf_list" ;
//    }
//
//    @GetMapping("/boss")
//    public String bossList() {
//        return "boss/boss_list" ;
//    }
//    @GetMapping("/boss2")
//    public String bossList2() {
//        return "boss/boss_list2" ;
//    }
//
//    @GetMapping("/other")
//    public String otherList() {
//        return "other/other_list" ;
//    }
//
//    @GetMapping("/users")
//    public String userList() {
//        return "users/user_list" ;
//    }
//
//    @GetMapping("/logs")
//    public String logList() {
//        return "logs/log_list" ;
//    }
//
//    @GetMapping("/depts")
//    public String deptList() {
//        return "depts/dept_list" ;
//    }
//
//    @GetMapping("/403")
//    public String error403() {
//        return "error/403" ;
//    }
//
//    @GetMapping("/404")
//    public String error404() {
//        return "error/404" ;
//    }
//
//    @GetMapping("/500")
//    public String error405() {
//        return "error/500" ;
//    }
//
//    @GetMapping("/main")
//    public String indexHome() {
//        return "main" ;
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "about" ;
//    }
//
//    @GetMapping("/build")
//    public String build() {
//        return "build" ;
//    }
//
//    @GetMapping("/sysContent")
//    public String sysContent() {
//        return "syscontent/list" ;
//    }
//
//    @GetMapping("/sysDict")
//    public String sysDict() {
//        return "sysdict/list" ;
//    }
//
//    @GetMapping("/sysGenerator")
//    public String sysGenerator() {
//        return "generator/list" ;
//    }
//
//    @GetMapping("/sysJob")
//    public String sysJob() {
//        return "sysjob/list";
//    }
//
//    @GetMapping("/sysJobLog")
//    public String sysJobLog() {
//        return "sysjoblog/list";
//    }
//
//    @GetMapping("/sysFiles")
//    public String sysFiles() {
//        return "sysfiles/list";
//    }
//    /*********************************chexing*******************************************/
//    @GetMapping("/surgery")
//    public String surgery() { return "surgery/surgery_list"; }
//    @GetMapping("/tooth")
//    public String tooth() { return "tooth/tooth_list"; }
//    @GetMapping("/ebh")
//    public String ebh() { return "ebh/ebh_list"; }
//    @GetMapping("/blood")
//    public String blood() { return "blood/blood_list"; }
//    @GetMapping("/eye")
//    public String eye() { return "eye/eye_list"; }
//    @GetMapping("/internal")
//    public String internal() { return "internal/internal_list"; }
//}
