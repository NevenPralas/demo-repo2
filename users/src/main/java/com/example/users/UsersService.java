package com.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository repo;

    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    public UsersModel registerUser(String login, String password, String email){
        if(login !=null && password !=null){
            if(repo.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return repo.save(usersModel);
        }
        else{
            return null;
        }
    }

    public UsersModel authenticate(String login, String password){
         return repo.findByLoginAndPassword(login, password).orElse(null);
    }
}
