package com.company.project.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "视图")
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/login")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/index/home";
        }
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/users/password")
    public String updatePassword() {
        return "users/update_password";
    }

    @GetMapping("/users/info")
    public String userDetail(Model model) {
        model.addAttribute("flagType", "edit");
        return "users/user_edit";
    }

    @GetMapping("/menus")
    public String menusList() {

        return "menus/menu_list";
    }

    @GetMapping("/roles")
    public String roleList() {
        return "roles/role_list";
    }

    @GetMapping("/users")
    public String userList() {
        return "users/user_list";
    }

    @GetMapping("/logs")
    public String logList() {
        return "logs/log_list";
    }

    @GetMapping("/depts")
    public String deptList() {
        return "depts/dept_list";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error405() {
        return "error/500";
    }

    @GetMapping("/main")
    public String indexHome() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/build")
    public String build() {
        return "surgery";
    }

    @GetMapping("/sysContent")
    public String sysContent() {
        return "syscontent/list";
    }

    @GetMapping("/sysDict")
    public String sysDict() {
        return "sysdict/list";
    }

    @GetMapping("/sysGenerator")
    public String sysGenerator() {
        return "generator/list";
    }

    @GetMapping("/sysJob")
    public String sysJob() {
        return "sysjob/list";
    }

    @GetMapping("/sysJobLog")
    public String sysJobLog() {
        return "sysjoblog/list";
    }

    @GetMapping("/sysFiles")
    public String sysFiles() {
        return "sysfiles/list";
    }

    @GetMapping("/surgery")
    @RequiresPermissions("sys:form:surgery")
    public String Surgery() {
        return "/surgery/surgery_list";
    }

    @GetMapping("/students")
    @RequiresPermissions("sys:stu:detail")
    public String Student() {
        return "/student";
    }

    @GetMapping("/internal")
    @RequiresPermissions("sys:form:internal")
    public String internal() {
        return "/internal/internal_list";
    }

    @GetMapping("/assay")
    @RequiresPermissions("sys:form:assay")
    public String assay() {
        return "/assay/assay_list";
    }

    @GetMapping("/eye")
    @RequiresPermissions("sys:form:eye")
    public String eye() {
        return "/eye/eye_list";
    }

    @GetMapping("/tooth")
    @RequiresPermissions("sys:form:tooth")
    public String tooth() {
        return "/tooth/tooth_list";
    }

    @GetMapping("/ebh")
    @RequiresPermissions("sys:form:EBH")
    public String EBH() {
        return "/ebh/ebh_list";
    }

    @GetMapping("/blood")
    @RequiresPermissions("sys:form:blood")
    public String blood() {
        return "/blood/blood_list";
    }

    @GetMapping("/other")
    @RequiresPermissions("sys:form:other")
    public String other() {
        return "/other/other_list";
    }

    @GetMapping("/obligation")
    @RequiresPermissions("sys:form:obligation")
    public String obligation() {
        return "/obligation";
    }

    @GetMapping("/boss")
    @RequiresPermissions("sys:form:boss")
    public String boss() {
        return "/boss/boss_list";
    }

    @GetMapping("/boss2")
    @RequiresPermissions("sys:form:boss")
    public String boss2() {
        return "/boss/boss_list2";
    }

    @GetMapping("/manage")
    @RequiresPermissions("sys:form:manage")
    public String manage() {
        return "/manage/manage_list";
    }

    @GetMapping("/manage2")
    @RequiresPermissions("sys:form:manage")
    public String manage2() {
        return "/manage/manage_list2";
    }

    @GetMapping("/chest")
    @RequiresPermissions("sys:form:chest")
    public String chest() {
        return "/chest/chest_list";
    }

}
