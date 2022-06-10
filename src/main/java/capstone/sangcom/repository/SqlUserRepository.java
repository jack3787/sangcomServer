package capstone.sangcom.repository;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SqlUserRepository implements UserRepository{

    private final String USER_TABLE = "user";

    private class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("id"), rs.getString("password"), rs.getString("name"),
                    rs.getString("phone"), rs.getInt("schoolgrade"), rs.getInt("schoolclass"),
                    rs.getInt("schoolnumber"), UserRole.valueOf(rs.getString("role")), rs.getInt("year"),
                    rs.getString("birth"), rs.getString("email"));
        }
    }

    private NamedParameterJdbcTemplate jdbcTemplate;

    private final UserRowMapper userRowMapper;

    public SqlUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.userRowMapper = new UserRowMapper();
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO " + USER_TABLE +
                " VALUES(:id, :password, :name, :phone, :schoolgrade," +
                " :schoolclass, :schoolnumber, :role, :year, :birth, :email)";

        Map<String, Object> params = makeUserParam(user);
        jdbcTemplate.update(query, params);

        return user;
    }

    //        int update = jdbcTemplate.update("INSERT INTO :table VALUES(:id, :password, :name, :phone, :schoolgrade, :schoolclass, :schoolnumber, :role, :year, :birth, :email)"
//                , USER_TABLE
//                , user.getId(), user.getPassword(), user.getName(), user.getPhone()
//                , user.getSchoolgrade(), user.getSchoolclass(), user.getSchoolnumber()
//                , user.getRole(), user.getYear(), user.getBirth(), user.getEmail());

    @Override
    public User findById(String id) {
        String query = "SELECT * FROM " + USER_TABLE + " WHERE id = :id";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("table", USER_TABLE);
        params.put("id", id);

        List<User> result = jdbcTemplate.query(query, params, userRowMapper);

        if(result.size() != 0)
            return result.get(0);
        else
            return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean update(String id, String password) {
        String query = "UPDATE " + USER_TABLE +
                " SET password = :password WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);

        int rs = jdbcTemplate.update(query, params);

        if(rs == 1) return true;
        else return false;
    }

    @Override
    public boolean update(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        String query = "UPDATE " + USER_TABLE +
                " SET phone = :phone, schoolgrade = :schoolgrade, schoolclass = :schoolclass, schoolnumber = :schoolnumber," +
                " birth = :birth, year = :year, email = :email" +
                " WHERE id = :id";
        Map<String, Object> params = makeDetailUserParam(id, updateUserInfoDTO);

        int rs = jdbcTemplate.update(query, params);

        if(rs == 1) return true;
        else return false;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM " + USER_TABLE + " WHERE id = '" + id + "'";

        int rs = jdbcTemplate.update(query, (SqlParameterSource) null);

        if(rs == 1) return true;
        else return false;
    }

    public void removeAll() {
        jdbcTemplate.update("DELETE FROM " + USER_TABLE, (SqlParameterSource) null);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + USER_TABLE
                , userRowMapper);
    }

    private Map<String, Object> makeUserParam(User user) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("id", user.getId());
        params.put("password", user.getPassword());
        params.put("name", user.getName());
        params.put("phone", user.getPhone());
        params.put("schoolgrade", user.getSchoolgrade());
        params.put("schoolclass", user.getSchoolclass());
        params.put("schoolnumber", user.getSchoolnumber());
        params.put("role", user.getRole().getValue());
        params.put("year", user.getYear());
        params.put("birth", user.getBirth());
        params.put("email", user.getEmail());

        return params;
    }

    private Map<String, Object> makeDetailUserParam(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("id", id);
        params.put("phone", updateUserInfoDTO.getPhone());
        params.put("schoolgrade", updateUserInfoDTO.getSchoolgrade());
        params.put("schoolclass", updateUserInfoDTO.getSchoolclass());
        params.put("schoolnumber", updateUserInfoDTO.getSchoolnumber());
        params.put("year", updateUserInfoDTO.getYear());
        params.put("birth", updateUserInfoDTO.getBirth());
        params.put("email", updateUserInfoDTO.getEmail());

        return params;
    }
}
