package com.google.homework2;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Работа на Ultimate версии просто одно удовольствие.)
 */

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        this.students.add(new Student("Javokhir Smith", "dev"));
        this.students.add(new Student("Ivan Li", "front"));
        this.students.add(new Student("Kristy Bazy", "web"));
        this.students.add(new Student("Vika Ask", "dev"));
        this.students.add(new Student("Artem Guss", "front"));
    }

    /**
     *
     * @param id поиск студента по id
     * @return вернет студента, в случае если нет такого студента то веннет исключение not found!
     */
    @GetMapping("student/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new IllegalArgumentException("Student with id %s" + id + " not found");
    }

    /**
     * Вывод всех студентов
     * @return возвращает копию листа из всех студентов
     * копия листа - что бы злоумышленники не очистили список
     */
    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    /**
     *
     * @param name получить список студентов, чье имя содердит подстроку studentName
     * @return возвращает список студентов чье имя сожержить
     */
    @GetMapping("/student/search")
    public List<Student> getStudentsByName(@RequestParam("name") String name) {
        List<Student> studentList = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                studentList.add(student);
            }
        }
        return List.copyOf(studentList);
    }

    /**
     *
     * @param groupName получим всех студентов группы
     * @return возвращаем список студентов по группе
     */
    @GetMapping("/student/group/{groupName}")
    public List<Student> getStudentsByGroup(@PathVariable("groupName") String groupName) {
        List<Student> studentList = new ArrayList<>();
        for (Student student : students) {
            if (student.getGroupName().equals(groupName)){
                studentList.add(student);
            }
        }
        return List.copyOf(studentList);
    }

    /**
     *
     * @param student добавление нового студента
     * @return и сразу вернем недавно добавленного студента и это удобно :)
     */
    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    /**
     *
     * @param id удалить студента по его id
     */
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        students.removeIf(student -> student.getId().equals(id));
        throw new IllegalArgumentException("Student with id %s" + id + " not found");
    }

}
