package cz.spsmb.demo.csv;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FormatterHolder {
    private List<CsvFormatter> formatters = new LinkedList<CsvFormatter>();
    private HashMap<String, PercentageFormatter> percentageFormatters = new HashMap<>();

    public List<CsvFormatter> getFormatters() {
        return formatters;
    }

    public void setFormatters(List<CsvFormatter> formatters) {
        this.formatters = formatters;
    }

    public HashMap<String, PercentageFormatter> getPercentageFormatters() {
        return percentageFormatters;
    }

    public void setPercentageFormatters(HashMap<String, PercentageFormatter> percentageFormatters) {
        this.percentageFormatters = percentageFormatters;
    }

    public void filterOption(CsvFormatter formatter) {
        formatters.add(formatter);
    }

    public void setPercentages() {
        for (CsvFormatter formatter : formatters) {
            String to = formatter.getTo();
            String from = formatter.getFrom();
            double value = formatter.getValue();
            System.out.println(formatter.toString());
            if (percentageFormatters.size() == 0) {
                PercentageFormatter percentageFormatterTo = new PercentageFormatter(to);
                PercentageFormatter percentageFormatterFrom = new PercentageFormatter(from);
                percentageFormatterTo.addValue(value);
                percentageFormatterFrom.addValue(value);
                percentageFormatters.put(to, percentageFormatterTo);
                percentageFormatters.put(from, percentageFormatterFrom);
            } else {
                percentageFormatters.get(to).addValue(value);
                percentageFormatters.get(from).addValue(value);
            }
//            else {
//                for (int i = 0; i < percentageFormatters.size(); i++) {
//                    if (Objects.equals(percentageFormatters.get(i).getName(), to)) {
//                        percentageFormatters.get(i).addValue(value);
//                    } else if (Objects.equals(percentageFormatters.get(i).getName(), from)) {
//                        percentageFormatters.get(i).addValue(value);
//                    } else {
//                        PercentageFormatter percentageFormatter1 = new PercentageFormatter(to);
//                        PercentageFormatter percentageFormatter2 = new PercentageFormatter(from);
//                        percentageFormatter1.addValue(value);
//                        percentageFormatter2.addValue(value);
//                        percentageFormatters.add(percentageFormatter1);
//                        percentageFormatters.add(percentageFormatter2);
//                    }
//                }
//            }
        }
    }
}
