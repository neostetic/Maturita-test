package cz.spsmb.demo.csv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PercentageFormatter {

    private List<Double> values = new LinkedList<Double>();
    private String name;

    public PercentageFormatter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addValue(double value) {
        this.values.add(value);
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public double gerPercentageOf(int index) {
        double help = 0;
        for (Double value : values) {
            char[] chars = value.toString().toCharArray();
            if (chars[0] == String.valueOf(index).toCharArray()[0]) {
                help++;
            }
        }
        return help / (double) values.size();
    }
}
