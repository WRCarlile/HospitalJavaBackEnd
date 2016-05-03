import java.util.List;
import org.sql2o.*;


public class Specialty {
  private int id;
  private String name;
  private int specialty_id;

  public Specialty(String name, int specialty_id) {
    this.name = name;
    this.specialty_id = specialty_id;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public int getSpecialtyId() {
    return specialty_id;
  }


}
