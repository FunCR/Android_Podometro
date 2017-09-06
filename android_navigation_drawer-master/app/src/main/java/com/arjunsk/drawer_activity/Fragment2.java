package com.arjunsk.drawer_activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;


public class Fragment2 extends Fragment {

    private OnFragmentInteractionListener mListener;

    GraphView graph;
    //LineGraphSeries<DataPoint> series;
    //LineGraphSeries<DataPoint> series2;

    ListView listViewDispositivos;
    ArrayAdapter<String> adapter;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onFragmentInteraction("Info Graficos");
        }

        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        graph = (GraphView) view.findViewById(R.id.graph);
        //cargarDatosGrafico();
        //Config Y
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-180);
        graph.getViewport().setMaxY(180);
        //Config X
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(20);
        //Config Label X and Y
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                    //return "";
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + "°";
                }
            }
        });
        //Config Intervalos
        graph.getGridLabelRenderer().setHumanRounding(false);


        Button button = (Button) view.findViewById(R.id.btn_updateGraph);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cargarDatosGrafico();
            }
        });

        listViewDispositivos = (ListView) view.findViewById(R.id.lv_dispositivos); // Instancio el ListView
        listViewDispositivos.setAdapter(new ListviewContactAdapter(getActivity(), MainActivity.listaDispositivos));


        return view;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }

    public void cargarDatosGrafico(){
        graph.removeAllSeries();

        /*DataPoint[] dataPointsAux1 = new DataPoint[MainActivity.cantidadTotalDatos];
        for(int i = 0; i<MainActivity.acelArray.size();i++){
            int valAux = Integer.parseInt(MainActivity.acelArray.get(i));
            dataPointsAux1[i]= new DataPoint(i, valAux);
        }
        series = new LineGraphSeries<>(dataPointsAux1);
        series.setTitle("Random Curve 1");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(8);
        series.setThickness(6);

        DataPoint[] dataPointsAux2 = new DataPoint[MainActivity.cantidadTotalDatos];
        for(int i = 0; i<MainActivity.giroArray.size();i++){
            int valAux = Integer.parseInt(MainActivity.giroArray.get(i));
            dataPointsAux2[i]= new DataPoint(i, valAux);
        }

        series2 = new LineGraphSeries<>(dataPointsAux2);
        series2.setTitle("Random Curve 2");
        series2.setColor(Color.RED);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(8);
        series2.setThickness(6);

        graph.addSeries(series);
        graph.addSeries(series2);*/
    }


    //CLASE ADAPTADOR PRUEBA
    public static class ListviewContactAdapter extends BaseAdapter{
        private static ArrayList<Device> listContact;

        private LayoutInflater mInflater;

        public ListviewContactAdapter(Context photosFragment, ArrayList<Device> results){
            listContact = results;
            mInflater = LayoutInflater.from(photosFragment);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listContact.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return listContact.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.prueba, null);
            }

            Device deviceAux = MainActivity.listaDispositivos.get(position);
            int cantLineasGrafico = deviceAux.listaCoordenadasAcelerometro.get(0).getValueForGraph().size();

            TextView listItemText = (TextView)convertView.findViewById(R.id.tv_nameDevice);
            listItemText.setText("Acelerometro    Device: "+deviceAux.name);

            GraphView graph = (GraphView) convertView.findViewById(R.id.graphAux);
            //Config Y
            graph.getViewport().setScrollableY(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(-180);
            graph.getViewport().setMaxY(180);
            //Config X
            graph.getViewport().setScrollable(true);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(20);

            graph.getGridLabelRenderer().setHumanRounding(false);


            for (int j = 0; j<cantLineasGrafico;j++){

                DataPoint[] dataPointsAux = new DataPoint[deviceAux.listaCoordenadasAcelerometro.size()];
                for(int i = 0; i<deviceAux.listaCoordenadasAcelerometro.size();i++){
                    int valAux = deviceAux.listaCoordenadasAcelerometro.get(i).getValueForGraph().get(j);
                    dataPointsAux[i]= new DataPoint(i, valAux);
                }
                LineGraphSeries<DataPoint> seriesAux = new LineGraphSeries<>(dataPointsAux);
                //seriesAux.setTitle("a");
                seriesAux.setColor(Color.argb(255,(j+1)*(250/cantLineasGrafico),(j+1)*(100/cantLineasGrafico),(j+1)*(200/cantLineasGrafico)));
                seriesAux.setDrawDataPoints(true);
                seriesAux.setDataPointsRadius(8);
                seriesAux.setThickness(6);

                graph.addSeries(seriesAux);

            }


            TextView listItemTextGiroscopio = (TextView)convertView.findViewById(R.id.tv_GiroscopioGraph);
            listItemTextGiroscopio.setText("Giroscopio    Device: "+deviceAux.name);

            return convertView;
        }

    }

}
