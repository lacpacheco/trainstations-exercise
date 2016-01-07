import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;

import com.heroku.sdk.jdbc.DatabaseUrl;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> {
      RelativisticModel.select();
      Amount<Mass> m = Amount.valueOf("12 GeV").to(KILOGRAM);
      return "E=mc^2: 12 GeV = " + m.toString();
    });

    get("/stations", (request, response) -> {
      //String[] stations = {"A","B","C","D","E"};
      //A to B 3
      //B to A 3
      //A to D 6
      //B to C 7
      //C to D 8
      //D to E 9
      //E to D 9
      //D to C 9
      //D to B 5
      //C to E 3
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("message", Dijkstra.test());

      return new ModelAndView(attributes, "pathFinder.ftl");
    }, new FreeMarkerEngine());

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());

  }



}

class Vertex implements Comparable<Vertex>
{
  public final String name;
  public Edge[] adjacencies;
  public double minDistance = Double.POSITIVE_INFINITY;
  public Vertex previous;
  public Vertex(String argName) { name = argName; }
  public String toString() { return name; }
  public int compareTo(Vertex other)
  {
    return Double.compare(minDistance, other.minDistance);
  }

}

class Edge
{
  public final Vertex target;
  public final double weight;
  public Edge(Vertex argTarget, double argWeight)
  { target = argTarget; weight = argWeight; }
}

class Dijkstra
{
  public static void computePaths(Vertex source)
  {
    source.minDistance = 0.;
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
      Vertex u = vertexQueue.poll();

      // Visit each edge exiting u
      for (Edge e : u.adjacencies)
      {
        Vertex v = e.target;
        double weight = e.weight;
        double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
          vertexQueue.remove(v);

          v.minDistance = distanceThroughU ;
          v.previous = u;
          vertexQueue.add(v);
        }
      }
    }
  }

  public static List<Vertex> getShortestPathTo(Vertex target)
  {
    List<Vertex> path = new ArrayList<Vertex>();
    for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
      path.add(vertex);

    Collections.reverse(path);
    return path;
  }

  public static String test()
  {
    // mark all the vertices
    Vertex A = new Vertex("A");
    Vertex B = new Vertex("B");
    Vertex D = new Vertex("D");
    Vertex F = new Vertex("F");
    Vertex K = new Vertex("K");
    Vertex J = new Vertex("J");
    Vertex M = new Vertex("M");
    Vertex O = new Vertex("O");
    Vertex P = new Vertex("P");
    Vertex R = new Vertex("R");
    Vertex Z = new Vertex("Z");

    // set the edges and weight
    A.adjacencies = new Edge[]{ new Edge(M, 8) };
    B.adjacencies = new Edge[]{ new Edge(D, 11) };
    D.adjacencies = new Edge[]{ new Edge(B, 11) };
    F.adjacencies = new Edge[]{ new Edge(K, 23) };
    K.adjacencies = new Edge[]{ new Edge(O, 40) };
    J.adjacencies = new Edge[]{ new Edge(K, 25) };
    M.adjacencies = new Edge[]{ new Edge(R, 8) };
    O.adjacencies = new Edge[]{ new Edge(K, 40) };
    P.adjacencies = new Edge[]{ new Edge(Z, 18) };
    R.adjacencies = new Edge[]{ new Edge(P, 15) };
    Z.adjacencies = new Edge[]{ new Edge(P, 18) };


    computePaths(A); // run Dijkstra
    System.out.println("Distance to " + Z + ": " + Z.minDistance);
    List<Vertex> path = getShortestPathTo(Z);
    System.out.println("Path: " + path);

    return path.toString();
  }
}
