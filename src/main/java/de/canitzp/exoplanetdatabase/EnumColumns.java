package de.canitzp.exoplanetdatabase;

/**
 * This is a list of all
 * different possible attributes
 * a exoplanet can get from
 * the Caltech/NASA Exoplanet database
 *
 * @author canitzp
 */
public enum EnumColumns {

    PLANET_NAME("pl_name", "Planet name most commonly used in the literature."),
    PLANET_TRANSIT_FLAG("pl_tranflag", "Flag indicating if the planet transits its host star (1=yes, 0=no)"),
    PLANET_RV_FLAG("pl_rvflag", "Flag indicating if the planet host star exhibits radial velocity variations due to the planet (1=yes, 0=no)"),
    PLANET_IMAGING_FLAG("pl_imgflag", "Flag indicating if the planet has been observed via imaging techniques (1=yes, 0=no)"),
    PLANET_ASTROMETRY_FLAG("pl_astflag", "Flag indicating if the planet host star exhibits astrometrical variations due to the planet (1=yes, 0=no)"),
    PLANET_ORBITAL_MODULATION_FLAG("pl_omflag", "Flag indicating whether the planet exhibits orbital modulations on the phase curve (1=yes, 0=no)");

    public String apiString, desc;

    EnumColumns(String apiString) {
        this.apiString = apiString;
    }

    EnumColumns(String apiString, String desc){
        this(apiString);
        this.desc = desc;
    }

    public static EnumColumns getByKey(String key){
        for(EnumColumns column : EnumColumns.values()){
            if(key.equals(column.apiString)){
                return column;
            }
        }
        return null;
    }
}
