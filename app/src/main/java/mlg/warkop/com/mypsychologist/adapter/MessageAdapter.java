package mlg.warkop.com.mypsychologist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist.model.Message;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;
    private List<Message> messagesO;
    private Context mContext;
    private Filter filter;
    private String name;
    public final int isMe = 1, isOther = 0;

    public static class MyViewHolderOther extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtMessage)
        TextView txtMessage;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.txtTime)
        TextView txtTime;

        public MyViewHolderOther(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class MyViewHolderMe extends RecyclerView.ViewHolder {
        @BindView(R.id.txtMessage)
        TextView txtMessage;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.txtTime)
        TextView txtTime;

        public MyViewHolderMe(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public MessageAdapter(Context context, List<Message> messageList, String name){
        this.messages = messageList;
        mContext = context;
        this.name = name;
    }

    public Filter getFilter() {
        if(filter == null)
            filter = new MessagesFilter();
        return filter;
    }

    private class MessagesFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub
            FilterResults result = new FilterResults();
            String substr = constraint.toString().toLowerCase();
            if(substr == null || substr.length() == 0) {
                result.values = messagesO;
                result.count = messagesO.size();
            } else {
                final ArrayList<Message> nlist = new ArrayList<Message>();
                int count = messagesO.size();

                for(int i = 0;i<count;i++) {
                    final Message message = messagesO.get(i);
                    String value = "", value2 = "";
                    value = message.getMessageFrom().toLowerCase();
                    value2 = message.getMessageFrom().toLowerCase();
                    if(value.contains(substr) || value2.contains(substr)) {
                        nlist.add(message);
                    }
                }
                result.values = nlist;
                result.count = nlist.size();
            }

            return result;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // TODO Auto-generated method stub
            messages = (List<Message>) results.values;
            notifyDataSetChanged();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case isMe:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message_layout_me, parent, false);
                return new MyViewHolderMe(view);
            case isOther:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message_layout_other, parent, false);
                return new MyViewHolderOther(view);
        }
        return null;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Layouting default
        float d = mContext.getResources().getDisplayMetrics().density;
        int top = (int)(5 * d), left = (int)(100 * d), bottom = (int)(5 * d), right = (int)(5 * d); // margin in pixels

        Message messageBefore = new Message();
        Message message = this.messages.get(position);
        Message messageNext = new Message();
        try{
            messageNext = this.messages.get(position + 1);
            messageBefore = this.messages.get(position - 1);
        }catch (Exception e){

        }

        String decryptedName = message.getMessageFrom();
        String decryptedMessage = message.getMessageText();
        String decryptedTime = message.getMessageTime();

        if (decryptedName.equals(this.name)) {
            final MyViewHolderMe myViewHolder = (MyViewHolderMe) holder;
            myViewHolder.txtMessage.setText(decryptedMessage);
            myViewHolder.txtTime.setText(decryptedTime);
            myViewHolder.txtTime.requestLayout();
            myViewHolder.txtMessage.requestLayout();
            myViewHolder.linearLayout.requestLayout();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)myViewHolder.linearLayout.getLayoutParams();
            int bottomNew = (int)(1 * d);
            params.setMargins(left, top, right, bottomNew);
            myViewHolder.linearLayout.setLayoutParams(params);

            myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(myViewHolder.txtTime.getVisibility() == View.GONE)
                        myViewHolder.txtTime.setVisibility(View.VISIBLE);
                    else
                        myViewHolder.txtTime.setVisibility(View.GONE);
                }
            });

            if(isOnlyEmoji(decryptedMessage)){
                if(decryptedMessage.equals("❤")){
                    myViewHolder.txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                    myViewHolder.txtMessage.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }else{
                    myViewHolder.txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38);
                    myViewHolder.txtMessage.setTextColor(Color.WHITE);
                }
                myViewHolder.linearLayout.setBackgroundColor(Color.TRANSPARENT);
            }else{
                myViewHolder.txtMessage.setTextColor(Color.WHITE);
                myViewHolder.txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                myViewHolder.linearLayout.setBackgroundColor(Color.parseColor("#E91E63"));
                if(messageNext.getMessageFrom().equals(this.name) && messageBefore.getMessageFrom().equals(this.name)){
                    int topThis = (int)(1 * d), bottomThis = topThis;
                    params.setMargins(left, topThis, right, bottomThis);
                    myViewHolder.linearLayout.setLayoutParams(params);
                }else if(messageNext.getMessageFrom().equals(this.name)){
                    int bottomThis = (int)(1 * d);
                    params.setMargins(left, top, right, bottomThis);
                    myViewHolder.linearLayout.setLayoutParams(params);
                }else if(messageBefore.getMessageFrom().equals(this.name)){
                    int topThis = (int)(1 * d);
                    params.setMargins(left, topThis, right, bottom);
                    myViewHolder.linearLayout.setLayoutParams(params);
                }
                myViewHolder.linearLayout.setBackgroundResource(R.layout.message_bubble_you);
            }
        } else {
            final MyViewHolderOther myViewHolder = (MyViewHolderOther) holder;
            myViewHolder.txtName.setText(decryptedName);
            myViewHolder.txtMessage.setText(decryptedMessage);
            myViewHolder.txtMessage.requestLayout();
            myViewHolder.txtTime.setText(decryptedTime);
            myViewHolder.txtTime.requestLayout();
            myViewHolder.linearLayout.requestLayout();
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)myViewHolder.linearLayout.getLayoutParams();
            int bottomNew = (int)(1 * d);
            params.setMargins(right, top, left, bottomNew);
            myViewHolder.linearLayout.setLayoutParams(params);

            myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(myViewHolder.txtTime.getVisibility() == View.GONE)
                        myViewHolder.txtTime.setVisibility(View.VISIBLE);
                    else
                        myViewHolder.txtTime.setVisibility(View.GONE);
                }
            });

            if(isOnlyEmoji(decryptedMessage)){
                if(decryptedMessage.equals("❤")){
                    myViewHolder.txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                    myViewHolder.txtMessage.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                }else{
                    myViewHolder.txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38);
                    myViewHolder.txtMessage.setTextColor(Color.BLACK);
                }
                myViewHolder.txtName.setVisibility(View.GONE);
                myViewHolder.linearLayout.setBackgroundColor(Color.TRANSPARENT);
            }else{
                myViewHolder.txtMessage.setTextColor(Color.BLACK);
                myViewHolder.txtMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                myViewHolder.linearLayout.setBackgroundColor(Color.WHITE);
                myViewHolder.txtName.setVisibility(View.VISIBLE);
                if(messageNext.getMessageFrom().equals(message.getMessageFrom()) && messageBefore.getMessageFrom().equals(message.getMessageFrom())){ //Midle of list
                    int topThis = (int)(1 * d), bottomThis = topThis;
                    params.setMargins(right, topThis, left, bottomThis);
                    myViewHolder.linearLayout.setLayoutParams(params);
                    myViewHolder.txtName.setVisibility(View.GONE);
                }else if(messageNext.getMessageFrom().equals(message.getMessageFrom())){ //Top of list
                    int bottomThis = (int)(1 * d);
                    params.setMargins(right, top, left, bottomThis);
                    myViewHolder.linearLayout.setLayoutParams(params);
                    myViewHolder.txtName.setVisibility(View.VISIBLE);
                }else if(messageBefore.getMessageFrom().equals(message.getMessageFrom())){ //bottom of list
                    int topThis = (int)(1 * d);
                    params.setMargins(right, topThis, left, bottom);
                    myViewHolder.linearLayout.setLayoutParams(params);
                    myViewHolder.txtName.setVisibility(View.GONE);
                }
                myViewHolder.linearLayout.setBackgroundResource(R.layout.message_bubble);
            }
        }
    }

    @Override
    public int getItemCount() { return messages == null ? 0 : messages.size(); }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        String nama = message.getMessageFrom();
        int type;
        if (nama.equals(this.name)) {
            type = isMe;
        } else {
            type = isOther;
        }
        return type;
    }

    public boolean isOnlyEmoji(String pesan){
        //String regex = "([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])";
        pesan = pesan.replaceAll("([\\u20a0-\\u32ff\\ud83c\\udc00-\\ud83d\\udeff\\udbb9\\udce5-\\udbb9\\udcee])", "");

        if(pesan.equals(""))
            return true;
        else
            return false;
    }
}
