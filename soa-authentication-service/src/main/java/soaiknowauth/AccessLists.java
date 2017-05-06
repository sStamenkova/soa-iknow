package soaiknowauth;

/**
 * Created by Popov on 06.5.2017.
 */
public class AccessLists {

    public static boolean checkUriAndUser(String uri, String user) {
        if(user=="admin") return true;
        else return false;
    }
}
