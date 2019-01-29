package ru.cft.starterkit.data;

public class PercentData {
    public class Row{
        final private double min;
        final private double max;
        final private double percent;

        Row(double min, double max, double percent){
            this.min = min;
            this.max = max;
            this.percent = percent;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public double getPercent() {
            return percent;
        }
    }
}
