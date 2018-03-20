package anki.hack;



        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Environment;
        import android.provider.Settings;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.opencsv.CSVWriter;

        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;


public class MainActivity extends AppCompatActivity  {


    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9,stop,getsetgo;
    private SensorManager msensormanager;
    public SensorEvent sensorEvent1;
    private SensorEventListener msensoreventlistener;
    float[] values;
    float x_value,y_value,z_value;

    String pin_entered;

    ArrayList<Float> x_acc,y_acc,z_acc,x_gyro,y_gyro,z_gyro,x_rot,y_rot,z_rot,x_pres,y_pres,z_pres;
    ArrayList<Long> time_acc,time_gyro,time_rot,time_pres,btn_click;
    ArrayList<Integer> btn_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0=(Button) findViewById(R.id.zero);
        b1=(Button) findViewById(R.id.one);
        b2=(Button) findViewById(R.id.two);
        b3=(Button) findViewById(R.id.three);
        b4=(Button) findViewById(R.id.four);
        b5=(Button) findViewById(R.id.five);
        b6=(Button) findViewById(R.id.six);
        b7=(Button) findViewById(R.id.seven);
        b8=(Button) findViewById(R.id.eight);
        b9=(Button) findViewById(R.id.nine);
        stop=(Button) findViewById(R.id.start);
        getsetgo=(Button) findViewById(R.id.getsetgo);



        getsetgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pin_entered="";

                btn_clicked=new ArrayList<Integer>();

                x_acc=new ArrayList<Float>();
                y_acc=new ArrayList<Float>();
                z_acc=new ArrayList<Float>();
                time_acc=new ArrayList<Long>();

                x_gyro=new ArrayList<Float>();
                y_gyro=new ArrayList<Float>();
                z_gyro=new ArrayList<Float>();
                time_gyro=new ArrayList<Long>();

                x_rot=new ArrayList<Float>();
                y_rot=new ArrayList<Float>();
                z_rot=new ArrayList<Float>();
                time_rot=new ArrayList<Long>();



                x_pres=new ArrayList<Float>();
                y_pres=new ArrayList<Float>();
                z_pres=new ArrayList<Float>();
                time_pres=new ArrayList<Long>();

                btn_click=new ArrayList<Long>();

                msensormanager=(SensorManager) getSystemService(SENSOR_SERVICE);


