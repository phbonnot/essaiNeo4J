import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class Connexion implements AutoCloseable{

    private final Driver driver;

    public Connexion(String uri, String user, String password){
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void open(){
        {
            // Sessions are lightweight and disposable connection wrappers.
            try (Session session = driver.session())
            {
                // Wrapping a Cypher Query in a Managed Transaction provides atomicity
                // and makes handling errors much easier.
                // Use `session.writeTransaction` for writes and `session.readTransaction` for reading data.
                // These methods are also able to handle connection problems and transient errors using an automatic retry mechanism.
                //Graphe graphe=new Graphe(5);
                for(int i=0;i<5;i++){
                    for(int j=0;j<5;j++){
                        int finalI = i;
                        int finalJ = j;
                        session.executeWrite(tx -> tx.run("MERGE (a:Tuile {posX:"+ finalI +",posY:"+ finalJ +"}"));
                    }
                }

            }
        }
    }
    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }
}
