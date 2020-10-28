package domain.po;
/*
 * @author p78o2
 * @date 2020/10/27
 */

import com.jfinal.plugin.activerecord.Model;

import java.io.Serializable;


public class Wl_Channel_Consumer extends Model<Wl_Channel_Consumer> implements Serializable {
    public static final Wl_Channel_Consumer dao = new Wl_Channel_Consumer().dao();
}
