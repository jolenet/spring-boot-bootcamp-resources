package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    @Mock   //mimic grade repo dependency while having no logic of its own
    private GradeRepository gradeRepository;

    @InjectMocks    //create object of gradeService class, and inject repo mock into it. 
    private GradeService gradeService;

    @Test   //tells JUnit to run method as test
    public void getGradesFromRepoTest(){
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Arithmancy", "A+")
        ));

        //store result in arraylist
        List<Grade> result = gradeService.getGrades();

        //compare what we're expecting vs the actual
        assertEquals("Harry", result.get(0).getName());
        assertEquals("Potions", result.get(0).getSubject());
    }

    @Test
    public void gradeIndexTest(){
        Grade grade = new Grade("Harry", "Potions", "C-");
        when(gradeRepository.getGrades()).thenReturn(
        Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        List<Grade> result = gradeService.getGrades();
        int valid = gradeService.getGradeIndex(result.get(0).getId());
        int notFound = gradeService.getGradeIndex("123");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest(){
        Grade grade = new Grade("Harry", "Potions", "C-");
        when(gradeRepository.getGrades()).thenReturn(
        Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        String id = grade.getId();
        Grade result = gradeService.getGradeById(id);
        assertEquals(grade, result);
    }

    @Test
    public void addGradeTest(){
        Grade grade = new Grade("Harry", "Potions", "C-");
        when(gradeRepository.getGrades()).thenReturn(
        Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        Grade newGrade = new Grade("Hermoine", "Arithmancy", "A+");
        gradeService.submitGrade(newGrade);

        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest(){
        Grade grade = new Grade("Harry", "Potions", "C-");
        when(gradeRepository.getGrades()).thenReturn(
        Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        grade.setScore("F");
        gradeService.submitGrade(grade);
        verify(gradeRepository, times(1)).updateGrade(grade, 0);
    }
}
