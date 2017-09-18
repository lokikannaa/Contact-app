package com.example.srinivasvarma.contacts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewContact extends AppCompatActivity {

    public final static int CAMERA_PIC_REQUEST = 101;
    int imageId;
    ImageButton ibProfilePic;
    EditText etFirstName;
    EditText etLastName;
    EditText etCompany;
    EditText etPhone;
    EditText etEmail;
    EditText etURL;
    EditText etAddress;
    EditText etBirthday;
    EditText etNickName;
    EditText etFbURL;
    EditText etTwitterURL;
    EditText etSkype;
    EditText etYouTube;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    int itemID;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);
        this.setTitle("Create New Contact");

        imageId = R.drawable.add_photo;
        ibProfilePic = (ImageButton) findViewById(R.id.ibProfilePic);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etCompany = (EditText) findViewById(R.id.etCompany);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etURL = (EditText) findViewById(R.id.etURL);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        etNickName = (EditText) findViewById(R.id.etNickName);
        etFbURL = (EditText) findViewById(R.id.etFBURL);
        etTwitterURL = (EditText) findViewById(R.id.etTwitterURL);
        etSkype = (EditText) findViewById(R.id.etSkype);
        etYouTube = (EditText) findViewById(R.id.etYoutube);

        Intent intent = getIntent();
        key = intent.getStringExtra(MainActivity.ACTION_KEY);
        itemID = intent.getIntExtra(MainActivity.ITEM_ID, 0);
        if (key.equals("DISPLAY") || key.equals("EDIT")) {

            this.setTitle("Editing Contact");
            Contact contact = (Contact) intent.getSerializableExtra(MainActivity.CONTACT_ITEM);
            MainActivity.contactList.remove(contact);
            Bitmap profilePic = CreateNewContact.decodeBase64(contact.getProfilePic());
            ibProfilePic.setImageBitmap(profilePic);
            etFirstName.setText(contact.getFirstName());
            etLastName.setText(contact.getLastName());
            etCompany.setText(contact.getCompany());
            etPhone.setText(contact.getPhone());
            etEmail.setText(contact.getEmail());
            etURL.setText(contact.getURL());
            etAddress.setText(contact.getAddress());
            if (contact.getBirthday() != null) {
                try {
                    Date date = contact.getBirthday();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    String mydate = month + "/" + day + "/" + year;
                    etBirthday.setText(mydate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                etBirthday.setText("");
            etNickName.setText(contact.getNickName());
            etFbURL.setText(contact.getFbURL());
            etTwitterURL.setText(contact.getTwitterURL());
            etSkype.setText(contact.getSkype());
            etYouTube.setText(contact.getYoutube());
            if (key.equals("DISPLAY")) {
                this.setTitle("Displaying Contact");
                etBirthday.setClickable(false);
                etBirthday.setFocusable(false);
                ibProfilePic.setEnabled(false);
                etFirstName.setKeyListener(null);
                etLastName.setKeyListener(null);
                etCompany.setKeyListener(null);
                etPhone.setKeyListener(null);
                etEmail.setKeyListener(null);
                etURL.setKeyListener(null);
                etAddress.setKeyListener(null);
                etBirthday.setKeyListener(null);
                etNickName.setKeyListener(null);
                etFbURL.setKeyListener(null);
                etTwitterURL.setKeyListener(null);
                etSkype.setKeyListener(null);
                etYouTube.setKeyListener(null);
                findViewById(R.id.btnSave).setVisibility(View.GONE);

                etURL.setFocusable(false);
                etFbURL.setFocusable(false);
                etTwitterURL.setFocusable(false);
                etSkype.setFocusable(false);
                etYouTube.setFocusable(false);

                if (!etURL.getText().toString().equals("")) {
                    etURL.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                    etURL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = etURL.getText().toString();
                            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                url = "http://" + url;
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(i);
                        }
                    });

                }
                if (!etYouTube.getText().toString().equals("")) {
                    etYouTube.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                    etYouTube.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = etYouTube.getText().toString();
                            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                url = "http://" + url;
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(i);
                        }
                    });
                }
                if (!etFbURL.getText().toString().equals("")) {
                    etFbURL.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                    etFbURL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = etFbURL.getText().toString();
                            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                url = "http://" + url;
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(i);
                        }
                    });

                }
                if (!etSkype.getText().toString().equals("")) {
                    etSkype.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                    etSkype.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = etSkype.getText().toString();
                            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                url = "http://" + url;
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(i);
                        }
                    });
                }
                if (!etTwitterURL.getText().toString().equals("")) {
                    etTwitterURL.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                    etTwitterURL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = etTwitterURL.getText().toString();
                            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                url = "http://" + url;
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(i);
                        }
                    });
                }


            }
        }
        if(!key.equals("DISPLAY")) {
            etBirthday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(CreateNewContact.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    Log.d("demo", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                    String date = month + "/" + day + "/" + year;
                    etBirthday.setText(date);
                }
            };
        }

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date birthday = null;
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = null;
                try {
                    startDate = df.parse("01/01/1850");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Bitmap bm = null;
                String profilePicByteString;
                String firstName = "";
                String lastName = "";
                String company = "";
                String phone = "";
                String email = "";
                String URL = "";
                String address = "";
                String nickName = "";
                String fbURL = "";
                String twitterURL = "";
                String skype = "";
                String youtube = "";

                if (imageId == R.drawable.add_photo) {
                    bm = BitmapFactory.decodeResource(getResources(), R.drawable.default_image);
                    profilePicByteString = encodeTobase64(bm);
                } else {
                    bm = ((BitmapDrawable) ibProfilePic.getDrawable()).getBitmap();
                    profilePicByteString = encodeTobase64(bm);
                }
                firstName = etFirstName.getText().toString();
                lastName = etLastName.getText().toString();
                company = etCompany.getText().toString();
                phone = etPhone.getText().toString();
                email = etEmail.getText().toString();
                URL = etURL.getText().toString();
                address = etAddress.getText().toString();
                try {
                    birthday = df.parse(etBirthday.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                nickName = etNickName.getText().toString();
                fbURL = etFbURL.getText().toString();
                twitterURL = etTwitterURL.getText().toString();
                skype = etSkype.getText().toString();
                youtube = etYouTube.getText().toString();

                if (birthday != null && birthday.before(startDate)) {
                    Toast.makeText(CreateNewContact.this, "Birthday should be greater than 01/01/1850", Toast.LENGTH_SHORT).show();
                } else if (!email.equals("") && !validEmail(email)) {
                    Toast.makeText(CreateNewContact.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                } else if (firstName.trim().equals("") || firstName == null) {
                    Toast.makeText(CreateNewContact.this, "Enter FirstName", Toast.LENGTH_SHORT).show();
                } else if (lastName.trim().equals("")) {
                    Toast.makeText(CreateNewContact.this, "Enter LastName", Toast.LENGTH_SHORT).show();
                } else if (phone.trim().equals("")) {
                    Toast.makeText(CreateNewContact.this, "Enter PhoneNumber", Toast.LENGTH_SHORT).show();
                } else if (!isValidPhoneNumber(phone)) {
                    Toast.makeText(CreateNewContact.this, "Invalid PhoneNUmber", Toast.LENGTH_SHORT).show();
                } else if (firstName.trim() != "" && lastName.trim() != "" && isValidPhoneNumber(phone)) {
                    Contact contact = new Contact(profilePicByteString, firstName, lastName, company, phone, email, URL, address, birthday, nickName, fbURL, twitterURL, skype, youtube);
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.CONTACT_KEY, contact);
                    intent.putExtra(MainActivity.ITEM_ID, new Intent());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        ibProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAMERA_PIC_REQUEST);
            }
        });
    }

    public boolean isValidPhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageButton ib = (ImageButton) findViewById(R.id.ibProfilePic); //sets imageview as the bitmap
            ib.setImageBitmap(image);
            imageId = 99;
        }
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap imagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static boolean validEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        boolean b = matcher.matches();
        return b;
    }
}

