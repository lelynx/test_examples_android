package example.course.android.listviewslidenerd;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactListActivity extends ListActivity {
    private ListView mListView;
    private String[] jours = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet"
            , "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mListView=getListView();

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,jours);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // récupérer le TextView
        TextView tvMois= (TextView) v;
        // récupérer le nom du jour
        String jour=tvMois.getText().toString();
        // affichage
        Toast.makeText(this, "Mois: "+jour, Toast.LENGTH_LONG).show();
    }
}
