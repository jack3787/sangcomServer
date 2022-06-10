package capstone.sangcom.service;

import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;


public interface UserService {

    public User login(LoginDTO loginDTO);
    public String findPassword(FindPasswordDTO findPasswordDTO);
    public User findById(String id);
    public User register(User use);
    public boolean editProfile(String id);
    public boolean editPassword(String id, String newPassword);
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO);
    public boolean leave(String id);

}
