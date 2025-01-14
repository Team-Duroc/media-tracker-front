package com.northcoders.media_tracker_front.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.northcoders.media_tracker_front.R;
import com.northcoders.media_tracker_front.databinding.FragmentStatsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * todo:
 *  - Find a way to bind data to the charts once the data is available
 *  - Decide what charts are used + What stats are shown
 *  - Place charts for today,weekly,monthly,yearly stats
 *  - Replace dummy information with information from backend
 */

/**
 * In order to add multiple charts in a single layout:
 * <a href="https://github.com/AnyChart/AnyChart-Android/wiki/Multiple-charts-in-a-single-layout">Guide</a>
 */


public class StatsFragment extends Fragment {

    FragmentStatsBinding binding;

    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // DUMMY INFORMATION - SEEING IF THE CHART APPEARS WHEN THE APP IS RUN
        // Example of how to set a Column Chart
        // Set the object progress bar from the binding
        binding.StatsChartView.setProgressBar(binding.StatsProgressBar);

        APIlib.getInstance().setActiveAnyChartView(binding.StatsChartView);
        // Set the column chart
        Cartesian cartesian = AnyChart.column();

        // Get the data in an arraylist
        List<DataEntry> data = new ArrayList<>();

        // ValueDataEntry takes an x value and a y value
        data.add(new ValueDataEntry("Monday",30));
        data.add(new ValueDataEntry("Tuesday",25));
        data.add(new ValueDataEntry("Wednesday",48));
        data.add(new ValueDataEntry("Thursday",0));
        data.add(new ValueDataEntry("Friday",120));
        data.add(new ValueDataEntry("Saturday",210));
        data.add(new ValueDataEntry("Sunday",90));

        // Set the data to a column chart object
        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        // Give the chart an animation
        cartesian.animation(true);

        // Give the chart a title
        cartesian.title("Time Watched This Week");

        // Setting a scale(?) for the yaxis
        cartesian.yScale().minimum(0d);

        // Setting the yAxis label
        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        // ??
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        // Set titles for the x and y axis
        cartesian.xAxis(0).title("Days");
        cartesian.yAxis(0).title("Time Watched (Minutes)");
        cartesian.yAxis(0).labels().rotation(-90);
        cartesian.xAxis(0).labels().rotation(-90);

        // Set the chart to a chartview via the binding
        binding.StatsChartView.setChart(cartesian);

        // PIE CHART EXAMPLE
        // Set Progress bar
        binding.StatsChartViewTwo.setProgressBar(binding.StatsProgressBarTwo);

        APIlib.getInstance().setActiveAnyChartView(binding.StatsChartViewTwo);
        //Create pie chart object
        Pie pie = AnyChart.pie();

        // Create pie chart data
        List<DataEntry> dataEntry = new ArrayList<>();
        dataEntry.add(new ValueDataEntry("Horror",20));
        dataEntry.add(new ValueDataEntry("Comedy",10));
        dataEntry.add(new ValueDataEntry("Action",45));
        dataEntry.add(new ValueDataEntry("Drama",9));
        dataEntry.add(new ValueDataEntry("Thriller",5));

        // Set pie chart data
        pie.data(dataEntry);

        // Set pie chart titles and legend (?) and positions
        pie.title("Genres watched this week");
        pie.labels().position("outside");
        pie.legend().title().enabled(true);
        pie.legend().title().text("Genre").padding(0d,0d,10d,0d);
        pie.legend().position("center-bottom").itemsLayout(LegendLayout.HORIZONTAL).align(Align.CENTER);
        // Set the pie chart to the Chart view via the binding
        binding.StatsChartViewTwo.setChart(pie);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_stats,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}