package com.votenoid.myapplication.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.data.recyclerview_helper.SuperEntity;

@Entity(tableName = "note")
public class NoteEntity extends SuperEntity {
    @PrimaryKey
    private long ID;
    private int type;
    private long timeStamp;
    private String tittle,body;



    public NoteEntity(long id,int type, long timeStamp, String tittle, String body) {

        this.type = type;
        this.ID=id;
        this.timeStamp = timeStamp;
        this.tittle = tittle;
        this.body = body;
    }


    public NoteEntity(){
        super();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
