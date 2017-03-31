package de.canitzp.exoplanetdatabase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * This is the parser that downloads
 * a json file from the Caltech webside
 * for all the exoplanets and their attributes.
 *
 * @author canitzp
 */
public class Parser {

    public File json;

    /**
     * This actually is the download method, where you can give the attribute types
     * you want to have in your list.
     * @param order This is the order type of the json table, normally it isn't important for your.
     * @param columns These are the different attributes you want to have for your exoplanet objects.
     * @return This parser object.
     * @throws IOException If anything goes wrong with the file writing or the URL parsing
     */
    public Parser download(EnumColumns order, EnumColumns... columns) throws IOException {
        StringBuilder builder = new StringBuilder("http://exoplanetarchive.ipac.caltech.edu/cgi-bin/nstedAPI/nph-nstedAPI?table=exoplanets&select=");
        for(EnumColumns column : columns){
            builder.append(column.apiString).append(",");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("&order=").append(order.apiString);
        builder.append("&format=json");
        Path temp = Files.createTempFile("exoplanetdatabase", "tmp");
        URL url = new URL(builder.toString());
        Files.copy(url.openStream(), temp, StandardCopyOption.REPLACE_EXISTING);
        this.json = temp.toFile();
        return this;
    }

    /**
     * This method can return a List of exoplanets with the attributes you gave to the download method.
     * @return A list of exoplanets
     * @throws IOException If something goes wrong with the file reading
     */
    public List<Exoplanet> read() throws IOException {
        List<Exoplanet> planets = new ArrayList<>();
        for(Object object : Files.lines(this.json.toPath()).toArray()){
            String line = (String) object;
            if(!line.contains("[") && !line.contains("]")){
                String[] split = line.replace("{", "").replace("}", "").split(",");
                Map<EnumColumns, String> attributes = new HashMap<>();
                for(String s : split){
                    String[] att = s.split(":");
                    String id = att[0].replace("\"", "");
                    String value = att[1].replace("\"", "");
                    EnumColumns column = EnumColumns.getByKey(id);
                    attributes.put(column, value);
                }
                Exoplanet planet = new Exoplanet();
                for(Map.Entry<EnumColumns, String> entry : attributes.entrySet()){
                    planet.setAttribute(entry.getKey(), entry.getValue());
                }
                planets.add(planet);
            }
        }
        this.json.delete();
        return planets;
    }

}
