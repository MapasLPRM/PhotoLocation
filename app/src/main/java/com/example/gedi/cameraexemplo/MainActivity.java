package com.example.gedi.cameraexemplo;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public static final int imagem_interna = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirCamera(View view){
        Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(camera);
    }

    public void abrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, imagem_interna);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == imagem_interna ){
            if(resultCode == RESULT_OK){
                Uri imagemSelecionada = intent.getData();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor =  getContentResolver().query(imagemSelecionada, colunas, null, null, null );
                cursor.moveToFirst();

                int indexColuna = cursor.getColumnIndex(colunas[0]);
                String pathImg  = cursor.getString(indexColuna);
                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
                ImageView iv =  (ImageView) findViewById(R.id.iv);
                iv.setImageBitmap(bitmap);


            }
        }

    }



}
