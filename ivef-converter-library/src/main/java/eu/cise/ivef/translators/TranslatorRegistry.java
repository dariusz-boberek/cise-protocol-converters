package eu.cise.ivef.translators;

import eu.cise.converters.exceptions.ConversionException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * TranslatorRegistry is a class responsible for managing and providing access to various data translators.
 * It initializes and stores instances of CiseDataTranslator subclasses, making them accessible for data conversion tasks.
 *
 * <p>The class utilizes a static initialization block to automatically discover and instantiate all available translators
 * based on the CiseDataTranslator class. This automatic registration is performed at class loading time.
 */
public class TranslatorRegistry {
    private static final String TRANSLATORS_IMPLEMENTATION_ROOT = TranslatorRegistry.class.getPackage().getName();
    private static final Logger logger = LoggerFactory.getLogger(TranslatorRegistry.class);
    private static Map<String, CiseDataTranslator> translatorsMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections(TRANSLATORS_IMPLEMENTATION_ROOT);
        for (Class<? extends CiseDataTranslator> clazz : reflections.getSubTypesOf(CiseDataTranslator.class)) {
            try {
                CiseDataTranslator instance = clazz.getDeclaredConstructor().newInstance();
                translatorsMap.put(instance.getName(), instance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                logger.error("Unable to get subclasses of CiseTranslator ", e);
            }
        }
    }

    /**
     * Retrieves a registered translator based on its class simple name.
     *
     * @param classSimpleName The simple name of the translator class to retrieve.
     * @return The instance of CiseDataTranslator associated with the given class simple name.
     * @throws ConversionException if no suitable translator is found for the specified class name.
     */
    public static CiseDataTranslator getTranslator(String classSimpleName) throws ConversionException {
        CiseDataTranslator result = translatorsMap.get(classSimpleName);
        if (result == null) {
            throw new ConversionException("Unable to find suitable translator for class : " + classSimpleName);
        }
        return result;
    }

}
