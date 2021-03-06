package home.diabetesapp;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import helper.database.DBHelper;
import helper.domain.BGL;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphViewFragment extends Fragment {
    private ListView listView;
    private List<BGL> bglList;
    private DBHelper dbHelper;

    private GraphView bglLineGraph;

    EditText textDateFrom, textDateTo;



    public GraphViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bglList = new ArrayList<BGL>();
        dbHelper = new DBHelper(this.getActivity());

        textDateFrom = (EditText) this.getActivity().findViewById(R.id.textDateFrom);
        textDateTo = (EditText) this.getActivity().findViewById(R.id.textDateTo);

        Log.i("INFO", "TextDateFrom is : "+ textDateFrom.getText() + ", TextDateTo is: "+ textDateTo.getText());


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph_view, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        showGraphView();

    }

    public void showGraphView() {
        bglLineGraph = (GraphView) getView().findViewById(R.id.bglgraph);
        bglLineGraph.setTitle("BGL Graph");
        Log.i("INFO", "After graph is instantiated");

        Log.i("INFO", "TextDateFrom is : "+ textDateFrom.getText() + ", TextDateTo is: "+ textDateTo.getText());

        bglList = dbHelper.getBGLBetweenDates(textDateFrom.getText().toString(), textDateTo.getText().toString());

        int len = bglList.size();
        final DataPoint[] bglDataPoint = new DataPoint[len];
        if (bglList.isEmpty()) {
            Toast.makeText(this.getActivity(), "No data is available for the selected dates to display", Toast.LENGTH_SHORT).show();
            return;
        }
        int counter = 0;
        for (BGL a : bglList) {

            bglDataPoint[counter] = new DataPoint(counter, a.getBgReading());
            counter++;
        }

        LineGraphSeries<DataPoint> line_series = new LineGraphSeries<DataPoint>();
        line_series.resetData(bglDataPoint);
        line_series.setDrawDataPoints(true);
        line_series.setDataPointsRadius(6);

//        line_series.setThickness(5);

        bglLineGraph.addSeries(line_series);

        // set the bound
        // set manual X bounds

        bglLineGraph.getViewport().setXAxisBoundsManual(true);
        bglLineGraph.getViewport().setMinX(1);
        bglLineGraph.getViewport().setMaxX(31);

        // set manual Y bounds
        bglLineGraph.getViewport().setYAxisBoundsManual(true);
        bglLineGraph.getViewport().setMinY(50);
        bglLineGraph.getViewport().setMaxY(300);

        bglLineGraph.getViewport().setScrollable(true);
        dbHelper.closeDB();
    }

}
