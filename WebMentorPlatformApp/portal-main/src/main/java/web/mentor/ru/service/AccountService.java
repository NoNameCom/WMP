package web.mentor.ru.service;

import web.mentor.ru.model.Account;

public interface AccountService {

    void addAccount(Account account);

    Account getAccountById(Long id);

    Account getAccountByLogin(String login);
}
