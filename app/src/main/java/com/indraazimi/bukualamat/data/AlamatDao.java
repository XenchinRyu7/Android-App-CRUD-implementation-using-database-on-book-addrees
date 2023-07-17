package com.indraazimi.bukualamat.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlamatDao {
    @Insert
    void insert(DataAlamat data);

    @Update
    void update(DataAlamat data);

    @Delete
    void delete(DataAlamat data);

    @Query("SELECT * FROM alamat")
    List<DataAlamat> getData();

    @Query("SELECT * FROM alamat WHERE id = :dataId")
    DataAlamat getDataById(long dataId);
}
