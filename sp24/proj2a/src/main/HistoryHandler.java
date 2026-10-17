package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private final NGramMap map;

    public HistoryHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> labels = new ArrayList<>();
        List<TimeSeries> histories = new ArrayList<>();
        for (String word : q.words()) {
            TimeSeries history = map.weightHistory(word, q.startYear(), q.endYear());
            if (!history.isEmpty() && !labels.contains(word)) {
                labels.add(word);
                histories.add(history);
            }
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, histories);
        return Plotter.encodeChartAsString(chart);
    }
}
