package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId",
            onDelete = CASCADE),
        @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quizId",
            onDelete = CASCADE)})
public class QuizScore {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public float finalScore;

    @TypeConverters(DateConverter.class)
    public Date timestamp;

    public int studentId;
    public int quizId;

    public QuizScore(int studentId, int quizId, float finalScore) {
        this.finalScore = finalScore;
        this.timestamp = new Date();
        this.studentId = studentId;
        this.quizId = quizId;
    }

    @Override
    public String toString() {
        int percentage = (int) Math.ceil((finalScore) * 100);
        return String.valueOf(percentage) + "%";
    }

    public String toStringWithTimeStamp(){
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(timestamp);
        return toString() + " " + date;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }
}
