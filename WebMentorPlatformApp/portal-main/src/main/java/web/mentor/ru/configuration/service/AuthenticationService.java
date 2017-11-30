package web.mentor.ru.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.mentor.ru.model.Account;
import web.mentor.ru.service.AccountService;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.getAccountByLogin(login);

        if (account == null) {
            throw new UsernameNotFoundException("Account with login " + login + " is not found");
        }

        return account;
    }
}
