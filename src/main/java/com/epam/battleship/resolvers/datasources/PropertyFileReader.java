package com.epam.battleship.resolvers.datasources;

import com.epam.battleship.resolvers.Resolver;
import com.epam.battleship.resolvers.loaders.PropertyFileLoader;

import java.io.File;
import java.util.Properties;

public class PropertyFileReader implements Resolver {

    private Properties         properties;
    private PropertyFileLoader loader;

    public PropertyFileReader(String configFile) {
        loader = new PropertyFileLoader(new File(configFile));
        properties = loader.getProperties();
    }

    @Override
    public String get(String name) {
        return properties.getProperty(name).toLowerCase();
    }

}