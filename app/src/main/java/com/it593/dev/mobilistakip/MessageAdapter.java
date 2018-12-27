package com.it593.dev.mobilistakip;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MessageAdapter extends BaseAdapter {


    private Activity _activity;
    private static LayoutInflater inflater = null;
    private List<Message> _messages;

    public MessageAdapter(Activity activity, List<Message> messages) {
        _activity = activity;
        _messages = messages;
        inflater = (LayoutInflater)_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (_messages == null)
            return 0;
        return _messages.size();
    }

    public Object getItem(int position) {
        if (_messages != null && _messages.size() > 0 && position < _messages.size())
            return _messages.get(position);

        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public String getItemGonderici(int position) {
        if (_messages != null && _messages.size() > 0 && position < _messages.size())
            return _messages.get(position).getGonderici();

        return "empty";
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if( convertView == null)
            vi = inflater.inflate(R.layout.list_item_users, null);

        TextView txtGonderen = (TextView)vi.findViewById(R.id.txtUserLeft);
        TextView txtContent = (TextView)vi.findViewById(R.id.txtMessageLeft);
        TextView txtTime = (TextView)vi.findViewById(R.id.txtTimeLeft);

        if (_messages != null && _messages.size() > 0 && position < _messages.size()) {
            Message message = _messages.get(position);

            if (message != null) {
                String gonderici = message.getGonderici();

                if (txtGonderen != null)
                    txtGonderen.setText(gonderici);

                if (txtContent != null)
                    txtContent.setText(message.getMesajText());

                if(txtTime != null)
                    txtTime.setText(message.getZaman());





            }
        }

        return vi;
    }
}
