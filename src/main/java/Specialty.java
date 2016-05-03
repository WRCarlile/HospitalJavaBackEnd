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

  public static List<Specialty> all() {
    String sql = "SELECT id, name, specialty_id FROM specialty";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Specialty.class);
    }
  }
  @Override
  public boolean equals(Object otherSpecialty){
    if (!(otherSpecialty instanceof Specialty)) {
      return false;
    } else {
      Specialty newSpecialty = (Specialty) otherSpecialty;
      return this.getName().equals(newSpecialty.getName()) &&
             this.getId() == newSpecialty.getId();
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO specialty (name, specialty_id) VALUES (:name, :specialty_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("specialty_id", this.specialty_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Specialty find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM specialty where id=:id";
      Specialty specialty = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Specialty.class);
      return specialty;
    }
  }

}
