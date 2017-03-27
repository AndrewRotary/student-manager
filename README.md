<h1>Simple Project</h1><br>
<h3>Technologies: Intellij Idea, Jetty, Postgres</h3> <br>
  <h4>Steps:</h4><br>
  <ul>
  <li>
     Create new project, add dependencies for postgres, servlet, javax.el, jstl, fileupload, jetty runner.
  </li>
  <li>
     Create Domains with varibales similar to tables in my db. Generate it's setters and getters.
  </li>
  <li>
     Create class Settings that contains Connection connection and has the settings for url: jdbc.., user, psw.
  </li>
  <li>
    Create Dao classes for entitys that has the CRUD methods, 
       ex: method CreateStudent has the Connection connection intialization from Settings class.
       Declare the PreparedStatment preparedStatment = null;
       preparedStatment = Settings.getConnection().prepareStatement("sql...");
       ...
       preparedStament.executeUpdate();
       ...
       ResultSet rs (is used to get the query Array) = preparedStatment.executeQuery();
   </li>
   <li>
   Create Jsp pages
   </li>
   <li>
   Create Servlet, initialize the Dao classes,
   declare the doGet & and doPost methods, where: <br>
   <b>doGet: </b> Initialize List with data using Dao object, and request.setAttribute(list..)... <br>
   <b>doPost: </b> request.getParameterMap().get(...name...)...
   </li>
</ul>
