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

  @Test
  public void getName_doctorInstantiatesWithName_String() {
    Doctor myDoctor = new Doctor("Dr. Connors");
    assertEquals("Dr. Connors", myDoctor.getName());

  }
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Doctor firstDoctor = new Doctor("Dr. Connors");
    Doctor secondDoctor = new Doctor("Dr. Connors");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("Dr. Connors");
    myDoctor.save();
    assertTrue(Doctor.all().get(0).equals(myDoctor));
  }

  @Test
  public void save_assignsIdToObject() {
    Doctor myDoctor = new Doctor("Dr. Connors");
    myDoctor.save();
    Doctor savedDoctor = Doctor.all().get(0);
    assertEquals(myDoctor.getId(), savedDoctor.getId());
  }

  @Test
    public void find_findDoctorInDatabase_true() {
      Doctor myDoctor = new Doctor("Dr. Connors");
      myDoctor.save();
      Doctor savedDoctor = Doctor.find(myDoctor.getId());
      assertTrue(myDoctor.equals(savedDoctor));
    }

    @Test
    public void methodAllReturnsPatientArray_True() {
      Doctor myDoctor = new Doctor("Dr. Connors");
      myDoctor.save();
      Patients firstPatient = new Patients("Buford Mcrae","12/10/1951",  myDoctor.getId());
      firstPatient.save();
      Patients secondPatient = new Patients("Edyth Grant", "6/24/1981", myDoctor.getId());
      secondPatient.save();
      Patients[] patients = new Patients[] { firstPatient, secondPatient };
      assertTrue(myDoctor.getPatients().containsAll(Arrays.asList(patients)));
    }
  }
