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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.perkkxapp.R.*;


public class PersonalFragment extends Fragment {


    private EditText firstname;
    private EditText lastname;
    private EditText phonenumber;
    private Button nextbt;
    ImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    Calendar dateAndTime = Calendar.getInstance();
    DateFormat fmtDateAndTime2 = DateFormat.getDateInstance();
    Calendar dateAndTime2 = Calendar.getInstance();

    TextView tv_dateofbirth,tv_anniversary,addphoto;
    public int RESULT_OK;
    public ContentProvider getContentResolver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(layout.fragment_personal,container,false);

        imageView = (ImageView) v.findViewById(id.imageView);

        tv_anniversary = (TextView)v.findViewById(R.id.tv_anniversary);
        tv_dateofbirth = (TextView)v.findViewById(R.id.tv_dateofbirth);
        addphoto = (TextView)v.findViewById(id.textView5);
        firstname=(EditText)v.findViewById(id.firstnam);
        lastname=(EditText)v.findViewById(id.lastnam);
        phonenumber=(EditText)v.findViewById(id.mobile);
        nextbt=(Button)v.findViewById(id.btnnext);


        nextbt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final String first = firstname.getText().toString();
                final String last=lastname.getText().toString();
                if (!isValidEmail(first)) {
                    firstname.setError("Invalid firstname");
                    lastname.setError("Invalid lastname");
                }

                final String phone = phonenumber.getText().toString();
                if (!isValidPassword(last)) {
                    phonenumber.setError("Invalid mobile");
                }

            }



        });

        tv_dateofbirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chooseDate(v);

            }


        });

        tv_anniversary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chooseDate2(v);
            }
        });

        addphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
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
            tv_anniversary.setText(fmtDateAndTime2.format(dateAndTime2.getTime()));
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
            tv_dateofbirth.setText(fmtDateAndTime.format(dateAndTime.getTime()));
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
            imageView.setImageBitmap(bm);

        }


    }
    // validating email id
    private boolean isValidEmail(String first) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(first);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

}