package hr.xmjosic.restfulwebservice.helloworld.service.impl;

import hr.xmjosic.restfulwebservice.helloworld.exception.UserException;
import hr.xmjosic.restfulwebservice.helloworld.model.User;
import hr.xmjosic.restfulwebservice.helloworld.repository.IUserH2Repository;
import hr.xmjosic.restfulwebservice.helloworld.service.IUserService;
import hr.xmjosic.restfulwebservice.helloworld.validator.IValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service("H2Service")
@AllArgsConstructor
@Slf4j
public class UserH2ServiceImpl implements IUserService {

    private final IUserH2Repository repository;
    private final IValidator validator;

    @Override
    public List<User> getUsers() {
        List<User> userList = this.repository.findAll();
        if(userList.isEmpty()) throw new UserException("No users found!");
        return userList;
    }

    @Override
    public User saveUser(User newUser) throws ExecutionException, InterruptedException {
        this.validator.validateUser(newUser.getUsername(), newUser.getBirthDate());
        return this.repository.save(newUser);
    }

    @Override
    public User getUserById(int id) {
        Optional<User> optionalUser = this.repository.findById(id);
        return optionalUser.orElseThrow(() -> new UserException("No user found!"));
    }

    @Override
    public boolean deleteUserById(int id) {
        this.repository.deleteById(id);
        return true;
    }
}
