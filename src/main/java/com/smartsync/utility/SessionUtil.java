package com.smartsync.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionUtil {
    public static void removeAlertMsg() {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest()
                    .getSession();
            session.removeAttribute("alertObject");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
