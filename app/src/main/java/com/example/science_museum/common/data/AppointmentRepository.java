package com.example.science_museum.common.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.science_museum.common.data.dao.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {


    public class AppointmentDatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "prefabricate_data.db";
        private static final int DATABASE_VERSION = 3;

        private static final String TABLE_APPOINTMENTS = "appointments";
        private static final String COLUMN_APPOINTMENT_ID = "appointment_id";
        private static final String COLUMN_UID = "uid";
        private static final String COLUMN_TELEPHONE_NUMBER = "telephone_number";
        private static final String COLUMN_ID_NUMBER = "ID_number";
        private static final String COLUMN_BOOKER_NAME = "booker_name";
        private static final String COLUMN_APPOINTMENT_DATE = "appointment_date";

        public AppointmentDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS + "("
                    + COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_UID + " INTEGER,"
                    + COLUMN_TELEPHONE_NUMBER + " TEXT,"
                    + COLUMN_ID_NUMBER + " TEXT,"
                    + COLUMN_BOOKER_NAME + " TEXT,"
                    + COLUMN_APPOINTMENT_DATE + " TEXT,"
                    + "FOREIGN KEY(" + COLUMN_UID + ") REFERENCES users(uid)" + ")";
            db.execSQL(CREATE_APPOINTMENTS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
            onCreate(db);
        }

        // TODO: Add methods for CRUD operations using AppointmentBean
        protected long addAppointment(AppointmentBean appointment) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_UID, appointment.getUid());
            values.put(COLUMN_TELEPHONE_NUMBER, appointment.getTelephoneNumber());
            values.put(COLUMN_ID_NUMBER, appointment.getIdNumber());
            values.put(COLUMN_BOOKER_NAME, appointment.getBookerName());
            values.put(COLUMN_APPOINTMENT_DATE, appointment.getAppointmentDate());

            long id = db.insert(TABLE_APPOINTMENTS, null, values);
            db.close();
            return id;
        }
        protected List<AppointmentBean> getAppointmentsByUID(long uid) {
            List<AppointmentBean> appointments = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_APPOINTMENTS, new String[] { COLUMN_APPOINTMENT_ID,
                            COLUMN_UID, COLUMN_TELEPHONE_NUMBER, COLUMN_ID_NUMBER, COLUMN_BOOKER_NAME,
                            COLUMN_APPOINTMENT_DATE }, COLUMN_UID + "=?",
                    new String[] { String.valueOf(uid) }, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    AppointmentBean appointment = new AppointmentBean();
                    appointment.setAppointmentId(cursor.getLong(0));
                    appointment.setUid(cursor.getLong(1));
                    appointment.setTelephoneNumber(cursor.getString(2));
                    appointment.setIdNumber(cursor.getString(3));
                    appointment.setBookerName(cursor.getString(4));
                    appointment.setAppointmentDate(cursor.getString(5));
                    appointments.add(appointment);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return appointments;
        }

        protected int updateAppointment(AppointmentBean appointment) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_UID, appointment.getUid());
            values.put(COLUMN_TELEPHONE_NUMBER, appointment.getTelephoneNumber());
            values.put(COLUMN_ID_NUMBER, appointment.getIdNumber());
            values.put(COLUMN_BOOKER_NAME, appointment.getBookerName());
            values.put(COLUMN_APPOINTMENT_DATE, appointment.getAppointmentDate());

            return db.update(TABLE_APPOINTMENTS, values, COLUMN_APPOINTMENT_ID + " = ?",
                    new String[] { String.valueOf(appointment.getAppointmentId()) });
        }

        protected void deleteAppointment(long appointmentId) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_APPOINTMENTS, COLUMN_APPOINTMENT_ID + " = ?",
                    new String[] { String.valueOf(appointmentId) });
            db.close();
        }

    }
    private Context mContext;
    private AppointmentDatabaseHelper mAppointmentDatabaseHelper;
    public AppointmentRepository(Context context)
    {
        mContext=context;
        mAppointmentDatabaseHelper=new AppointmentDatabaseHelper(context);
    }

    public long addAppointment(AppointmentBean appointment) {
        return mAppointmentDatabaseHelper.addAppointment(appointment);
    }

    public List<AppointmentBean> getAppointmentsByUID(long uid) {
        return mAppointmentDatabaseHelper.getAppointmentsByUID(uid);
    }

}
