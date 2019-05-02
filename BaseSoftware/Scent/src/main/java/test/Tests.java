package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.javaparser.ast.CompilationUnit;

import parser.Parser;
import smell.Smell;
import smell.abuser.Abuser;
import smell.bloater.Bloater;
import smell.coupler.Coupler;
import smell.dispensable.Dispensable;

public class Tests {

    @Test
    public void testBloaters() {
        Bloater bloaterTest = new Bloater();
        try {
            List<CompilationUnit> cUnits = new Parser(new File("src/main/java/test/BloaterTestFile").getAbsolutePath()).getAllCu();
            bloaterTest.detect(cUnits);
            List<Map<String, Map<Smell.Bloaters, Smell>>> smells = bloaterTest.getBloaters();


            for (Map<Smell.Bloaters, Smell> m : smells.get(0).values()) {
                assertEquals(6, m.get(Smell.Bloaters.LONG_METHOD).getIssue().size());
                assertEquals(6, m.get(Smell.Bloaters.LONG_PARAMETER_LIST).getIssue().size());
                assertEquals(1, m.get(Smell.Bloaters.LARGE_CLASS).getIssue().size());
                assertEquals(1, m.get(Smell.Bloaters.PRIMITIVE_OBSESSION).getIssue().size());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void testAbusers() {
        Abuser abuserTest = new Abuser();
        try {
            List<CompilationUnit> cUnits = new Parser(new File("src/main/java/test/AbuserTestFile").getAbsolutePath()).getAllCu();
            abuserTest.detect(cUnits);
            List<Map<String, Map<Smell.Abusers, Smell>>> smells = abuserTest.getAbusers();

            for (Map<Smell.Abusers, Smell> m : smells.get(0).values()) {
                assertEquals(1, m.get(Smell.Abusers.SWITCH_STATEMENT).getIssue().size());
                assertEquals(1, m.get(Smell.Abusers.DATA_HIDING).getIssue().size());
            }

            for (Map<Smell.Abusers, Smell> m : smells.get(1).values()) {
                assertEquals(0, m.get(Smell.Abusers.SWITCH_STATEMENT).getIssue().size());
                assertEquals(0, m.get(Smell.Abusers.DATA_HIDING).getIssue().size());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    @Test
    public void testDispensable() {
        Dispensable dispensableTest = new Dispensable();
        try {
            List<CompilationUnit> cUnits = new Parser(new File("src/main/java/test/DispensableTestFile").getAbsolutePath()).getAllCu();
            dispensableTest.detect(cUnits);
            List<Map<String, Map<Smell.Dispensables, Smell>>> smells =
                    dispensableTest.getDispensables();


            for (Map<Smell.Dispensables, Smell> m : smells.get(0).values()) {
                assertEquals(1, m.get(Smell.Dispensables.COMMENTS).getIssue().size());
                assertEquals(1, m.get(Smell.Dispensables.DATA_CLASS).getIssue().size());
                assertEquals(0, m.get(Smell.Dispensables.DEAD_CODE).getIssue().size());
                assertEquals(1, m.get(Smell.Dispensables.LAZY_CLASS).getIssue().size());
            }

            for (Map<Smell.Dispensables, Smell> m : smells.get(1).values()) {
                assertEquals(1, m.get(Smell.Dispensables.COMMENTS).getIssue().size());
                assertEquals(1, m.get(Smell.Dispensables.DATA_CLASS).getIssue().size());
                assertEquals(1, m.get(Smell.Dispensables.DEAD_CODE).getIssue().size());
                assertEquals(1, m.get(Smell.Dispensables.LAZY_CLASS).getIssue().size());
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    @Test
    public void testCoupler() {
        Coupler couplerTest = new Coupler();
        try {
            List<CompilationUnit> cUnits = new Parser(new File("src/main/java/test/CouplerTestFile").getAbsolutePath()).getAllCu();
            couplerTest.detect(cUnits);
            List<Map<String, Map<Smell.Couplers, Smell>>> smells = couplerTest.getCouplers();

            for (Map<Smell.Couplers, Smell> m : smells.get(0).values()) {
                assertEquals(0, m.get(Smell.Couplers.FEATURE_ENVY).getIssue().size());
                assertEquals(1, m.get(Smell.Couplers.MESSAGE_CHAIN).getIssue().size());
                assertEquals(0, m.get(Smell.Couplers.MIDDLE_MAN).getIssue().size());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
