
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbService {

    private String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu129";

    private String username = "cs3220stu129";

    private String password = "HePo6ycph3Rr";

    private Connection connection;

    public DbService()
    {
        try
        {
            connection = DriverManager.getConnection( url, username, password );
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        if( connection != null )
        {
            try
            {
                connection.close();
            }
            catch( SQLException e )
            {
                e.printStackTrace();
            }
        }
    }

    public List<Users> getUsers()
    {
        List<Users> entries = new ArrayList<Users>();

        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from users" );
            while( rs.next() )
            {
            	Users entry = new Users();
            	entry.setId( rs.getInt( "id" ) );
                entry.setName( rs.getString( "name" ) );
                entries.add( entry );
            }
            stmt.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return entries;
    }
    
    public List<BloodPressure> getBloodpressure()
    {
        List<BloodPressure> entries = new ArrayList<BloodPressure>();

        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from bloodpressure" );
            while( rs.next() )
            {
            	BloodPressure entry = new BloodPressure();
            	entry.setId( rs.getInt( "id" ) );
            	entry.setUserid( rs.getInt( "userid" ) );
            	entry.setSystolic( rs.getInt( "systolic" ) );
            	entry.setDiastolic( rs.getInt( "diastolic" ) );
            	entry.setTime( rs.getDate( "time" ) );
                entries.add( entry );
            }
            stmt.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return entries;
    }

    public int addBloodPressure(int userid, int systolic, int diastolic  )
    //insert into bloodpressure(systolic,diastolic,time) value (10,42,now());

    {
        int id = 0;
        try
        {
            String sql = "insert into bloodpressure(userid,systolic,diastolic,time) value (?,?,?,now())";
            PreparedStatement pstmt = connection.prepareStatement( sql,
                Statement.RETURN_GENERATED_KEYS );
            pstmt.setInt( 1, userid );
            pstmt.setInt( 2, systolic );
            pstmt.setInt( 3, diastolic );
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if( rs.next() ) id = rs.getInt( 1 );
            pstmt.close();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        return id;
    }
//
//    public void received(int id)
//    {
//        try
//        {
//            String sql = "update patient set seconddose = ? where id = ?";
//            PreparedStatement pstmt = connection.prepareStatement( sql );
//            Date myDate = new Date();
//            System.out.println(myDate);
//            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String dmy = dmyFormat.format(myDate);
//            pstmt.setString( 1, dmy );
//            pstmt.setInt( 2, id );
//            pstmt.executeUpdate();
//            pstmt.close();
//        }
//        catch( SQLException e )
//        {
//            e.printStackTrace();
//        }
//    }
//    
//    public void useOne(int id, int totalleft)
//    {
//        try
//        {
//            String sql = "update vaccine set totalleft = ? where id = ?";
//            PreparedStatement pstmt = connection.prepareStatement( sql );
//            pstmt.setInt( 1, totalleft-1 );
//            pstmt.setInt( 2, id );
//            pstmt.executeUpdate();
//            pstmt.close();
//        }
//        catch( SQLException e )
//        {
//            e.printStackTrace();
//        }
//    }
}
