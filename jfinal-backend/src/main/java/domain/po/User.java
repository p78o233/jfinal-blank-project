package domain.po;/*
 * @author p78o2
 * @date 2020/5/25
 */

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
    public static final User dao = new User().dao();
}
