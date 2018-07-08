package mlg.warkop.com.mypsychologist.helper;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mlg.warkop.com.mypsychologist.database.MessageObject;
import mlg.warkop.com.mypsychologist.model.Message;

public class MessageRealmHelper {
    private static final String TAG = "MessageRealmHelper";

    private Realm realm;

    public MessageRealmHelper(Context context, Realm mRealm) {
        realm = mRealm;
    }

    public void addMessage(Message message) {
        MessageObject listObj = new MessageObject();
        listObj.setId(message.getId());
        listObj.setMessageText(message.getMessageText());
        listObj.setMessageFrom(message.getMessageFrom());
        listObj.setMessageTo(message.getMessageTo());
        listObj.setMessageTime(message.getMessageTime());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(listObj);
        realm.commitTransaction();
    }

    public ArrayList<Message> getListMessage() {
        ArrayList<Message> data = new ArrayList<>();

        RealmResults<MessageObject> rCall = realm.where(MessageObject.class).findAll();
        if(rCall.size() > 0)
            showLog("Size : " + rCall.size());

        for (int i = 0; i < rCall.size(); i++) {
            data.add(new Message(rCall.get(i)));
        }

        return data;
    }

    public ArrayList<Message> getListMessage(String userName) {
        ArrayList<Message> data = new ArrayList<>();

        RealmResults<MessageObject> rCall = realm.where(MessageObject.class).equalTo("messageFrom", userName).or().equalTo("messageTo", userName).findAll();
        if(rCall.size() > 0)
            showLog("Size : " + rCall.size());

        for (int i = 0; i < rCall.size(); i++) {
            data.add(new Message(rCall.get(i)));
        }

        return data;
    }

    public void clearMessage(){
        RealmResults<MessageObject> data = realm.where(MessageObject.class).findAll();
        realm.beginTransaction();
        data.clear();
        realm.commitTransaction();
    }

    private void showLog(String s) {
        Log.d(TAG, s);
    }
}
