import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class SpecialtyTest {

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
  public void specialty_instantiatesCorrectly_true() {
    Specialty mySpecialty = new Specialty("General Practicioner", 1);
    assertEquals(true, mySpecialty instanceof Specialty);
  }

  @Test
  public void getName_specialtyInstantiatesWithName_String() {
    Specialty mySpecialty = new Specialty("General Practicioner", 1);
    assertEquals("General Practicioner", mySpecialty.getName());
  }
  @Test
  public void all_emptyAtFirst() {
    assertEquals(Specialty.all().size(), 0);
  }
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Specialty firstSpecialty = new Specialty("General Practicioner", 1);
    Specialty secondSpecialty = new Specialty("General Practicioner", 1);
    assertTrue(firstSpecialty.equals(secondSpecialty));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Specialty mySpecialty = new Specialty("General Practicioner", 1);
    mySpecialty.save();
    assertTrue(Specialty.all().get(0).equals(mySpecialty));
  }

  @Test
  public void save_assignsIdToObject() {
    Specialty mySpecialty = new Specialty("General Practicioner", 1);
    mySpecialty.save();
    Specialty savedSpecialty = Specialty.all().get(0);
    assertEquals(mySpecialty.getId(), savedSpecialty.getId());
  }
}
