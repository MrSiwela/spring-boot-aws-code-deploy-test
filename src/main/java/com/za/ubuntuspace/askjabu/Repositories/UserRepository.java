package com.za.ubuntuspace.askjabu.Repositories;

import com.za.ubuntuspace.askjabu.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    public List<User> findAllByVendorId(int id);

    public List<User> findAllByEnabled(boolean isEnabled);
}
