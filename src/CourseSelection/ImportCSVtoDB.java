package CourseSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class ImportCSVtoDB {
    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\olade\\Downloads\\First Year(1st semester)\\Group Project\\Assessment A - Software Design (X)-20241018\\UMS Data\\UMS Data\\KISCOURSE.csv";
        String dbPath = "jdbc:sqlite:db/UMS.db";

        try (Connection conn = DriverManager.getConnection(dbPath)) {
            String createTable = "CREATE TABLE IF NOT EXISTS KISCOURSE (" +
                    "PUBUKPRN TEXT, " +
                    "UKPRN TEXT, " +
                    "ASSURL TEXT, " +
                    "CRSECSTURL TEXT, " +
                    "CRSEURL TEXT, " +
                    "EMPLOYURL TEXT, " +
                    "FOUNDATION TEXT, " +
                    "HONOURS TEXT, " +
                    "KISCOURSEID TEXT, " +
                    "KISMODE TEXT, " +
                    "NUMSTAGE TEXT, " +
                    "SANDWICH TEXT, " +
                    "SUPPORTURL, " +
                    "TITLE, " +
                    "UCASPROGID, " +
                    "UKPRNAPPLY, " +
                    "YEARABROAD, " +
                    "KISAIMCODE, " +
                    "KISLEVEL TEXT)";
 
            conn.createStatement().execute(createTable);

            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            String line;
            boolean isFirstLine = true;

            String insertSQL = "INSERT INTO KISCOURSE (PUBUKPRN, UKPRN, ASSURL, CRSECSTURL, CRSEURL,EMPLOYURL,FOUNDATION,HONOURS,KISCOURSEID,KISMODE,NUMSTAGE,SANDWICH,SUPPORTURL,TITLE,UCASPROGID,UKPRNAPPLY,YEARABROAD,KISAIMCODE,KISLEVEL) VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertSQL);

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",", -1); // To keep empty values

                if (values.length >= 19) {
                    for (int i = 0; i < 19; i++) {
                        ps.setString(i + 1, values[i].trim());
                    }
                    ps.executeUpdate();
                }
            }

            br.close();
            ps.close();
            System.out.println("CSV imported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
