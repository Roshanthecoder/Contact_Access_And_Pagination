package com.example.myapplication

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.core.content.ContextCompat


class LoadContactsTask(private val context: Context, private val listener: ContactLoadingListener) :
    AsyncTask<Void, Void, List<Contact>>() {

    override fun doInBackground(vararg params: Void): List<Contact> {
        val contacts = ArrayList<Contact>()
        val permission = Manifest.permission.READ_CONTACTS
        val granted = PackageManager.PERMISSION_GRANTED
        val hasPermission = ContextCompat.checkSelfPermission(context, permission) == granted

        if (hasPermission) {
            val contentResolver: ContentResolver = context.contentResolver
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )

            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, null, null, null
            )

            cursor?.use { c ->
                val nameColumnIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberColumnIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                while (c.moveToNext()) {
                    val name = c.getString(nameColumnIndex)
                    var number = c.getString(numberColumnIndex)

                    if (!name.isNullOrBlank() && !number.isNullOrBlank()) {
                        // Remove spaces from the phone number
                        number = number.replace(" ", "")

                        contacts.add(Contact(name, number))
                    }
                }
            }
        }

        // Filter duplicates based on phone number
        val distinctContacts = contacts.distinctBy { it.number }

        return distinctContacts
    }

    override fun onPostExecute(result: List<Contact>) {
        listener.onContactsLoaded(result)
    }
}


interface ContactLoadingListener {
    fun onContactsLoaded(contacts: List<Contact>)
}


