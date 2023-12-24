package com.example.science_museum.common.data.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "appointments")
public class Appointment {
    @PrimaryKey()
    public long appointment_id;

    @ColumnInfo(name = "uid")
    public long uid;

    @ColumnInfo(name = "telephone_number")
    public String telephone_number;

    @ColumnInfo(name = "ID_number")
    public String ID_number;

    @ColumnInfo(name = "booker_name")
    public String booker_name;

    @ColumnInfo(name = "appointment_date")
    public String appointment_date;

}
