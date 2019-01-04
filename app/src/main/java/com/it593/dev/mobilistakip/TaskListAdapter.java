package com.it593.dev.mobilistakip;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskListAdapter extends BaseAdapter {
    private Activity _activity;
    private static LayoutInflater inflater = null;
    private List<Task> _tasks;

    public TaskListAdapter(Activity activity, List<Task> tasks) {
        _activity = activity;
        _tasks = tasks;
        inflater = (LayoutInflater)_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (_tasks == null)
            return 0;
        return _tasks.size();
    }

    public Object getItem(int position) {
        if (_tasks != null && _tasks.size() > 0 && position < _tasks.size())
            return _tasks.get(position);

        return null;
    }

    public long getItemId(int position) {
        if (_tasks != null && _tasks.size() > 0 && position < _tasks.size())
            return _tasks.get(position).getidTask();

        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if( convertView == null)
            vi = inflater.inflate(R.layout.list_item_task, null);

        TextView txtTask_Id = (TextView)vi.findViewById(R.id.txtTask_Id);
        TextView txtTask_Name = (TextView)vi.findViewById(R.id.txtTask_name);
        TextView txtTask_Desc = (TextView)vi.findViewById(R.id.txtShort_Desc);

        if (_tasks != null && _tasks.size() > 0 && position < _tasks.size()) {
            Task task = _tasks.get(position);

            if (task != null) {
                int id = task.getidTask();

                if (txtTask_Id != null)
                    txtTask_Id.setText("ID: "+ String.valueOf(id));

                if (txtTask_Name != null)
                    txtTask_Name.setText("Adı: " + task.getName());

                if (txtTask_Desc != null)
                    txtTask_Desc.setText("Açıklama: " + task.getShort_description());



            }
        }

        return vi;
    }
}
