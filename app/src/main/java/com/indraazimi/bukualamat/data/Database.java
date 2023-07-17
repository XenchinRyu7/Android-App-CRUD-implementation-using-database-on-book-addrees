package com.indraazimi.bukualamat.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {DataAlamat.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract AlamatDao dao();

    private static Database INSTANCE = null;

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        Database.class,
                        "alamat.db")
                        .allowMainThreadQueries()
                        .build();
        }
        return INSTANCE;
    }
}
