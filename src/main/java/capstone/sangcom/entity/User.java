package capstone.sangcom.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

    private String id;
    private String password;
    private String name;
    private String phone;
    private Integer schoolgrade;
    private Integer schoolclass;
    private Integer schoolnumber;
    private UserRole role;
    private Integer year;
    private String birth;
    private String email;

}

