package com.it593.dev.mobilistakip;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListAdaper extends BaseAdapter {
    private Activity _activity;
    private static LayoutInflater inflater = null;
    private List<User> _users;

    public UserListAdaper(Activity activity, List<User> users) {
        _activity = activity;
        _users = users;
        inflater = (LayoutInflater)_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (_users == null)
            return 0;
        return _users.size();
    }

    public Object getItem(int position) {
        if (_users != null && _users.size() > 0 && position < _users.size())
            return _users.get(position);

        return null;
    }

    public long getItemId(int position) {
        if (_users != null && _users.size() > 0 && position < _users.size())
            return _users.get(position).getId();

        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if( convertView == null)
            vi = inflater.inflate(R.layout.list_item_users, null);

        TextView txtUser_Id = (TextView)vi.findViewById(R.id.txtUser_Id);
        TextView txtUser_Name = (TextView)vi.findViewById(R.id.txtUser_Name);
        TextView txtUser_Department = (TextView)vi.findViewById(R.id.txtUser_Department);

        if (_users != null && _users.size() > 0 && position < _users.size()) {
            User user = _users.get(position);

            if (user != null) {
                int id = user.getId();

                if (txtUser_Id != null)
                    txtUser_Id.setText(String.valueOf(id));

                if (txtUser_Name != null)
                    txtUser_Name.setText("Adı: " + user.getFirstName()+" "+ user.getLastName());

                if (txtUser_Department != null)
                    txtUser_Department.setText("Şeflik: " + user.getJob());



            }
        }

        return vi;
    }
}
