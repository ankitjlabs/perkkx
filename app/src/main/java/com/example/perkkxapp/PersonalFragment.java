package com.example.perkkxapp;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.perkkxapp.R.*;


public class PersonalFragment extends Fragment {

    private static String url_new_user = "http://jlabs.co/perkkx/profilepost.php";
    private static final String TAG_SUCCESS = "success";

    private static int RESULT_LOAD_IMAGE = 1;
    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    Calendar dateAndTime = Calendar.getInstance();
    DateFormat fmtDateAndTime2 = DateFormat.getDateInstance();
    Calendar dateAndTime2 = Calendar.getInstance();

    static  boolean IsMale=true;
    static boolean IsMarried=false;

    public int RESULT_OK;
    public ContentProvider getContentResolver;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        context=getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(layout.fragment_personal,container,false);

        ViewHolder.imageView = (ImageView) v.findViewById(id.imageView);

        ViewHolder.tv_anniversary = (TextView)v.findViewById(R.id.tv_anniversary);
        ViewHolder.tv_dateofbirth = (TextView)v.findViewById(R.id.tv_dateofbirth);
        ViewHolder.addphoto = (TextView)v.findViewById(id.textView5);
        ViewHolder.firstname=(EditText)v.findViewById(id.firstnam);
        ViewHolder.lastname=(EditText)v.findViewById(id.lastnam);
        ViewHolder.phonenumber=(EditText)v.findViewById(id.mobile);
        ViewHolder.nextbt=(Button)v.findViewById(id.btnnext);
        ViewHolder.Male_Image = (ImageView) v.findViewById(id.imageView2);
        ViewHolder.Female_Image = (ImageView) v.findViewById(id.imageView3);
        ViewHolder.Married_CheckBox=(CheckBox)v.findViewById(id.checkBox);
        ViewHolder.UnMarried_CheckBox=(CheckBox)v.findViewById(id.checkBox2);
        ViewHolder.LinearLayout_Anniversary=(LinearLayout)v.findViewById(id.LinearLayout_Anniversary);
        ViewHolder.NextButton=(Button)v.findViewById(id.btnnext);

        //validation code













        ViewHolder.nextbt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


            }



        });

        ViewHolder.tv_dateofbirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chooseDate(v);

            }


        });

        ViewHolder.tv_anniversary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chooseDate2(v);
            }
        });

        ViewHolder.addphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        ViewHolder.Male_Image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                if (!IsMale)
                {
                    ViewHolder.Male_Image.setImageResource(drawable.male_selected);
                    ViewHolder.Female_Image.setImageResource(drawable.female_default);

                    IsMale=!IsMale;
                }

            }
        });

        ViewHolder.Female_Image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                System.out.println(IsMale);
                if (IsMale)
                {
                    ViewHolder.Male_Image.setImageResource(drawable.male_default);
                    ViewHolder.Female_Image.setImageResource(drawable.female_selected);

                    IsMale=!IsMale;
                }


            }
        });

        ViewHolder.Married_CheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!IsMarried)
                {
                    IsMarried=!IsMarried;
                    ViewHolder.Married_CheckBox.setChecked(IsMarried);
                    ViewHolder.UnMarried_CheckBox.setChecked(!IsMarried);

                    if (!IsMarried)
                    {
                        ViewHolder.LinearLayout_Anniversary.setVisibility(View.GONE);
                    }
                    else
                    {
                        ViewHolder.LinearLayout_Anniversary.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        ViewHolder.UnMarried_CheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                System.out.println(IsMarried);
                if (IsMarried)
                {
                    IsMarried=!IsMarried;
                    ViewHolder.Married_CheckBox.setChecked(IsMarried);
                    ViewHolder.UnMarried_CheckBox.setChecked(!IsMarried);
                    if (!IsMarried)
                    {
                        ViewHolder.LinearLayout_Anniversary.setVisibility(View.GONE);
                    }
                    else
                    {
                        ViewHolder.LinearLayout_Anniversary.setVisibility(View.VISIBLE);
                    }

                }

            }
        });

        ViewHolder.NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new PostJson().execute();
                /*Intent i=new Intent (v.getContext(),CorporateFragment.class);
                startActivity(i);*/

            }
        });
        return v;
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateAndTime2.set(Calendar.YEAR, year);
            dateAndTime2.set(Calendar.MONTH, monthOfYear);
            dateAndTime2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();
        }

        private void updateLabel2() {
            // TODO Auto-generated method stub
            ViewHolder.tv_anniversary.setText(fmtDateAndTime2.format(dateAndTime2.getTime()));
        }
    };

    public void chooseDate2(View v) {
        DatePickerDialog d22 = new DatePickerDialog(getActivity(),
                d2,
                dateAndTime2.get(Calendar.YEAR),
                dateAndTime2.get(Calendar.MONTH),
                dateAndTime2.get(Calendar.DAY_OF_MONTH));
        d22.show();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
          /*  dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);*/
            updateLabel();
        }

        private void updateLabel() {
            // TODO Auto-generated method stub
            ViewHolder.tv_dateofbirth.setText(fmtDateAndTime.format(dateAndTime.getTime()));
        }
    };
    public void chooseDate(View v) {
        DatePickerDialog d11 = new DatePickerDialog(getActivity(),
                d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));
        d11.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };



            Cursor cursor = getContentResolver.query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bm = BitmapFactory.decodeFile(picturePath);
            ViewHolder.imageView.setImageBitmap(bm);

        }


    }

static class ViewHolder
{
    private static  EditText firstname;
    private static EditText lastname;
    private static EditText phonenumber;
    private static Button nextbt;
    private static ImageView imageView;
    private static TextView tv_dateofbirth;
    private static TextView tv_anniversary;
    private static TextView addphoto;
    private static ImageView Male_Image;
    private static ImageView Female_Image;
    private static CheckBox Married_CheckBox;
    private static CheckBox UnMarried_CheckBox;
    private static LinearLayout LinearLayout_Anniversary;
    private static Button NextButton;
}


private class PostJson extends AsyncTask<string,string,string>
{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected string doInBackground(string... args) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("fname", ViewHolder.firstname.getText().toString()));
        params.add(new BasicNameValuePair("lname", ViewHolder.lastname.getText().toString()));
        params.add(new BasicNameValuePair("phoneNumber", ViewHolder.phonenumber.getText().toString()));
        params.add(new BasicNameValuePair("tv_dateofbirth", ViewHolder.tv_dateofbirth.getText().toString()));
        params.add(new BasicNameValuePair("IsMarried", String.valueOf(IsMarried)));
        if (IsMarried)
        {
            params.add(new BasicNameValuePair("tv_anniversary", ViewHolder.tv_anniversary.getText().toString()));
        }
        params.add(new BasicNameValuePair("gender", String.valueOf(IsMale)));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = JSONfunctions.makeHttpRequest(url_new_user,
                "POST", params);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                Toast.makeText(context,TAG_SUCCESS,Toast.LENGTH_LONG).show();

                // successfully created product
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
//
//                // closing this screen
//                finish();
            } else {
                // failed to create product
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(string string) {
        super.onPostExecute(string);
    }


}



}