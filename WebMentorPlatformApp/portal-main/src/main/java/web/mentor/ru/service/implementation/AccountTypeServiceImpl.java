package web.mentor.ru.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mentor.ru.model.AccountType;
import web.mentor.ru.repository.AccountTypeRepository;
import web.mentor.ru.service.AccountTypeService;

import java.util.List;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public List<AccountType> getAllAccountTypes() {
        return (List<AccountType>) accountTypeRepository.findAll();
    }

    @Override
    public AccountType getAccountType(Long id) {
        return accountTypeRepository.findOne(id);
    }
}
