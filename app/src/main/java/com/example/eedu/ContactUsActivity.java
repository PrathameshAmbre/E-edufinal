package com.example.eedu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ContactUsActivity extends AppCompatActivity {
    EditText sender_mail,sender_password,sender_subject,sender_message;
    Button submit_btn;
    String username,password,subject,message,receiver_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        sender_mail=findViewById(R.id.contact_email);
        sender_password=findViewById(R.id.contact_password);
        sender_subject=findViewById(R.id.contact_subject);
        sender_message=findViewById(R.id.contact_message);
        submit_btn=findViewById(R.id.send_mail);

        username=sender_mail.getText().toString().trim();
        password=sender_password.getText().toString().trim();
        subject=sender_subject.getText().toString().trim();
        message=sender_message.getText().toString().trim();

        receiver_mail="project.msc003@gmail.com";

        SendingMail();


    }

    public void SendingMail(){
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                final String username="project.msc003@gmail.com"; //sender email & password
                final String password="student@123";
                String messageToSend=message.getText().toString().trim(); */

                Properties properties=new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");
                Session session=Session.getInstance(properties,new Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username,password); //sender
                    }
                });
                try {
                    Message msg= new MimeMessage(session);
                    //sender email
                    msg.setFrom(new InternetAddress(username));
                    //recipient email
                    InternetAddress[] toAddresses = {new InternetAddress(receiver_mail)};
                    msg.setRecipients(Message.RecipientType.TO, toAddresses);
                    //email subject
                    msg.setSubject(subject);
                    //email msg
                    msg.setText(message);

                    //send email
                    new SendMail().execute(msg);
                }
                catch (MessagingException e){
                    e.printStackTrace();

                }

            }
        });
    }

    private class SendMail extends AsyncTask<Message,String,String>{
        private ProgressDialog progressDialog;

        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog=ProgressDialog.show(ContactUsActivity.this,"Please Wait","Sending Mail....",true,false);

        }

        @Override
        protected String doInBackground(Message... msgs) {
            try {
                //when success
                Transport.send(msgs[0]);
                return "Success";
            } catch (MessagingException e) {
                //when error
                e.printStackTrace();
                return "Error";
            }

        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //dismiss progress dialog
            progressDialog.dismiss();
            if(s.equals("Success")){
                //when success
                AlertDialog.Builder builder=new AlertDialog.Builder(ContactUsActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        sender_mail.setText("");
                        sender_password.setText("");
                        sender_subject.setText("");
                        sender_message.setText("");
                    }
                });
                builder.show();
            }
            else {
                Toast.makeText(ContactUsActivity.this,"Something went wrong ?",Toast.LENGTH_SHORT).show();
            }
        }
    }








}