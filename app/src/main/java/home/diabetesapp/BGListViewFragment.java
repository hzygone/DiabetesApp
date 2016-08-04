package home.diabetesapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        listView.setAdapter(new ArrayAdapter<BGL>(this.getActivity(), android.R.layout.simple_list_item_1, bglList));
    }

}
