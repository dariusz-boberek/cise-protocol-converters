package eu.cise.converters.utils;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static eu.cise.converters.utils.DelimiterType.KEEP;
import static java.lang.Long.MAX_VALUE;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliterator;

/**
 * Utility class for converting an InputStream to a Stream of strings,
 * allowing for more flexible and functional-style operations on the data.
 *
 * <p>It supports custom delimiter settings to define how the input stream should be split
 * into individual strings. The handling of delimiters can be configured to either keep or strip
 * them from the resulting strings.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * InputStream someStream = ...; // Obtain an InputStream
 * InputStreamToStream converter = new InputStreamToStream(someStream, "\n", DelimiterType.STRIP);
 * Stream<String> neededStream = converter.stream();
 * // Process the stream as needed
 * }</pre>
 *
 *
 * @see DelimiterType for the types of delimiter handling supported.
 */
public class InputStreamToStream {

    private final String delimiter;
    private final Scanner scanner;
    private final Spliterator<String> split;
    private final AtomicLong count = new AtomicLong();

    public InputStreamToStream(InputStream is, String delimiter, DelimiterType type) {

        this.scanner = new Scanner(is, "UTF-8").useDelimiter(delimiter);

        this.split = spliterator(scanner, MAX_VALUE, ORDERED | NONNULL);

        this.delimiter = type.equals(KEEP) ? delimiter : "";
    }

    public Stream<String> stream() {
        return StreamSupport.stream(split, false)
            .onClose(scanner::close)
            .peek(m -> count.getAndIncrement())
//            .filter(m -> !m.isEmpty())
            .map(m -> delimiter + m);
    }
}
