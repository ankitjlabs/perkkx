package com.example.perkkxapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleItemView extends Activity {

    Context context;
    LayoutInflater inflater;
    static  Catalouge_json resultp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(setView());

    }

    public View setView()
    {
        Button code;
        TextView vendor_name;
        TextView address_text,discount;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.redeem_popup, null);


        code=(Button) itemView.findViewById(R.id.redeembtn);
        vendor_name=(TextView) itemView.findViewById(R.id.merchantname);
        address_text=(TextView) itemView.findViewById(R.id.mer_location);
        discount=(TextView) itemView.findViewById(R.id.offer);





        address_text.setText(resultp.address.getValue().text.getValue());

       discount.setText(resultp.deal.getValue());
        code.setText(resultp.rcode.getValue());
        vendor_name.setText(resultp.vendor_name.getValue());


        return  itemView;
    }

}


