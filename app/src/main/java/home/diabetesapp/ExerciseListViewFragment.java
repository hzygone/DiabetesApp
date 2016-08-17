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

import helper.database.DBHelper;
import helper.domain.Exercise;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseListViewFragment extends Fragment {
  private   List<Exercise> exerciseList;
  private   DBHelper dbHelper;
  private   ListView listView;

  private   EditText textDateFrom, textDateTo;

    public ExerciseListViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        exerciseList = new ArrayList<Exercise>();
        dbHelper = new DBHelper(this.getActivity());

        textDateFrom = (EditText) this.getActivity().findViewById(R.id.textDateFrom);
        textDateTo = (EditText) this.getActivity().findViewById(R.id.textDateTo);

        Log.i("INFO", "TextDateFrom is : " + textDateFrom.getText().toString() + ", TextDateTo is: " + textDateTo.getText().toString());

        return inflater.inflate(R.layout.fragment_bgl_list_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        showCustomListView();
    }

    public void showCustomListView() {
        listView = (ListView) getView().findViewById(R.id.bglListView);
        Log.i("INFO", "TextDateFrom is : " + textDateFrom.getText() + ", TextDateTo is: " + textDateTo.getText());

        exerciseList = dbHelper.getExerciseBetweenDates(textDateFrom.getText().toString(), textDateTo.getText().toString());

        if (exerciseList.isEmpty()) {
            Toast.makeText(this.getActivity(), "No data is available for the selected dates to display", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this.getActivity(), android.R.layout.simple_list_item_1, exerciseList);
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
                String nameString= "";
                String timeString = "";
                String dateString = "";
                String durationString = "";
                String commentString = "";

                //Strip out the values of id,time,date,bglvalue and comment from the clicked item.
                if (tempText.getText().toString() != null) {
                    itemString = tempText.getText().toString().split(",");
                    idString = itemString[0].split("=")[1];
                    nameString = itemString[1].split("=")[1];
                    dateString = itemString[2].split("=")[1];
                    timeString = itemString[3].split("=")[1];
                    durationString = itemString[4].split("=")[1];
                    if (itemString[5].split("=").length > 1) {
                        commentString = itemString[5].split("=")[1];
                    }
                }

                Intent modifyIntent = new Intent(view.getContext(), ModifyExerciseActivity.class);
                Log.i("INFO", idString + " "+ nameString + " "+ dateString + " "+ timeString+ " "+ durationString+ " "+ commentString);
                modifyIntent.putExtra("id", idString);
                modifyIntent.putExtra("name", nameString);
                modifyIntent.putExtra("date", dateString);
                modifyIntent.putExtra("time", timeString);
                modifyIntent.putExtra("duration", durationString);
                modifyIntent.putExtra("comment", commentString);
                dbHelper.closeDB();
                startActivity(modifyIntent);
            }
        });
    }

}
