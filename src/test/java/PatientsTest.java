import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PatientsTest {

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
  public void patients_instantiatesCorrectly_true() {
    Patients myPatient = new Patients("Buford Mcrae","12/10/1951", 1);
    assertEquals(true, myPatient instanceof Patients);
  }

  @Test
  public void getName_patientInstantiatesWithName_String() {
    Patients myPatient = new Patients("Buford Mcrae","12/10/1951", 1);
    assertEquals("Buford Mcrae", myPatient.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patients.all().size(), 0);
  }
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Patients firstPatients = new Patients("Buford Mcrae","12/10/1951", 1);
    Patients secondPatients = new Patients("Buford Mcrae","12/10/1951", 1);
    assertTrue(firstPatients.equals(secondPatients));
  }
  @Test
  public void save_savesIntoDatabase_true() {
    Patients myPatients = new Patients("Buford Mcrae","12/10/1951", 1);
    myPatients.save();
    assertTrue(Patients.all().get(0).equals(myPatients));
  }

  @Test
  public void save_assignsIdToObject() {
    Patients myPatients = new Patients("Buford Mcrae","12/10/1951", 1);
    myPatients.save();
    Patients savedPatients = Patients.all().get(0);
    assertEquals(myPatients.getId(), savedPatients.getId());
  }

}
