package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Query("SELECT * FROM Student WHERE Student.username LIKE :username")
    List<Student> getStudentByUsername(String username);

    @Query("SELECT * FROM Student WHERE Student.id = :id")
    List<Student> getStudentById(int id);

    @Insert
    long registerNewStudent(Student student);

    @Delete
    void delete(Student student);

}
