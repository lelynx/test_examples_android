package example.course.android.listviewslidenerd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class ContactsAdapter extends ArrayAdapter {
    public ContactsAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
