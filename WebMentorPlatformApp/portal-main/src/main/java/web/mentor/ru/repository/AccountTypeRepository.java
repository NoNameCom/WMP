package web.mentor.ru.repository;

import org.springframework.data.repository.CrudRepository;
import web.mentor.ru.model.AccountType;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
}
