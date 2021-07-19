package com.example.quarterproj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView t1;
    TextView t2;
    ArrayList<cars> carList;
    String check="";
    TextView des;
    TextView land2;

    TextView port_title;
    TextView port_desc;

    TextView land_title;
    TextView land_desc;

    int selected = -1;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("A", check);
        outState.putSerializable("B", carList);
        outState.putInt("C", selected);


        if(getResources().getConfiguration().orientation==1) {
            if (check.equals("Tesla")) {
                t2.setText("Elon musk is the founder of tesla");
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview_main);
        t2=findViewById(R.id.textView2);
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE )
            t2.setText("Hi welcome to my App");

        port_title = findViewById(R.id.port_title);
        port_desc = findViewById(R.id.port_desc);

        land_title = findViewById(R.id.land_title);
        land_desc  = findViewById(R.id.land_desc);


      // t1=findViewById(R.id.textView_main1);
      // t2=findViewById(R.id.textView_main2);
      // des=findViewById(R.id.textView_landscape);
        land2=findViewById(R.id.land_title);

        carList=new ArrayList<>();
        carList.add(new cars(R.drawable.teslarcar, "Tesla", "A new, modern electric car that has had a successful start"));
        carList.add(new cars(R.drawable.toyotacar, "Toyota", "One of the most renown companies in the world, responsible for the cars rustic  design"));
        carList.add(new cars(R.drawable.audicar, "Audi", "A sports car that has been known for its countless races across the globe"));
        carList.add(new cars(R.drawable.bmwcar, "BMW", "A german luxury company, that has been the icon of riches and luxury"));
        carList.add(new cars(R.drawable.ferraricar, "Ferrari", "A Sports car company that has been enlisted in many races"));
        carList.add(new cars(R.drawable.porschecar, "Porsche", "An expensive car brand that has been a rare and old appearence"));
        carList.add(new cars(R.drawable.bentleycar, "Bentley", "A british car marketer of luxury SUV's"));





        if(savedInstanceState!=null){
            check=savedInstanceState.getString("A");
            selected = savedInstanceState.getInt("C");
            carList= (ArrayList<cars>) savedInstanceState.getSerializable("B");
            if (selected >= 0) {
                if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
                    if(!carList.isEmpty()) {
                        land_title.setText(carList.get(selected).getName());
                        land_desc.setText(carList.get(selected).getInfo());
                    }
                }
                else {
                    if(!carList.isEmpty()) {
                        port_title.setText(carList.get(selected).getName());
                        port_desc.setText(carList.get(selected).getInfo());
                    }
                }
            }
            if (check.equals("Tesla")) {
                t1.setText("tesla was founded in 2003");
                t2.setText("Elon musk is the founder of tesla");
                if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
                    des.setText("A new, modern electric car that has had a successful start");
            }
        }


        ListViewAdapter adapter =  new ListViewAdapter(this, R.layout.layout_adapterportrait, carList);
        listView.setAdapter(adapter);

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*        if(carList.get(position).getName().equals("Tesla")) {
            t1.setText("tesla was founded in 2003");
            t2.setText("Elon musk is the founder of tesla");
        }

        if(carList.get(position).getName().equals("Toyota")) {
            t1.setText("toyota was founded in 1937");
            t2.setText("Kiichiro Toyoda is the founder of tesla");
        }

        if(carList.get(position).getName().equals("BMW")) {
            t1.setText("Audi was founded in 1932");
            t2.setText("August Horch is the founder of tesla");
        }

        if(carList.get(position).getName().equals("Audi")) {
            t1.setText("BMW was founded in 1916");
            t2.setText("Karl Rapp is the founder of tesla");
        }*/
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(!carList.isEmpty()) {
                port_title.setText(carList.get(position).getName());
                port_desc.setText(carList.get(position).getInfo());
            }
     }
        else {
            if(!carList.isEmpty()) {
                port_title.setText(carList.get(position).getName());
                port_desc.setText(carList.get(position).getInfo());
            }
        }
    }
});



    }


    public class ListViewAdapter extends ArrayAdapter<cars> {
        Context myContext;
        int xml;
        List<cars> listy;

        public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<cars> objects) {
            super(context, resource, objects);
            myContext = context;
            xml = resource;
            listy = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View temp, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
             temp = layoutInflater.inflate(xml, parent, false);
            View temp2 = temp;


            Button button = temp.findViewById(R.id.button_adapter);
            ImageView image =temp.findViewById(R.id.imageView_adapter);
            image.setImageResource(listy.get(position).getImage());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listy.remove(position);
                    notifyDataSetChanged();
                }
            });

            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = position;
                    if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
                        if(!carList.isEmpty()) {

                            land_title.setText(carList.get(position).getName());
                            land_desc.setText(carList.get(position).getInfo());
                        }
                    }
                    else {
                        if(!carList.isEmpty()) {
                            port_title.setText(carList.get(position).getName());
                            port_desc.setText(carList.get(position).getInfo());
                        }
                    }
                }
            });




            return temp;



        }
    }

        //object class
    public class cars{

        int image;
        String name;
        String info;

        public cars( int x, String y, String z){
           image=x;
           name=y;
            info=z;
        }

        public int getImage(){
            return image;
            }

            public String getName(){
                return name;
            }
            public String getInfo(){
                return info;
            }
    }


}