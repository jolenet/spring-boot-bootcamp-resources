package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
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
            new Grade("Hermoine", "Arithmancy", "a+")
        ));

        //store result in arraylist
        List<Grade> result = gradeService.getGrades();

        assertEquals("Harry", result.get(0).getName());
    }
}
