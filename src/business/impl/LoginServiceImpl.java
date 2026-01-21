package business.impl;

import business.ILoginService;
import dao.impl.LoginDAOImpl;

public class LoginServiceImpl implements ILoginService {
    private final LoginDAOImpl loginDao;
    public LoginServiceImpl() {
        loginDao = new LoginDAOImpl();
    }

    @Override
    public Boolean login(String username, String password) {
        return loginDao.login(username, password);
    }
}
