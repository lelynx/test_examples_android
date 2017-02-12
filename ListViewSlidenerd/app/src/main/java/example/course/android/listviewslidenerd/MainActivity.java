package example.course.android.listviewslidenerd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // réferencer la ListView
        mListView = (ListView) findViewById(R.id.listOfContacts);

        // créer l'ArrayAdapter--NB: ici, on utilise un fichier d'apparence prédéfinie
        // en le cherchant dans android.R.layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, jours);

        // affecter cet adapter à la listView
        mListView.setAdapter(adapter);

        // créer un click listener
        mListView.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this,ContactListActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.mnuShowContactListActivity){
            startActivity(new Intent(this, ContactListActivity.class));
        }
        if(id==R.id.mnuShowWeekDaysActivity){
            startActivity(new Intent(this, WeekDaysActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ici, du fait du layout utilisé, et qui ne contient qu'un TexteView,
        // la paramètre view est le TextView qui affiche le contact--On peut le récupérer directement
        TextView tv = (TextView) view;
        // lire le jour
        String jour = tv.getText().toString();
        // on affiche l'élément cliqué
        Toast.makeText(MainActivity.this, "Item#" + position + " " + jour, Toast.LENGTH_LONG).show();

    }
}
