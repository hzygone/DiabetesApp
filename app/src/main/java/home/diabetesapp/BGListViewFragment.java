package home.diabetesapp;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import helper.database.BGLDBHelper;
import helper.domain.BGL;


/**
 * A simple {@link Fragment} subclass.
 */
public class BGListViewFragment extends Fragment {
    List<BGL> bglList;
    BGLDBHelper dbHelper;
    ListView listView ;
    public BGListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bglList = new ArrayList<BGL>();
        dbHelper = new BGLDBHelper(this.getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bgl_list_view, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        showWeeklyActivity();
    }
    public void showWeeklyActivity() {
        listView = (ListView) getView().findViewById(R.id.bglListView);
        bglList = dbHelper.getAllBGL();
        if(bglList.isEmpty()){
            Toast.makeText(this.getActivity(),"No data is available to display", Toast.LENGTH_SHORT).show();
            return;
        }
        listView.setAdapter(new ArrayAdapter<BGL>(this.getActivity(), android.R.layout.simple_list_item_1, bglList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditText tempText = new EditText(getActivity());
                TextView tem = (TextView) view;

                tempText.setText(tem.getText().toString()+ "Hello " + view.toString());
                Intent editBGLIntent = new Intent(getActivity(),AddBGLactivity.class);
                editBGLIntent.putExtra("EXTRA_TEXT", tem.getText());

                Toast.makeText(getActivity(),"Item is clicked "+ id + tempText.getText().toString()+" "+ position, Toast.LENGTH_SHORT).show();
                 startActivity(editBGLIntent);

            }
        });

    }

}
