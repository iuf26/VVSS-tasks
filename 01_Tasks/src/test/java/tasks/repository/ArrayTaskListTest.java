package tasks.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {

    private TaskList tasksRepo = new ArrayTaskList();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm", Locale.ENGLISH);


    @DisplayName("Test for adding valid Task") // 1.annotation
    @Test
    void addValidTask() {
        try {
            Task task = new Task("valid", formatter.parse("2023-03-21 12:34"), formatter.parse("2023-03-21 13:34"), 2);
            tasksRepo.add(task);

        } catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        assert (true);
    }

    @ParameterizedTest //3.annotation
    @ValueSource(ints = {2, 3, 4}) //2.annotation
    @DisplayName("Check For Task Add With Different Interval Values")
    void checkTaskAddWithDifferentValidIntervalValues(int interval) {
        try {
            Task task = new Task("valid", formatter.parse("2023-03-21 12:34"), formatter.parse("2023-03-21 13:34"), interval);
            tasksRepo.add(task);
        } catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        assert (true);
    }

    //TO DO: Add missing tests following BVA & ECP test cases and conditions
    //TO DO: Add 2 more adnotation that were not used before for testing

    //ECP test cases
    @Test
    @DisplayName("Descriere 2")
    void checkTaskAddIncorrectEndTimeAndStartTime() {
        try {
            Task task = new Task("invalid", formatter.parse("2023-03-11 22:32") , formatter.parse("2023-03-11 12:32"),2);
            tasksRepo.add(task);
            assert(false);
        }
        catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(true);
        }
    }

    @Test
    @DisplayName("Descriere 3")
    void checkTaskAddIntervalValueLessThanOneShouldThrowError() {
        try {
            Task task = new Task("invalid",  formatter.parse("2023-03-11 12:32"),formatter.parse("2023-03-11 22:32"),-4321);
            tasksRepo.add(task);
            assert(false);
        }
        catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        catch(IllegalArgumentException e)
        {
            assert(e.getMessage().equals("interval should be > 1"));
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(true);
        }
    }


    //BVA
    @Test
    @DisplayName("test1")
    void checkStartTimeShouldBeLessThanEndTime () {
        try {
            Task task = new Task("invalid", formatter.parse("2023-03-11 13:32:23") , formatter.parse("2023-03-11 13:32:23"),2);
            tasksRepo.add(task);
            assert(false);
        }
        catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(e.getMessage().equals("StartTime should be < EndTime"));
        }
    }

    @Test
    @DisplayName("test2")
    void checkIntervalLessThan1ShouldThrowError () {
        try {
            Task task = new Task("invalid", formatter.parse("2023-03-11 12:32:23") , formatter.parse("2023-03-11 13:32:23"),0);
            tasksRepo.add(task);
            assert(false);
        }
        catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            assert(e.getMessage().equals("interval should be > 1"));
        }
    }

    @Test
    @DisplayName("test3")
    void checkIntervalEquals1ShouldThrowError () {
        try {
            Task task = new Task("invalid", formatter.parse("2023-03-11 12:32:23") , formatter.parse("2023-03-11 13:32:23"),1);
            tasksRepo.add(task);
            assert(false);
        }
        catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        catch (Exception e) {
            e.printStackTrace();
            assert(e.getMessage().equals("interval should be > 1"));
        }
    }

    @Test
    @DisplayName("test4")
    void checkIntervalGreaterThan1 () {
        try {
            Task task = new Task("invalid", formatter.parse("2023-03-11 12:32:23") , formatter.parse("2023-03-11 13:32:23"),2);
            tasksRepo.add(task);
        }
        catch (ParseException e) {
            e.printStackTrace();
            assert (false);
        }
        assert(true);
    }

}