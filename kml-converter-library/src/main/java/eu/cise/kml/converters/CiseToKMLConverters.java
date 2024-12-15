package eu.cise.kml.converters;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import eu.cise.converters.exceptions.ConversionException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class works using {@link Reflections} to get all the subtypes of {@link CiseConverter} interface. Its main method {@link #getTranslator(String)} allows
 * the user to get the corresponding converter providing the Simple Class Name of the CisePayloadAny type object to translate. In case an appropriate Coverter is not found
 * a {@link ConversionException} is thrown
 */
public class CiseToKMLConverters {
    private static Map<String, CiseConverter<?>> translatorMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger("cise-kml-translators");

    static {
        Reflections reflections = new Reflections("eu.cise.kml.converters");
        for (Class<? extends CiseConverter> clazz : reflections.getSubTypesOf(CiseConverter.class)){
            CiseConverter currentClass = null;
            try {
                currentClass = clazz.getConstructor().newInstance();
                translatorMap.put(currentClass.getName(),currentClass);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error("Unable to get subclasses of CiseTranslator ", e);
            }

        }

    }

    /**
     * Main method of {@link CiseToKMLConverters}  that allows
     * the user to get the corresponding converter providing the Simple Class Name of the CisePayloadAny type object to translate. In case an appropriate Coverter is not found
     *  a {@link ConversionException} is thrown
     * @param classSimpleName
     * @return
     * @throws ConversionException
     */
    public static CiseConverter getTranslator(String classSimpleName) throws ConversionException {
        CiseConverter result = translatorMap.get(classSimpleName);
        if (result == null){
            throw new ConversionException("Unable to find suitable translator for class : " + classSimpleName);
        }
        return  result;
    }
}
