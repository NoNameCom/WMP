package web.mentor.ru.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = AccountType.class)
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private AccountType accountType;

    @OneToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private Form form;

    public Account() {
    }

    public Account(String login, String email, String password, AccountType accountType) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public Account(String login, String email, String password, AccountType accountType, Form form) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.form = form;
    }

    public Account(Long id, String login, String email, String password, AccountType accountType, Form form) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.form = form;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Account account = (Account) object;

        if (!login.equals(account.login)) return false;
        if (!email.equals(account.email)) return false;
        return password.equals(account.password);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType.getType() +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AccountType> accountTypes = new ArrayList<>();
        accountTypes.add(accountType);
        return accountTypes;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
