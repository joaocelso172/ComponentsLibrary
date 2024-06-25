package org.example.project.feature.data

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import commons.domain.Callback
import entity.Contacts
import extensions.Extensions.getAlias
import feature.domain.repository.IContactsRepository

class ContactsRepository(private val contentResolver: ContentResolver? = null) :
    IContactsRepository {

    @SuppressLint("InlinedApi")
    private val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.Data._ID,
        ContactsContract.Data.DISPLAY_NAME_PRIMARY,
        ContactsContract.Data.CONTACT_ID,
        ContactsContract.Data.LOOKUP_KEY,
        ContactsContract.Data.DATA1
    )

    override fun getContacts(): ArrayList<Contacts> {
        return retrieveContactList()
//        return arrayListOf(Contacts("Jao", "1102020202", "JC"),
//            Contacts("Ssd", "222222", "DS"),
//            Contacts("dsad", "2222", "AS"))
    }

    fun retrieveContactList(): ArrayList<Contacts> {
        val phones: Cursor? =
            contentResolver?.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null
            )

        ArrayList<Contacts>().apply {
            phones?.let { it ->
                while (it.moveToNext()) {
                    Contacts().apply {
                        name =
                            it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY))
                                .also {
                                    aka =
                                        it.getAlias()
                                }
                        cellphone =
                            it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))

                        add(this)
                    }
                }

                it.close()
                return this
            }
        }
        return ArrayList()
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
                        it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY))
                            .also {
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