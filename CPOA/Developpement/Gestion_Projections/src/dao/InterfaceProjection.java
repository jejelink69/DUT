
package dao;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import metier.Projection;

/**
 *
 * @author p1506068
 */
public interface InterfaceProjection {
    public void setDataSource(DataSource ds);
    public void setConnection(Connection c);
    public List<Projection>getProjections();
    
}
