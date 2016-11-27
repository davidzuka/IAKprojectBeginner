package id.david.belajarlayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static id.david.belajarlayout.R.id.mRadio1;
import static id.david.belajarlayout.R.id.mRadio2;
import static id.david.belajarlayout.R.id.mRadioGroup;
import static id.david.belajarlayout.R.id.mSpinnerMenu;
import static id.david.belajarlayout.R.id.mTextName;

public class CoffeeActivity extends AppCompatActivity {

    EditText mTextName;
    Button mButtonOrder, mButtonPlus, mButtonMin, mButtonReset;
    TextView mTextHarga, mTextQty;
    RadioGroup mRadioGroup;
    RadioButton mRadio1, mRadio2;
    Context mContext;
    //Spinner
    Spinner mSpinnerMenu;
    List<String> mListMenu = new ArrayList<>();
    int harga = 0;
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        /*
          alt + enter di tengah, cth di : findViewById
        */

        mContext = getApplicationContext();
        mTextName = (EditText) findViewById(R.id.mTextName);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);

        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonReset = (Button) findViewById(R.id.mButtonReset);

//        radiobutton
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadio1 = (RadioButton) findViewById(R.id.mRadio1);
        mRadio2 = (RadioButton) findViewById(R.id.mRadio2);


        mRadio2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "item 2 diklik", Toast.LENGTH_SHORT).show();
            }
        });

        mRadio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "item 1 diklik", Toast.LENGTH_SHORT).show();
            }
        });
        //Spinner
        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok coklat");
        mListMenu.add("Ice Cream");
        mListMenu.add("Lumpia");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);

    }

    //cara 2
    public void onClickOrder(View v) {
//            Toast.makeText(getApplicationContext(),"Hi, Nama Saya "+mTextName.getText(),Toast.LENGTH_LONG).show();
//            mTextHarga.setText(mTextName.getText());
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "arieridwansyah@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mTextName.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Saya pesan "
                + mSpinnerMenu.getSelectedItem()
                + " sebanyak "
                + mTextQty.getText()
                + " seharga "
                + mTextHarga.getText());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "There is no email client installed", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickPlus(View vplus) {
        harga = harga + 5;
        qty = qty + 1;
        mTextQty.setText(qty + "");
        mTextHarga.setText("$" + harga);
    }

    public void onClickMin(View vMin) {
        if (harga != 0) {
            harga = harga - 5;
            qty = qty - 1;
            mTextQty.setText(qty + "");
            mTextHarga.setText("$" + harga);
        } else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty + "");
            mTextHarga.setText("$" + harga);
        }
    }

    public void onClickReset(View vReset) {
        harga = 0;
        qty = 0;
        mTextName.setText("");
        mTextHarga.setText("$" + harga);
        mTextQty.setText(qty + "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
