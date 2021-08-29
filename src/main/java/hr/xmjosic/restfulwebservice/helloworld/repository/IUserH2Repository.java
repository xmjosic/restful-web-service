package hr.xmjosic.restfulwebservice.helloworld.repository;

import hr.xmjosic.restfulwebservice.helloworld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("H2Repository")
public interface IUserH2Repository extends JpaRepository<User, Integer> {}
