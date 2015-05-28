package com.example.perkkxapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<Catalouge_json> data;
	ImageLoader imageLoader;
    Catalouge_json resultp = new Catalouge_json();

	public ListViewAdapter(Context context,
			ArrayList<Catalouge_json> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables

        ImageButton shareButton;
        TextView open;
		TextView rating;
		TextView followers;
		TextView vendor_name;
        TextView address_text;
		TextView cuisine_text;
		TextView ruppes_text;
		ImageButton callButton;
		ImageView drinks;
		ImageView home_dlvry;
		ImageView credit_card;
		ImageView wifi;
		ImageView parking;
		ImageView group;
		TextView discount;
		Button code;
		ImageView Background_Image;
        ImageView veg;
        Button viewMenu;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.deal_page, parent, false);
		// Get the position
		resultp = data.get(position);

        open=(TextView) itemView.findViewById(R.id.textView2);
        rating=(TextView) itemView.findViewById(R.id.ratingpoint);//
         followers=(TextView) itemView.findViewById(R.id.textView3);
         vendor_name=(TextView) itemView.findViewById(R.id.textView4);
         address_text=(TextView) itemView.findViewById(R.id.textView5);
		cuisine_text=(TextView) itemView.findViewById(R.id.textView6);
		ruppes_text=(TextView) itemView.findViewById(R.id.textView7);
		callButton=(ImageButton) itemView.findViewById(R.id.callbutton);
		drinks=(ImageView) itemView.findViewById(R.id.imagebeer);
		home_dlvry=(ImageView) itemView.findViewById(R.id.imagedlvr);
		wifi=(ImageView) itemView.findViewById(R.id.imagewifi);
		parking=(ImageView) itemView.findViewById(R.id.imagepark);
		group=(ImageView) itemView.findViewById(R.id.imagegrp);
		credit_card=(ImageView) itemView.findViewById(R.id.imagecard);
		discount=(TextView) itemView.findViewById(R.id.textView10);
		code=(Button) itemView.findViewById(R.id.imageButton5);
		Background_Image=(ImageView) itemView.findViewById(R.id.imageview);
        viewMenu=(Button) itemView.findViewById(R.id.button);
        shareButton=(ImageButton)itemView.findViewById(R.id.sharebtn);
        veg=(ImageView)itemView.findViewById(R.id.imageveg);



        rating.setText(resultp.rating.getValue());
        followers.setText(resultp.followers.getValue());
        vendor_name.setText(resultp.vendor_name.getValue());
        address_text.setText(resultp.address.getValue().text.getValue());


		//set menu
        String cuisine_string="";
        for (int j=0; j<resultp.cuisine.getValue().size()-1;j++)
        {
            cuisine_string+=resultp.cuisine.getValue().get(j)+",";
        }
        cuisine_string+=resultp.cuisine.getValue().get(resultp.cuisine.getValue().size()-1);

		cuisine_text.setText(cuisine_string);
		//set ruppes
		String ruppes_String="Rs. "+resultp.price.getValue() +" For Single Person";
		ruppes_text.setText(ruppes_String);

        //set icons


		RemoveView(resultp.icons.getValue().cards.getValue(), credit_card);
		RemoveView(resultp.icons.getValue().delivery.getValue(),home_dlvry);
		RemoveView(resultp.icons.getValue().drinks.getValue(),drinks);
		RemoveView(resultp.icons.getValue().parking.getValue(),parking);
		RemoveView(resultp.icons.getValue().wifi.getValue(),wifi);//
        RemoveView(resultp.icons.getValue().veg.getValue(),veg);



//set desc
		discount.setText(resultp.desc.getValue());

//set background Image

		imageLoader.DisplayImage(resultp.img.getValue(), Background_Image);





		//Click Listners

		callButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri number = Uri.parse("tel:" + resultp.phone.getValue());
				Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
				context.startActivity(callIntent);
			}
		});

		code.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {


                SingleItemView.resultp=resultp;
                Intent merchant=new Intent(context,SingleItemView.class);
                context.startActivity(merchant);


			//Toast.makeText(context, resultp.rcode.getValue(), Toast.LENGTH_LONG).show();
			}
		});

        shareButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                shareTextUrl();

            }
        });



        viewMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
                Merchant_Activity.resultp=resultp;
                System.out.println(Merchant_Activity.resultp.vendor_name.getValue());
                System.out.println(resultp.vendor_name.getValue());
                Intent merchant=new Intent(context,Merchant_Activity.class);
                context.startActivity(merchant);

			}
		});

		// Locate the TextViews in listview_item.xml
//		rank = (TextView) itemView.findViewById(R.id.rank);
//		country = (TextView) itemView.findViewById(R.id.country);
//		population = (TextView) itemView.findViewById(R.id.population);
//
//		// Locate the ImageView in listview_item.xml
//		flag = (ImageView) itemView.findViewById(R.id.flag);
//
//		// Capture position and set results to the TextViews
//		rank.setText(resultp.get(MainActivity.RANK));
//		country.setText(resultp.get(MainActivity.COUNTRY));
//		population.setText(resultp.get(MainActivity.POPULATION));
//		// Capture position and set results to the ImageView
//		// Passes flag images URL into ImageLoader.class
//		imageLoader.DisplayImage(resultp.get(MainActivity.FLAG), flag);
//		// Capture ListView item click
//		itemView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// Get the position
//				resultp = data.get(position);
//				Intent intent = new Intent(context, SingleItemView.class);
//				// Pass all data rank
//				intent.putExtra("rank", resultp.get(MainActivity.RANK));
//				// Pass all data country
//				intent.putExtra("country", resultp.get(MainActivity.COUNTRY));
//				// Pass all data population
//				intent.putExtra("population",resultp.get(MainActivity.POPULATION));
//				// Pass all data flag
//				intent.putExtra("flag", resultp.get(MainActivity.FLAG));
//				// Start SingleItemView Class
//				context.startActivity(intent);
//
//			}
//		});
		return itemView;
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
        share.putExtra(Intent.EXTRA_SUBJECT, " post data");
        share.putExtra(Intent.EXTRA_TEXT, "www.jlabs.co ");

        context.startActivity(Intent.createChooser(share, "Share link!"));
    }
}
