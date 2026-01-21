package business;

import dao.impl.LoginDAOImpl;

public interface ILoginService {
    Boolean login(String username, String password);
}
