package example.course.android.sqlitecomplete;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ContactAdapter mAdapter;
    private EditText txtName;
    private EditText txtAdresse;
    private TextView txtListOfContacts;
    private TextView txtChercherContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: entrée");

        // récupérer les widget
        txtName = (EditText) findViewById(R.id.txtName);
        txtAdresse = (EditText) findViewById(R.id.txtAdresse);
        txtListOfContacts = (TextView) findViewById(R.id.txtListOfContacts);
        txtChercherContact = (TextView) findViewById(R.id.txtRecherche);
        // instancier le Contact Adapter
        mAdapter = new ContactAdapter(this);
    }

    public void insertContacts(View v) {
        String name = txtName.getText().toString();
        String adresse = txtAdresse.getText().toString();
        // insérer ce contact
        long id = mAdapter.insert(name, adresse);
    }

    public void showContacts2(View v) {
        Log.d(TAG, "showContacts: entrée");
        String name, address;
        Cursor cursor = mAdapter.getContacts_rawQuery();
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(1);
                address = cursor.getString(2);
                Log.d(TAG, "Contact: " + name + "; Address: " + address);
            } while (cursor.moveToNext());
        }
    }//showContacts

    public void showContacts(View v) {
        Log.d(TAG, "showContacts2: entrée");
        String name, address;
        String liste = mAdapter.getContacts_query();
        // afficher la liste
        txtListOfContacts.setText(liste);

    }//showContacts

    public void chercherContact(View v) {
        String name = txtChercherContact.getText().toString();
        String address=null;
        int uid;
        String[] contactData = mAdapter.getContactByName(name);
        if (contactData != null) {
            Toast.makeText(this, contactData[0], Toast.LENGTH_SHORT).show();
        }
        // afficher le contact
        txtName.setText(name);
        txtAdresse.setText(address);
    }

    public void updateContact(View v){
        String name=txtName.getText().toString();
        String address=txtAdresse.getText().toString();

        int nbLigne=mAdapter.updateContact(name, address);

    }
    public void deleteContact(View v){
        String name=txtName.getText().toString();
        int nbLigne=mAdapter.deleteContact(name);

    }

}
