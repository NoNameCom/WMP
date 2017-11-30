package web.mentor.ru.repository;

import org.springframework.data.repository.CrudRepository;
import web.mentor.ru.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByLogin(String login);
}
