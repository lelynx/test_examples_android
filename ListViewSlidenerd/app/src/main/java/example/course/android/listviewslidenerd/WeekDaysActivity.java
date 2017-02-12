package example.course.android.listviewslidenerd;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class WeekDaysActivity extends AppCompatActivity {
    private ListView lvImage;
    private String[] jours;
    private String[] descriptions;
    private int[] images = {R.drawable.lundi, R.drawable.mardi, R.drawable.mercredi,
            R.drawable.jeudi, R.drawable.vendredi, R.drawable.samedi, R.drawable.dimanche};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_days);

        // obtenir un objet Ressource
        Resources res = getResources();
        // obtenir les titres
        jours = res.getStringArray(R.array.title);
        // obtenir les descriptions
        descriptions = res.getStringArray(R.array.description);
        // obtenir un eréférence à la ListView
        lvImage = (ListView) findViewById(R.id.lvImage);

        // instancier l'Adapter
        WeekDaysAdapter adapter = new WeekDaysAdapter(this);
        lvImage.setAdapter(adapter);

    }

    // la classe Adapter
    class WeekDaysAdapter extends ArrayAdapter {
        private Context mContext;


        public WeekDaysAdapter(Context context) {
            super(context, R.layout.single_row, R.id.tvTitle, jours);
            //context: le context, ici this
            // resource: le layout d'apparence
            // textViewResourceId: l'élément auquel on doit affecter une data
            // objects: la référence de la source de données
            mContext = context;
        } // CTor


        public View getView_ORIGINAL(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            // convertir en objet java, le layout single_row.xml
            View row = inflater.inflate(R.layout.single_row, parent, false);
            // affecter les données aux widgets
            TextView tv;
            //tv = (TextView) v.findViewById(R.id.tvTitle);
            //tv.setText(jours[position]);
            tv = (TextView) row.findViewById(R.id.tvDescription);
            tv.setText(descriptions[position]);
            ImageView iv = (ImageView) row.findViewById(R.id.ivImage);
            iv.setImageResource(images[position]);
            // rendre la vue avec les données à afficher
            return row;
        }

        public View getView_Optimized150(int position, View convertView, ViewGroup parent) {
            // convertView est une ligne à réutiliser
            View row = convertView;
            // une nouvelle ligne est construite si nécessaire
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                // convertir en objet java, le layout single_row.xml
                row = inflater.inflate(R.layout.single_row, parent, false);
            }
            // affecter les données aux widgets
            TextView tv;
            tv = (TextView) row.findViewById(R.id.tvDescription);
            tv.setText(descriptions[position]);
            ImageView iv = (ImageView) row.findViewById(R.id.ivImage);
            iv.setImageResource(images[position]);
            // rendre la vue avec les données à afficher
            return row;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Version optimisée 175%: utilisation d'un ViewHolder
            // convertView est une ligne à réutiliser
            View row = convertView;
            // une nouvelle ligne est construite si nécessaire
            ViewHolder holder;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                // convertir en objet java, le layout single_row.xml
                row = inflater.inflate(R.layout.single_row, parent, false);
                // instancier le ViewHolder
                holder = new ViewHolder(row);
                // sauvegarder le ViewHolder dans row
                row.setTag(holder);
            }
            // affecter les données aux widgets
            holder = (ViewHolder) row.getTag();
            holder.getTitle().setText(jours[position]);
            holder.getDescription().setText(descriptions[position]);
            holder.getImageView().setImageResource(images[position]);
            // rendre la vue avec les données à afficher
            return row;
        }// getView
    }// class WeekDaysAdapter

    /*
    le ViewHolder
     */
    class ViewHolder {

        private ImageView mImageView;
        private TextView mTitle;
        private TextView mDescription;
        public ViewHolder(View v) {
            mImageView = (ImageView) v.findViewById(R.id.ivImage);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mDescription = (TextView) v.findViewById(R.id.tvDescription);
        }

        public ImageView getImageView() {
            return mImageView;
        }

        public TextView getTitle() {
            return mTitle;
        }

        public TextView getDescription() {
            return mDescription;
        }
    }//class ViewHolder
}
