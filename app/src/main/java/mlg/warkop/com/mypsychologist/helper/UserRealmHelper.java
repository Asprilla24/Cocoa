package mlg.warkop.com.mypsychologist.helper;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mlg.warkop.com.mypsychologist.database.MessageObject;
import mlg.warkop.com.mypsychologist.database.UserObject;
import mlg.warkop.com.mypsychologist.model.Message;
import mlg.warkop.com.mypsychologist.model.User;

public class UserRealmHelper {
    private static final String TAG = "UserRealmHelper";

    private Realm realm;

    public UserRealmHelper(Context context, Realm mRealm) {
        realm = mRealm;
    }

    public void addUser(User user) {
        UserObject listObj = new UserObject();
        listObj.setId(user.getId());
        listObj.setEmail(user.getEmail());
        listObj.setUserName(user.getUserName());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(listObj);
        realm.commitTransaction();
    }

    public ArrayList<User> getListUser() {
        ArrayList<User> data = new ArrayList<>();

        RealmResults<UserObject> rCall = realm.where(UserObject.class).findAll();
        if(rCall.size() > 0)
            showLog("Size : " + rCall.size());

        for (int i = 0; i < rCall.size(); i++) {
            data.add(new User(rCall.get(i)));
        }

        return data;
    }

    public boolean isUserAvailable(String email){
        RealmResults<UserObject> rCall = realm.where(UserObject.class).equalTo("email", email).findAll();

        return rCall.size() > 0;
    }

    public void clearUser(){
        RealmResults<UserObject> data = realm.where(UserObject.class).findAll();
        realm.beginTransaction();
        data.clear();
        realm.commitTransaction();
    }

    private void showLog(String s) {
        Log.d(TAG, s);
    }
}
