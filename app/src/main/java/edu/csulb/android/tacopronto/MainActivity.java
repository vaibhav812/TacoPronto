package edu.csulb.android.tacopronto;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static android.R.attr.key;

public class MainActivity extends Activity{
    static boolean stopOrder = false;
    private static final int PLACE_TACO_ORDER = 1;
    InputStream pricesStream;
    Properties prop;
    RadioGroup sizeGroup, tortillasGroup;
    int fillings = 0, size = 0, tortillas = 0;
    HashMap<String, String> items = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sizeGroup = (RadioGroup) findViewById(R.id.size_button_group);
        tortillasGroup = (RadioGroup) findViewById(R.id.tortilla_button_group);

        //Saved all prices in a properties file because prices can keep changing.
        try {
            pricesStream = getBaseContext().getAssets().open("app.properties");
            prop = new Properties();
            prop.load(pricesStream);
        }
        catch (IOException ioe) {
            //TODO - make it better perhaps?
            Toast.makeText(this, "Exception encountered", Toast.LENGTH_SHORT).show();
        }

    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.large_radio_button:
                if(checked) {
                    if(items.containsKey("size_Medium"))
                        items.remove("size_Medium");
                    if(!items.containsKey("size_Large"))
                        items.put("size_Large", prop.getProperty("Large"));
                    size++;
                }
                break;
            case R.id.medium_radio_button:
                if(checked) {
                    if(items.containsKey("size_Large"))
                        items.remove("size_Large");
                    if(!items.containsKey("size_Medium"))
                        items.put("size_Medium", prop.getProperty("Medium"));
                    size++;
                }
                break;
            case R.id.corn_radio_button:
                if(checked) {
                    if(items.containsKey("tortilla_Flour"))
                        items.remove("tortilla_Flour");
                    if(!items.containsKey("tortilla_Corn"))
                        items.put("tortilla_Corn", prop.getProperty("Corn"));
                    tortillas++;
                }
                break;
            case R.id.flour_radio_button:
                if(checked) {
                    if(items.containsKey("tortilla_Corn"))
                        items.remove("tortilla_Corn");
                    if(!items.containsKey("tortilla_Flour"))
                        items.put("tortilla_Flour", prop.getProperty("Flour"));
                    tortillas++;
                }
                break;
        }
    }

    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()){
            case R.id.beef:
                if(checked) {
                    items.put("fillings_Beef", prop.getProperty("Beef"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Beef");
                    fillings--;
                }
                break;
            case R.id.rice:
                if(checked) {
                    items.put("fillings_Rice", prop.getProperty("Rice"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Rice");
                    fillings--;
                }
                break;
            case R.id.chicken:
                if(checked) {
                    items.put("fillings_Chicken", prop.getProperty("Chicken"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Chicken");
                    fillings--;
                }
                break;
            case R.id.beans:
                if(checked) {
                    items.put("fillings_Beans", prop.getProperty("Beans"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Beans");
                    fillings--;
                }
                break;
            case R.id.white_fish:
                if(checked) {
                    items.put("fillings_White_Fish", prop.getProperty("White_Fish"));
                    fillings++;
                }
                else {
                    items.remove("fillings_White_Fish");
                    fillings--;
                }
                break;
            case R.id.pico_de_gallo:
                if(checked) {
                    items.put("fillings_Pico_De_Gallo", prop.getProperty("Pico_de_Gallo"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Pico_De_Gallo");
                    fillings--;
                }
                break;
            case R.id.cheese:
                if(checked) {
                    items.put("fillings_Cheese", prop.getProperty("Cheese"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Cheese");
                    fillings--;
                }
                break;
            case R.id.guacomole:
                if(checked) {
                    items.put("fillings_Guacomole", prop.getProperty("Guacamole"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Guacomole");
                    fillings--;
                }
                break;
            case R.id.seafood:
                if(checked) {
                    items.put("fillings_Sea_Food", prop.getProperty("Sea_Food"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Sea_Food");
                    fillings--;
                }
                break;
            case R.id.lbt:
                if(checked) {
                    items.put("fillings_Lbt", prop.getProperty("LBT"));
                    fillings++;
                }
                else {
                    items.remove("fillings_Lbt");
                    fillings--;
                }
                break;
            case R.id.soda:
                if(checked)
                    items.put("beverage_Soda", prop.getProperty("Soda"));
                else
                    items.remove("beverage_Soda");
                break;
            case R.id.margarita:
                if(checked)
                    items.put("beverage_Margarita", prop.getProperty("Margarita"));
                else
                    items.remove("beverage_Margarita");
                break;
            case R.id.cerveza:
                if(checked)
                    items.put("beverage_Cerveza", prop.getProperty("Cerveza"));
                else
                    items.remove("beverage_Cerveza");
                break;
            case R.id.tequila:
                if(checked)
                    items.put("beverage_Tequila", prop.getProperty("Tequila"));
                else
                    items.remove("beverage_Tequila");
                break;
        }
    }

    public void onButtonClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.stopOrder = false;
                dialog.cancel();
            }
        });
        if(size == 0 && tortillas == 0){
            MainActivity.stopOrder = true;
            builder.setMessage(R.string.size_type_failure);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(size == 0){
            MainActivity.stopOrder = true;
            builder.setMessage(R.string.size_failure);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(tortillas == 0){
            MainActivity.stopOrder = true;
            builder.setMessage(R.string.type_failure);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(fillings == 0){
            MainActivity.stopOrder = true;
            builder.setMessage(R.string.fillings_confirm);
            builder.setPositiveButton(R.string.place_order_confirm, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    sendMessageConfirmation();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    MainActivity.stopOrder = false;
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(!stopOrder)
            sendMessageConfirmation();
    }

    public void sendMessageConfirmation(){
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionStatus != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PLACE_TACO_ORDER);
        }
        else{
            placeOrder();
            Toast.makeText(this, R.string.order_confirm, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PLACE_TACO_ORDER: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    placeOrder();
                    Toast.makeText(this, R.string.order_confirm, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.order_failure, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void placeOrder(){
        String size="", tortilla="", key, value, filling="";
        ArrayList<String> fillings = new ArrayList<String>();
        ArrayList<String> beverage = new ArrayList<String>();
        float totalPrice = 0;
        for(Map.Entry<String, String> item : items.entrySet()){
            totalPrice = totalPrice + Float.parseFloat(item.getValue());
            key = item.getKey();
            if(key.startsWith("size_")){
                size = key.split("_")[1];
            }
            else if(key.startsWith("fillings_")){
                String[] parts = key.split("_");
                if(parts.length > 2){
                    for(int i =0; i < parts.length; i++){
                        if(!parts[i].equals("fillings")) {
                            if(i == parts.length - 1)
                                filling += parts[i];
                            else
                                filling += parts[i] + " ";
                        }
                    }
                }
                else{
                    filling = parts[1];
                }
                fillings.add(filling);
                filling="";
            }
            else if(key.startsWith("tortilla_")){
                tortilla = key.split("_")[1];
            }
            else if(key.startsWith("beverage_")){
                beverage.add(key.split("_")[1]);
            }
        }
        StringBuilder textMessage = new StringBuilder();
        textMessage.append("You have a Taco Order!\n");
        textMessage.append("Size: " + size + " \n");
        textMessage.append("Tortilla: " + tortilla + " \n");
        textMessage.append("Fillings: ");
        if(fillings.size() != 0) {
            if(fillings.size() == 1){
                textMessage.append(fillings.get(0));
            }
            else {
                for (int i = 0; i < fillings.size(); i++) {
                    if(i == fillings.size() - 1)
                        textMessage.append(fillings.get(i) + ".");
                    else
                        textMessage.append(fillings.get(i) + ", ");
                }
            }
        }
        else{
            textMessage.append("None");
        }
        textMessage.append("\n");
        textMessage.append("Beverage: ");
        if(beverage.size() != 0){
            if(beverage.size() == 1)
                textMessage.append(beverage.get(0));
            else {
                for (int i = 0; i < beverage.size(); i++) {
                    if(i == beverage.size() - 1)
                        textMessage.append(beverage.get(i) + ".");
                    else
                        textMessage.append(beverage.get(i) + ", ");
                }
            }
        }
        else{
            textMessage.append("None");
        }
        textMessage.append("\n");
        DecimalFormat format = new DecimalFormat("#.##");
        textMessage.append("Total Price: $" + format.format(totalPrice));
        SmsManager manager = SmsManager.getDefault();
        if(textMessage.length() < 160)
            manager.sendTextMessage(prop.getProperty("vendor_phone_number"), null, textMessage.toString(), null, null);
        else{
            ArrayList<String> parts = manager.divideMessage(textMessage.toString());
            manager.sendMultipartTextMessage(prop.getProperty("vendor_phone_number"), null, parts, null, null);
        }
    }
}