                msensoreventlistener=new SensorEventListener() {
                    @Override
                    public void onSensorChanged(final SensorEvent sensorEvent) {
                        sensorEvent1=sensorEvent;

                        new Thread()
                        {
                            public void run()
                            {
                                if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                                {
                                    values=sensorEvent.values;
                                    x_value=values[0];
                                    y_value=values[1];
                                    z_value=values[2];

                                    x_acc.add(x_value);
                                    y_acc.add(y_value);
                                    z_acc.add(z_value);
                                    time_acc.add((sensorEvent.timestamp));
                                    try
                                    {
                                        Thread.sleep(2);
                                    }
                                    catch (Exception e)
                                    {

                                    }

                                }
                            }
                        }.run();


                        new Thread(){
                            public void run()
                            {
                                if(sensorEvent.sensor.getType()==Sensor.TYPE_GYROSCOPE)
                                {
                                    values=sensorEvent.values;
                                    x_value=values[0];
                                    y_value=values[1];
                                    z_value=values[2];

                                    time_gyro.add((sensorEvent.timestamp));

                                    x_gyro.add(x_value);
                                    y_gyro.add(y_value);
                                    z_gyro.add(z_value);

                                    try
                                    {
                                        Thread.sleep(2);
                                    }
                                    catch (Exception e)
                                    {

                                    }

                                }
                            }
                        }.run();

                        new Thread()
                        {
                            public void run()
                            {
                                if(sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR)
                                {
                                    values=sensorEvent.values;
                                    x_value=values[0];
                                    y_value=values[1];
                                    z_value=values[2];

                                    time_rot.add((sensorEvent.timestamp));

                                    x_rot.add(x_value);
                                    y_rot.add(y_value);
                                    z_rot.add(z_value);
                                    try
                                    {
                                        Thread.sleep(2);
                                    }
                                    catch (Exception e)
                                    {

                                    }

                                }
                            }
                        }.run();

                        new Thread()
                        {
                            public void run()
                            {
                                if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY)
                                {
                                    values=sensorEvent.values;
                                    x_value=values[0];
                                    y_value=values[1];
                                    z_value=values[2];

                                    time_pres.add((sensorEvent.timestamp)/1000000);
                                    x_pres.add(x_value);
                                    y_pres.add(y_value);
                                    z_pres.add(z_value);

                                    try
                                    {
                                        Thread.sleep(2);
                                    }
                                    catch (Exception e)
                                    {

                                    }

                                }
                            }
                        }.run();



                    }
                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {


                    }
                };

                msensormanager.registerListener(msensoreventlistener,msensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
                msensormanager.registerListener(msensoreventlistener,msensormanager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_FASTEST);
                msensormanager.registerListener(msensoreventlistener,msensormanager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),SensorManager.SENSOR_DELAY_FASTEST);
                msensormanager.registerListener(msensoreventlistener,msensormanager.getDefaultSensor(Sensor.TYPE_PRESSURE),SensorManager.SENSOR_DELAY_FASTEST);


            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"0";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(0);

            }
        });






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"1";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(1);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"2";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(2);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"3";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(3);
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"4";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(4);
            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"5";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(5);
            }
        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"6";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(6);
            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"7";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(7);
            }
        });


        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"8";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(8);
            }
        });


        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin_entered=pin_entered+"9";
                btn_click.add(sensorEvent1.timestamp);
                btn_clicked.add(9);
            }
        });





        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                msensormanager.unregisterListener(msensoreventlistener);

                String basedir=android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
                Log.d("filepath-------",basedir);
                int a = (int)(Math.random()*10000);

                String filename=""+pin_entered+"_"+a+"_acc.csv";
                String filename_gyro=""+pin_entered+"_"+a+"_gyro.csv";
                String filename_rot=""+pin_entered+"_"+a+"_rot.csv";
                String filename_pres=""+pin_entered+"_"+a+"_pres.csv";
                String time_file=""+pin_entered+"_"+a+"-time.csv";

                String filepath=basedir+File.separator+"Think"+File.separator+filename;
                String filepath_gyro=basedir+File.separator+"Think"+File.separator+filename_gyro;
                Log.d("filepath-------",filepath_gyro);
                String filepath_rot=basedir+File.separator+"Think"+File.separator+filename_rot;
                String filepath_pres=basedir+File.separator+"Think"+File.separator+filename_pres;
                String filepath_time=basedir+File.separator+"Think"+File.separator+time_file;

                File f=new File(filepath);
                File f_gyro=new File(filepath_gyro);
                File f_rot=new File(filepath_rot);
                File f_pres=new File(filepath_pres);
                File t_file=new File(filepath_time);

                CSVWriter csvWriter,csvWriter_gyro,csvWriter_rot,csvWriter_pres,csvWritertime;

                FileWriter mFileWriter,fileWriter_gyro,mFileWriter_rot,mFileWriter_pres,timefilewriter;

                try
                {
                    if(f.exists() && !f.isDirectory()){
                        mFileWriter = new FileWriter(filepath,true);
                        fileWriter_gyro=new FileWriter(filepath_gyro,true);
                        mFileWriter_rot=new FileWriter(filepath_rot,true);
                        mFileWriter_pres=new FileWriter(filepath_pres,true);
                        timefilewriter=new FileWriter(filepath_time,true);
                        Log.d("filepath-------","writing...............");
                        csvWriter = new CSVWriter(mFileWriter);
                        csvWriter_gyro=new CSVWriter(fileWriter_gyro);
                        csvWriter_rot=new CSVWriter(mFileWriter_rot);
                        csvWriter_pres=new CSVWriter(mFileWriter_pres);
                        csvWritertime=new CSVWriter(timefilewriter);

                        int length=x_acc.size();
                        int length_gyro=x_gyro.size();
                        int length_rot=x_rot.size();
                        int length_pres=x_pres.size();

                        String x_axis_acc="x-acc";
                        String y_axis_acc="y-acc";
                        String z_axis_acc="z-acc";
                        String acc_time="time-acc";

                        String[] head={x_axis_acc,y_axis_acc,z_axis_acc,acc_time};


                        csvWriter.writeNext(head);
                        for(int i=0;i<length;i++)
                        {

                            String x_value=(x_acc.get(i)).toString();
                            String y_value=(y_acc.get(i)).toString();
                            String z_value=(z_acc.get(i)).toString();
                            String time_string=time_acc.get(i).toString();
                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter.writeNext(data);


                        }
                        Log.d("filepath-------","writing...............");
                        csvWriter.close();


                        String time_field="time";
                        String time_field1="digit";
                        String[] time_data={time_field,time_field1};
                        csvWritertime.writeNext(time_data);
                        for(int p=0;p<btn_click.size();p++)
                        {
                            String time_value=(btn_click.get(p)).toString();
                            String digit=(btn_clicked.get(p)).toString();
                            String[] time_array={time_value,digit};
                            csvWritertime.writeNext(time_array);

                        }

                        csvWritertime.close();


                        String x_axis_gyro="x-gyro";
                        String y_axis_gyro="y-gyro";
                        String z_axis_gyro="z-gyro";
                        String gyro_time="time-gyro";

                        String[] head1={x_axis_gyro,y_axis_gyro,z_axis_gyro,gyro_time};


                        csvWriter_gyro.writeNext(head1);

                        for(int j=0;j<length_gyro;j++)
                        {

                            String x_value=(x_gyro.get(j)).toString();
                            String y_value=(y_gyro.get(j)).toString();
                            String z_value=(z_gyro.get(j)).toString();
                            String time_string=time_gyro.get(j).toString();
                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter_gyro.writeNext(data);


                        }
                        csvWriter_gyro.close();



                        String x_axis_rot="x-rot";
                        String y_axis_rot="y-rot";
                        String z_axis_rot="z-rot";
                        String rot_time="rot-time";

                        String[] head2={x_axis_rot,y_axis_rot,z_axis_rot,rot_time};

                        csvWriter_rot.writeNext(head2);

                        for(int k=0;k<length_rot;k++)
                        {

                            String x_value=(x_rot.get(k)).toString();
                            String y_value=(y_rot.get(k)).toString();
                            String z_value=(z_rot.get(k)).toString();
                            String time_string=time_rot.get(k).toString();

                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter_rot.writeNext(data);


                        }
                        csvWriter_rot.close();



                        String x_axis_pres="x-pres";
                        String y_axis_pres="y-pres";
                        String z_axis_pres="z-pres";
                        String pres_time="pres-time";

                        String[] head3={x_axis_pres,y_axis_pres,z_axis_pres,pres_time};

                        csvWriter_pres.writeNext(head3);
                        for(int l=0;l<length_pres;l++)
                        {

                            String x_value=(x_pres.get(l)).toString();
                            String y_value=(y_pres.get(l)).toString();
                            String z_value=(z_pres.get(l)).toString();

                            String time_string=time_pres.get(l).toString();
                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter_pres.writeNext(data);


                        }
                        csvWriter_pres.close();
                        Toast.makeText(getApplicationContext(),"added to csv",Toast.LENGTH_LONG).show();
                    }
                    else {
                        mFileWriter = new FileWriter(filepath,true);
                        fileWriter_gyro=new FileWriter(filepath_gyro,true);
                        mFileWriter_rot=new FileWriter(filepath_rot,true);
                        mFileWriter_pres=new FileWriter(filepath_pres,true);
                        timefilewriter=new FileWriter(filepath_time,true);
                        Log.d("filepath-------","writing...............");
                        csvWriter = new CSVWriter(mFileWriter);
                        csvWriter_gyro=new CSVWriter(fileWriter_gyro);
                        csvWriter_rot=new CSVWriter(mFileWriter_rot);
                        csvWriter_pres=new CSVWriter(mFileWriter_pres);
                        csvWritertime=new CSVWriter(timefilewriter);

                        int length=x_acc.size();
                        int length_gyro=x_gyro.size();
                        int length_rot=x_rot.size();
                        int length_pres=x_pres.size();

                        String x_axis_acc="x-acc";
                        String y_axis_acc="y-acc";
                        String z_axis_acc="z-acc";
                        String acc_time="time-acc";

                        String[] head={x_axis_acc,y_axis_acc,z_axis_acc,acc_time};


                        csvWriter.writeNext(head);
                        for(int i=0;i<length;i++)
                        {

                            String x_value=(x_acc.get(i)).toString();
                            String y_value=(y_acc.get(i)).toString();
                            String z_value=(z_acc.get(i)).toString();
                            String time_string=time_acc.get(i).toString();
                            String[] data={x_value,y_value,z_value,time_string};
                            System.out.println(data);
                            csvWriter.writeNext(data);


                        }
                        Log.d("filepath2-------","writing...............");
                        csvWriter.close();


                        String time_field="time";
                        String time_field1="digit";
                        String[] time_data={time_field,time_field1};
                        csvWritertime.writeNext(time_data);
                        for(int p=0;p<btn_click.size();p++)
                        {
                            String time_value=(btn_click.get(p)).toString();
                            String digit=(btn_clicked.get(p)).toString();
                            String[] time_array={time_value,digit};
                            csvWritertime.writeNext(time_array);

                        }

                        csvWritertime.close();


                        String x_axis_gyro="x-gyro";
                        String y_axis_gyro="y-gyro";
                        String z_axis_gyro="z-gyro";
                        String gyro_time="time-gyro";

                        String[] head1={x_axis_gyro,y_axis_gyro,z_axis_gyro,gyro_time};


                        csvWriter_gyro.writeNext(head1);

                        for(int j=0;j<length_gyro;j++)
                        {

                            String x_value=(x_gyro.get(j)).toString();
                            String y_value=(y_gyro.get(j)).toString();
                            String z_value=(z_gyro.get(j)).toString();
                            String time_string=time_gyro.get(j).toString();
                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter_gyro.writeNext(data);


                        }
                        csvWriter_gyro.close();



                        String x_axis_rot="x-rot";
                        String y_axis_rot="y-rot";
                        String z_axis_rot="z-rot";
                        String rot_time="rot-time";

                        String[] head2={x_axis_rot,y_axis_rot,z_axis_rot,rot_time};

                        csvWriter_rot.writeNext(head2);

                        for(int k=0;k<length_rot;k++)
                        {

                            String x_value=(x_rot.get(k)).toString();
                            String y_value=(y_rot.get(k)).toString();
                            String z_value=(z_rot.get(k)).toString();
                            String time_string=time_rot.get(k).toString();

                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter_rot.writeNext(data);


                        }
                        csvWriter_rot.close();



                        String x_axis_pres="x-pres";
                        String y_axis_pres="y-pres";
                        String z_axis_pres="z-pres";
                        String pres_time="pres-time";

                        String[] head3={x_axis_pres,y_axis_pres,z_axis_pres,pres_time};

                        csvWriter_pres.writeNext(head3);
                        for(int l=0;l<length_pres;l++)
                        {

                            String x_value=(x_pres.get(l)).toString();
                            String y_value=(y_pres.get(l)).toString();
                            String z_value=(z_pres.get(l)).toString();

                            String time_string=time_pres.get(l).toString();
                            String[] data={x_value,y_value,z_value,time_string};

                            csvWriter_pres.writeNext(data);


                        }
                        csvWriter_pres.close();
                        Toast.makeText(getApplicationContext(),"added to csv",Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e)
                {
                    System.out.println(e);
                }


                pin_entered="";

            }
        });
    }

    @Override
    protected void onPause() {
        msensormanager.unregisterListener(msensoreventlistener);
        super.onPause();

    }

}

