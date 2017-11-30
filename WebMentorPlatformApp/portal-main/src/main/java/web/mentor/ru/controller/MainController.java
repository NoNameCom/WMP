package web.mentor.ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import web.mentor.ru.EmailService;
import web.mentor.ru.model.Account;
import web.mentor.ru.model.AccountType;
import web.mentor.ru.service.AccountService;
import web.mentor.ru.service.AccountTypeService;
import web.mentor.ru.service.RememberMeService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
public class MainController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTypeService accountTypeService;

//    @Autowired
//    private RememberMeService rememberMeService;

//    @RequestMapping(value = "/update**", method = RequestMethod.GET)
//    public ModelAndView updatePage(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView();
//
//        if (rememberMeService.isRememberMeAuthenticated()) {
//            rememberMeService.setRememberMeTargetUrlToSession(request);
//            model.addObject("loginUpdate", true);
//            model.setViewName("index");
//
//        } else {
//            model.setViewName("update");
//        }
//
//        return model;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if("anonymousUser".equals(object)){
            ModelAndView model = new ModelAndView();
            model.setViewName("index");
            model.addObject("account", null);

            return model;
        }else {
            Account account = (Account)object;

            ModelAndView model = new ModelAndView();
            model.setViewName("index");
            model.addObject("account", account);

            return model;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?logout";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(){
//        return "redirect:/";
//    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String registration(ModelMap model){
//        List<AccountType>accountTypes = accountTypeService.getAllAccountTypes();
//        System.out.println(accountTypes);
//        return "index";
//    }

//    @RequestMapping(value = "/mail", method = RequestMethod.GET)
//    public String sendMail() throws MessagingException {
//        EmailService.sendEmail("smtp.gmail.com", "notificationwmp@gmail.com", "32167Lbvf666", "kuzminda1992@gmail.com", "Web Mentor Platform: notification", "Test: New user want to start learning");
//
//
//        return "redirect:/";
//    }
}
