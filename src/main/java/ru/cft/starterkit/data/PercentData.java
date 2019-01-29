package ru.cft.starterkit.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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

    private final static String FILENAME = "src/main/java/ru/cft/starterkit/data/percent.txt";
    private ArrayList<Row> rows = new ArrayList<>();

    private PercentData() throws IOException{
        readFile();
    }

    private void readFile() throws IOException {
        try(FileReader fr = new FileReader(FILENAME)){
            Scanner scan = new Scanner(fr);
            L1: while(scan.hasNext()){
                double min = scan.nextDouble();
                double max = scan.nextDouble();
                double percent = scan.nextDouble();
                Row row = new Row(min, max, percent);
                Iterator<Row> iter = rows.iterator();   //Проверка, не пересекаются ли ранги
                while(iter.hasNext()){
                    Row t = iter.next();
                    double tmin = t.getMin();
                    double tmax = t.getMax();
                    if (! (((min < tmin) && (max <=tmin)) || ((min >= tmax) && (max >tmax))) ){
                        continue L1;
                    }
                }
                rows.add(row);
            }
        }
    }

    //Singleton
    private static PercentData single = null;

    public ArrayList<Row> getRows(){
        return rows;
    }

    public static PercentData getInstance() throws IOException{
        if (single==null)
            single = new PercentData();
        return single;
    }
}
