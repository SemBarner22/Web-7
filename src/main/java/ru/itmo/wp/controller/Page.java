package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.NoticeService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";
    private static final String NOTICE_ID_SESSION_KEY = "noticeId";
    private static final String MESSAGE_SESSION_KEY = "message";

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @ModelAttribute("user")
    public User getUser(HttpSession httpSession) {
        return userService.findById((Long) httpSession.getAttribute(USER_ID_SESSION_KEY));
    }

    @ModelAttribute("notices")
    public List<Notice> getNotices(HttpSession httpSession) {
        return noticeService.findAll();
    }

    @ModelAttribute("notice")
    public Notice getNotice(HttpSession httpSession) {
        return noticeService.findById((Long) httpSession.getAttribute(NOTICE_ID_SESSION_KEY));
    }

    @ModelAttribute("message")
    public String getMessage(HttpSession httpSession) {
        String message = (String) httpSession.getAttribute(MESSAGE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_SESSION_KEY);
        return message;
    }

    void setUser(HttpSession httpSession, User user) {
        if (user != null) {
            httpSession.setAttribute(USER_ID_SESSION_KEY, user.getId());
        } else {
            unsetUser(httpSession);
        }
    }

    void setNotice(HttpSession httpSession, Notice notice) {
        if (notice != null) {
            httpSession.setAttribute(NOTICE_ID_SESSION_KEY, notice.getId());
        } else {
            unsetNotice(httpSession);
        }
    }

    void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    void unsetNotice(HttpSession httpSession) {
        httpSession.removeAttribute(NOTICE_ID_SESSION_KEY);
    }

    void putMessage(HttpSession httpSession, String message) {
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
    }
}
