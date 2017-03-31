package de.canitzp.exoplanetdatabase;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the Exoplanet Type.
 * You can get the attributes of the planet,
 * but you only get a valid value
 * if your Parser has the correct arguments.
 * @see Parser
 *
 * @author canitzp
 */
public class Exoplanet {

    private Map<EnumColumns, String> attributes = new HashMap<>();

    public Exoplanet setAttribute(@NotNull EnumColumns column, @NotNull String value){
        this.attributes.put(column, value);
        return this;
    }

    @Nullable
    public String get(EnumColumns column){
        return this.attributes.get(column);
    }

    @Override
    public String toString() {
        return "Exoplanet:" + this.attributes.toString();
    }

    /**
     * Default getter:
     */
    @Nullable
    public String getPlanetName(){
        return this.get(EnumColumns.PLANET_NAME);
    }

}
