package eu.cise.converters.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static eu.cise.converters.utils.DelimiterType.KEEP;
import static eu.cise.converters.utils.DelimiterType.STRIP;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static co.unruly.matchers.StreamMatchers.contains;


class InputStreamToStreamTest {


    private String aisOne = "!AIVDM,1,1,,B,13P88o@02=OqlrHM6FATwCvf08=E,0*73";
    private String aisTwo = "!AIVDM,1,1,,A,13P88o@uB=Oqm2<M6EkTvkvp0@@b,0*44";
    private String aisThree = "!AIVDM,1,1,,A,13P88o@uB=OqmFPM6DSTukwB0<1G,0*7D";

    @Test
    public void it_translate_an_InputStream_to_a_Stream_of_Strings_CR_LF() {
        ByteArrayInputStream is = getAisInputStream(getAisMessages("\r\n"));
        InputStreamToStream toStream = new InputStreamToStream(is, "\r\n", STRIP);

        assertThat(toStream.stream(), contains(aisOne, aisTwo, aisThree));
    }

    @Test
    public void it_translate_an_InputStream_to_a_Stream_of_Strings_CR() {
        ByteArrayInputStream is = getAisInputStream(getAisMessages("\n"));
        InputStreamToStream toStream = new InputStreamToStream(is, "\n", STRIP);

        assertThat(toStream.stream(), contains(aisOne, aisTwo, aisThree));
    }

    @Test
    public void it_translate_an_InputStream_to_a_Stream_of_Strings_NOTHING() {
        ByteArrayInputStream is = getAisInputStream(getAisMessages("\n"));
        InputStreamToStream toStream = new InputStreamToStream(is, "\n", STRIP);

        assertThat(toStream.stream(), contains(aisOne, aisTwo, aisThree));
    }
    @Test
    public void it_translate_an_InputStream_to_a_Stream_of_Strings_EXCLAMATION() {
        ByteArrayInputStream is = getAisInputStream(getAisMessages(""));

        InputStreamToStream toStream = new InputStreamToStream(is, "!", KEEP);

        assertThat(toStream.stream(), contains(aisOne, aisTwo, aisThree));
    }

    private String getAisMessages(String separator) {
        return aisOne + separator + aisTwo + separator + aisThree + separator;
    }

    private ByteArrayInputStream getAisInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes(UTF_8));
    }
}