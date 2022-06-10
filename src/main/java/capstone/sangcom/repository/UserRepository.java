package capstone.sangcom.repository;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;

public interface UserRepository {

    public User create(User user);
    public User findById(String id);
    public boolean update();
    public boolean update(String id, String password);
    public boolean update(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean delete(String id);

}
