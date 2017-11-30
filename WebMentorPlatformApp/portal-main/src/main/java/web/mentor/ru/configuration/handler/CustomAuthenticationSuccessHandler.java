package web.mentor.ru.configuration.handler;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import web.mentor.ru.model.AccountType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        //Проверяет, не был ли уже отправлен ответ с сервера
        if (response.isCommitted()) {
            logger.info("Response has already been committed. Unable to redirect to" + targetUrl);
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        if (authorities.contains(new AccountType("USER"))) {
            return "/";
        } else if (authorities.contains(new AccountType("STUDENT"))) {
            return "/";
        } else if (authorities.contains(new AccountType("MENTOR"))) {
            return "/";
        } else if (authorities.contains(new AccountType("NO_NAME"))) {
            return "/";
        } else {
            //.......
            return "/access_denied";
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        //Возвращает сессию. Если текущего сеанса нет, то метод не будет создавать новый
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        //Отключает кеширование исключительных ситуаций
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
