package web.mentor.ru.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mentor.ru.model.Account;
import web.mentor.ru.repository.AccountRepository;
import web.mentor.ru.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return accountRepository.getAccountByLogin(login);
    }
}
