package android.thaihn.androidadvance.content_provider;

import android.content.ContentResolver;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.thaihn.androidadvance.BaseActivity;
import android.thaihn.androidadvance.R;

import java.util.ArrayList;

public class ContentProviderActivity extends BaseActivity
implements PhoneAdapter.ClickPhone{

    private RecyclerView mRecycle;
    private PhoneAdapter mPhoneAdapter;
    private Cursor mCursor;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_content_provider;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mRecycle = findViewById(R.id.recycle_phone);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mCursor = getCursor();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        mRecycle.setHasFixedSize(true);
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setItemAnimator(new DefaultItemAnimator());
        mPhoneAdapter = new PhoneAdapter(this, new ArrayList<PhoneObject>());
        mPhoneAdapter.setCLickPhone(this);
        mRecycle.setAdapter(mPhoneAdapter);
        mPhoneAdapter.notifyDataSetChanged();

        new LoadAsync().execute(mCursor);
    }

    @Override
    public void onClick(PhoneObject phone) {

    }

    public class LoadAsync extends AsyncTask<Cursor, Void, ArrayList<PhoneObject>> {

        @Override
        protected ArrayList<PhoneObject> doInBackground(Cursor... cursor) {
            return getContact(cursor[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<PhoneObject> phoneObjects) {
            super.onPostExecute(phoneObjects);
            mPhoneAdapter.setListPhone(phoneObjects);
            mPhoneAdapter.notifyDataSetChanged();
        }
    }

    private ArrayList<PhoneObject> getContact(Cursor c) {
        ArrayList<PhoneObject> ls = new ArrayList<>();

        ContentResolver cr = getContentResolver();
        //---display the contact id and name and phone number----
        if (c.moveToFirst()) {
            do {
                //---get the contact id and name----
                String contactID = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                String contactDisplayName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String contactDisplayPhone = "";
                //---get phone number by getColumnIndex will be return value zero-base and -1 if don't have numColumn---
                int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (hasPhone == 1) {
                    //---get phone number by command Cursor---
                    Cursor phoneCursor = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
                                    contactID, null, null);
                    while (phoneCursor.moveToNext()) {
                        //---get phone number in here---
                        contactDisplayPhone = phoneCursor.getString(
                                phoneCursor.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phoneCursor.close();

                }
                ls.add(new PhoneObject(contactID, R.mipmap.ic_launcher, contactDisplayName, contactDisplayPhone));

            } while (c.moveToNext());
        }

        return ls;
    }


    private Cursor getCursor() {
        Cursor cursor;
        Uri allContact = ContactsContract.Contacts.CONTENT_URI;

        /* >>The second parameter of the managedQuery() method (third parameter for the CursorLoader class)
            controls how many columns are returned by the query; this parameter is known as the projection
           >>The third parameter of the managedQuery() method (fourth parameter for the CursorLoader class)
           enable you to specify a SQL WHERE clause to filter the result of the query
           >>The fourth parameter of the managedQuery() method (the fifth parameter for the CursorLoader class)
           enables you to specify a SQL ORDER BY clause to sort the result of the query, either in ascending or descending order
        * */
        String[] projection = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER};


        if (Build.VERSION.SDK_INT < 11) {
            cursor = managedQuery(allContact, projection, null, null, null);
        } else {

            CursorLoader cursorLoader = new CursorLoader(this, allContact, projection, null, null, null);
            cursor = cursorLoader.loadInBackground();
        }
        return cursor;
    }

}
