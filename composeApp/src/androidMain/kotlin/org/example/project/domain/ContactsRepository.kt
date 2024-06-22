package org.example.project.domain

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import commons.domain.Callback
import entity.Contacts
import extensions.Extensions.getAlias
import feature.domain.IContactsRepository

class ContactsRepository(private val contentResolver: ContentResolver? = null) : IContactsRepository {

    @SuppressLint("InlinedApi")
    private val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.Data._ID,
        // The primary display name
        ContactsContract.Data.DISPLAY_NAME_PRIMARY,
        // The contact's _ID, to construct a content URI
        ContactsContract.Data.CONTACT_ID,
        // The contact's LOOKUP_KEY, to construct a content URI
        ContactsContract.Data.LOOKUP_KEY,
        ContactsContract.Data.DATA1
    )

    override fun getContacts() : ArrayList<Contacts> {
        return arrayListOf(Contacts("Jao", "1102020202", "JC"),
            Contacts("Ssd", "222222", "DS"),
            Contacts("dsad", "2222", "AS"))
    }


    override fun sendContactQuery(callback: Callback<List<Contacts>>) {
        val phones: Cursor? =
            contentResolver?.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null
            )

        val phoneBook = mutableSetOf<Contacts>()

        phones?.let { it ->
            while (it.moveToNext()) {
                Contacts().apply {
                    name =
                        it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)).also {
                            aka =
                                it.getAlias()
                        }
                    cellphone =
                        it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    phoneBook.add(this)
                }
            }

            callback.onSuccess(phoneBook.toMutableList())
        }
    }
}