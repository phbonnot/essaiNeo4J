import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import static org.neo4j.driver.Values.parameters;

public class Main {


    public static void main(String... args) {
        Connexion connexion=new Connexion("neo4j://docker.iut.univ-paris8.fr:7687", "pbonnot",Parametres.pwd);
        connexion.open();
        connexion.close();
    }


}
