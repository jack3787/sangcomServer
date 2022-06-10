package capstone.sangcom.repository;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryUserRepository implements UserRepository{
    private Map<String, User> repository;

    public MemoryUserRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public User create(User user){
        if (!repository.containsKey(user.getId())) {
            repository.put(user.getId(), user);
        }

        return repository.get(user);
    }

    @Override
    public User findById(String id) {
        if(repository.containsKey(id))
            return repository.get(id);
        else
            return null;
    }

    @Override
    public boolean update(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        User user = repository.get(id);
        if (user != null) {
            user.setPhone(updateUserInfoDTO.getPhone());
            user.setSchoolgrade(updateUserInfoDTO.getSchoolgrade());
            user.setSchoolclass(updateUserInfoDTO.getSchoolclass());
            user.setSchoolnumber(updateUserInfoDTO.getSchoolnumber());
            user.setBirth(updateUserInfoDTO.getBirth());
            user.setYear(updateUserInfoDTO.getYear());
            user.setEmail(updateUserInfoDTO.getEmail());

            repository.put(id, user);
            return true;
        }else
            return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean update(String id, String password) {
        if (repository.containsKey(id)) {
            User user = repository.get(id);
            user.setPassword(password);

            repository.put(id, user);
            return true;
        }else
            return false;
    }

    @Override
    public boolean delete(String id) {
        if (repository.containsKey(id)) {
            repository.remove(id);

            return true;
        }else
            return false;
    }

}
