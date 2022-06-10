package capstone.sangcom.entity;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtUser {

    private String id;
    private Integer schoolgrade;
    private Integer schoolclass;
    private Integer schoolnumber;
    private UserRole role;
    private Integer year;
    private String name;

}
