package de.canitzp.exoplanetdatabase;

import java.io.IOException;

/**
 * This class is only for testing purpose.
 *
 * @author canitzp
 */
public class ExoplanetDatabase {

    public static void main(String[] args){
        try {
            new Parser().download(EnumColumns.PLANET_NAME, EnumColumns.PLANET_NAME, EnumColumns.PLANET_TRANSIT_FLAG).read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
