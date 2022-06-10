package capstone.sangcom.dto.login;

import lombok.Data;

@Data
public class UpdateUserInfoDTO {

    private final String phone;
    private final Integer schoolgrade;
    private final Integer schoolclass;
    private final Integer schoolnumber;
    private final String birth;
    private final Integer year;
    private final String email;

}
