/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freemrs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;


public class ChartPanelDraw {

    XYDataset dataset;
    private final ChartPanel chartPanel ;
    java.util.List<Vitals> result;
    String type;

    public ChartPanelDraw(java.util.List<Vitals> result, String type) {
        this.type=type;
        this.result=result;
        dataset = createTimeDataset();
        chartPanel = createChart(dataset,type);
        
        JFrame f = new JFrame("Vital Plot");
        f.setTitle("Vital Plot");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setLayout(new BorderLayout(0, 5));
        f.add(chartPanel, BorderLayout.CENTER);
        f.setIconImage(new ImageIcon(getClass().getResource("/images/icon_transparent.png")).getImage());
        
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setMouseWheelEnabled(true);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createTrace());
        panel.add(createDate());
        panel.add(createZoom());
        f.add(panel, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private XYDataset createTimeDataset() {
         TimeSeries s1 = new TimeSeries(type);       //Creating time series plot for X

        if (type.equals("Weight")) {

            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getWeight());
            }
        }else if(type.equals("Height")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getHeight());
            }
        }else if(type.equals("BP Systolic")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getBpSystolic());
            }
        }else if(type.equals("BP Diastolic")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getBpDiastolic());
            }
        }else if(type.equals("Pulse")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getPulse());
            }
        }else if(type.equals("Temperature")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getTemperature());
            }
        }else if(type.equals("Oxygen Saturation")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getOxygenSaturation());
            }
        }else if(type.equals("BMI")){
            for (Vitals vital : result) {
                s1.add(new Day(vital.getDateTime().getDate(), vital.getDateTime().getMonth() + 1, vital.getDateTime().getYear()+1900), vital.getBmi());
            }
        }else{
            System.out.println("Type select vital error");
        }
        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }
    private JComboBox createTrace() {
        final JComboBox trace = new JComboBox();
        final String[] traceCmds = {"Enable Trace", "Disable Trace"};
        trace.setModel(new DefaultComboBoxModel(traceCmds));
        trace.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                if (traceCmds[0].equals(trace.getSelectedItem())) {
                    chartPanel.setHorizontalAxisTrace(true);
                    chartPanel.setVerticalAxisTrace(true);
                    chartPanel.repaint();
                } else {
                    chartPanel.setHorizontalAxisTrace(false);
                    chartPanel.setVerticalAxisTrace(false);
                    chartPanel.repaint();
                }
            }
        });
        return trace;
    }

    private JComboBox createDate() {
        final JComboBox date = new JComboBox();
        final String[] dateCmds = {"Horizontal Dates", "Vertical Dates"};
        date.setModel(new DefaultComboBoxModel(dateCmds));
        date.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFreeChart chart = chartPanel.getChart();
                XYPlot plot = (XYPlot) chart.getPlot();
                DateAxis domain = (DateAxis) plot.getDomainAxis();
                if (dateCmds[0].equals(date.getSelectedItem())) {
                    domain.setVerticalTickLabels(false);
                } else {
                    domain.setVerticalTickLabels(true);
                }
            }
        });
        return date;
    }

    private JButton createZoom() {
        final JButton auto = new JButton(new AbstractAction("Auto Zoom") {

            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.restoreAutoBounds();
            }

        });
        return auto;
    }

    private ChartPanel createChart(XYDataset dataset,String type) {
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(type, "Date", getValueAxis(), dataset);
        
    
    
        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return new ChartPanel(chart);
    }
    
    private String getValueAxis(){
        if (type.equals("Weight")) {

            return "kg";
        }else if(type.equals("Height")){
            return "m";
        }else if(type.equals("BP Systolic")){
            return "mmHg";
        }else if(type.equals("BP Diastolic")){
            return "mmHg";
        }else if(type.equals("Pulse")){
            return "per min";
        }else if(type.equals("Temperature")){
            return "C";
        }else if(type.equals("Oxygen Saturation")){
            return "%";
        }else if(type.equals("BMI")){
            return "kg/m^2";
        }else{
            return null;
          }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ChartPanelDraw cpd = new ChartPanelDraw(null,null);
            }
        });
    }
}
