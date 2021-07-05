package sg.edu.rp.c346.id20014009.cagassignmentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ItemListActivity extends AppCompatActivity {
EditText etProduct;
EditText etPositon;
DatePicker dp;
Button btnAdd;
Button btnUpdate;
Button btnDelete;
Spinner spinnerMaintain;
Spinner spinnerFilter;
ListView lvProduct;
ArrayAdapter aaProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ArrayList<String> alProducts=new ArrayList<>();
        ArrayList<String> alDate=new ArrayList<>();
        ArrayList<String> productDate=new ArrayList<>();

        lvProduct=findViewById(R.id.listViewProducts);
        etProduct=findViewById(R.id.etNameProduct);
        etPositon=findViewById(R.id.editPosition);
        dp=findViewById(R.id.datepicker);
        btnAdd=findViewById(R.id.btnAdd);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        spinnerMaintain=findViewById(R.id.spinnerMaintain);
        spinnerFilter=findViewById(R.id.spinnerFilter);
        aaProducts=new ArrayAdapter<>(ItemListActivity.this,android.R.layout.simple_list_item_1,productDate);

        alProducts.add(0,"Airpod");
        alProducts.add(1,"Apple watch");
        alProducts.add(2,"Calculator");
        alProducts.add(3,"Charger");
        alProducts.add(4,"Iphone X");
        alProducts.add(5,"Hua Wei Pro");

        alDate.add(0,"2022-4-5");
        alDate.add(1,"2022-5-5");
        alDate.add(2,"2022-6-5");
        alDate.add(3,"2022-8-5");
        alDate.add(4,"2022-9-5");
        alDate.add(5,"2022-12-4");

        for(int i=0;i<alProducts.size();i++){
            if(!alProducts.get(i).isEmpty()|| !alDate.get(i).isEmpty()){
                productDate.add("Expired in "+alDate.get(i)+" "+alProducts.get(i));
            }
        }

        lvProduct.setAdapter(aaProducts);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etProduct.getText().toString();
                String date=dp.getYear()+"-"+(dp.getMonth()+1)+"-"+dp.getDayOfMonth();
                String nameDate="Expires "+date+" "+name;
                alProducts.add(name);
                alDate.add(date);
                productDate.add(nameDate);

                aaProducts.notifyDataSetChanged();
                etProduct.setText(null);
                etPositon.setText(null);
                dp.updateDate(2021,0,1);
                Collections.sort(alProducts);
                String msg=name+" have been succesfully added!";
                Toast.makeText(ItemListActivity.this,msg,Toast.LENGTH_LONG).show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alProducts.size()==0){
                    Toast.makeText(ItemListActivity.this,"There is nothing for you to update in the list!",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    int ip=Integer.parseInt(etPositon.getText().toString());
                    int pos=ip-1;
                    String name=etProduct.getText().toString();
                    String date=dp.getYear()+"-"+(dp.getMonth()+1)+"-"+dp.getDayOfMonth();
                    String nameDate="Expires "+date+" "+name;
                    alProducts.set(pos,name);
                    alDate.set(pos,date);
                    productDate.set(pos,nameDate);

                    aaProducts.notifyDataSetChanged();
                    etProduct.setText(null);
                    etPositon.setText(null);
                    dp.updateDate(2021,0,1);
                    Collections.sort(alProducts);
                    String msg=name+" have been succesfully updated in the list!";
                    Toast.makeText(ItemListActivity.this,msg,Toast.LENGTH_LONG).show();


                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alProducts.size()==0){
                    Toast.makeText(ItemListActivity.this,"There is nothing for you to delete in the list!",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    int ip = Integer.parseInt(etPositon.getText().toString());
                    int pos = ip - 1;

                    alProducts.remove(pos);
                    alDate.remove(pos);
                    productDate.remove(pos);

                    aaProducts.notifyDataSetChanged();
                    etProduct.setText(null);
                    etPositon.setText(null);
                    dp.updateDate(2021, 0, 1);
                    Collections.sort(alProducts);
                    String msg = "The position " + ip + " have been succesfully deleted in the list!";
                    Toast.makeText(ItemListActivity.this, msg,Toast.LENGTH_LONG).show();
                }
                }
        });

        spinnerMaintain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        etPositon.setEnabled(false);
                        btnDelete.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(false);
                        btnUpdate.setEnabled(true);
                        break;
                    case 2:
                        etPositon.setEnabled(true);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnUpdate.setEnabled(false);
                        etProduct.setEnabled(false);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        ArrayList<String>alFilter=new ArrayList<>();

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        int monthadd=month+1;
                        String monthdesirestring=Integer.toString(monthadd);
                        String elementcontain=year+"-"+monthdesirestring+"-";
                        if(monthadd>1 && monthadd<=12){
                            for(int i=0;i<alProducts.size();i++){
                                if(productDate.get(i).contains(elementcontain)==false){
                                    alFilter.add(productDate.get(i));

                                }

                            }
                        }else if(monthadd>12){
                            int monthover=monthadd-12;
                            int yearadd=year+1;

                            String monthoverString=Integer.toString(monthover);
                            String yearaddString=Integer.toString(yearadd);
                            String elementcontainOver=yearaddString+"-"+monthoverString+"-";

                            for(int i=0;i<alProducts.size();i++){
                                if(alProducts.get(i).contains(elementcontainOver)==false){
                                    alFilter.add(productDate.get(i));
                                }
                            }
                            productDate.clear();

                            for(int i=0;i<alFilter.size();i++){
                                if(!(alFilter.get(i)==null)){
                                    productDate.add(alFilter.get(i));

                                }
                            }

                        }
                        break;

                    case 1:
                        int monthadd1=month+3;
                        String monthdesirestring1=Integer.toString(monthadd1);
                        String elementcontain1=year+"-"+monthdesirestring1+"-";

                        if(monthadd1>1 && monthadd1<=12){
                            for(int i=0;i<alProducts.size();i++){
                                if(productDate.get(i).contains(elementcontain1)==false){
                                    alFilter.add(productDate.get(i));

                                }
                            }

                        }else if(monthadd1>12){
                            int monthover=monthadd1-12;
                            int yearadd=year+1;

                            String monthoverString=Integer.toString(monthover);
                            String yearaddString=Integer.toString(yearadd);
                            String elementcontainOver=yearaddString+"-"+monthoverString+"-";

                            for(int i=0;i<alProducts.size();i++){
                                if(alProducts.get(i).contains(elementcontainOver)==false){
                                    alFilter.add(productDate.get(i));
                                }
                            }

                            productDate.clear();



                            for(int i=0;i<alFilter.size();i++){
                                if(!(alFilter.get(i)==null)){
                                    productDate.add(alFilter.get(i));

                                }
                            }

                        }
                        break;

                    case 2:

                        int monthadd2=month+6;
                        String monthdesirestring2=Integer.toString(monthadd2);
                        String elementcontain2=year+"-"+monthdesirestring2+"-";

                        if(monthadd2>1 && monthadd2<=12){
                            for(int i=0;i<alProducts.size();i++){
                                if(productDate.get(i).contains(elementcontain2)==false){
                                    alFilter.add(productDate.get(i));




                                }

                            }


                        }else if(monthadd2>12){
                            int monthover=monthadd2-12;
                            int yearadd=year+1;

                            String monthoverString=Integer.toString(monthover);
                            String yearaddString=Integer.toString(yearadd);
                            String elementcontainOver=yearaddString+"-"+monthoverString+"-";

                            for(int i=0;i<alProducts.size();i++){
                                if(alProducts.get(i).contains(elementcontainOver)==false){
                                    alFilter.add(productDate.get(i));
                                }
                            }

                            productDate.clear();

                            for(int i=0;i<alFilter.size();i++){
                                if(!(alFilter.get(i)==null)){
                                    productDate.add(alFilter.get(i));

                                }
                            }

                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }
}