package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuizScoreDao {

    @Query("SELECT * FROM QuizScore")
    List<QuizScore> getAllQuizScores();

    @Insert
    long addQuizScore(QuizScore quizScore);

    @Delete
    void deleteQuizScore(QuizScore quizScore);

    @Query("SELECT DISTINCT Quiz.name FROM Quiz " +
            "INNER JOIN Student ON Student.id = Quiz.studentId " +
            "INNER JOIN QuizScore on QuizScore.quizId = Quiz.id " +
            "WHERE Student.username LIKE :username " +
            "ORDER BY QuizScore.timestamp DESC ")
    List<String> getQuizzesByStudent(String username);

    @Query("SELECT * FROM QuizScore " +
            "INNER JOIN Student ON Student.id = QuizScore.studentId " +
            "INNER JOIN Quiz on Quiz.id = QuizScore.quizId " +
            "WHERE Student.username LIKE :username AND Quiz.name LIKE :quizName " +
            "ORDER BY QuizScore.timestamp ASC ")
    List<QuizScore> getFirstScoreByStudentQuiz(String username, String quizName);

    @Query("SELECT * FROM QuizScore " +
            "INNER JOIN Student ON Student.id = QuizScore.studentId " +
            "INNER JOIN Quiz on Quiz.id = QuizScore.quizId " +
            "WHERE Student.username LIKE :username AND Quiz.name LIKE :quizName " +
            "ORDER BY QuizScore.finalScore DESC ")
    List<QuizScore> getHighestScoreByStudentQuiz(String username, String quizName);

    @Query("SELECT DISTINCT Student.username FROM Student " +
            "INNER JOIN QuizScore ON QuizScore.studentId = Student.id " +
            "INNER JOIN Quiz on Quiz.studentId = Student.id " +
            "WHERE Quiz.name LIKE :name AND CAST(QuizScore.finalScore AS int) = 1 " +
            "ORDER BY QuizScore.timestamp DESC ")
    List<String> getFirstThreeStudentHundredsByQuizName(String name);

}
