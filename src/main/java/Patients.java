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

  public static List<Patients> all() {
    String sql = "SELECT id, name, birthdate, dr_id FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patients.class);
    }
  }
  @Override
  public boolean equals(Object otherPatients){
    if (!(otherPatients instanceof Patients)) {
      return false;
    } else {
      Patients newPatients = (Patients) otherPatients;
      return this.getName().equals(newPatients.getName()) &&
             this.getId() == newPatients.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, birthdate, dr_id) VALUES (:name, :birthdate, :dr_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("birthdate", this.birthdate)
        .addParameter("dr_id", this.dr_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patients find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients  where id=:id";
      Patients patients = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patients.class);
      return patients;
    }
  }
}
