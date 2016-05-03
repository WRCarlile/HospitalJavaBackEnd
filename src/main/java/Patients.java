import java.util.List;
import org.sql2o.*;

public class Patients {
  private int id;
  private String name;
  private String birthdate;
  private int dr_id;

  public Patients(String name, String birthdate, int dr_id) {
    this.name = name;
    this.birthdate = birthdate;
    this.dr_id = dr_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getDr_Id() {
    return dr_id;
  }
}
