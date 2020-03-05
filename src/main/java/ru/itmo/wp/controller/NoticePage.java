package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.validator.NoticeCredentialsCreateValidator;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page{
    private final NoticeService noticeService;
    private final NoticeCredentialsCreateValidator noticeCredentialsCreateValidator;

    public NoticePage(NoticeService noticeService, NoticeCredentialsCreateValidator noticeCredentialsCreateValidator) {
        this.noticeService = noticeService;
        this.noticeCredentialsCreateValidator = noticeCredentialsCreateValidator;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(noticeCredentialsCreateValidator);
//    }

    @GetMapping("/noticepage")
    public String createGet(Model model, HttpSession httpSession) {
        model.addAttribute("createForm", new NoticeCredentials());
        if (getUser(httpSession) == null) {
            putMessage(httpSession, "Congrats, you are not authorized!");
            return "redirect:/";
        }
        return "NoticePage";
    }

    @PostMapping("/noticepage")
    public String createPost(@Valid @ModelAttribute("createForm") NoticeCredentials createForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }
        noticeService.create(createForm);
        putMessage(httpSession, "Congrats, you have been posted!");
        return "redirect:/";
    }

//    @PostMapping(value = "newLife")
//    public String madeNotice(Model model) {
//        System.out.println(model.getAttribute("title"));
//        return "IndexPage";
//    }
}
