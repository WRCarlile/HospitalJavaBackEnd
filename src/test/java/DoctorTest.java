import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hospital_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteDoctorsQuery = "DELETE FROM doctor *;";
      String deleteSpecialtiesQuery = "DELETE FROM specialty *;";
      String deletePatientsQuery = "DELETE FROM patients *;";
      con.createQuery(deleteDoctorsQuery).executeUpdate();
      con.createQuery(deleteSpecialtiesQuery).executeUpdate();
      con.createQuery(deletePatientsQuery).executeUpdate();
    }
  }

  @Test
  public void doctor_instantiatesCorrectly_true() {
    Doctor myDoctor = new Doctor("Dr. Connors");
    assertEquals(true, myDoctor instanceof Doctor);
  }
}
