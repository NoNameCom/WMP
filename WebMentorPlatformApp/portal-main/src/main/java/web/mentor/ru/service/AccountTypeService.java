package web.mentor.ru.service;

import web.mentor.ru.model.AccountType;

import java.util.List;

public interface AccountTypeService {

    List<AccountType> getAllAccountTypes();

    AccountType getAccountType(Long id);
}
