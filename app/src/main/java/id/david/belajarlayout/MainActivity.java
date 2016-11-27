package id.david.belajarlayout;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMe(View v) {
       // Log.e("onClickMe: ", "sudah");
        Intent intent = new Intent(MainActivity.this,CoffeeActivity.class);
        startActivity(intent);
    }

    // alt + insert untuk tambahain menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mains, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.about:
               //TODO pindah ke halaman about
               Intent iAbout = new Intent(MainActivity.this,ProfileActivity.class);
               startActivity(iAbout);
                break;
           case R.id.order:
               //TODO pindah ke halaman order
               Intent iOrder = new Intent(MainActivity.this,CoffeeActivity.class);
               startActivity(iOrder);
               break;
           case R.id.quit:
               //quit
               AlertDialog.Builder builder = new AlertDialog.Builder(this);
               builder.setTitle("Anda Yakin Ingin Keluar ?");
               builder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                   }
               });
               builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener(){
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                   }
               });
               AlertDialog quit = builder.create();
               quit.show();

               break;
       }
        return super.onOptionsItemSelected(item);
    }
}
