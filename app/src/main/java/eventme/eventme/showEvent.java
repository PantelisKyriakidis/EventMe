package eventme.eventme;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



public class showEvent extends AppCompatActivity {
       private Button date,time,location;
    private TextView description;
    private ImageView image;
    private String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        Intent mIntent = getIntent();

        description=(TextView) findViewById(R.id.Description);

        date = (Button) findViewById(R.id.date);

        time = (Button) findViewById(R.id.hour);

        location = (Button) findViewById(R.id.location);

        time.setText(mIntent.getStringExtra("Time"));
        date.setText(mIntent.getStringExtra("Date"));
        location.setText(mIntent.getStringExtra("Location"));
        description.setText(mIntent.getStringExtra("Description"));
        description.setMovementMethod(new ScrollingMovementMethod());   //description scrolls down (shows 4lines)


        TextView text = (TextView) findViewById(R.id.name);
        temp =mIntent.getStringExtra("Email");

        image = (ImageView) findViewById(R.id.imageView);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);


        retrieveImage(temp,date.getText().toString());
        text.setText(temp);



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database

    }
    private void retrieveImage(String email,String date)
    {
        StorageReference mStorageRef= FirebaseStorage.getInstance().getReference();
        StorageReference islandRef = mStorageRef.child(email+date.replace("/","")+".jpg");

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(islandRef)
                .into(image);

    }

    public void goToShop(View v)
    {
       // Intent intent = new Intent(showEvent.this, shop_profile.class);
        //intent.putExtra("profile",temp);
        //startActivity(intent);
    }
}
