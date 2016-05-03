import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Doctor {
  private int id;
  private String name;

  public Doctor (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
}
