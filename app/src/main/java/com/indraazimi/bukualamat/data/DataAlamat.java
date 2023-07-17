package com.indraazimi.bukualamat.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alamat")
public class DataAlamat {
   @PrimaryKey(autoGenerate = true)
   long id;
   String name;
   String address;

   public DataAlamat(long id, String name, String address) {
      this.id = id;
      this.name = name;
      this.address = address;
   }

   public long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getAddress() {
      return address;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAddress(String address) {
      this.address = address;
   }
}
