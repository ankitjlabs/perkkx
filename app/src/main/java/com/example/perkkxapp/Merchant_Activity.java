package com.example.perkkxapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sandy on 5/24/2015.
 */
public class Merchant_Activity extends Activity
{

    Context context;
    LayoutInflater inflater;
    static  Catalouge_json resultp;
    ImageLoader imageLoader = new ImageLoader(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(setView());

    }

    public View setView()
    {
        TextView address_text;
        TextView minimumoder;
        TextView ruppes_text;
        TextView rating;
        TextView followers;
        TextView vendor_name;
        ImageView drinks;
        ImageView home_dlvry;
        ImageView credit_card;
        ImageView wifi;
        ImageView parking;
        ImageView group;
        ImageButton callButton,shareButton;
        ImageView Background_Image,imageveg;
        TextView cuisine_text;
        ImageView imagefood,imagephoto,imagefood1,imagefood2;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.merchant_page, null);

        rating=(TextView) itemView.findViewById(R.id.ratingpoint);
        followers=(TextView) itemView.findViewById(R.id.textView3);
        vendor_name=(TextView) itemView.findViewById(R.id.textView4);
        drinks=(ImageView) itemView.findViewById(R.id.imagebeer);
        home_dlvry=(ImageView) itemView.findViewById(R.id.imagedlvr);
        wifi=(ImageView) itemView.findViewById(R.id.imagewifi);
        parking=(ImageView) itemView.findViewById(R.id.imagepark);
        group=(ImageView) itemView.findViewById(R.id.imagegrp);
        credit_card=(ImageView) itemView.findViewById(R.id.imagecard);
        callButton=(ImageButton) itemView.findViewById(R.id.callbutton);
        ruppes_text=(TextView) itemView.findViewById(R.id.costs);
         minimumoder=(TextView) itemView.findViewById(R.id.orderprice);
        cuisine_text=(TextView) itemView.findViewById(R.id.textcuis);
        address_text=(TextView) itemView.findViewById(R.id.textView8);
        Background_Image=(ImageView) itemView.findViewById(R.id.image);
        shareButton=(ImageButton) itemView.findViewById(R.id.sharebtn);
        imageveg=(ImageView)itemView.findViewById(R.id.imageveg);
        imagefood=(ImageView)itemView.findViewById(R.id.imageViewfood);
        imagephoto=(ImageView)itemView.findViewById(R.id.imageView1photo);
        imagefood1=(ImageView)itemView.findViewById(R.id.imageViewfood1);
        imagefood2=(ImageView)itemView.findViewById(R.id.imageViewfood2);


        rating.setText(resultp.rating.getValue());
        followers.setText(resultp.followers.getValue());
        vendor_name.setText(resultp.vendor_name.getValue());
        minimumoder.setText(resultp.desc2.getValue());
        address_text.setText(resultp.address.getValue().text.getValue());


        imageLoader.DisplayImage(resultp.img.getValue(), Background_Image);


        //cuisine array
        String cuisine_string="";
        for (int j=0; j<resultp.cuisine.getValue().size()-1;j++)
        {
            cuisine_string+=resultp.cuisine.getValue().get(j)+",";
        }
        cuisine_string+=resultp.cuisine.getValue().get(resultp.cuisine.getValue().size()-1);

        cuisine_text.setText(cuisine_string);

        //menu array


        String menu_string="";

        for(int i=0;i<resultp.cuisine.getValue().size()-1;i++){
           menu_string+=resultp.menu.getValue().get(i)+"";


        }
        menu_string+=resultp.menu.getValue().get(resultp.menu.getValue().size()-1);
        imageLoader.DisplayImage(resultp.img.getValue(), imagefood);
        imageLoader.DisplayImage(resultp.img.getValue(), imagefood1);
        imageLoader.DisplayImage(resultp.img.getValue(), imagefood2);






        String ruppes_String="Rs. "+resultp.price.getValue() +" For Single Person";
        ruppes_text.setText(ruppes_String);

        RemoveView(resultp.icons.getValue().cards.getValue(), credit_card);
        RemoveView(resultp.icons.getValue().delivery.getValue(),home_dlvry);
        RemoveView(resultp.icons.getValue().drinks.getValue(),drinks);
        RemoveView(resultp.icons.getValue().parking.getValue(),parking);
        RemoveView(resultp.icons.getValue().wifi.getValue(),wifi);



        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:" + resultp.phone.getValue());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                context.startActivity(callIntent);
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareTextUrl();

            }
        });
        return  itemView;
    }

    public  void RemoveView(String str, View view)
    {

        if (Integer.parseInt(str)==0)
        {

            ((LinearLayout)view.getParent()).removeView(view);
        }
    }
    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, " ");
        share.putExtra(Intent.EXTRA_TEXT, " ");

        context.startActivity(Intent.createChooser(share, "Share link!"));
    }
}
