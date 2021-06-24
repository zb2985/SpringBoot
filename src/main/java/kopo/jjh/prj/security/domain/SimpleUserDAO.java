package kopo.jjh.prj.security.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SimpleUserDAO {
    @Autowired
    JdbcTemplate jt;
    public String getRolesByusername(String username) {
        return jt.queryForObject("select role from account where username=?", new Object[] {username}, (rs, rowNum) -> {
            return rs.getString(1);
        });
    }

    public List<Map<String, String>> getOAuthInfoByProviderAndUniqueId(String provider, String uniqueId) {
        return jt.query("select * from users_oauth where provider=? and unique_id=?",
                new Object[] { provider, uniqueId }, (rs, rowNum) -> {

                    Map<String, String> aRow = new HashMap<>();
                    aRow.put("seq", rs.getString("seq"));
                    aRow.put("username", rs.getString("username"));
                    aRow.put("provider", rs.getString("provider"));
                    aRow.put("uniqueId", rs.getString("unique_id"));
                    aRow.put("regDate", rs.getString("reg_date"));
                    aRow.put("lastDate", rs.getString("last_date"));
                    return aRow;

                });
    }

}
