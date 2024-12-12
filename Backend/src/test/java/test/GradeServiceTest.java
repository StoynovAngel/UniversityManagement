package test;

import dto.Grade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.GradeService;
import uk.org.webcompere.systemstubs.SystemStubs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GradeServiceTest {
    private GradeService gradeService;

    @Test
    @DisplayName("Check for identical grades.")
    void testAddGradeToUser() {

    }

    @Test
    void testUpdateGrade() {
        fail("not implemented");
    }

    @Test
    void testAddGrade() {
    }
}