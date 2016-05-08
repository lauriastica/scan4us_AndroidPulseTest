package app.kevininnovation.com.scan4uspro.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import app.kevininnovation.com.scan4uspro.R;

public class ManagerPhoneActivity extends AppCompatActivity {


   final int REQUEST_CAMERA=112;
   final int TAKE_PIC =1;
    Button btonManagerPhoto;
    Bitmap imageBitmap=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_phone);
        btonManagerPhoto=(Button) findViewById(R.id.bton_take_repeat_photo);
    }


    /**
     * metodo que implementa los clicks que se le hagan a las vistas del contexto actual
     * */
    public void clickBton(View view){

        switch (view.getId()) {

            case R.id.bton_take_repeat_photo:
                /*cuando el usuario le da click al bton <Tomar Foto> se le saca la interfaz de android
                 *para tomar la foto
                */
                startActivityForResult( new Intent(MediaStore.ACTION_IMAGE_CAPTURE),TAKE_PIC);
                break;

            case R.id.bton_save_photo:
                 if(imageBitmap!=null){
                     finish();
                     Toast.makeText(getApplicationContext(),getString(R.string.message_saved),
                             Toast.LENGTH_LONG).show();
                 }else{
                     Toast.makeText(getApplicationContext(),getString(R.string.message_not_saved_photo),
                             Toast.LENGTH_LONG).show();
                 }
                break;
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CAMERA: {

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {

        //valido haber si el usuario se tomo la foto o no se la tomo
        if (requestCode == TAKE_PIC && resultCode==RESULT_OK){
            //obtengo la imagen que el usuario se tomo con su camara
                    imageBitmap = (Bitmap) data.getExtras().get("data");
            //le seteo a <imagaView_Photo> la imagen que se tomo el usuario
            ((ImageView) findViewById(R.id.imageView_Photo)).setImageBitmap(imageBitmap);

            btonManagerPhoto.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_replay_photo_white_36dp
                    ,0,0);
        }

    }
}
