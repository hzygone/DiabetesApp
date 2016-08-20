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
import helper.domain.Nutrition;


/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionListViewFragment extends Fragment {
    private List<Nutrition> nutritionList;
    private DBHelper dbHelper;
    private ListView listView;

    private EditText textDateFrom, textDateTo;

    public NutritionListViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        nutritionList = new ArrayList<Nutrition>();
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

        nutritionList = dbHelper.getNutritionBetweenDates(textDateFrom.getText().toString(), textDateTo.getText().toString());

        if (nutritionList.isEmpty()) {
            Toast.makeText(this.getActivity(), "No data is available for the selected dates to display", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayAdapter<Nutrition> adapter = new ArrayAdapter<Nutrition>(this.getActivity(), android.R.layout.simple_list_item_1, nutritionList);
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
                String nameString = "";
                String timeString = "";
                String dateString = "";
                String quantityString = "";
                String commentString = "";

                //Strip out the values of id,time,date,bglvalue and comment from the clicked item.
                if (tempText.getText().toString() != null) {
                    itemString = tempText.getText().toString().split(",");
                    idString = itemString[0].split("=")[1];
                    nameString = itemString[1].split("=")[1];
                    dateString = itemString[2].split("=")[1];
                    timeString = itemString[3].split("=")[1];
                    quantityString = itemString[4].split("=")[1];
                        if (itemString[5].split("=").length > 1) {
                            commentString = itemString[5].split("=")[1];

                    }
                    else{
                        commentString = "";
                    }
                }

                Intent modifyIntent = new Intent(view.getContext(), ModifyNutritionActivity.class);
                Log.i("INFO", idString + " " + nameString + " " + dateString + " " + timeString + " " + quantityString + " " + commentString);
                modifyIntent.putExtra("id", idString);
                modifyIntent.putExtra("name", nameString);
                modifyIntent.putExtra("date", dateString);
                modifyIntent.putExtra("time", timeString);
                modifyIntent.putExtra("quantity", quantityString);
                modifyIntent.putExtra("comment", commentString);
                dbHelper.closeDB();
                Log.i("INFO", "id="+idString+ " name="+ nameString+ " date="+ dateString+" time="+
                        timeString+ " quantity="+ quantityString+ " comment="+ commentString );
                startActivity(modifyIntent);
            }
        });
    }

}
