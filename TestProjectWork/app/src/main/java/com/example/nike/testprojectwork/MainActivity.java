package com.example.nike.testprojectwork;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private WetherAdapter wAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy \n время: HH");
    private ListView wetherList;

    MyTask mt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wetherList=(ListView)findViewById(R.id.listView);
        final TextView nodata =(TextView)findViewById(R.id.nodata);
        btn = (Button)findViewById(R.id.weather);
        final Intent intent = new Intent(this,SecondActivity.class);
        wetherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    intent.putExtra("Data",sdf.format(mt.get().get(position).getDate().getTime()));
                    intent.putExtra("maxPresure",String.valueOf(mt.get().get(position).getMaxPressure()));
                    intent.putExtra("minPresure",String.valueOf(mt.get().get(position).getMinPressure()));
                    intent.putExtra("maxTemperature",String.valueOf(mt.get().get(position).getMaxTemp()));
                    intent.putExtra("minTemperature",String.valueOf(mt.get().get(position).getMinTemp()));
                    intent.putExtra("minWind",String.valueOf(mt.get().get(position).getMinWind()));
                    intent.putExtra("maxWind",String.valueOf(mt.get().get(position).getMaxWind()));
                    intent.putExtra("maxRelwet",String.valueOf(mt.get().get(position).getMaxRelwet()));
                    intent.putExtra("minRelwet",String.valueOf(mt.get().get(position).getMinRelwet()));
                    intent.putExtra("minHeat",String.valueOf(mt.get().get(position).getMinHeat()));
                    intent.putExtra("maxHeat",String.valueOf(mt.get().get(position).getMaxHeat()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mt = new MyTask();
               mt.execute();
               try {
                   wAdapter = new WetherAdapter(MainActivity.this, mt.get());
                    if(wAdapter.wethers.size()==0){
                        nodata.setVisibility(View.VISIBLE);
                   wetherList.setAdapter(wAdapter);}
                   else{nodata.setVisibility(View.INVISIBLE);
                        wetherList.setAdapter(wAdapter);}
               } catch (InterruptedException e) {
                   e.printStackTrace();

               } catch (ExecutionException e) {

                   e.printStackTrace();
               }

           }
       });


            }


    public class MyTask extends AsyncTask<Void,Void,List<StackWether>> {
        static final String LOG_TAG="mylogtag";
        static final String KEY_FORECAST="FORECAST";
        static final String KEY_PRESSURE="PRESSURE";
        static final String KEY_TEMPERATURE="TEMPERATURE";
        static final String KEY_WIND="WIND";
        static final String KEY_RELWET="RELWET";
        static final String KEY_HEAT="HEAT";

        boolean isError;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<StackWether> doInBackground(Void... params) {

            List<StackWether> stackWether= new ArrayList<StackWether>();
            StackWether curStackWether=null;


            try {
                URL url = new URL("http://informer.gismeteo.ru/xml/29634_1.xml");
                HttpURLConnection connect = (HttpURLConnection)url.openConnection();
                connect.setReadTimeout(10000);
                connect.setConnectTimeout(15000);
                connect.setRequestMethod("GET");
                connect.setDoInput(true);
                connect.connect();

                InputStream stream = connect.getInputStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp =  factory.newPullParser();
                xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
                xpp.setInput(stream, null);
                while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT)
                {
                    switch (xpp.getEventType()) {
                        case XmlPullParser.START_TAG:
                            if(xpp.getName().equalsIgnoreCase(KEY_FORECAST))
                            {curStackWether = new StackWether();

                                curStackWether.setDate(Integer.valueOf(xpp.getAttributeValue(0)),Integer.valueOf(xpp.getAttributeValue(1)),
                                        Integer.valueOf(xpp.getAttributeValue(2)),Integer.valueOf(xpp.getAttributeValue(3)));
                            }

                            if(xpp.getName().equalsIgnoreCase(KEY_PRESSURE))
                            {
                                curStackWether.setMaxPressure(Integer.valueOf(xpp.getAttributeValue(0)));
                                curStackWether.setMinPressure(Integer.valueOf(xpp.getAttributeValue(1)));
                            }
                            if(xpp.getName().equalsIgnoreCase(KEY_TEMPERATURE))
                            {
                                curStackWether.setMaxTemp(Integer.valueOf(xpp.getAttributeValue(0)));
                                curStackWether.setMinTemp(Integer.valueOf(xpp.getAttributeValue(1)));
                            }
                            if(xpp.getName().equalsIgnoreCase(KEY_WIND))
                            {
                                curStackWether.setMinWind(Integer.valueOf(xpp.getAttributeValue(0)));
                                curStackWether.setMaxWind(Integer.valueOf(xpp.getAttributeValue(1)));
                            }
                            if(xpp.getName().equalsIgnoreCase(KEY_RELWET))
                            {
                                curStackWether.setMaxRelwet(Integer.valueOf(xpp.getAttributeValue(0)));
                                curStackWether.setMinRelwet(Integer.valueOf(xpp.getAttributeValue(1)));
                            }
                            if(xpp.getName().equalsIgnoreCase(KEY_HEAT))
                            {
                                curStackWether.setMinHeat(Integer.valueOf(xpp.getAttributeValue(0)));
                                curStackWether.setMaxHeat(Integer.valueOf(xpp.getAttributeValue(1)));
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if(xpp.getName().equalsIgnoreCase(KEY_FORECAST)){stackWether.add(curStackWether);}
                            break;
                        default:
                            break;
                    }
                    xpp.next();
                }
                Log.d(LOG_TAG, "End_Document");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                isError=true;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                isError=true;
            } catch (ProtocolException e) {
                e.printStackTrace();
                isError=true;
            } catch (IOException e) {
                e.printStackTrace();
                isError=true;
            }

            return stackWether;
        }

        @Override
        protected void onPostExecute(List<StackWether> result) {
            super.onPostExecute(result);
            if(isError){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ошибка")
                        .setMessage("Сервер не доступен")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }

    }

}
