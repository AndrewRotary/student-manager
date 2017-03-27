Simple Project
technologies: Intellij Idea, Jetty, Posthres
  Steps:
    1. Create new project, add dependencies for postgres, servlet, javax.el, jstl, fileupload, jetty runner.
    2. Create Domains with varibales similar to tabels in my db. Generate it's setters and getters.
    3. Create class Settings that contains Connection connection and has the settings for url: jdbc.., user, psw.
    4. Create Dao classes for entitys that has the CRUD methods, 
       ex: method CreateStudent has the Connection connection intialization from Settings class.
       Declare the PreparedStatment preparedStatment = null;
       preparedStatment = Settings.getConnection().prepareStatement("sql...");
       ...
       preparedStament.executeUpdate();
       ...
       ResultSet rs (is used to get the query Array) = preparedStatment.executeQuery();
