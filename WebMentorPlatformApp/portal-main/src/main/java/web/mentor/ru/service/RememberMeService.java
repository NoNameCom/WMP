package web.mentor.ru.service;

import javax.servlet.http.HttpServletRequest;

public interface RememberMeService {
    boolean isRememberMeAuthenticated();

    void setRememberMeTargetUrlToSession(HttpServletRequest request);

    String getRememberMeTargetUrlFromSession(HttpServletRequest request);
}
