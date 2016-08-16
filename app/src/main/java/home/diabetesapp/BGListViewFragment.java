package home.diabetesapp;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    ListView listView;

    EditText textDateFrom, textDateTo;

    public BGListViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bglList = new ArrayList<BGL>();
        dbHelper = new BGLDBHelper(this.getActivity());

        textDateFrom = (EditText) this.getActivity().findViewById(R.id.textDateFrom);
        textDateTo = (EditText) this.getActivity().findViewById(R.id.textDateTo);

        Log.i("INFO", "TextDateFrom is : "+ textDateFrom.getText() + ", TextDateTo is: "+ textDateTo.getText());

        return inflater.inflate(R.layout.fragment_bgl_list_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        showCustomListView();
    }

    public void showCustomListView() {
        listView = (ListView) getView().findViewById(R.id.bglListView);
        Log.i("INFO", "TextDateFrom is : "+ textDateFrom.getText() + ", TextDateTo is: "+ textDateTo.getText());

        bglList = dbHelper.getBGLBetweenDates(textDateFrom.getText().toString(), textDateTo.getText().toString());

        if (bglList.isEmpty()) {
            Toast.makeText(this.getActivity(), "No data is available for the selected dates to display", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayAdapter<BGL> adapter = new ArrayAdapter<BGL>(this.getActivity(), android.R.layout.simple_list_item_1, bglList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditText tempText = new EditText(getActivity());
                TextView temp = (TextView) view;
                tempText.setText(temp.getText().toString());

                String[] itemString;
                String idString = "";
                String timeString = "";
                String dateString = "";
                String bglString = "";
                String commentString = "";

                //Strip out the values of id,time,date,bglvalue and comment from the clicked item.
                if (tempText.getText().toString() != null) {
                    itemString = tempText.getText().toString().split(",");
                    idString = itemString[0].split("=")[1];
                    timeString = itemString[1].split("=")[1];
                    dateString = itemString[2].split("=")[1];
                    bglString = itemString[3].split("=")[1];
                    if (itemString[4].split("=").length > 1) {
                        commentString = itemString[4].split("=")[1];
                    }
                }

                Intent modifyIntent = new Intent(view.getContext(), ModifyBGLActivity.class);
                modifyIntent.putExtra("id", idString);
                modifyIntent.putExtra("date", dateString);
                modifyIntent.putExtra("time", timeString);
                modifyIntent.putExtra("bglValue", bglString);
                modifyIntent.putExtra("comment", commentString);

                startActivity(modifyIntent);
            }
        });
    }

}
